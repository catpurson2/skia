
package skia;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Plate extends Object {
	
	boolean isDirty = false;
	float alpha = 1f;
	boolean sold = false;
	int timer = 0;
	

	public Plate() {
    super();
		super.empty = false;
		super.plate = getImg("plate");
		// TODO Auto-generated constructor stub
	}
	
	public boolean isDirty() {
		return isDirty;
	}
	
	public void paint(Graphics g, int x, int y) {
		
		if(sold) {
			Graphics2D g2 = (Graphics2D) g;
			//fades out the plate when being sold
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			
			alpha -= 0.025;
			if(alpha < 0) {
				super.plate = null;
				alpha = 1f;
				timer++;
				empty = true;
				ingredients = new ArrayList<BufferedImage>();
				in = new ArrayList<String>();
				bar = null;
				if(timer == 10){
					timer = 0;
					isDirty = true;
					super.plate = getImg("dirtyplate");
					empty = false;
					super.paint(g, x+80, y-5); 
					sold = false;
				}
			}else {
				super.paint(g, x, y);
			}
			
		}else {
			super.paint(g, x, y);
		}
		
	}
	
	public void add(String ingredient) {
		//combines cakes and frosting on the plate
		ingredients.add(getImg(ingredient));
		in.add(ingredient);
		progress = 0;
		bar = new IngredientBar(in); //labels whats on the plate
	}
	
}
