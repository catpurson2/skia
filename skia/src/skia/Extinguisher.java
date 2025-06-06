package skia;

import java.awt.image.BufferedImage;

public class Extinguisher extends Object {
	
	BufferedImage up = getImg("extinguisher180");
	BufferedImage right = getImg("extinguisher90");
	BufferedImage down = getImg("extinguisher0");
	BufferedImage left = getImg("extinguisher270");
	
	public Extinguisher() {
		
		super.plate = up;
		empty = false;
		
		
	}
	
}
