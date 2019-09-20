package miniapps;

import java.util.ArrayList;
import java.util.List;

public class ObservablePictureImpl implements ObservablePicture {

	private Picture pic;
	// encapsulates list of observers
	private List<RegisteredROIObserver> observers;
	// encapsulated boolean value to suspend/resume notifications
	private boolean suspended;
	private Region OCregion;

	public ObservablePictureImpl(Picture p) {

		if (p == null) {
			throw new IllegalArgumentException("Picture cannot be null");
		}
		this.pic = p;
		observers = new ArrayList<RegisteredROIObserver>();
		this.suspended = false;
		this.OCregion = null;
	}

	@Override
	public int getWidth() {
		return pic.getWidth();
	}

	@Override
	public int getHeight() {
		return pic.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return pic.getPixel(x, y);
	}

	// In all paint methods -->
	// Notifies observers of change in region of interest by
	// creating a region and running a helper method on the new region
	// Helper method checks if the new region intersects with the observer's
	// region of interest and notifies it if so
	@Override
	public Picture paint(int x, int y, Pixel p) {
		return this.paint(x, y, p, 1.0);
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {

		this.pic = pic.paint(x, y, p, factor);
		Region region = new RegionImpl(x, y, x, y);
		OCregion = region.union(OCregion);
		this.notifyObservers(OCregion);
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return this.paint(ax, ay, bx, by, p, 1.0);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		Region region;
		this.pic = pic.paint(ax, ay, bx, by, p, factor);
		if (ax < bx && ay < by) {
			region = new RegionImpl(ax, ay, bx, by);
		} else if (bx < ax && by < ay) {
			region = new RegionImpl(bx, by, ax, ay);
		} else if (ax < bx && by < ay) {
			region = new RegionImpl(ax, by, bx, ay);
		} else {
			region = new RegionImpl(bx, ay, ax, by);
		}
		OCregion = region.union(OCregion);
		this.notifyObservers(OCregion);
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		return this.paint(cx, cy, radius, p, 1.0);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {

		this.pic = pic.paint(cx, cy, radius, p, factor);
		Region region = new RegionImpl((int) (cx - radius), (int) (cy - radius), (int) (cx + radius),
				(int) (cy + radius));
		OCregion = region.union(OCregion);
		this.notifyObservers(OCregion);
		return this;
	}

	@Override
	public Picture paint(int x, int y, Picture p) {
		return this.paint(x, y, p, 1.0);
	}

	@Override
	public Picture paint(int x, int y, Picture p, double factor) {

		this.pic = pic.paint(x, y, p, factor);
		Region region = new RegionImpl(x, y, x + p.getWidth() - 1, y + p.getHeight() - 1);
		OCregion = region.union(OCregion);
		this.notifyObservers(OCregion);
		return this;
	}

	@Override
	public String getCaption() {
		return pic.getCaption();
	}

	@Override
	public void setCaption(String caption) {
		pic.setCaption(caption);
	}

	@Override
	public SubPicture extract(int x, int y, int width, int height) {
		return pic.extract(x, y, width, height);
	}

	// adds a RegisteredROIObserver to the encapsulated array list
	@Override
	public void registerROIObserver(ROIObserver observer, Region r) {

		if (observer == null || r == null) {
			throw new IllegalArgumentException("Observer or region cannot be null");
		}
		observers.add(new RegisteredROIObserverImpl(observer, r));
	}

	// Checks if the given region intersects with a region of interest
	// of an observer
	// If so, removes the observer from the encapsulated array list
	@Override
	public void unregisterROIObservers(Region r) {

		List<RegisteredROIObserver> foundObservers = new ArrayList<RegisteredROIObserver>();

		for (RegisteredROIObserver variable : observers) {
			try {
				r.intersect(variable.getRegion());
				foundObservers.add(variable);
			} catch (NoIntersectionException e) {
			}
		}
		observers.removeAll(foundObservers);
	}

	// Casts the encapsulated array list of observers to an array
	// Loops through the array checking if the given observer
	// equals an observer in the array
	// If so, removes that observer from the encapsulated array list
	@Override
	public void unregisterROIObserver(ROIObserver observer) {

		List<RegisteredROIObserver> foundObservers = new ArrayList<RegisteredROIObserver>();
		for (RegisteredROIObserver variable : observers) {
			if (variable.getObserver() == observer) {
				foundObservers.add(variable);
			}
		}
		observers.removeAll(foundObservers);
	}

	// Loops through the list of observers and checks if they intersect
	// with the given region
	// if so, stores that observer on a list to be converted to an array
	// and returns that array
	@Override
	public ROIObserver[] findROIObservers(Region r) {

		List<RegisteredROIObserver> foundObservers = new ArrayList<RegisteredROIObserver>();
		for (RegisteredROIObserver variable : observers) {
			try {
				r.intersect(variable.getRegion());
				foundObservers.add(variable);
			} catch (NoIntersectionException e) {
			}
		}
		ROIObserver[] observerArray = new ROIObserver[foundObservers.size()];
		int i = 0;
		for (RegisteredROIObserver variable : foundObservers) {
			observerArray[i] = variable.getObserver();
			i++;
		}
		return observerArray;
	}

	@Override
	public void suspendObservable() {
		this.suspended = true;
	}

	@Override
	public void resumeObservable() {

		suspended = false;
		notifyObservers(OCregion);
	}

	// Helper method to notify observers if their regions have been painted
	public void notifyObservers(Region r) {

		if (suspended || OCregion == null) {
			return;
		}
		for (RegisteredROIObserver variable : observers) {
			try {
				r.intersect(variable.getRegion());
				variable.notify(this, OCregion);
			} catch (NoIntersectionException e) {
			}
		}
		OCregion = null;
	}

}
