package sonic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import sonic.controller.Controller;

public class Model {

	private Controller controller;

	private LinkedList<SelfUpdatable> selfUpdatables;
	private LinkedList<Hittable> fixedHittables;
	private ArrayList<Hittable> movingHittables;
	private static final int RIGHT=0;
	private static final int TOP=1;
	private static final int LEFT=2;
	private static final int BOTTOM=3;
	private static final double COLLISION_DISTANCE = 1;
	private Sonic sonic;

	public Model(Controller c){

		controller=c;

		sonic =new Sonic(0, 20);
		Block aB=new Block(-100.0, 100.0,-10.0,10.0);
		Block aB2 = new Block(20.0,40.0,10.0,50.0);
		Block aB3 = new Block(-20.0,-40.0,10.0,50.0);

		selfUpdatables =  new LinkedList<SelfUpdatable>(Arrays.asList(new SelfUpdatable[]{sonic}));
		movingHittables = new ArrayList<Hittable>(Arrays.asList(new Hittable[]{sonic}));

		fixedHittables = new LinkedList<Hittable>(Arrays.asList(new Hittable[]{aB,aB2,aB3}));
	}

	public boolean gameOver() {
		return false;
	}
	public Sonic getSonic() {
		return sonic;
	}

	public void update(Double dT) {
		for (int i = 0; i<movingHittables.size();i++){
			for (Hittable fixedHittable : fixedHittables){
				handleSingleCollision(movingHittables.get(i), fixedHittable);
				if (fixedHittable.getType() =="DESTROYED"){
					fixedHittables.remove(fixedHittable);
				}

			}
			for (int j=i+1; j<movingHittables.size();j++){
				handleSingleCollision(movingHittables.get(i),movingHittables.get(j));
				if (movingHittables.get(i).getType()=="DESTROYED"){
					movingHittables.remove(movingHittables.get(i));
					selfUpdatables.remove(movingHittables.get(i));
				}
				if (movingHittables.get(j).getType()=="DESTROYED"){
					movingHittables.remove(movingHittables.get(j));
					selfUpdatables.remove(movingHittables.get(j));
				}

			}
		}
		for (SelfUpdatable s :selfUpdatables){
			s.selfUpdate(dT);
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
				if ((dX<h1.getSize(LEFT)+h2.getSize(RIGHT)) && Math.abs(-dY-h1.getSize(TOP)-h2.getSize(BOTTOM))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(TOP),h2.normalAt(BOTTOM)};
				}
				else if((-dY<h1.getSize(TOP)+h2.getSize(BOTTOM)) && Math.abs(dX-h1.getSize(LEFT)-h2.getSize(RIGHT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(LEFT),h2.normalAt(RIGHT)};
				}
			}
		}
		else{
			if (dY>0){
				if ((-dX<h1.getSize(RIGHT)+h2.getSize(LEFT)) && Math.abs(dY-h1.getSize(BOTTOM)-h2.getSize(TOP))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(BOTTOM),h2.normalAt(TOP)};
				}
				else if((dY<h1.getSize(BOTTOM)+h2.getSize(TOP)) && Math.abs(-dX-h1.getSize(RIGHT)-h2.getSize(LEFT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(RIGHT),h2.normalAt(LEFT)};
				}
			}
			else{
				if ((-dX<h1.getSize(RIGHT)+h2.getSize(LEFT)) && Math.abs(-dY-h1.getSize(TOP)-h2.getSize(BOTTOM))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(TOP),h2.normalAt(BOTTOM)};
				}
				else if((-dY<h1.getSize(TOP)+h2.getSize(BOTTOM)) && Math.abs(-dX-h1.getSize(RIGHT)-h2.getSize(LEFT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(RIGHT),h2.normalAt(LEFT)};
				}
			}
		}

		return normals;
	}
}