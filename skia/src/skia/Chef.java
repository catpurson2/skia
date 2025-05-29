package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Chef {
	
	int x, y;
	int width,height;
	int vx, vy;
	Object obj;
	int dir;
	
	public Chef() {
		x = 150;
		y = 300;
		width = 60;
		height = 60;
		vx = 0;
		vy = 0;
		obj = new Object(true, 0);
		dir = 0;
	}
	
	public void paint(Graphics g) {
		
		
		if(dir == 0) {
			g.setColor(Color.green);
			
			obj.paint(g, x+10, y-60);
		} else if(dir == 90) {
			g.setColor(Color.black);
			
			obj.paint(g, x+60+20, y+10);
		} else if(dir == 180) {
			g.setColor(Color.white);
			
			obj.paint(g, x+10, y+60+20);
		} else if(dir == 270) {
			g.setColor(Color.blue);
			
			obj.paint(g, x-60, y+10);
		}
		
		g.fillOval(x, y, width,height);
		
	}
	
	public boolean collided(Counter character) {
		
		//represent eachj object as a rectange
		//then check if they are interesecting
		
		Rectangle main = new Rectangle(character.x,character.y,character.width,character.height);
		Rectangle thisObject = new Rectangle(x,y,width,height);
		
		return main.intersects(thisObject);
		
	}
	
	public void setVX(int v) {
		vx = v;
	}
	
	public void setVY(int v) {
		vy = v;
	}

	public void move() {
		x+=vx;
		y+=vy;
	}

}
