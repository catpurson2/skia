package skia;

import java.util.ArrayList;

public class ReturnCounter extends Counter {
	
	ArrayList<Plate> plates = new ArrayList<Plate>();
	
	public ReturnCounter(int x, int y) {
		super(x,y,2);
		super.x = x;
		super.y = y;
		
	}
	
}
