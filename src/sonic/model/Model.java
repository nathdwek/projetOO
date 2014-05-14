package sonic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import sonic.controller.Controllable;
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
	private static final double COLLISION_DISTANCE = 5;
	private Sonic hero;
	private Point playPanelCenter;
	private Point playPanelCenterSpeed;
	private Point playPanelCenterAcceleration;
	private Double kX = 70.0;
	private Double kY = 20.0;
	private Double lambdaX = 15.0;
	private Double lambdaY = 6.0;
	private boolean gamePaused = false;
	public Model(){

		/*Map map = new Map("src/map.xml");
		selfUpdatables = map.getSelfUpdatables();
		movingHittables = map.getMovingHittables();
		fixedHittables = map.getFixedHittables();
		drawables = map.getDrawables();*/

		hero =new Sonic(500, 150);
		AMonster m1 = new AMonster(100.0,200.0 ,50.0, 0.0);
		Block aB=new GroundBlock(-10000.0, 1000.0,0.0,125.0);


		Slope slope = new Slope(995.0,5000.0,125.0,-500.0, 125.0);
		Slope slope2 = new Slope(4999.0,10000.0,-500.0, 375.0, 20.0);
		Block aB2 = new GroundBlock(0.0,40.0,125.0,155.0);
		Block aB3 = new GroundBlock(300.0,340.0,125.0,155.0);
		Coin c1 = new Coin(360.0 , 140.0 );

		selfUpdatables =  new LinkedList<SelfUpdatable>(Arrays.asList(new SelfUpdatable[]{hero,m1}));
		movingHittables = new ArrayList<Hittable>(Arrays.asList(new Hittable[]{hero,m1}));

		fixedHittables = new LinkedList<Hittable>(Arrays.asList(new Hittable[]{aB,aB2,aB3, c1}));
		fixedHittables.addAll(slope.getBlocks());
		fixedHittables.addAll(slope2.getBlocks());

		drawables = new LinkedList<Drawable>(Arrays.asList(new Drawable[]{hero,m1,aB,aB2,aB3,c1}));
		drawables.addAll(slope.getBlocks());
		drawables.addAll(slope2.getBlocks());

		playPanelCenter = hero.getPosition().copy();
		playPanelCenterSpeed = new Point(0,0);
		playPanelCenterAcceleration = new Point(0,0);
	}

	public boolean gameOver() {
		return false; //hero.isDead();
	}

	public Controllable getHero() {
		return hero;
	}
	public LinkedList<Drawable> getDrawables(){
		return drawables;
	}
	public Point getPlayPanelCenter(){
		return playPanelCenter;
	}
	public Integer getCoins(){
		return hero.getCoins();
	}
	public boolean gamePaused() {
		return gamePaused;
	}
	public void setGamePaused(boolean b){
		gamePaused = b;
	}

	public void update(Double dT) {
		LinkedList<Hittable> toDestroy = new LinkedList<Hittable>();
		for (int i = 0; i<movingHittables.size();i++){
			for (Hittable fixedHittable : fixedHittables){
				handleSingleCollision(movingHittables.get(i), fixedHittable, toDestroy);
			}
			for (int j=i+1; j<movingHittables.size();j++){
				handleSingleCollision(movingHittables.get(i),movingHittables.get(j), toDestroy);
			}
		}
		for (SelfUpdatable s :selfUpdatables){
			s.selfUpdate(dT);
		}
		for (Hittable h : toDestroy){
			destroy(h);
		}

		Point heroPosition = hero.getPosition();
		playPanelCenterAcceleration.setX(kX*(heroPosition.getX() - playPanelCenter.getX())-lambdaX*playPanelCenterSpeed.getX());
		playPanelCenterAcceleration.setY(kY*(heroPosition.getY() - playPanelCenter.getY())-lambdaY*playPanelCenterSpeed.getY());
		playPanelCenterSpeed.add(playPanelCenterAcceleration.times(dT));
		playPanelCenter.add(playPanelCenterSpeed.times(dT));
	}

	private void destroy(Hittable h) {
		selfUpdatables.remove(h);
		movingHittables.remove(h);
		fixedHittables.remove(h);
		drawables.remove(h);
	}

	private void handleSingleCollision(Hittable h1, Hittable h2, LinkedList<Hittable> toDestroy){
		Point[] normals=collision(h1,h2);
		if (normals.length!=0){
			if (h1.handleCollision(h2,normals[1])){
				toDestroy.add(h1);
			}
			if (h2.handleCollision(h1,normals[0])){
				toDestroy.add(h2);
			}
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