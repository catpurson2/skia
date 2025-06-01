package skia;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Register {
	
	int width, height, x, y, c;
	BufferedImage img;
	float alpha = 1f;
	Plate obj;
	
	public Register(int x, int y) {
		width = 160;
		height = 80;
		this.x = x;
		this.y = y;
		
		img = getImg("register");
		
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		
		
		g.drawImage(img, x, y, width, height, null);
		
		if(obj != null) {
			obj.paint(g, x+80, y-(int)(1-10*obj.alpha));
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
	
	public void sell(Plate temp) {
		temp.sold = true;
		obj = temp;
		
		
		
	}
	
}
