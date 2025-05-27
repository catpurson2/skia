package skia;

import java.awt.Color;
import java.awt.Graphics;

public class Chef {
	
	int x, y;
	int vx, vy;
	
	public Chef() {
		x = 150;
		y = 300;
		vx = 0;
		vy = 0;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.green);
		
		x+=vx;
		y+=vy;
		g.fillOval(x, y, 80, 80);
		
	}
	
	public void setVX(int v) {
		vx = v;
	}
	
	public void setVY(int v) {
		vy = v;
	}

	

}
