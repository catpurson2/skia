package skia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Trashcan extends Counter{
	
	BufferedImage trash = getImg("trashcan");
	int size;
	
	public Trashcan(int x, int y, int size) {
		super(x,y, 0);
		
		this.size = size;
		
		super.img = trash;
		
			
	}
	
	public void throwOut(Object obj) {
		
		int size = obj.in.size();
		for(int i = 0; i < size; i++) {
			obj.in.remove(0);
			obj.ingredients.remove(0);
			
		}
		
		if(obj.bowl != null) {
			obj = new Bowl();
		} else if (obj.plate != null) {
			obj = new Plate();
		}
		//remove the ingredient bar
	}
	
}


