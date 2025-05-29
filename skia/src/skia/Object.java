package skia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Object {
	
	BufferedImage bowl, obj;
	
	public Object(Boolean bowl, int obj) {
		
		if (bowl) {
			this.bowl = getImg("bowl");
		} else {
			bowl = null;
		}
		
		if(obj == 0) {
			this.obj = null;
		}
		
	}
	
	public void paint(Graphics g, int x, int y) {
		
		if(bowl != null) {
			g.drawImage(bowl, x, y, 70, 70, null);
		}
		if(obj != null) {
			g.drawImage(bowl, x, y, 70, 70, null);
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
