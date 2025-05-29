package skia;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Counter {
	
	int width, height, x, y, c;
	Object obj = new Object(false, 0);
	BufferedImage img;
	static BufferedImage metal;
	static BufferedImage wood;
	
	public Counter(int x, int y, int img) {
		width = 80;
		height = 80;
		this.x = x;
		this.y = y;
		
		if(metal == null && wood == null) {
			metal = getImg("metal");
			wood = getImg("wood");
		}
		
		if(img == 0) {
			this.img = metal;
		} else if(img == 1) {
			this.img = wood;
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(img, x, y, width, height, null);
		if(obj != null) {
			obj.paint(g, x+10, y+10);
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
