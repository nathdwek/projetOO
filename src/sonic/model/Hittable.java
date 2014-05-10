package sonic.model;

public abstract class Hittable{
	private Point position;

	public Hittable(Point position){
		this.position= position;
	}

	public Point getPosition(){
		return position;
	}

	public abstract void handleCollision(Hittable otherHittable,Point normal);
	public abstract Double getSize(int side);
	public abstract Point normalAt(int side);
	public abstract String getType();
}

