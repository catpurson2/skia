package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Progress {
	
	int x, y;
	static BufferedImage bar;
	int progress;
	Boolean on;
	
	public Progress(int x, int y) {
		this.x = x;
		this.y = y;
		progress = 0;
		on = false;
		
		if(bar == null) {
			bar = getImg("progress");
		}
	}
	
	public Boolean paint(Graphics g) {
		//changes colors at certain values of progress
		//to tell you how close it is to being done/burning
		if(progress <= 500) {
			
			if(progress < 250) {
				g.setColor(Color.orange);
			} else {
				g.setColor(Color.yellow);
			}
			
			g.fillRect(x+(int) (80.0/30*2)+1, y+(int) (80.0/30*2), progress*((int) (80.0/30*26))/500, (int) (80.0/30*2)+3);
			
			g.drawImage(bar, x, y, 80, 80, null);
			
			progress++;
			return false;
			
		} else if (progress <= 650) {
			
			g.setColor(Color.green);
			g.fillRect(x+(int) (80.0/30*2)+1, y+(int) (80.0/30*2), (int) (80.0/30*26), (int) (80.0/30*2)+3);
			
			
			g.drawImage(bar, x, y, 80, 80, null);
			progress++;
			return false;
			
		} else if (progress <= 800) {
			g.setColor(Color.red);
			
			g.fillRect(x+(int) (80.0/30*2)+1, y+(int) (80.0/30*2), (int) (80.0/30*26), (int) (80.0/30*2)+3);
			
			g.drawImage(bar, x, y, 80, 80, null);
			progress++;
			return false;
			
		} else {
			
			
			
			g.setColor(Color.red);
			g.fillRect(x+(int) (80.0/30*2)+1, y+(int) (80.0/30*2), (int) (80.0/30*26), (int) (80.0/30*2)+3);
			
			g.drawImage(bar, x, y, 80, 80, null);
			return true;
		}
	}
	
	public void turnOn(int progress) {
		if(!on) {
			this.progress = progress;
			on = true;
		}
		//System.out.println("didnt work");
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
