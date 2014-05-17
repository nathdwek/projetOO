package sonic.model;

import sonic.view.CrabSprite;


public class GroundMonster extends Unit {

	private static Double[] hitbox = new Double[]{20.0,20.0,20.0,20.0};
	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};

	public GroundMonster(Point position, Point speed) {
		super(position, speed);
		setSprite(new CrabSprite(this));
	}

	public GroundMonster(double x, double y, double vX, double vY) {
		this(new Point(x,y), new Point(vX,vY));
	}

	@Override
	public Boolean handleCollision(Hittable otherHittable, Point normal) {
		return otherHittable.handleGroundMonster(normal);
	}

	public Boolean handleBlock(Point normal) {
		if (normal.getX()*getSpeed().getX()<0){
			Point s=getSpeed();
			Double vX = s.getX();
			s.setX(vX+2*normal.getX()*Math.abs(vX));

		}
		return super.handleBlock(normal);
	}

	public void selfUpdate(Double dT) {
		if (getFloor()){
			Point s=getSpeed();
			if (s.getY()<0){
				s.setY(0);
			}
		}
		else{
			getAcceleration().setY(Unit.getGravity());
		}
		super.selfUpdate(dT);
	}

	public Double getSize(int side) {
		return hitbox[side];
	}

	public Point normalAt(int side) {
		return normals[side];
	}

	@Override
	public Boolean handleGroundMonster(Point normal) {
		return false;
	}

	@Override
	public Boolean handleSonic(Point normal, Sonic sonic) {
		return sonic.isBall();
	}

}
