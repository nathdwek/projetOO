package sonic.model;

import sonic.view.Drawable;

public abstract class Unit extends Hittable implements SelfUpdatable, Drawable {
	private Point speed;
	private Point acceleration;
	private static final Double miniRealSpeed = 5.0;

	public static Double getMiniRealSpeed(){
		return miniRealSpeed;
	}

	public Unit(Point position, Point speed){
		super(position);
		this.speed= speed;
		this.acceleration=new Point(0,0);
	}

	public Point getSpeed(){
		return speed;
	}

	public Point getAcceleration(){
		return acceleration;
	}

	public void accelerate(Point acceleration){
		speed.add(acceleration);
	}
	public void move(Point movement){
		getPosition().add(movement);
	}

	public void selfUpdate(Double dT){
		accelerate(this.acceleration.times(dT));
		move(this.speed.times(dT));
		stepReset();
	}

	public void stepReset(){
		this.acceleration.setZero();
	}

}
