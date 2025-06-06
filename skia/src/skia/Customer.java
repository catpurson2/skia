package skia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Customer {
	
	static BufferedImage c1;
	static BufferedImage c2;
	static BufferedImage c3;
	static BufferedImage r1;
	static BufferedImage r2;
	static BufferedImage r3;
	BufferedImage rotate;
	BufferedImage img;
	int x, y;
	int i;
	Boolean move;
	Boolean leave;
	
	public Customer(int x, int y) {
		if(c1 == null) {
			c1 = getImg("customer1");
			c2 = getImg("customer2");
			c3 = getImg("customer3");
			r1 = getImg("rotate1");
			r2 = getImg("rotate2");
			r3 = getImg("rotate3");
		}
		
		int i = (int) (Math.random()*3);
		
		if(i == 0) {
			img = c1;
			rotate = r1;
		} if (i == 1) {
			img = c2;
			rotate = r2;
		} if (i == 2) {
			img = c3;
			rotate = r3;
		}
		
		this.x = x;
		this.y = y;
		i = 0;
		move = false;
		leave = false;
	}
	
	public void paint(Graphics g) {
		if(x-i*5 == 190) {
			g.drawImage(rotate, x-i*5, y, 60, 67, null);
			//System.out.println("rotate");
		} else {
			g.drawImage(img, x-i*5, y, 60, 67, null);
			//System.out.println("save");
		}
	}
	
	public Boolean move() {
		if(move) {
			i++;
			if(i == 8) {
				return true;
			}
		}
		if(i >= 16) {
			if(!leave) {
				x = x-i*5;
				i = 0;
				move = false;
				//System.out.println("stop");
			}
		}
		return false;
	}
	
	public BufferedImage getImg(String path) {
		
		try {
			return ImageIO.read(getClass().getResource("/character/" + path + ".png"));
		} catch(Exception e) {
			System.out.println("tuesday");
		}
		
		return null;
		
	}
	
	

}
