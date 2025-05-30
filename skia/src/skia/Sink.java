package skia;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sink {
	
	int width, height, x, y, c;
	BufferedImage img;
	Object obj = new Object();
	
	public Sink(int x, int y) {
		width = 160;
		height = 80;
		this.x = x;
		this.y = y;
		
		img = getImg("sink");
		
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(img, x, y, width, height, null);
		if(obj != null && this.getClass().getName().equals("skia.Sink")) {
			obj.paint(g, x+8, y+5);
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
