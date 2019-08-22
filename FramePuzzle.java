package miniapps;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FramePuzzle {
	public static JInternalFrame main(String[] args) throws IOException {

		Picture p = PictureReader.readFromFile();
		p.setCaption("KMP in Namibia");
		PuzzlePictureViewWidget puzzle_widget = new PuzzlePictureViewWidget(p);

		JInternalFrame main_frame = new JInternalFrame();
		main_frame.setName("FramePuzzle");

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(puzzle_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
		
		return main_frame;
	}
}
