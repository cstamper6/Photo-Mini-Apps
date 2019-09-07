package miniapps;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class PixelInspector {

	public static JInternalFrame main(Picture pic) throws IOException {

		Picture p = pic;
		InspectorPictureViewWidget inspector_widget = new InspectorPictureViewWidget(p);

		JInternalFrame main_frame = new JInternalFrame();

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(inspector_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		BasicInternalFrameUI bi = (BasicInternalFrameUI) main_frame.getUI();
		bi.setNorthPane(null);

		main_frame.pack();
		main_frame.setVisible(true);

		return main_frame;
	}
}
