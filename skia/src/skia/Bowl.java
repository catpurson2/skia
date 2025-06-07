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
		//combines into cake batter or frosting in mixer
		if(ingredients.size() > 0) {
			if(in.contains("milk") && in.contains("sugar") && !in.contains("egg") && !in.contains("flour")) {
				add("frosting");
				if(in.contains("strawberry")) {
					add("strawberryfrosting");
				}
			} else {
				add("batter");
				
				if(in.contains("strawberry")) {
					add("strawberrybatter");
				}
			}
		}
		
	}
	
	public void bake() {
		//combines into a cake/strawberry cake in oven
		if(ingredients.size() > 0) {
			if(in.contains("milk") && in.contains("egg") && in.contains("flour") && in.contains("sugar") && (in.get(in.size()-1).equals("batter") || in.get(in.size()-1).equals("strawberrybatter"))) {
				add("cake");
				if(in.contains("strawberry")) {
					add("strawberrycake");
				}
			} else { //if theres not enough ingredients ruins the cake
				add("green");
			}
		}
	}
	
	public void burn() {
		//burns when the oven catches on fire
		add("burnt");
		burnt = true;
	}


}
