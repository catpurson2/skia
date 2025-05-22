package skia;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Oven extends Counter {
	
	int c;
	String path;

	public Oven(int x, int y) {
		super(x, y);
		
		path = "oven1";
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		c++;
		if(c%5 == 0) {
			if (path.equals("oven1")) {
				path = "oven2";
			} else {
				path = "oven1";
			}
		}
		
		
		super.setImg(path);
		super.paint(g);
		
		
	}


}
