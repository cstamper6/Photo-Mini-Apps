package miniapps;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InspectorPictureViewWidget extends JPanel implements MouseListener {

	private PictureView pictureView;
	private JLabel xLabel;
	private JLabel yLabel;
	private JLabel redLabel;
	private JLabel greenLabel;
	private JLabel blueLabel;
	private JLabel brightnessLabel;

	public InspectorPictureViewWidget(Picture picture) {

		// left-side panel containing labels
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(0, 1));
		setLayout(new BorderLayout());
		add(listPanel, BorderLayout.WEST);

		try {pictureView = new PictureView(picture.createObservable());
		pictureView.addMouseListener(this);
		add(pictureView, BorderLayout.CENTER);
		} catch (Exception e) {}

		// label creations
		xLabel = new JLabel("X: ");
		listPanel.add(xLabel);
		yLabel = new JLabel("Y: ");
		listPanel.add(yLabel);
		redLabel = new JLabel("Red: ");
		listPanel.add(redLabel);
		greenLabel = new JLabel("Green: ");
		listPanel.add(greenLabel);
		blueLabel = new JLabel("Blue: ");
		listPanel.add(blueLabel);
		brightnessLabel = new JLabel("Brightness: ");
		listPanel.add(brightnessLabel);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		// updates labels depending on location of mouse press
		xLabel.setText("X: " + arg0.getX());
		yLabel.setText("Y: " + arg0.getY());
		redLabel.setText("Red: " + roundAvoid(pictureView.getPicture().getPixel(arg0.getX(), arg0.getY()).getRed(), 2));
		greenLabel.setText(
				"Green: " + roundAvoid(pictureView.getPicture().getPixel(arg0.getX(), arg0.getY()).getGreen(), 2));
		blueLabel.setText(
				"Blue: " + roundAvoid(pictureView.getPicture().getPixel(arg0.getX(), arg0.getY()).getBlue(), 2));
		brightnessLabel.setText("Brightness: "
				+ roundAvoid(pictureView.getPicture().getPixel(arg0.getX(), arg0.getY()).getIntensity(), 2));

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// truncates value to specified decimal place
	public static double roundAvoid(double value, int places) {
		double scale = Math.pow(10, places);
		return Math.round(value * scale) / scale;
	}
}
