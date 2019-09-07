package miniapps;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import java.io.IOException;

public class AppGUI {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGUI window = new AppGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AppGUI() throws IOException {
		initialize();
	}

	private void initialize() throws IOException {
		frame = new JFrame();

		Picture picture = PictureReader.readFromFile();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JInternalFrame internalFrame = PictureViewer.main(picture);
		JInternalFrame internalFrame_1 = PixelInspector.main(picture);
		JInternalFrame internalFrame_2 = ImageAdjuster.main(picture);
		JInternalFrame internalFrame_3 = FramePuzzle.main(picture);

		frame.setBounds(10, 10, internalFrame_2.getWidth() + 150, internalFrame_2.getHeight() + 80);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Picture Viewer", panel);
		tabbedPane.add(new JScrollPane(panel), "Picture Viewer");
		panel.add(internalFrame);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Pixel Inspector", panel_1);
		tabbedPane.add(new JScrollPane(panel_1), "Pixel Inspector");
		panel_1.add(internalFrame_1);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Image Adjuster", panel_2);
		tabbedPane.add(new JScrollPane(panel_2), "Image Adjuster");
		panel_2.add(internalFrame_2);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Frame Puzzle", panel_3);
		tabbedPane.add(new JScrollPane(panel_3), "Frame Puzzle");
		panel_3.add(internalFrame_3);

	}

}
