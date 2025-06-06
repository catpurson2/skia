package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Object {
	
	BufferedImage bowl, plate;
	ArrayList<BufferedImage> ingredients;
	IngredientBar bar;
	ArrayList<String> in;
	int progress;
	boolean empty;
	boolean mixed;
	static BufferedImage[] ing;
	boolean burnt = false;
	

	public Object() {
		bowl = null;
		empty = true;
		ingredients = new ArrayList<BufferedImage>();
		in = new ArrayList<String>();
		mixed = true;
		
		if(ing == null) {
			ing = new BufferedImage[4];
			ing[0] = getImg("milk");
			ing[1] = getImg("egg");
			ing[2] = getImg("flour");
			ing[3] = getImg("sugar");
		}
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
		
		if(bar != null) {
			bar.paint(g, x+83, y, 0);
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
		in.add(ingredient);
		progress = 0;
	}

}
