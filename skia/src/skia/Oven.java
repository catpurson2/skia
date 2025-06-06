package skia;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Oven extends Counter {
	
	int c;
	int b;
	Boolean extinguished = false;
	BufferedImage oven;
	BufferedImage oven1;
	BufferedImage oven2;
	BufferedImage fires;
	BufferedImage fire1;
	BufferedImage fire2;
	BufferedImage fire3;
	BufferedImage fire4;
	
	Progress bar = new Progress(x, y-20);
	Boolean on;
	Boolean fire;

	public Oven(int x, int y, int dir) {
		super(x, y, 0);
		fires = getImg("fire1");
		if(dir == 0) {
			oven = getImg("oven");
			oven1 = getImg("oven1");
			oven2 = getImg("oven2");
		} else if (dir == 90) {
			oven = getImg("ovens");
			oven1 = getImg("oven1s");
			oven2 = getImg("oven2s");
		}
		
		fire1 = getImg("fire1");
		fire2 = getImg("fire2");
		fire3 = getImg("fire3");
		fire4 = getImg("fire4");
		fires = fire1;
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
    }
    
		if(obj.bowl != null && !fire && !extinguished) {

			bar.turnOn(obj.progress);
			on = true;
			
			if(bar.progress == 500) {
				((Bowl) obj).bake();
				
				SimpleAudioTester.loopSound("alarmforoven");
				bar.progress++;
			} else if(bar.progress == 800) {
				((Bowl) obj).burn();
				
			}
		} else {
			
			bar.on = false;
			on = false;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		
		//check if ingredients of obj contain burnt to turn off extinguished, wait no do that in chef
		
		if(on|| fire) {
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
	
		if(fire && !extinguished) {
			SimpleAudioTester.stopSound("alarmforoven");
			SimpleAudioTester.loopSound("smokealarm");
			b++;
			if(b%5 == 0) {
				if(fires.equals(fire1)) {
					fires = fire2;
				}else if(fires.equals(fire2)) {
					fires = fire3;
				}else if(fires.equals(fire3)) {
					fires = fire4;
				}else {
					fires = fire1;
					b=0;
				}
			}
			g.drawImage(fires, x,y, 80,80,null);
			
		}
		
	}
	
	public Boolean fireCheck() {
		return fire;
	}
	
	public void extinguish() {
		SimpleAudioTester.stopSound("smokealarm");
		obj.progress = 0;
		bar.on = false;
		extinguished = true;
		fire = false;
		
	}

}
