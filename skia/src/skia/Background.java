package skia;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Background {
	
	BufferedImage back2;
	
	public Background() {
		
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		try {
			back2 = ImageIO.read(getClass().getResource("/imgs/background.png"));
			g.drawImage(back2, 0, 0, 1000, 980, null);
		} catch(Exception e) {
			System.out.println("tuesday");
		}
		
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
