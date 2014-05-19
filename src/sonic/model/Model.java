package sonic.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Model {

	private LinkedList<SelfUpdatable> selfUpdatables;
	private LinkedList<Hittable> fixedHittables;
	private ArrayList<Hittable> movingHittables;
	private LinkedList<HasSprite> paintables;
	private Double deathLevel;

	private Sonic hero;
	private Point heroPosition;

	private static final Double COLLISION_DISTANCE = 10.0;

	private Point playPanelCenter;
	private Point playPanelCenterSpeed;
	private Point playPanelCenterAcceleration;

	private static final Double KX = 50.0;
	private static final Double KY =20.0;
	private static final Double LAMBDA_X = 15.0;
	private static final Double LAMBDA_Y = 6.0;

	private String mapXML;

	public Model(){
		mapXML = "src/sonic/map.xml";
		initialize();
	}

	public void initialize() {
		Map map = new Map(mapXML);
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
		playPanelCenterAcceleration.setX(KX*(heroPosition.getX() - playPanelCenter.getX())-LAMBDA_X*playPanelCenterSpeed.getX());
		playPanelCenterAcceleration.setY(KY*(heroPosition.getY() - playPanelCenter.getY())-LAMBDA_Y*playPanelCenterSpeed.getY());
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