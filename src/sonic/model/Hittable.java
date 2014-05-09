package sonic.model;
import java.awt.Graphics;

import sonic.view.Drawable;
public abstract class Hittable {
	private Point position;

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
	public abstract double getSize(int side);
	public abstract Point normalAt(int side);
	public abstract String getType();

	public void paint(Graphics g){}

}

