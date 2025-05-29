package skia;

import java.awt.Color;
import java.awt.Graphics;

public class Chef {
	
	int x, y;
	int vx, vy;
	Object obj;
	int dir;
	
	public Chef() {
		x = 150;
		y = 300;
		vx = 0;
		vy = 0;
		obj = new Object(true, 0);
		dir = 0;
	}
	
	public void paint(Graphics g) {
		
		x+=vx;
		y+=vy;
		
		if(dir == 0) {
			g.setColor(Color.green);
			
			obj.paint(g, x, y-70);
			
			if(obj.bowl != null) {
				g.fillOval(x, y-35, 20, 20);
				g.fillOval(x+60, y-35, 20, 20);
			}
		} else if(dir == 90) {
			g.setColor(Color.black);
			
			obj.paint(g, x+70, y);
		} else if(dir == 180) {
			g.setColor(Color.white);
			
			obj.paint(g, x, y+70);
		} else if(dir == 270) {
			g.setColor(Color.blue);
			
			obj.paint(g, x-70, y);
		}
		
		g.fillOval(x, y, 80, 80);
		
	}
	
	public void setVX(int v) {
		vx = v;
	}
	
	public void setVY(int v) {
		vy = v;
	}

	

}
