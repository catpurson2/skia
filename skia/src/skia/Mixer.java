package skia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Mixer extends Counter {
	
	BufferedImage notmix;
	BufferedImage mixer;
	BufferedImage mixer2;
	BufferedImage mixer3;
	BufferedImage mixer4;
	BufferedImage img;
	Progress bar = new Progress(x, y-20);
	//String mixImg;
	Boolean mixing;
	int f;

	public Mixer(int x, int y) {
		super(x, y, 1);
		obj = new Object();
		//mixImg = "mixer";
		notmix = getImg("nomix");
		mixer = getImg("mixer");
		mixer2 = getImg("mixer1");
		mixer3 = mixer;
		mixer4 = getImg("mixer2");
		img = mixer;
		mixing = false;
		f = 0;
		
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		f++;
		
		if(obj.bowl != null) {
			mixing = true;
			mix();
		} else {
			mixing = false;
		}
		
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
			img = notmix;
		}
		
		g.drawImage(img, x, y-4, (int) (1*width), (int) (1*height), null);
		
	}
	
	public void mix() {
		if(img.equals(notmix)) {
			img = mixer;
		}
	}
	

}
