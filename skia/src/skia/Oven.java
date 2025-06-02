package skia;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Oven extends Counter {
	
	int c;
	BufferedImage oven;
	BufferedImage oven1;
	BufferedImage oven2;
	Progress bar = new Progress(x, y-20);
	Boolean on;
	Boolean fire;

	public Oven(int x, int y, int dir) {
		super(x, y, 0);
		
		if(dir == 0) {
			oven = getImg("oven");
			oven1 = getImg("oven1");
			oven2 = getImg("oven2");
		} else if (dir == 90) {
			oven = getImg("ovens");
			oven1 = getImg("oven1s");
			oven2 = getImg("oven2s");
		}
		
		this.img = oven;
		fire = false;
		on = false;
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		if(obj.bowl != null) {
			if(obj.mixed && obj.ingredients.size() > 0) {
				obj.progress = 0;
				obj.mixed = false;
			}
			bar.turnOn(obj.progress);
			on = true;
			
			if(bar.progress == 500) {
				((Bowl) obj).bake();
			} else if(bar.progress == 800) {
				((Bowl) obj).burn();
			}
		} else {
			bar.on = false;
			on = false;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		
		
		if(on) {
			c++;
			if(c%10 == 0) {
				if (this.img.equals(oven1)) {
					img = oven2;
				} else {
					img = oven1;
				}
				c = 0;
			}
			fire = bar.paint(g);
			obj.progress = bar.progress;
		} else {
			img = oven;
		}
		
		super.paint(g);
		
		
	}
	
	public Boolean fireCheck() {
		return fire;
	}


}
