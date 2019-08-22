package miniapps;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JTabbedPane;

public class appGUI {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					appGUI window = new appGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public appGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1383, 940);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Button button = new Button("Picture Viewer");
		button.setBounds(256, 166, 836, 84);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					PictureViewer.main(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(button);

		Button button_1 = new Button("Pixel Inspector");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					PixelInspector.main(null);
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			}
		});
		button_1.setBounds(256, 328, 836, 84);
		frame.getContentPane().add(button_1);

		Button button_2 = new Button("Image Adjuster");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ImageAdjuster.main(null);
				} catch (IOException g) {
					// TODO Auto-generated catch block
					g.printStackTrace();
				}
			}
		});
		button_2.setBounds(256, 480, 836, 84);
		frame.getContentPane().add(button_2);

		Button button_3 = new Button("Frame Puzzle");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					FramePuzzle.main(null);
				} catch (IOException h) {
					// TODO Auto-generated catch block
					h.printStackTrace();
				}
			}
		});
		button_3.setBounds(256, 635, 836, 84);
		frame.getContentPane().add(button_3);
		
		JButton btnFileChooser = new JButton("File Chooser");
		btnFileChooser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					FileChooser.main();
			}
		});
		btnFileChooser.setBounds(1067, 55, 192, 29);
		frame.getContentPane().add(btnFileChooser);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					FramePuzzle.main(null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tabbedPane.setBounds(126, 46, 79, 29);
		frame.getContentPane().add(tabbedPane);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(258, 46, 71, 29);
		frame.getContentPane().add(tabbedPane_1);
	}
}
