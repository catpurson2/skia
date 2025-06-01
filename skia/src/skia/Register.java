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
		for(Plate obj: plates) {
			if(obj != null && !obj.isDirty) {
				obj.paint(g, x+80, y-5-(int)(1-10*obj.alpha));
			}else if(obj != null && obj.isDirty) {
				obj.paint(g, x+165, y+5+4*i);
				
				i++;
				if(i%2 == 0) {
					i*=2;
				}else {
					i*=-1;
				}
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
	
	public void sell(Plate temp) {
		temp.sold = true;
		plates.add(temp);
		
		score ++;
	}
	
}
