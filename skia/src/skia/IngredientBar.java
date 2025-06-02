package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class IngredientBar {
	
	ArrayList<BufferedImage> in;
	int x;
	int y;
	
	public IngredientBar(ArrayList<String> in) {
		
		this.in = new ArrayList<BufferedImage>();
		this.x = x;
		this.y = y;
		
		for(String i : in) {
			if(i.equals("milk")) {
				this.in.add(getImg("flourbin"));
			} else if(i.equals("egg")) {
				this.in.add(getImg("eggbin"));
			} else if(i.equals("flour")) {
				this.in.add(getImg("flourbin"));
			} else if(i.equals("sugar")) {
				this.in.add(getImg("sugarbin"));
			} else if(i.equals("cake")) {
				this.in.add(getImg("cakebin"));
			} else if(i.equals("frosting")) {
				this.in.add(getImg("frostingbin"));
			} else if(i.equals("strawberry")) {
				this.in.add(getImg("strawberrybin"));
			}
		}
		
	}
	
	public void paint(Graphics g, int x, int y) {
		
		g.setColor(Color.white);
		
		g.fillRect(x+60, y, 80*8/30, 10+20*in.size());
		
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
