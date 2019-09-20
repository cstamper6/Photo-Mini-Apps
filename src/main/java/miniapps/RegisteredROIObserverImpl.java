package miniapps;

public class RegisteredROIObserverImpl implements RegisteredROIObserver {

	private ROIObserver observer;
	private Region region;

	public RegisteredROIObserverImpl(ROIObserver observer, Region region) {

		this.observer = observer;
		this.region = region;
	}

	public void notify(ObservablePicture picture, Region changed_region) {
		try {
			observer.notify(picture, region.intersect(changed_region));
		} catch (NoIntersectionException e) {
		}
	}

	public ROIObserver getObserver() {
		return observer;
	}

	public Region getRegion() {
		return region;
	}

}
