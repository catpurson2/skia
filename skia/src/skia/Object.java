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
	
	/*public Object(Boolean bowl, int obj) {
		
		if (bowl) {
			this.bowl = getImg("bowl");
		} else {
			bowl = null;
		}
		
		if(obj == 0) {
			this.obj = null;
		}
		
	}*/
	
	public void paint(Graphics g, int x, int y) {
		
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
