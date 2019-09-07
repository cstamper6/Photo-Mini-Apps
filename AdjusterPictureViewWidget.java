package miniapps;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AdjusterPictureViewWidget extends JPanel implements ChangeListener {

	private PictureView pictureView;
	private JLabel blurLabel;
	private JSlider blurSlider;
	private JLabel saturationLabel;
	private JSlider saturationSlider;
	private JLabel brightnessLabel;
	private JSlider brightnessSlider;
	private Pixel[][] picArray;
	private ObservablePicture pic;

	public AdjusterPictureViewWidget(Picture picture) {

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(0, 1));
		setLayout(new BorderLayout());
		add(listPanel, BorderLayout.SOUTH);

		// use helper method to create copy
		Picture picCopy = copyAsImmutable(picture);
		pictureView = new PictureView(picCopy.createObservable());
		add(pictureView, BorderLayout.CENTER);
		pic = pictureView.getPicture();

		blurLabel = new JLabel("Blur: ");
		listPanel.add(blurLabel);

		blurSlider = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
		blurSlider.setPaintTicks(true);
		blurSlider.setMajorTickSpacing(1);
		blurSlider.setSnapToTicks(false);

		Hashtable<Integer, JLabel> blurLabels = new Hashtable<>();
		blurLabels.put(0, new JLabel("0"));
		blurLabels.put(1, new JLabel("1"));
		blurLabels.put(2, new JLabel("2"));
		blurLabels.put(3, new JLabel("3"));
		blurLabels.put(4, new JLabel("4"));
		blurLabels.put(5, new JLabel("5"));
		blurSlider.setLabelTable(blurLabels);
		blurSlider.setPaintLabels(true);
		listPanel.add(blurSlider);
		blurSlider.addChangeListener(this);

		saturationLabel = new JLabel("Saturation: ");
		listPanel.add(saturationLabel);

		saturationSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
		saturationSlider.setPaintTicks(true);
		saturationSlider.setMajorTickSpacing(25);
		saturationSlider.setSnapToTicks(false);

		Hashtable<Integer, JLabel> saturationLabels = new Hashtable<>();
		saturationLabels.put(-100, new JLabel("-100"));
		saturationLabels.put(-75, new JLabel("-75"));
		saturationLabels.put(-50, new JLabel("-50"));
		saturationLabels.put(-25, new JLabel("-25"));
		saturationLabels.put(0, new JLabel("0"));
		saturationLabels.put(25, new JLabel("25"));
		saturationLabels.put(50, new JLabel("50"));
		saturationLabels.put(75, new JLabel("75"));
		saturationLabels.put(100, new JLabel("100"));
		saturationSlider.setLabelTable(saturationLabels);
		saturationSlider.setPaintLabels(true);
		listPanel.add(saturationSlider);
		saturationSlider.addChangeListener(this);

		brightnessLabel = new JLabel("Brightness: ");
		listPanel.add(brightnessLabel);

		brightnessSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
		brightnessSlider.setPaintTicks(true);
		brightnessSlider.setMajorTickSpacing(25);
		brightnessSlider.setSnapToTicks(false);

		Hashtable<Integer, JLabel> brightnessLabels = new Hashtable<>();
		brightnessLabels.put(-100, new JLabel("-100"));
		brightnessLabels.put(-75, new JLabel("-75"));
		brightnessLabels.put(-50, new JLabel("-50"));
		brightnessLabels.put(-25, new JLabel("-25"));
		brightnessLabels.put(0, new JLabel("0"));
		brightnessLabels.put(25, new JLabel("25"));
		brightnessLabels.put(50, new JLabel("50"));
		brightnessLabels.put(75, new JLabel("75"));
		brightnessLabels.put(100, new JLabel("100"));
		brightnessSlider.setLabelTable(saturationLabels);
		brightnessSlider.setPaintLabels(true);
		listPanel.add(brightnessSlider);
		brightnessSlider.addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		JSlider source = (JSlider) e.getSource();
		if (source.getValueIsAdjusting()) {
			changes(pic);
			pictureView.setPicture(new ObservablePictureImpl(
					new ImmutablePixelArrayPicture(picArray, pictureView.getPicture().getCaption())));
		}

	}

	public void changes(Picture pic) {

		picArray = new Pixel[pic.getWidth()][pic.getHeight()];
		for (int i = 0; i < pic.getWidth(); i++) {
			for (int j = 0; j < pic.getHeight(); j++) {
				picArray[i][j] = pic.getPixel(i, j);
			}
		}

		// blur
		for (int i = 0; i < pic.getWidth(); i++) {
			for (int j = 0; j < pic.getHeight(); j++) {
				double red = 0;
				double green = 0;
				double blue = 0;
				double redSum = 0;
				double greenSum = 0;
				double blueSum = 0;
				int legalPixels = 0;

				try {
					for (int x = i - blurSlider.getValue(); x < i + blurSlider.getValue(); x++) {
						for (int y = j - blurSlider.getValue(); y < j + blurSlider.getValue(); y++) {
							red = pic.getPixel(x, y).getRed();
							green = pic.getPixel(x, y).getGreen();
							blue = pic.getPixel(x, y).getBlue();
							redSum += red;
							greenSum += green;
							blueSum += blue;
							legalPixels++;
							picArray[i][j] = new ColorPixel(redSum / legalPixels, greenSum / legalPixels,
									blueSum / legalPixels);
						}
					}
				} catch (IllegalArgumentException e) {
				}
			}
		}

		// saturate
		int f = saturationSlider.getValue();
		double lc = 0;
		for (int i = 0; i < pic.getWidth(); i++) {
			for (int j = 0; j < pic.getHeight(); j++) {
				double b = picArray[i][j].getIntensity();
				if (f < 0) {

					double newRed = picArray[i][j].getRed() * (1.0 + (f / 100.0)) - (b * f / 100.0);
					if (newRed < 0) {
						newRed = 0;
					}
					double newGreen = picArray[i][j].getGreen() * (1.0 + (f / 100.0)) - (b * f / 100.0);
					if (newGreen < 0) {
						newGreen = 0;
					}
					double newBlue = picArray[i][j].getBlue() * (1.0 + (f / 100.0)) - (b * f / 100.0);
					if (newBlue < 0) {
						newBlue = 0;
					}
					picArray[i][j] = new ColorPixel(newRed, newGreen, newBlue);
				}
				if (f > 0) {

					double oldRed = picArray[i][j].getRed();
					double oldGreen = picArray[i][j].getGreen();
					double oldBlue = picArray[i][j].getBlue();

					// set lc value
					if (oldRed > oldGreen && oldRed > oldBlue) {
						lc = oldRed;
					}
					if (oldGreen > oldRed && oldGreen > oldBlue) {
						lc = oldGreen;
					}
					if (oldBlue > oldRed && oldBlue > oldGreen) {
						lc = oldBlue;
					}

					if (lc < 0.01) {
						picArray[i][j] = Pixel.BLACK;
					} else {
						double newRed = oldRed * ((lc + ((1.0 - lc) * (f / 100.0))) / lc);
						if (newRed > 1) {
							newRed = 1;
						}
						double newGreen = oldGreen * ((lc + ((1.0 - lc) * (f / 100.0))) / lc);
						if (newGreen > 1) {
							newGreen = 1;
						}
						double newBlue = oldBlue * ((lc + ((1.0 - lc) * (f / 100.0))) / lc);
						if (newBlue > 1) {
							newBlue = 1;
						}
						picArray[i][j] = new ColorPixel(newRed, newGreen, newBlue);
					}
				}
			}
		}

		// brighten
		double bright = brightnessSlider.getValue();
		for (int i = 0; i < pic.getWidth(); i++) {
			for (int j = 0; j < pic.getHeight(); j++) {
				if (bright > 0) {
					picArray[i][j] = picArray[i][j].lighten(bright * 0.01);
				}
				if (bright < 0) {
					picArray[i][j] = picArray[i][j].darken(bright * (-0.01));
				}
			}
		}
	}

	private static Picture copyAsImmutable(Picture p) {

		if (p == null) {
			throw new IllegalArgumentException("Picture p is null");
		}

		Pixel[][] parray = new Pixel[p.getWidth()][p.getHeight()];
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				parray[x][y] = p.getPixel(x, y);
			}
		}
		return new ImmutablePixelArrayPicture(parray, p.getCaption());
	}
}
