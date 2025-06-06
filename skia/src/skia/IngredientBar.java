package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class IngredientBar {
	
	ArrayList<BufferedImage> in;
	static BufferedImage bar;
	static BufferedImage vcake;
	static BufferedImage scake;
	static BufferedImage vfrosting;
	static BufferedImage sfrosting;
	static BufferedImage strawberry;
	static BufferedImage burnt;
	static BufferedImage green;
	int x;
	int y;
	
	public IngredientBar(ArrayList<String> in) {
		
		this.in = new ArrayList<BufferedImage>();
		
		if(bar == null) {
			bar = getImg("bar");
			vcake = getImg("tinyvcake");
			scake = getImg("tinyscake");
			vfrosting = getImg("tinyvfrosting");
			sfrosting = getImg("tinysfrosting");
			strawberry = getImg("tinystrawberry");
			burnt = getImg("tinyburnt");
			green = getImg("tinygreen");
		}
		
		if(in.contains("strawberry")) {
			this.in.add(strawberry);
		}
		
		if(in.contains("strawberryfrosted")) {
			this.in.add(sfrosting);
		} else if (in.contains("frosted")) {
			this.in.add(vfrosting);
		}
		
		if(in.contains("burnt")) {
			this.in.add(green);
		} else if (in.contains("green")) {
			this.in.add(burnt);
		} else if (in.contains("strawberrycake")) {
			this.in.add(scake);
		} else if (in.contains("cake")) {
			this.in.add(vcake);
		}
		
		
	}
	
	public void paint(Graphics g, int x, int y, int dir) {
		
		for(int i = 0; i < in.size(); i++) {
			g.drawImage(bar, x, y+(8*80*i)/30, (9*80)/30, (10*80)/30, null);
		}
		
		for(int i = 0; i < in.size(); i++) {
			g.drawImage(in.get(i), x, y+(8*80*i)/30, (9*80)/30, (10*80)/30, null);
		}
		
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
