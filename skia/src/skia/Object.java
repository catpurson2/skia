package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Object {
	
	BufferedImage bowl, plate;
	ArrayList<BufferedImage> ingredients;
	int progress;
	

	public Object() {
		bowl = null;
		ingredients = new ArrayList<BufferedImage>();
	}
	
	public void paint(Graphics g, int x, int y) {
		if(bowl != null) {
			g.drawImage(bowl, x, y, 70, 70, null);
		}
		if(plate != null) {
			g.drawImage(plate, x, y, 70, 70, null);
		}
		
	
		for(BufferedImage i : ingredients) {
			g.drawImage(i, x, y, 70, 70, null);
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
	
	public void add(String ingredient) {
		ingredients.add(getImg(ingredient));
	}

}
