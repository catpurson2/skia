
package skia;

import java.awt.image.BufferedImage;

public class Plate extends Object {
	
	boolean isDirty = true;
	
	public Plate() {
		super.empty = false;
		super.plate = getImg("dirtyplate");
		// TODO Auto-generated constructor stub
	}
	
	public boolean isDirty() {
		return isDirty;
	}
	
	
	
}
