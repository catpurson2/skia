package skia;

public class Box extends Counter {
	
	String type;

	public Box(int x, int y, int t) {
		
		super(x, y, 0);
		//gives each box its specific ingredient
		switch(t) {
			case 0:
				type = "milk";
				break;
			case 1:
				type = "egg";
				break;
			case 2:
				type = "flour";
				break;
			case 3:
				type = "sugar";
				break;
			case 4:
				type = "strawberry";
				break;
		}
		
		img = getImg(type+"bin");
		
		// TODO Auto-generated constructor stub
	}
	
	public String getType() {
		return type;
	}

}
