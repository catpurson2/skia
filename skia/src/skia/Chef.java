package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Chef {
	
	int x, y;
	int width,height;
	int vx, vy;
	Object obj;
	int dir;
	int size;
	String chara = Runner.name;
	BufferedImage chef;
	BufferedImage chef0 = getImg("0");
	BufferedImage chef90 = getImg("90");
	BufferedImage chef180 = getImg("180");
	BufferedImage chef270 = getImg("270");
	BufferedImage leftHand;
	BufferedImage rightHand;
	BufferedImage upLeft = getImg("left0");
	BufferedImage upRight = getImg("right0");
	BufferedImage downLeft = getImg("left180");
	BufferedImage downRight = getImg("right180");
	BufferedImage leftLeft = getImg("left270");
	BufferedImage leftRight = getImg("right270");
	BufferedImage rightLeft = getImg("left90");
	BufferedImage rightRight = getImg("right90");
	
	public Chef() {
		x = 150;
		y = 300;
		width = 60;
		height = 67;
		size = 60;
		vx = 0;
		vy = 0;

		

		obj = new Object();

		dir = 0;
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(chef, x, y, width, height, null);
		//makes sure chef is actually facing the right direction when moving
		//dir == 0 is when facing up
		if(dir == 0) { 
			obj.paint(g, x-5, y-60);
			if(obj instanceof Extinguisher) {
				((Extinguisher) obj).plate = ((Extinguisher) obj).up; ///extinguisher is only object with direction
			}
			
			if(obj.bowl != null || obj.plate != null) {
				leftHand = upLeft;
				rightHand = upRight;
				g.drawImage(leftHand, x-5, y-30, 20, 20, null);
				g.drawImage(rightHand, x+45, y-30, 20, 20, null);
			}
			chef = chef0;
			height = 67;
			width = 60;
			
		} else if(dir == 90) { //facing right
			if(obj instanceof Extinguisher) {
				((Extinguisher) obj).plate = ((Extinguisher) obj).right;
			}
			obj.paint(g, x+50, y-5);
			
			if(obj.bowl != null || obj.plate != null) {
				leftHand = rightLeft;
				rightHand = rightRight;
				g.drawImage(leftHand, x+70, y-5, 20, 20, null);
				g.drawImage(rightHand, x+70, y+45, 20, 20, null);
			}
			chef = chef90;
			width = 67;
			height = 60;
		} else if(dir == 180) { //facing down
			if(obj instanceof Extinguisher) {
				((Extinguisher) obj).plate = ((Extinguisher) obj).down;
			}
			obj.paint(g, x-5, y+50);
			
			if(obj.bowl != null || obj.plate != null) {
				leftHand = downLeft;
				rightHand = downRight;
				g.drawImage(leftHand, x-5, y+70, 20, 20, null);
				g.drawImage(rightHand, x+45, y+70, 20, 20, null);
			}
			chef = chef180;
			height = 67;
			width = 60;
		} else if(dir == 270) { //facing left
			g.setColor(Color.blue);
			if(obj instanceof Extinguisher) {
				((Extinguisher) obj).plate = ((Extinguisher) obj).left;
			}
			obj.paint(g, x-60, y-5);
			
			if(obj.bowl != null || obj.plate != null) {
				leftHand = leftLeft;
				rightHand = leftRight;
				g.drawImage(leftHand, x-30, y-5, 20, 20, null);
				g.drawImage(rightHand, x-30, y+45, 20, 20, null);
			}
			chef = chef270;
			width = 67;
			height = 60;
		}

	}
	
	public boolean touching(Counter character) {
		
		//represent each object as a rectangle
		//then check if they are intersecting
		//checks for direction bc wanting to know
		//what the front of the chef is looking at
		
		Rectangle main = new Rectangle(character.x,character.y,character.width,character.height);
		Rectangle thisObject;
		if(dir == 0) {
			thisObject = new Rectangle(x,y-20,60,20);
			
		}else if(dir == 90) {
			thisObject = new Rectangle(x+width,y,20,60);
		}else if(dir == 180) {
			thisObject = new Rectangle(x,y+height,60,20);
		}else {
			thisObject = new Rectangle(x-20,y,20,60);
		}
		
		return main.intersects(thisObject);
		
	}
	public boolean touching(Sink character) {
		
		//represent each object as a rectangle
		//then check if they are intersecting
		//overloaded to work with sinks as well
		//checks for direction bc wanting to know
		//what the front of the chef is looking at
		
		Rectangle main = new Rectangle(character.x,character.y,character.width,character.height);
		Rectangle thisObject;
		if(dir == 0) {
			thisObject = new Rectangle(x,y-20,60,20);
			
		}else if(dir == 90) {
			thisObject = new Rectangle(x+width,y,20,60);
		}else if(dir == 180) {
			thisObject = new Rectangle(x,y+height,60,20);
		}else {
			thisObject = new Rectangle(x-20,y,20,60);
		}
		
		return main.intersects(thisObject);
		
	}
	
	public boolean touching(Register character) {
		
		//represent each object as a rectangle
		//then check if they are intersecting
		//overloaded to work with registers as well
		//checks for direction bc wanting to know
		//what the front of the chef is looking at
		
		Rectangle main = new Rectangle(character.x,character.y,character.width,character.height);
		Rectangle thisObject;
		if(dir == 0) {
			thisObject = new Rectangle(x,y-20,60,20);
			
		}else if(dir == 90) {
			thisObject = new Rectangle(x+width,y,20,60);
		}else if(dir == 180) {
			thisObject = new Rectangle(x,y+height,60,20);
		}else {
			thisObject = new Rectangle(x-20,y,20,60);
		}
		
		return main.intersects(thisObject);
		
	}
	
	public boolean collided(Counter character) {
		
		//represent each object as a rectangle
		//then check if they are intersecting
		
		Rectangle main = new Rectangle(character.x,character.y,character.width,character.height);
		Rectangle thisObject = new Rectangle(x, y, size, size);
		
		return main.intersects(thisObject);
		
	}
	
	public boolean collided(Sink character) {
		
		//represent each object as a rectangle
		//then check if they are intersecting
		
		Rectangle main = new Rectangle(character.x,character.y,character.width,character.height);
		Rectangle thisObject = new Rectangle(x, y, size, size);
		
		return main.intersects(thisObject);
		
	}
	
	public boolean collided(Register character) {
		
		//represent each object as a rectangle
		//then check if they are intersecting
		
		Rectangle main = new Rectangle(character.x, character.y, character.width, character.height);
		Rectangle thisObject = new Rectangle(x, y, size, size);
		
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

	public BufferedImage getImg(String path) {
		//helper method to draw things easier
		try {
			return ImageIO.read(getClass().getResource("/character/" + chara + path + ".png"));
		} catch(Exception e) {
			System.out.println(chara);
		}
		
		return null;
		
	}
	
}
