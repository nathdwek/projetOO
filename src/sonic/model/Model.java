package sonic.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Model {
	
	private LinkedList<SelfUpdatable> selfUpdatables;
	private LinkedList<Hittable> fixedHittables;
	private ArrayList<Hittable> movingHittables;

	public boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	public void update() {
		for (int i = 0; i<movingHittables.size();i++){
			for (Hittable fixedHittable : fixedHittables){
				detectSingleCollision(movingHittables.get(i), fixedHittable);
			}
			for (int j=i+1; j<movingHittables.size();j++){
				detectSingleCollision(movingHittables.get(i),movingHittables.get(j));
			}
		}
		for (SelfUpdatable s :selfUpdatables){
			s.selfUpdate();	
		}	
	}
	
	public void detectSingleCollision(Hittable h1, Hittable h2){
		int collisionAngle=h1.getHitbox().collides(h2.getHitbox();
		if (collisionAngle != -1){
			h1.handleCollision(h2,collisionAngle);
			h2.handleCollision(h1, collisionAngle);
		}
	}

}
