package miniapps;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class PuzzlePictureViewWidget extends JPanel implements MouseListener, KeyListener {

	private PictureView[][] picViewArray;
	private ObservablePicture blankPic;
	private int blankRow;
	private int blankColumn;

	public PuzzlePictureViewWidget(Picture picture) {

		setLayout(new GridLayout(5, 5));

		// fill 2d picture array with 5x5 grid of encapsulated picture
		int incWidth = (int) picture.getWidth() / 5;
		int incHeight = (int) picture.getHeight() / 5;
		SubPicture[][] subPicArray = new SubPicture[5][5];

		// first row
		subPicArray[0][0] = picture.extract(0, 0, incWidth, incHeight);
		subPicArray[0][1] = picture.extract(incWidth, 0, incWidth, incHeight);
		subPicArray[0][2] = picture.extract(incWidth * 2, 0, incWidth, incHeight);
		subPicArray[0][3] = picture.extract(incWidth * 3, 0, incWidth, incHeight);
		subPicArray[0][4] = picture.extract(incWidth * 4, 0, incWidth, incHeight);
		// second row
		subPicArray[1][0] = picture.extract(0, incHeight, incWidth, incHeight);
		subPicArray[1][1] = picture.extract(incWidth, incHeight, incWidth, incHeight);
		subPicArray[1][2] = picture.extract(incWidth * 2, incHeight, incWidth, incHeight);
		subPicArray[1][3] = picture.extract(incWidth * 3, incHeight, incWidth, incHeight);
		subPicArray[1][4] = picture.extract(incWidth * 4, incHeight, incWidth, incHeight);
		// third row
		subPicArray[2][0] = picture.extract(0, incHeight * 2, incWidth, incHeight);
		subPicArray[2][1] = picture.extract(incWidth, incHeight * 2, incWidth, incHeight);
		subPicArray[2][2] = picture.extract(incWidth * 2, incHeight * 2, incWidth, incHeight);
		subPicArray[2][3] = picture.extract(incWidth * 3, incHeight * 2, incWidth, incHeight);
		subPicArray[2][4] = picture.extract(incWidth * 4, incHeight * 2, incWidth, incHeight);
		// fourth row
		subPicArray[3][0] = picture.extract(0, incHeight * 3, incWidth, incHeight);
		subPicArray[3][1] = picture.extract(incWidth, incHeight * 3, incWidth, incHeight);
		subPicArray[3][2] = picture.extract(incWidth * 2, incHeight * 3, incWidth, incHeight);
		subPicArray[3][3] = picture.extract(incWidth * 3, incHeight * 3, incWidth, incHeight);
		subPicArray[3][4] = picture.extract(incWidth * 4, incHeight * 3, incWidth, incHeight);
		// fifth row
		subPicArray[4][0] = picture.extract(0, incHeight * 4, incWidth, incHeight);
		subPicArray[4][1] = picture.extract(incWidth, incHeight * 4, incWidth, incHeight);
		subPicArray[4][2] = picture.extract(incWidth * 2, incHeight * 4, incWidth, incHeight);
		subPicArray[4][3] = picture.extract(incWidth * 3, incHeight * 4, incWidth, incHeight);

		// blank picture
		subPicArray[4][4] = (SubPicture) picture.extract(incWidth * 4, incHeight * 4, incWidth, incHeight).paint(0, 0,
				incWidth, incHeight, Pixel.WHITE);
		blankPic = new ObservablePictureImpl(subPicArray[4][4]);
		blankRow = 4;
		blankColumn = 4;

		// fills grid and adds listeners
		picViewArray = new PictureView[5][5];
		for (int i = 0; i < subPicArray.length; i++) {
			for (int j = 0; j < subPicArray[0].length; j++) {
				picViewArray[i][j] = new PictureView(subPicArray[i][j].createObservable());
				picViewArray[i][j].addMouseListener(this);
				picViewArray[i][j].addKeyListener(this);
				picViewArray[i][j].setFocusable(true);
				add(picViewArray[i][j]);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		picViewArray[blankRow][blankColumn].setPicture(keySwap(e));
		picViewArray[blankRow][blankColumn].setPicture(blankPic);
	}

	public ObservablePicture keySwap(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_UP) {
			blankRow -= 1;
			if (blankRow < 0) {
				blankRow = 0;
			}
			return picViewArray[blankRow][blankColumn].getPicture();
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			blankRow += 1;
			if (blankRow > 4) {
				blankRow = 4;
			}
			return picViewArray[blankRow][blankColumn].getPicture();
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			blankColumn += 1;
			if (blankColumn > 4) {
				blankColumn = 4;
			}
			return picViewArray[blankRow][blankColumn].getPicture();
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			blankColumn -= 1;
			if (blankColumn < 0) {
				blankColumn = 0;
			}
			return picViewArray[blankRow][blankColumn].getPicture();
		} else {
			return blankPic;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		PictureView picView = (PictureView) e.getSource();
		ObservablePicture clickedPic = (picView.getPicture());
		for (int i = 0; i < picViewArray.length; i++) {
			for (int j = 0; j < picViewArray[0].length; j++) {
				if (picViewArray[i][j].getPicture() == clickedPic) {

					if (blankRow != i && blankColumn != j) {
						return;
					} else {

						// horizontal movement
						// click is left of blank
						if (picView.getX() < picViewArray[blankRow][blankColumn].getX()) {

							// slides pictures right
							for (int k = blankColumn; k > j; k--) {
								picViewArray[blankRow][k].setPicture(picViewArray[blankRow][k - 1].getPicture());
							}
							blankColumn = j;
							picViewArray[blankRow][blankColumn].setPicture(blankPic);
						}

						// click is right of blank
						if (picView.getX() > picViewArray[blankRow][blankColumn].getX()) {

							// slides pictures left
							for (int k = blankColumn; k < j; k++) {
								picViewArray[blankRow][k].setPicture(picViewArray[blankRow][k + 1].getPicture());
							}
							blankColumn = j;
							picViewArray[blankRow][blankColumn].setPicture(blankPic);
						}

						// vertical movement
						// click is above the blank
						if (picView.getY() < picViewArray[blankRow][blankColumn].getY()) {

							// slides pictures down
							for (int k = blankRow; k > i; k--) {
								picViewArray[k][blankColumn].setPicture(picViewArray[k - 1][blankColumn].getPicture());
							}
							blankRow = i;
							picViewArray[blankRow][blankColumn].setPicture(blankPic);
						}

						// click is below the blank
						if (picView.getY() > picViewArray[blankRow][blankColumn].getY()) {

							// slides pictures up
							for (int k = blankRow; k < i; k++) {
								picViewArray[k][blankColumn].setPicture(picViewArray[k + 1][blankColumn].getPicture());
							}
							blankRow = i;
							picViewArray[blankRow][blankColumn].setPicture(blankPic);
						}
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
