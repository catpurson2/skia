package skia;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Register {
	
	int width, height, x, y, c;
	BufferedImage img;
	float alpha = 1f;
	ArrayList<Plate> plates = new ArrayList<Plate>();
	int score = 0;
	SimpleAudioTester audio = new SimpleAudioTester();
	
	public Register(int x, int y) {
		width = 160;
		height = 80;
		this.x = x;
		this.y = y;
		
		img = getImg("register");
		
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		
		int i =0;
		g.drawImage(img, x, y, width, height, null);
		
		//draw plates
		for(int j =0; j< plates.size(); j++) {
			Plate obj = plates.get(j);
			if(obj != null && !obj.isDirty) {
				obj.paint(g, x+80, y-5-(int)(1-10*obj.alpha));
			}
		}
		
	}
	
	public BufferedImage getImg(String path) {
		
		try {
			return ImageIO.read(getClass().getResource("/imgs/" + path + ".png"));
		} catch(Exception e) {
			System.out.println("tuesday");
		}
		
		return null;
		
	}
		
		//take plate then increase score

	public void sell(Plate temp, SimpleAudioTester audio) {
		temp.sold = true;
		score += 40 + 5*(temp.in.size()-2);
		audio.playSound("cheering");
		plates.add(temp);
		
	}
	
	public void ew(Plate temp) {
		
		//take plate then decrease score
		temp.sold = true;
		audio.playSound("arghhh");
		plates.add(temp);
		score -= 10;
	}
	
	public Plate remove() {
		
		//remove plates from register
		for(int i = 0; i<plates.size(); i++) {
			if(plates.get(i) != null && plates.get(i).isDirty) {
				return plates.remove(i);
			}
		}
		
		return null;
	}
}
