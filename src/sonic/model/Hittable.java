package sonic.model;

public abstract class Hittable{
	public static final int RIGHT=0;
	public static final int TOP=1;
	public static final int LEFT=2;
	public static final int BOTTOM=3;

	private Point position;

	public Hittable(Point position){
		this.position= position;
	}

	public Point getPosition(){
		return position;
	}

	public abstract Double getSize(int side);
	public abstract Point normalAt(int side);

	public abstract Boolean handleCollision(Hittable otherHittable,Point normal);

	public abstract Boolean handleGroundMonster(Point normal);
	public abstract Boolean handleSonic(Sonic sonic);
	public abstract Boolean handleBlock(Point normal);
	public abstract Boolean handleSlopeBlock(Point normal);

	public Boolean handleCoin(){
		return false;
	}

	public Boolean handleSpikes() {
		return false;
	}
}

