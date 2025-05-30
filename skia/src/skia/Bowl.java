package skia;

public class Bowl extends Object {

	public Bowl(Boolean bowl, int obj) {
		
		if (bowl) {
			this.bowl = getImg("bowl");
		} else {
			bowl = null;
		}
		
		if(obj == 0) {
			this.obj = null;
		}
		// TODO Auto-generated constructor stub
	}

}
