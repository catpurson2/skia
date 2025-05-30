package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bowl extends Object {

	
	
	public Bowl() {
		
		this.bowl = getImg("bowl");
		this.ingredients = new ArrayList<BufferedImage>();
		// TODO Auto-generated constructor stub

	}
	
	public void paint(Graphics g, int x, int y) {
		
		if(bowl != null) {
			g.drawImage(bowl, x, y, 70, 70, null);
		}
		for(BufferedImage i : ingredients) {
			g.drawImage(i, x, y, 70, 70, null);
		}
		
	}


}
