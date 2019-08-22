package miniapps;

import java.io.File;
import javax.swing.JFileChooser;

public class FileChooser {
	
	public static File main() {
		
		// accepts only images
		// throws error if null
		
		final JFileChooser fc = new JFileChooser();
		int val = fc.showOpenDialog(null);
		if (val == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		} else {
			System.out.print("Cancelled");
		}
		return fc.getSelectedFile();
	}

}
