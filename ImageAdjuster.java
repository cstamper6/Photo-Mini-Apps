package miniapps;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class ImageAdjuster {

	public static JInternalFrame main(String[] args) throws IOException {

		Picture p = PictureReader.readFromFile();
		p.setCaption("KMP in Namibia");
		AdjusterPictureViewWidget adjuster_widget = new AdjusterPictureViewWidget(p);

		JInternalFrame main_frame = new JInternalFrame();
		main_frame.setTitle("ImageAdjuster");

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(adjuster_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
		
		return main_frame;
	}
}
