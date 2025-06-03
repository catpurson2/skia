package skia;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bowl extends Object {

	
	public Bowl() {
		super();
		
		this.bowl = getImg("bowl");
		// TODO Auto-generated constructor stub
		super.empty = false;
	}
	
	public void mix() {
		if(ingredients.size() > 0) {
			if(in.contains("milk") && in.contains("sugar") && !in.contains("egg") && !in.contains("flour")) {
				add("frosted");
			} else {
				add("batter");
			}
		}
	}
	
	public void bake() {
		if(ingredients.size() > 0) {
			
			if(in.contains("milk") && in.contains("egg") && in.contains("flour") && in.contains("sugar") && in.get(in.size()-1).equals("batter")) {
				add("cake");
			} else {
				add("green");
			}
		}
	}
	
	public void burn() {
		add("burnt");
		burnt = true;
	}


}
