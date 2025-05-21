package skia;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Counter {
	
	int width, height, x, y, c;
	BufferedImage img;
	
	public Counter(int x, int y) {
		width = 80;
		height = 80;
		this.x = x;
		this.y = y;
		setImg("counter");
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g.drawImage(img, x, y, width, height, null);
		
		
		
		
	}
	
	public void setImg(String path) {
		
		try {
			img = ImageIO.read(getClass().getResource("/imgs/" + path + ".png"));
		} catch(Exception e) {
			System.out.println("tuesday");
		}
		
	}

}
