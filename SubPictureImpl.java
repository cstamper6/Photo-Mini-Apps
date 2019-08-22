package miniapps;

// Creates a SubPicture based off of a given picture
// Accounts for SubPicture being created from a mutable or immutable source picture
public class SubPictureImpl extends PictureImpl implements SubPicture {

	private Picture source;
	private int xoffset;
	private int yoffset;
	private int width;
	private int height;

	public SubPictureImpl(Picture source, int xoffset, int yoffset, int width, int height) {

		super(source.getCaption());

		if ((xoffset < 0) || (xoffset >= source.getWidth()) || (yoffset < 0) || (yoffset >= source.getHeight())
				|| (width < 1) || (xoffset + width > source.getWidth()) || (height < 1)
				|| (yoffset + height > source.getHeight())) {
			throw new IllegalArgumentException("Illegal subpicture geometry");
		}
		this.source = source;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		this.width = width;
		this.height = height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	// Returns specific pixel of SubPicture
	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}
		return source.getPixel(x + xoffset, y + yoffset);
	}

	// Paints a single pixel by changing its RGB values
	// Returns this picture if mutable, or a new picture if immutable
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}

		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if (factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("factor is out of bounds");
		}

		Picture p1 = source.paint(x + xoffset, y + yoffset, p, factor);
		if (p1 == source) {
			return this;
		} else {
			SubPicture newSubPic = p1.extract(getXOffset(), getYOffset(), getWidth(), getHeight());
			newSubPic.setCaption(getCaption());
			return newSubPic;
		}
	}

	@Override
	public int getXOffset() {
		return xoffset;
	}

	@Override
	public int getYOffset() {
		return yoffset;
	}

	@Override
	public Picture getSource() {
		return source;
	}

}
