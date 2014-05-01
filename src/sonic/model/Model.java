package sonic.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Model {

	private LinkedList<SelfUpdatable> selfUpdatables;
	private LinkedList<Hittable> fixedHittables;
	private ArrayList<Hittable> movingHittables;
	private static final int RIGHT=0;
	private static final int TOP=1;
	private static final int LEFT=2;
	private static final int BOTTOM=3;
	private static final double COLLISION_DISTANCE = 1;

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
		Point[] normals=collision(h1,h2);
		if (normals.length!=0){
			h1.handleCollision(h2,normals[1]);
			h2.handleCollision(h1,normals[0]);
		}
	}

	private Point[] collision(Hittable h1, Hittable h2){
		Double dX=h1.getPosition().getX()-h2.getPosition().getX();
		Double dY=h1.getPosition().getY()-h2.getPosition().getY();

		Point[] normals =  new Point[0];

		if (dX>0){
			if (dY>0){
				if ((dX<h1.getSize(LEFT)+h2.getSize(RIGHT)) && Math.abs(dY-h1.getSize(BOTTOM)-h2.getSize(TOP))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(BOTTOM),h2.normalAt(TOP)};
				}
				else if((dY<h1.getSize(BOTTOM)+h2.getSize(TOP)) && Math.abs(dX-h1.getSize(LEFT)-h2.getSize(RIGHT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(LEFT),h2.normalAt(RIGHT)};
				}
			}
			else{
				if ((dX<h1.getSize(LEFT)+h2.getSize(RIGHT)) && Math.abs(dY-h1.getSize(TOP)-h2.getSize(BOTTOM))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(TOP),h2.normalAt(BOTTOM)};
				}
				else if((dY<h1.getSize(TOP)+h2.getSize(BOTTOM)) && Math.abs(dX-h1.getSize(LEFT)-h2.getSize(RIGHT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(LEFT),h2.normalAt(RIGHT)};
				}
			}
		}
		else{
			if (dY>0){
				if ((dX<h1.getSize(RIGHT)+h2.getSize(LEFT)) && Math.abs(dY-h1.getSize(BOTTOM)-h2.getSize(TOP))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(BOTTOM),h2.normalAt(TOP)};
				}
				else if((dY<h1.getSize(BOTTOM)+h2.getSize(TOP)) && Math.abs(dX-h1.getSize(RIGHT)-h2.getSize(LEFT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(RIGHT),h2.normalAt(LEFT)};
				}
			}
			else{
				if ((dX<h1.getSize(RIGHT)+h2.getSize(LEFT)) && Math.abs(dY-h1.getSize(TOP)-h2.getSize(BOTTOM))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(TOP),h2.normalAt(BOTTOM)};
				}
				else if((dY<h1.getSize(TOP)+h2.getSize(BOTTOM)) && Math.abs(dX-h1.getSize(RIGHT)-h2.getSize(LEFT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(RIGHT),h2.normalAt(LEFT)};
				}
			}
		}

		return normals;
	}
}