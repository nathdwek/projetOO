package sonic.model;
import sonic.view.Drawable;
public abstract class Hittable implements Drawable {
	private Point position;
	private Double[] Hitbox;

	public Hittable(Point position){
		this.position= position;

	}

	public Point getPosition(){
		return position;
	}
	public void setPosition(Point p){
		position = p;
	}

	public abstract void handleCollision(Hittable otherHittable,Point normal);

	public double getSize(int side){
		return Hitbox[side];
	}

}
