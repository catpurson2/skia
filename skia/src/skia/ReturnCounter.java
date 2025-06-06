package skia;

import java.awt.Graphics;
import java.util.ArrayList;

public class ReturnCounter extends Counter {
	
	ArrayList<Plate> plates = new ArrayList<Plate>();
	
	public ReturnCounter(int x, int y) {
		super(x,y,2);
		super.x = x;
		super.y = y;
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		//draw plates
		int i =0;
		for(Plate obj : plates) {
			if(obj!=null) {
				obj.paint(g, x+5, y+5+4*i);
				
				i++;
				if(i%2 == 0) {
					i*=2;
				}else {
					i*=-1;
				}
			}
			
		}
		
		
	}
	
	
}
