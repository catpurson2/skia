package skia;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Oven extends Counter {
	
	int c;
	BufferedImage oven1;
	BufferedImage oven2;

	public Oven(int x, int y, int dir) {
		super(x, y, 0);
		
		if(dir == 0) {
			oven1 = getImg("oven1");
			oven2 = getImg("oven2");
		} else if (dir == 90) {
			oven1 = getImg("oven1s");
			oven2 = getImg("oven2s");
		}
		
		this.img = oven1;
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		c++;
		if(c%10 == 0) {
			if (this.img.equals(oven1)) {
				img = oven2;
			} else {
				img = oven1;
			}
			c = 0;
		}
		
		super.paint(g);
		
		
	}


}
