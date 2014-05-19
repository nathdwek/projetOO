package sonic.model;

import sonic.view.WinFlagSprite;

public class WinFlag extends MapObject {

	private static final Double[] hitbox = new Double[]{20.0,100.0,20.0,0.0};

	public WinFlag(Point position) {
		super(position);
		setSprite(new WinFlagSprite(this));
	}

	@Override
	public Double getSize(int side) {
		return hitbox[side];
	}

	@Override
	public Boolean handleCollision(Hittable otherHittable, Point normal) {
		return otherHittable.handleWinFlag();
	}

	@Override
	public Boolean handleSonic(Sonic sonic) {
		return false;
	}
}
