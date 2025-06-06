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
	BufferedImage fires;
	BufferedImage fire1;
	BufferedImage fire2;
	BufferedImage fire3;
	BufferedImage fire4;
	Progress bar = new Progress(x, y+80);
	//String mixImg;
	Boolean mixing;
	Boolean fire;
	Boolean extinguished;
	//counting variables
	int f;
	int b;

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
		fire = false;
		f = 0;
		fire1 = getImg("fire1");
		fire2 = getImg("fire2");
		fire3 = getImg("fire3");
		fire4 = getImg("fire4");
		fires = fire1;
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		f++;
		
		if(obj.bowl != null) {
			if(!obj.in.contains("green")) {
				bar.turnOn(obj.progress);
				
			}
			bar.paint(g);
			mixing = true;
			mix();
			
			
			if(bar.progress == 500) {
				((Bowl) obj).mix();
			}
			if(bar.progress == 800) {
				((Bowl) obj).add("green");
				fire = true;
				extinguished = false;
				
			}
			obj.progress = bar.progress;
		} else {
			mixing = false;
			bar.on = false;
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
	
	public void extinguish() {
		SimpleAudioTester.stopSound("smokealarm");
		obj.progress = 0;
		bar.on = false;
		extinguished = true;
		fire = false;
		
	}
	
	public void mix() {
		if(img.equals(notmix)) {
			img = mixer;
		}
	}
	

}
