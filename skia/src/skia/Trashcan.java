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
	
}


