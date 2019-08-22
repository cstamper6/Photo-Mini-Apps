package miniapps;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import java.io.IOException;

public class appGUIDEV {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					appGUIDEV window = new appGUIDEV();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public appGUIDEV() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1006, 885);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JInternalFrame internalFrame = PictureViewer.main(null);	
		JInternalFrame internalFrame_1 = PixelInspector.main(null);
		JInternalFrame internalFrame_2 = ImageAdjuster.main(null);
		JInternalFrame internalFrame_3 = FramePuzzle.main(null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Picture Viewer", panel);
		panel.add(internalFrame);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Pixel Inspector", panel_1);
		panel_1.add(internalFrame_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Image Adjuster", panel_2);
		panel_2.add(internalFrame_2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Frame Puzzle", panel_3);
		panel_3.add(internalFrame_3);
		
	}

}
