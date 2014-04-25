package sonic.model;
import sonic.view.Drawable;
public abstract class Hittable implements Drawable {
	private Point position;
	
	public Hittable(Point position){
		this.position= position;
		
	}
	
	public Point getPosition(){
		return position;
	}

	public void handleCollision(Hittable otherHittable);

}
