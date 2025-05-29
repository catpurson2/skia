package skia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Progress {
	
	int x, y;
	static BufferedImage bar;
	int progress;
	
	public Progress(int x, int y) {
		this.x = x;
		this.y = y;
		progress = 0;
		
		if(bar == null) {
			bar = getImg("progress");
		}
	}
	
	public void paint(Graphics g) {
		
		
		if(progress != 2000) {
			g.fillRect(x+(int) (80.0/30*2), y+(int) (80.0/30*2), progress*((int) (80.0/30*28))/500, (int) (80.0/30*2));
			
			g.drawImage(bar, x, y, 80, 80, null);
			
			progress++;
			
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
