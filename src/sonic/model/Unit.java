package sonic.model;

import sonic.view.Drawable;

public abstract class Unit extends Hittable implements SelfUpdatable, HasSprite {
	private Point speed;
	private Point acceleration;
	private Boolean floor;

	private Drawable sprite;
	private static final Double miniRealSpeed = 10.0;
	private static final Double GRAVITY = -1000.0;

	public static Double getMiniRealSpeed(){
		return miniRealSpeed;
	}

	public static Double getGravity(){
		return GRAVITY;
	}

	public Unit(Point position, Point speed){
		super(position);
		this.speed= speed;
		this.acceleration=new Point(0,0);
		this.floor = false;
	}

	public Point getSpeed(){
		return speed;
	}

	public Point getAcceleration(){
		return acceleration;
	}

	public Boolean getFloor(){
		return floor;
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

	public Boolean handleBlock(Point normal) {
		floor = floor || normal.getY()>=1;
		if (normal.getY()*getSpeed().getY()<0){
			getSpeed().setY(0);
		}
		return false;
	}

	public Boolean handleSlopeBlock(Point normal) {
		Point s = getSpeed();
		if (normal.getY()>=1){
			floor = true;
			Double effect = normal.getX()*s.getX();
			if (s.getY() < effect){
				s.setY(effect);
			}
		}
		else if (normal.getY()*getSpeed().getY()<0){
			getSpeed().setY(0);
		}
		return false;
	}

	public void stepReset(){
		this.acceleration.setZero();
		this.floor = false;
	}

	public Drawable getSprite(){
		return this.sprite;
	}

	public void setSprite(Drawable sprite){
		this.sprite = sprite;
	}
}
