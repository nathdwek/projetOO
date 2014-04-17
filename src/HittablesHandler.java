import java.util.LinkedList;

public class HittablesHandler {
	
	private LinkedList<Hittable> hittables;
	private LinkedList<Hittable> movingHittables;
	
	
	public HittablesHandler(){}
	
	public void handleCollisions(){
		for (Hittable movingHittable : movingHittables){
			for (Hittable otherHittable : hittables){
				detectSingleCollision(movingHittable, otherHittable);
			}
		}
	}
	
	public void detectSingleCollision(Hittable h1, Hittable h2){
		if (h1.getHitbox().collides(h2.getHitbox())){
		//TODO (Maybe previous line too)
		}
	}
}
