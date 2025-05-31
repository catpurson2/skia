package skia;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Sink {
	
	int width, height, x, y, c;
	BufferedImage img;
	ArrayList<Plate> cleanPlates = new ArrayList<Plate>(); 
	ArrayList<Plate> dirtyPlates = new ArrayList<Plate>(); 
	
	
	public Sink(int x, int y) {
		
		width = 160;
		height = 80;
		this.x = x;
		this.y = y;
		
		img = getImg("sink");
		
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		int i = 0;
		
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(img, x, y, width, height, null);
		for(Object obj: cleanPlates) {
			if(obj instanceof Plate) {
				obj.plate = getImg("plate");
				obj.paint(g, x+8, y+5);
			}
			
		}for(Object obj : dirtyPlates) {
			
			if(obj instanceof Plate) {
				obj.plate = getImg("sideplate");
				obj.paint(g, x+96 - 5*i, y+5);
			}
			
			i++;
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

}
