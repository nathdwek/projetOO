package sonic.model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import sonic.controller.Controllable;

public class Model {

	private Map map;

	private LinkedList<SelfUpdatable> selfUpdatables;
	private LinkedList<Hittable> fixedHittables;
	private ArrayList<Hittable> movingHittables;
	private LinkedList<HasSprite> paintables;
	private Double deathLevel;

	private Sonic hero;
	private Point heroPosition;

	private static final double COLLISION_DISTANCE = 10;

	private Point playPanelCenter;
	private Point playPanelCenterSpeed;
	private Point playPanelCenterAcceleration;

	private final Double kX = 70.0;
	private final Double kY =20.0;
	private final Double lambdaX = 15.0;
	private final Double lambdaY = 6.0;

	private boolean gamePaused;

	private String mapXML;

	public Model(){
		mapXML = "src/sonic/map.xml";
		initialize();
	}

	public void initialize() {
		map = new Map(mapXML);
		selfUpdatables = map.getSelfUpdatables();
		movingHittables = map.getMovingHittables();
		fixedHittables = map.getfixedHittables();
		paintables = map.getPaintables();
		hero = map.getHero();
		deathLevel = map.getDeathLevel();

		playPanelCenter = hero.getPosition().copy();
		playPanelCenterSpeed = new Point(0,0);
		playPanelCenterAcceleration = new Point(0,0);
		heroPosition = hero.getPosition();
		gamePaused = false;
	}

	public boolean gameOver() {
		return (hero.isDead() || hero.getPosition().getY() < deathLevel);
	}

	public Controllable getControlledHero() {
		return hero;
	}
	public LinkedList<HasSprite> getPaintables(){
		return paintables;
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
		updatePlayPanelCenter(dT);
	}

	private void updatePlayPanelCenter(Double dT) {
		playPanelCenterAcceleration.setX(kX*(heroPosition.getX() - playPanelCenter.getX())-lambdaX*playPanelCenterSpeed.getX());
		playPanelCenterAcceleration.setY(kY*(heroPosition.getY() - playPanelCenter.getY())-lambdaY*playPanelCenterSpeed.getY());
		playPanelCenterSpeed.add(playPanelCenterAcceleration.times(dT));
		playPanelCenter.add(playPanelCenterSpeed.times(dT));
	}

	private void destroy(Hittable h) {
		selfUpdatables.remove(h);
		movingHittables.remove(h);
		fixedHittables.remove(h);
		paintables.remove(h);
	}

	private void handleSingleCollision(Hittable h1, Hittable h2, LinkedList<Hittable> toDestroy){
		Point[] normals=collision(h1,h2);
		if (normals.length!=0){
			if (h1.handleCollision(h2,normals[0])){
				toDestroy.add(h2);
			}
			if (h2.handleCollision(h1,normals[1])){
				toDestroy.add(h1);
			}
		}
	}

	private Point[] collision(Hittable h1, Hittable h2){
		Double dX=h1.getPosition().getX()-h2.getPosition().getX();
		Double dY=h1.getPosition().getY()-h2.getPosition().getY();

		Point[] normals =  new Point[0];

		if (dX>0){
			if (dY>0){
				if ((dX<h1.getSize(Hittable.LEFT)+h2.getSize(Hittable.RIGHT)) && Math.abs(dY-h1.getSize(Hittable.BOTTOM)-h2.getSize(Hittable.TOP))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(Hittable.BOTTOM),h2.normalAt(Hittable.TOP)};
				}
				else if((dY<h1.getSize(Hittable.BOTTOM)+h2.getSize(Hittable.TOP)) && Math.abs(dX-h1.getSize(Hittable.LEFT)-h2.getSize(Hittable.RIGHT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(Hittable.LEFT),h2.normalAt(Hittable.RIGHT)};
				}
			}
			else{
				if ((dX<h1.getSize(Hittable.LEFT)+h2.getSize(Hittable.RIGHT)) && Math.abs(-dY-h1.getSize(Hittable.TOP)-h2.getSize(Hittable.BOTTOM))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(Hittable.TOP),h2.normalAt(Hittable.BOTTOM)};
				}
				else if((-dY<h1.getSize(Hittable.TOP)+h2.getSize(Hittable.BOTTOM)) && Math.abs(dX-h1.getSize(Hittable.LEFT)-h2.getSize(Hittable.RIGHT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(Hittable.LEFT),h2.normalAt(Hittable.RIGHT)};
				}
			}
		}
		else{
			if (dY>0){
				if ((-dX<h1.getSize(Hittable.RIGHT)+h2.getSize(Hittable.LEFT)) && Math.abs(dY-h1.getSize(Hittable.BOTTOM)-h2.getSize(Hittable.TOP))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(Hittable.BOTTOM),h2.normalAt(Hittable.TOP)};
				}
				else if((dY<h1.getSize(Hittable.BOTTOM)+h2.getSize(Hittable.TOP)) && Math.abs(-dX-h1.getSize(Hittable.RIGHT)-h2.getSize(Hittable.LEFT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(Hittable.RIGHT),h2.normalAt(Hittable.LEFT)};
				}
			}
			else{
				if ((-dX<h1.getSize(Hittable.RIGHT)+h2.getSize(Hittable.LEFT)) && Math.abs(-dY-h1.getSize(Hittable.TOP)-h2.getSize(Hittable.BOTTOM))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(Hittable.TOP),h2.normalAt(Hittable.BOTTOM)};
				}
				else if((-dY<h1.getSize(Hittable.TOP)+h2.getSize(Hittable.BOTTOM)) && Math.abs(-dX-h1.getSize(Hittable.RIGHT)-h2.getSize(Hittable.LEFT))<COLLISION_DISTANCE){
					normals= new Point[] {h1.normalAt(Hittable.RIGHT),h2.normalAt(Hittable.LEFT)};
				}
			}
		}

		return normals;
	}

	public void setMap(String selectedMap) {
		this.mapXML = selectedMap;
	}
}