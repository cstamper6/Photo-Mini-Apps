package miniapps;

// Subclass of Picture that is used as a stepping stone to provide Mutable and Immutable
// pictures pixel arrays that can be extracted, painted, etc.
public abstract class PixelArrayPicture extends PictureImpl implements Picture {

	protected Pixel[][] parray;

	public PixelArrayPicture(Pixel[][] parray, String caption) {

		super(caption);
		this.parray = copyPixelArray(parray);
	}

	public int getWidth() {
		return parray.length;
	}

	public int getHeight() {
		return parray[0].length;
	}

	public Pixel getPixel(int x, int y) {

		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}
		return parray[x][y];
	}

	// Paints a single pixel at the given location by changing its RGB values
	// Returns the original picture with a newly painted pixel
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

		parray[x][y] = parray[x][y].blend(p, factor);

		return this;
	}

	// Checks if given array is valid
	// Copies each pixel into a new 2d array
	private static Pixel[][] copyPixelArray(Pixel[][] pixel_array) {

		if (pixel_array == null) {
			throw new IllegalArgumentException("pixel_array is null");
		}
		int width = pixel_array.length;

		if (width == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");
		}

		for (int x = 0; x < width; x++) {
			if (pixel_array[x] == null) {
				throw new IllegalArgumentException("pixel_array includes null columns");
			}
		}

		int height = pixel_array[0].length;
		if (height == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");
		}

		for (int x = 0; x < width; x++) {
			if (pixel_array[x].length != height) {
				throw new IllegalArgumentException("Columns in picture are not all the same height.");
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (pixel_array[x][y] == null) {
					throw new IllegalArgumentException("pixel_array includes null pixels");
				}
			}
		}

		Pixel[][] copy = new Pixel[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				copy[x][y] = pixel_array[x][y];
			}
		}

		return copy;
	}

}
