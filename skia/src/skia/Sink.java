package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Sink {
	
	int timer = 0;
	int width, height, x, y, c;
	BufferedImage img;
	ArrayList<Plate> cleanPlates = new ArrayList<Plate>(); 
	ArrayList<Plate> dirtyPlates = new ArrayList<Plate>(); 
	Progress bar = new Progress (x+100, y-20);
	boolean washing;
	Boolean fire;
	
	public Sink(int x, int y) {
		
		width = 160;
		height = 80;
		this.x = x;
		this.y = y;
		
		img = getImg("sink");
		
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		
		int i = 0; //counting variable to see how many plates there are
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(img, x, y, width, height, null);
		for(Object obj: cleanPlates) {
			if(obj instanceof Plate) {
				obj.plate = getImg("plate");
				obj.paint(g, x+8, y+5+4*i);
			}
			i++;
			//staggers the plates so you can visually
			//see them stacked on the sink
			if(i%2 == 0) {
				i*=2;
			}else {
				i*=-1;
			}
		}
		i = 0;
		for(Object obj : dirtyPlates) {
			//if the plate is dirty, shows it in the water
			if(obj instanceof Plate) {
				obj.plate = getImg("sideplate");
				obj.paint(g, x+96 - 5*i, y+5);
			}
			
			i++;
		}
		//creates the progress bar when washing
		if(washing) {
			g.setColor(Color.green);
			g.fillRect(x+80+(int) (80.0/30*2)+1, y-20+(int) (80.0/30*2), timer*((int) (80.0/18*26*3))/500, (int) (80.0/30*2)+3);
			g.drawImage(bar.bar, x+80, y-20, 80, 80, null);
			
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
