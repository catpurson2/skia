package skia;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Background {
	
	public Background() {
		
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		Image grass = getImage("/imgs/map-pixilart.png");
		
		g.drawImage(grass, 0, 0, 1000, 800, null);
		
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
