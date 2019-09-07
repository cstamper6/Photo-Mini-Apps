package miniapps;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	public static File main() {

		final JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("Pictures"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "tif", "tiff", "bmp",
				"png");
		fc.setFileFilter(filter);
		int val = fc.showOpenDialog(null);
		if (val == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		} else {
			System.out.print("Cancelled");
		}
		return fc.getSelectedFile();
	}

}
