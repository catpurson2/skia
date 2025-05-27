package skia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Mixer extends Counter {
	
	BufferedImage mixer;
	BufferedImage mixer2;
	BufferedImage mixer3;
	BufferedImage mixer4;
	BufferedImage img;
	String miximg;
	Boolean mixing;
	int f;

	public Mixer(int x, int y) {
		super(x, y);
		
		miximg = "mixer";
		mixer = getImg("mixer");
		mixer2 = getImg("mixer1");
		mixer3 = mixer;
		mixer4 = getImg("mixer2");
		img = mixer;
		mixing = true;
		f = 0;
		
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		f++;
		
		if(mixing && f%5 == 0) {
			if(img.equals(mixer)) {
				img = mixer2;
			} else if(img.equals(mixer2)) {
				img = mixer3;
			} else if(img.equals(mixer3)) {
				img = mixer4;
			} else if(img.equals(mixer4)) {
				img = mixer;
			}
		}
		if(!mixing) {
			img = mixer;
		}
		
		g.drawImage(img, x, y-4, (int) (1*width), (int) (1*height), null);
		
	}
	

}
