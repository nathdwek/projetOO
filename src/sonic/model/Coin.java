package sonic.model;

import sonic.view.Drawable;
import sonic.view.CoinSprite;

public class Coin extends Hittable implements HasSprite{

	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private static Double[] hitbox= new Double[]{10.0,10.0,10.0,10.0};
	private Drawable sprite;

	public Coin(double posX, double posY) {
		super(new Point(posX, posY));
		this.sprite = new CoinSprite(this);

	}

	@Override
	public Boolean handleCollision(Hittable otherHittable, Point normal) {
		return otherHittable.handleCoin();
	}

	@Override
	public Double getSize(int side) {
		return hitbox[side];
	}

	@Override
	public Point normalAt(int side) {
		return normals[side];
	}

	public static Double getWidth(){
		return hitbox[Hittable.LEFT]+hitbox[Hittable.RIGHT];
	}

	public static Double getHeight() {
		return hitbox[Hittable.TOP]+hitbox[Hittable.BOTTOM];
	}

	public Drawable getSprite(){
		return this.sprite;
	}

	@Override
	public void setSprite(Drawable sprite) {
		this.sprite = sprite;
	}

	@Override
	public Boolean handleGroundMonster(Point normal) {
		return false;
	}

	@Override
	public Boolean handleSonic(Sonic sonic) {
		return true;
	}

	@Override
	public Boolean handleSlopeBlock(Point normal) {
		System.out.println("Coin and Block just collided?");
		return false;
	}

	@Override
	public Boolean handleBlock(Point normal) {
		System.out.println("Coin and Block just collided?");
		return false;
	}
}
