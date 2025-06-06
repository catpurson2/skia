package skia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Customer {
	
	static BufferedImage c1;
	static BufferedImage c2;
	static BufferedImage c3;
	BufferedImage save;
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
		}
		
		int i = (int) (Math.random()*3);
		
		if(i == 0) {
			save = c1;
		} if (i == 1) {
			save = c2;
		} if (i == 2) {
			save = c3;
		}
		
		rotate = save;
		img = save;
		
		this.x = x;
		this.y = y;
		i = 0;
		move = false;
		leave = false;
	}
	
	public void paint(Graphics g) {
		if(x-i*5 == 190) {
			g.drawImage(rotate, x-i*5, y, 60, 67, null);
			System.out.println("rotate");
		} else {
			g.drawImage(save, x-i*5, y, 60, 67, null);
			System.out.println("save");
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
