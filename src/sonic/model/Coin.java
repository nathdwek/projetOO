package sonic.model;

import sonic.view.CoinSprite;

public class Coin extends MapObject{

	private static final Double[] hitbox= new Double[]{10.0,10.0,10.0,10.0};

	public Coin(double posX, double posY) {
		super(new Point(posX, posY));
		setSprite(new CoinSprite(this));

	}

	@Override
	public Boolean handleCollision(Hittable otherHittable, Point normal) {
		return otherHittable.handleCoin();
	}

	@Override
	public Double getSize(int side) {
		return hitbox[side];
	}


	public static Double getWidth(){
		return hitbox[Hittable.LEFT]+hitbox[Hittable.RIGHT];
	}

	public static Double getHeight() {
		return hitbox[Hittable.TOP]+hitbox[Hittable.BOTTOM];
	}

	@Override
	public Boolean handleSonic(Sonic sonic) {
		return true;
	}
}
