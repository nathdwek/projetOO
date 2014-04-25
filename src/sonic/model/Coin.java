package sonic.model;

public class Coin extends Bonus{

	public Coin(Point position) {
		super(position);

	}


	public void handleCollision(Hittable otherHittable, Point normal) {
		if (otherHittable instanceof Sonic){
			//pièce disparait
		}
	}


}
