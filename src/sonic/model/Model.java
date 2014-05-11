package sonic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import sonic.controller.Controllable;
import sonic.controller.Controller;
import sonic.view.Drawable;

public class Model {

	private LinkedList<SelfUpdatable> selfUpdatables;
	private LinkedList<Hittable> fixedHittables;
	private ArrayList<Hittable> movingHittables;
	private LinkedList<Drawable> drawables;
	private static final int RIGHT=0;
	private static final int TOP=1;
	private static final int LEFT=2;
	private static final int BOTTOM=3;
	private static final double COLLISION_DISTANCE = 1;
	private Sonic hero;

	public Model(){

		hero =new Sonic(500, 150);
		AMonster m1 = new AMonster(100.0,200.0 ,50.0, 0.0);
		Block aB=new Block(-1000.0, 1000.0,0.0,125.0);
		Block aB2 = new Block(0.0,40.0,125.0,155.0);
		Block aB3 = new Block(300.0,340.0,125.0,155.0);
		Coin c1 = new Coin(360.0 , 140.0 );

		selfUpdatables =  new LinkedList<SelfUpdatable>(Arrays.asList(new SelfUpdatable[]{hero,m1}));
		movingHittables = new ArrayList<Hittable>(Arrays.asList(new Hittable[]{hero,m1}));

		fixedHittables = new LinkedList<Hittable>(Arrays.asList(new Hittable[]{aB,aB2,aB3}));

		drawables = new LinkedList<Drawable>(Arrays.asList(new Drawable[]{hero,m1,aB,aB2,aB3,c1}));

		fixedHittables = new LinkedList<Hittable>(Arrays.asList(new Hittable[]{aB,aB2,aB3, c1}));
	}

	public boolean gameOver() {
		return false;
	}

	public Controllable getHero() {
		return hero;
	}
	public LinkedList<Drawable> getDrawables(){
		return drawables;
	}

	public void update(Double dT) {
		for (int i = 0; i<movingHittables.size();i++){
			for (Hittable fixedHittable : fixedHittables){
				handleSingleCollision(movingHittables.get(i), fixedHittable);
				if (fixedHittable.getType() =="DESTROYED"){
					fixedHittables.remove(fixedHittable);
					drawables.remove(fixedHittable);
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