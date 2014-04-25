package sonic.model;

import sonic.view.Drawable;

public abstract class Bonus extends Hittable {

	public Bonus(Point position){
		super(position);
	}

	public void draw() {
	}

	public void handleCollision(Hittable otherHittable) {
		if (otherHittable instanceof Sonic){
			//disparait
		}
	}

}
