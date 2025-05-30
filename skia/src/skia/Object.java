package skia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Object {
	
	BufferedImage bowl, obj;
	
	public Object() {
		
		
		
	}
	
	public void paint(Graphics g, int x, int y) {
		
		if(bowl != null) {
			g.drawImage(bowl, x, y, 60, 60, null);
		}
		if(obj != null) {
			g.drawImage(bowl, x, y, 60, 60, null);
		}
		
	}
	
	public BufferedImage getImg(String path) {
		
		try {
			return ImageIO.read(getClass().getResource("/imgs/" + path + ".png"));
		} catch(Exception e) {
			System.out.println("tuesday");
		}
		
		return null;
		
	}

}
