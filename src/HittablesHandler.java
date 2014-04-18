import java.util.LinkedList;
import java.util.ArrayList;

public class HittablesHandler {
	
	private LinkedList<Hittable> fixedHittables;
	private ArrayList<Hittable> movingHittables;
	
	
	public HittablesHandler(){}
	
	public void handleCollisions(){
		for (int i = 0; i<movingHittables.size();i++){
			for (Hittable fixedHittable : fixedHittables){
				detectSingleCollision(movingHittables.get(i), fixedHittable);
			}
			for (int j=i+1; j<movingHittables.size();j++){
				detectSingleCollision(movingHittables.get(i),movingHittables.get(j));
			}
		}
	}
	
	public void addFixedHittable(Hittable h){
		//Check if already in?
		fixedHittables.add(h);
	}
	
	public void addMovingHittable(Hittable h){
		//Check if already in?
		movingHittables.add(h);
	}
	
	public void destroyHittable(Hittable h){
		//h should appear only one time across fixedHittables AND movingHittables
		//return value if not found?
	}
	
	public void detectSingleCollision(Hittable h1, Hittable h2){
	}
}
