package miniapps;

import java.util.Iterator;

// Foundational parent class used to provide Picture subclasses with method implementation
public abstract class PictureImpl implements Picture {

	protected String caption;

	public PictureImpl(String caption) {

		if (caption == null) {
			throw new IllegalArgumentException("Invalid caption");
		}
		this.caption = caption;
	}

	// Getter and setter for caption of pictures

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public void setCaption(String caption) {

		if (caption == null) {
			throw new IllegalArgumentException("Invalid caption");
		}
		this.caption = caption;
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		return paint(x, y, p, 1.0);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return paint(ax, ay, bx, by, p, 1.0);
	}

	// Paints a rectangle of pixels
	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		int min_x = (ax < bx) ? ax : bx;
		int max_x = (ax > bx) ? ax : bx;
		int min_y = (ay < by) ? ay : by;
		int max_y = (ay > by) ? ay : by;

		min_x = (min_x < 0) ? 0 : min_x;
		min_y = (min_y < 0) ? 0 : min_y;
		max_x = (max_x > getWidth() - 1) ? getWidth() - 1 : max_x;
		max_y = (max_y > getHeight() - 1) ? getHeight() - 1 : max_y;

		Picture result = this;
		for (int x = min_x; x <= max_x; x++) {
			for (int y = min_y; y <= max_y; y++) {
				result = result.paint(x, y, p, factor);
			}
		}
		return result;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		return paint(cx, cy, radius, p, 1.0);
	}

	// Paints a circle of pixels
	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		if (radius < 0) {
			throw new IllegalArgumentException("radius is negative");
		}

		int min_x = (int) (cx - (radius + 1));
		int max_x = (int) (cx + (radius + 1));
		int min_y = (int) (cy - (radius + 1));
		int max_y = (int) (cy + (radius + 1));

		min_x = (min_x < 0) ? 0 : min_x;
		min_y = (min_y < 0) ? 0 : min_y;
		max_x = (max_x > getWidth() - 1) ? getWidth() - 1 : max_x;
		max_y = (max_y > getHeight() - 1) ? getHeight() - 1 : max_y;

		Picture result = this;
		for (int x = min_x; x <= max_x; x++) {
			for (int y = min_y; y <= max_y; y++) {
				if (Math.sqrt((cx - x) * (cx - x) + (cy - y) * (cy - y)) <= radius) {
					result = result.paint(x, y, p, factor);
				}
			}
		}
		return result;
	}

	@Override
	public Picture paint(int x, int y, Picture p) {
		return paint(x, y, p, 1.0);
	}

	// Paints a group of pixels with a given picture
	@Override
	public Picture paint(int x, int y, Picture p, double factor) {

		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of range");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		if (p == null) {
			throw new IllegalArgumentException("Picture p is null");
		}

		Picture result = this;
		for (int px = 0; px < p.getWidth() && px + x < getWidth(); px++) {
			for (int py = 0; py < p.getHeight() && py + y < getHeight(); py++) {
				result = result.paint(px + x, py + y, p.getPixel(px, py), factor);
			}
		}
		return result;
	}

	@Override
	public SubPicture extract(int x, int y, int width, int height) {
		return new SubPictureImpl(this, x, y, width, height);
	}

	/*
	 * Dormant until needed
	 * 
	 * // Delegate the work to iterator subclasses
	 * 
	 * @Override public Iterator<Pixel> sample(int init_x, int init_y, int dx, int
	 * dy) { return new SampleIterator(this, init_x, init_y, dx, dy); }
	 * 
	 * @Override public Iterator<SubPicture> window(int window_width, int
	 * window_height) { return new WindowIterator(this, window_width,
	 * window_height); }
	 * 
	 * @Override public Iterator<SubPicture> tile(int tile_width, int tile_height) {
	 * return new TileIterator(this, tile_width, tile_height); }
	 * 
	 * @Override public Iterator<Pixel> zigzag() { return new ZigZagIterator(this);
	 * }
	 * 
	 */

}
