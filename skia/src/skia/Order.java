package skia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Order {
	
	Boolean cake;
	Boolean frosting;
	Boolean topping;
	
	static BufferedImage small;
	static BufferedImage big;
	static BufferedImage vcake;
	static BufferedImage scake;
	static BufferedImage vfrosting;
	static BufferedImage sfrosting;
	static BufferedImage strawberry;
	
	public Order() {
		
		//randomize order
		
		if(Math.random() < 0.5) {
			cake = false;
		} else {
			cake = true;
		}
		
		if(Math.random() < 0.5) {
			frosting = false;
		} else {
			frosting = true;
		}
		
		if(Math.random() < 0.5) {
			topping = false;
		} else {
			topping = true;
		}
		
		//load images
		if(small == null) {
			small = getImg("small");
			big = getImg("big");
			vcake = getImg("vcake");
			scake = getImg("scake");
			vfrosting = getImg("vfrosting");
			sfrosting = getImg("sfrosting");
			strawberry = getImg("topping");
		}
		
	}
	
	public void paint(Graphics g, int x, int y) {
		
		
		//draw correct image
		if(topping) {
			g.drawImage(big, x, y, 200, 176, null);
		} else {
			g.drawImage(small, x, y, 200, 176, null);
		}
		if(cake) {
			g.drawImage(scake, x, y, 200, 176, null);
		} else {
			g.drawImage(vcake, x, y, 200, 176, null);
		}
		if(frosting) {
			g.drawImage(sfrosting, x, y, 200, 176, null);
		} else {
			g.drawImage(vfrosting, x, y, 200, 176, null);
		}
		
		if(topping) {
			g.drawImage(strawberry, x, y, 200, 176, null);
		}
		
		//g.drawImage(getImg("cake"), x, y, 200, 176, null);
		
	}
	
	public BufferedImage getImg(String path) {
		
		try {
			return ImageIO.read(getClass().getResource("/order/" + path + ".png"));
		} catch(Exception e) {
			System.out.println("tuesday");
		}
		
		return null;
		
	}

}
