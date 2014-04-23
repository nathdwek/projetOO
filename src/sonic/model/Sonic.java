package sonic.model;

public class Sonic implements selfUpdatable {

	public void accelerateRight() {
		if (vx<vMaxX){
			decelarating= false;
		}
		
	}

	public void accelerateLeft() {
		if (vx>-vMaxX){
			
		}
		
	}

	public void jump() {
		if (vy<vMaxY){
			
		}
		
	}

	public void beBall() {
		vMaxX= 
		isBall=true;
		
	}

	public void startDecelerateRight() {
		decelerating=true;
			
		
	}
	

}
