package frame;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PicturePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon imageIcon;
	Image image;
	String imageIconPath;

	public void setImageIconPath(String imageIconPath) {
		this.imageIconPath = imageIconPath;
	}

	public PicturePanel(String imageIconPath) {
		imageIcon = new ImageIcon(getClass().getResource(imageIconPath));
		image = imageIcon.getImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}