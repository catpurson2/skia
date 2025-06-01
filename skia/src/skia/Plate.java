
package skia;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Plate extends Object {
	
	boolean isDirty = true;
	float alpha = 1f;
	boolean sold = false;
	int timer = 0;
	
	public Plate() {
		super.empty = false;
		super.plate = getImg("dirtyplate");
		// TODO Auto-generated constructor stub
	}
	
	public boolean isDirty() {
		return isDirty;
	}
	
	public void paint(Graphics g, int x, int y) {
		
		if(sold) {
			Graphics2D g2 = (Graphics2D) g;
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			
			alpha -= 0.025;
			if(alpha < 0) {
				super.plate = null;
				alpha = 1f;
				timer++;
				if(timer == 10){
					timer = 0;
					isDirty = true;
					super.plate = getImg("dirtyplate");
					super.paint(g, x+80, y-5); //DONT TOUCH THIS IM SCARED WHAT WILL HAPPEN 
					sold = false;
				}
			}else {
				super.paint(g, x, y);
			}
			
		}else {
			super.paint(g, x, y);
		}
		
		
		
		
		
		
	}
	
}
