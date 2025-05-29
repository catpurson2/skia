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
	int size;
	
	public Chef() {
		x = 150;
		y = 300;
		width = 80;
		height = 80;
		size = 60;
		vx = 0;
		vy = 0;
		obj = new Object(true, 0);
		dir = 0;
	}
	
	public void paint(Graphics g) {
		
		
		if(dir == 0) {
			g.setColor(Color.green);
			
			obj.paint(g, x+5, y-50);
			
			if(obj.bowl != null) {
				g.fillOval(x+5, y-20, 20, 20);
				g.fillOval(x+55, y-20, 20, 20);
			}
		} else if(dir == 90) {
			g.setColor(Color.black);
			
			obj.paint(g, x+60, y+5);
			
			if(obj.bowl != null) {
				g.fillOval(x+80, y+5, 20, 20);
				g.fillOval(x+80, y+55, 20, 20);
			}
		} else if(dir == 180) {
			g.setColor(Color.white);
			
			obj.paint(g, x+5, y+60);
			
			if(obj.bowl != null) {
				g.fillOval(x+5, y+80, 20, 20);
				g.fillOval(x+55, y+80, 20, 20);
			}
		} else if(dir == 270) {
			g.setColor(Color.blue);
			
			obj.paint(g, x-50, y+5);
			
			if(obj.bowl != null) {
				g.fillOval(x-20, y+5, 20, 20);
				g.fillOval(x-20, y+55, 20, 20);
			}
		}
		
		g.fillOval(x+10, y+10, size, size);
		
	}
	
	public boolean collided(Counter character) {
		
		//represent eachj object as a rectange
		//then check if they are interesecting
		
		Rectangle main = new Rectangle(character.x,character.y,character.width,character.height);
		Rectangle thisObject = new Rectangle(x+10, y+10, size, size);
		
		return main.intersects(thisObject);
		
	}
	
	public boolean collided(Sink character) {
		
		//represent eachj object as a rectange
		//then check if they are interesecting
		//System.out.println(((80-size)/2) + " " + size);
		
		Rectangle main = new Rectangle(character.x,character.y,character.width,character.height);
		Rectangle thisObject = new Rectangle(x+10, y+10, size, size);
		
		return main.intersects(thisObject);
		
	}
	
	public boolean collided(Register character) {
		
		//represent eachj object as a rectange
		//then check if they are interesecting
		
		Rectangle main = new Rectangle(character.x, character.y, character.width, character.height);
		Rectangle thisObject = new Rectangle(x+10, y+10, size, size);
		
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
