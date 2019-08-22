package miniapps;

// Subclass of a Picture that contains a mutable pixel array
public class MutablePixelArrayPicture extends PixelArrayPicture implements Picture {

	public MutablePixelArrayPicture(Pixel[][] parray, String caption) {
		super(parray, caption);
	}

}
