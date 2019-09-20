package miniapps;

public class RegionImpl implements Region {

	private int left;
	private int top;
	private int right;
	private int bottom;

	public RegionImpl(int left, int top, int right, int bottom) {

		if (left > right || top > bottom) {
			throw new IllegalArgumentException(
					"Invalid size: Right must be greater than left and" + " bottom must be greater than top");
		}
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	@Override
	public int getTop() {
		return top;
	}

	@Override
	public int getBottom() {
		return bottom;
	}

	@Override
	public int getLeft() {
		return left;
	}

	@Override
	public int getRight() {
		return right;
	}

	// Checks if the regions intersect
	// Compares region values to set new region values of enclosed area
	// of compared regions
	@Override
	public Region intersect(Region other) throws NoIntersectionException {

		int newTop;
		int newBottom;
		int newLeft;
		int newRight;

		if (other == null || top > other.getBottom() || right < other.getLeft() || bottom < other.getTop()
				|| left > other.getRight()) {
			throw new NoIntersectionException();
		} else {
			// Series of if statement that set the boundaries of the new region
			if (top < other.getTop()) {
				newTop = other.getTop();
			} else {
				newTop = top;
			}
			if (bottom < other.getBottom()) {
				newBottom = bottom;
			} else {
				newBottom = other.getBottom();
			}
			if (left < other.getLeft()) {
				newLeft = other.getLeft();
			} else {
				newLeft = left;
			}
			if (right < other.getRight()) {
				newRight = right;
			} else {
				newRight = other.getRight();
			}
			return new RegionImpl(newLeft, newTop, newRight, newBottom);
		}
	}

	// Compares region values to create and returns the region encompassing
	// the compared regions
	// returns original region if no region is passed in
	@Override
	public Region union(Region other) {

		int newTop;
		int newBottom;
		int newLeft;
		int newRight;

		if (other == null) {
			return this;
		} else {
			// Series of if statement that set the boundaries of the new region
			if (top < other.getTop()) {
				newTop = top;
			} else {
				newTop = other.getTop();
			}
			if (bottom < other.getBottom()) {
				newBottom = other.getBottom();
			} else {
				newBottom = bottom;
			}
			if (left < other.getLeft()) {
				newLeft = left;
			} else {
				newLeft = other.getLeft();
			}
			if (right < other.getRight()) {
				newRight = other.getRight();
			} else {
				newRight = right;
			}
			return new RegionImpl(newLeft, newTop, newRight, newBottom);
		}
	}

}
