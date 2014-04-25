package sonic.model;
import sonic.view.Drawable;

public abstract class Monster extends Hittable implements SelfUpdatable  {
	private Point speed;
	
	public Monster(Point position, Point speed){
		super(position);
		this.speed= speed;
	}
	
	public Point getSpeed(){
		return speed;
	}
	public void setSpeed(Point s){
		speed=s;
	}
	
	public void handleCollision(Hittable otherHittable) {
		// TODO Auto-generated method stub

	}

}
