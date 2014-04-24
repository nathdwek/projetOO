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
				handleSingleCollision(movingHittables.get(i), fixedHittable);
			}
			for (int j=i+1; j<movingHittables.size();j++){
				handleSingleCollision(movingHittables.get(i),movingHittables.get(j));
			}
		}
		for (SelfUpdatable s :selfUpdatables){
			s.selfUpdate();
		}
	}

	private void handleSingleCollision(Hittable h1, Hittable h2){
		if (false){//TODOOOOOOOOO
			h1.handleCollision(h2);
			h2.handleCollision(h1);
		}
	}

}
