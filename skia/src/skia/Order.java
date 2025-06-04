package skia;

import java.awt.Graphics;

public class Order {
	
	int cake;
	int frosting;
	int topping;
	
	public Order() {
		
		if(Math.random() < 0.5) {
			cake = 0;
		} else {
			cake = 1;
		}
		
		if(Math.random() < 0.5) {
			frosting = 0;
		} else {
			frosting = 1;
		}
		
		if(Math.random() < 0.5) {
			topping = 0;
		} else {
			topping = 1;
		}
		
	}
	
	public void paint(Graphics g) {
		
	}

}
