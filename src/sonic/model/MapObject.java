package sonic.model;

import sonic.view.Drawable;

public abstract class MapObject extends Hittable implements HasSprite{

	private static final Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private Drawable sprite;

	public MapObject(Point position) {
		super(position);
	}

	@Override
	public Point normalAt(int side) {
		return normals[side];
	}

	@Override
	public Boolean handleGroundMonster(Point normal) {
		return false;
	}

	@Override
	public Boolean handleBlock(Point normal) {
		return false;
	}

	@Override
	public Boolean handleSlopeBlock(Point normal) {
		return false;
	}

	public Drawable getSprite(){
		return this.sprite;
	}

	public void setSprite(Drawable sprite) {
		this.sprite = sprite;
	}
}
