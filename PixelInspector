package miniapps;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class PixelInspector {

	public static JInternalFrame main(String[] args) throws IOException {

		Picture p = PictureReader.readFromFile();
		p.setCaption("KMP in Namibia");
		InspectorPictureViewWidget inspector_widget = new InspectorPictureViewWidget(p);

		JInternalFrame main_frame = new JInternalFrame();
		main_frame.setTitle("PixelInspector");
		//main_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(inspector_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
		
		return main_frame;
	}
}
