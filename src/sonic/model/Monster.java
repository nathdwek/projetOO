package sonic.model;

public abstract class Monster extends Hittable implements SelfUpdatable {
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

	public void move(Point movement){
		getPosition().add(movement);
	}
}
