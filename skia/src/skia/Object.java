package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Object {
	
	BufferedImage bowl, plate, obj;
	int progress;
	

	public Object() {
		bowl = null;
	}
	
	public void paint(Graphics g, int x, int y) {
		
		if(bowl != null) {
			g.drawImage(bowl, x, y, 70, 70, null);
			
			if(progress > 0 && progress < 250) {
				g.setColor(Color.yellow);
				g.fillOval(x+15, y+15, 40, 40);
			} else if (progress > 0 && progress < 500) {
				g.setColor(Color.orange);
				g.fillOval(x+15, y+15, 40, 40);
			} else if (progress > 0){
				g.setColor(Color.green);
				g.fillOval(x+15, y+15, 40, 40);
			}
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
