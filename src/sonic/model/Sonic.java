package sonic.model;

import sonic.controller.Controllable;
import sonic.view.SonicSprite;


public class Sonic extends Unit implements Controllable {

	private Integer life;
	private Integer coins;

	private Boolean isBall;

	private Double maxXSpeed;
	private static final Double normalMaxXSpeed=300.0;
	private static final Double ballMaxXSpeed=600.0;

	private Double naturalXBrake = 1000.0;
	private Integer acceleratingX;
	private Double maxXAcceleration = 2000.0;

	private Double maxYUpSpeed = 400.0;
	private Double maxYDownSpeed = -800.0;

	private Boolean blockedRight;
	private Boolean blockedLeft;
	private Boolean falling;

	private Integer acceleratingY;
	private Double maxYAcceleration = 2000.0;

	private Double[] hitbox = new Double[]{20.0,20.0,20.0,20.0};
	private Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};

	private boolean isDead;

	public Sonic(int posX, int posY) {
		super(new Point(posX,posY), new Point (0,0));
		this.life=0;
		this.coins=0;
		this.falling=true;
		stepReset();
		beNormal();
		this.acceleratingX=0;
		this.acceleratingY=0;
		this.isDead = false;
		setSprite(new SonicSprite(this));
	}

	public Integer getCoins(){
		return coins;
	}

	public void getLife(){
		life+=1;
	}

	public void stepReset(){
		falling = falling && !getFloor();
		super.stepReset();
		this.blockedRight = false;
		this.blockedLeft = false;
	}

	@Override
	public Double getSize(int side) {
		return hitbox[side];
	}

	@Override
	public Point normalAt(int side) {
		return normals[side];
	}

	@Override
	public void goRight() {
		acceleratingX = 1;
	}

	@Override
	public void goLeft() {
		acceleratingX = -1;
	}

	@Override
	public void jump() {
		if (!falling){
			acceleratingY=1;
		}
	}

	@Override
	public void beBall() {
		isBall = true;
		maxXSpeed = ballMaxXSpeed;

	}

	public void beNormal(){
		isBall = false;
		maxXSpeed = normalMaxXSpeed;

	}

	@Override
	public Boolean handleCollision(Hittable otherHittable, Point normal) {
		return otherHittable.handleSonic(this);
	}

	public Boolean handleSlopeBlock(Point normal) {
		if (normal.getY()>1){
			Double effect = normal.getX()*getSpeed().getX();
			getAcceleration().setX(-2.0*effect);
		}
		if (normal.getY()<0){
			falling = true;
		}
		return super.handleSlopeBlock(normal);
	}

	public Boolean handleGroundMonster(Point normal) {
		Boolean dead;
		if (isBall){
			dead = false;
		}
		else{
			dead = true;
			this.isDead = true;
		}
		return dead;
	}

	public Boolean handleCoin() {
		this.coins+=1;
		return false;
	}

	public Boolean handleSpikes(){
		isDead = true;
		return true;
	}

	public Boolean handleBlock(Point normal) {
		blockedRight = blockedRight || normal.getX()<=-1;
		blockedLeft = blockedLeft || normal.getX()>=1;
		if (normal.getY()<0){
			falling = true;
		}
		if (normal.getX()*getSpeed().getX()<0){
			getSpeed().setX(0);
		}
		return super.handleBlock(normal);
	}



	public void selfUpdate(Double dT){
		Point s = getSpeed();
		Double sX = s.getX();
		Double sY = s.getY();

		Point a = getAcceleration();

		if (acceleratingX!=0){
			if (acceleratingX*sX<maxXSpeed && !blockedX(acceleratingX)){
				a.setX(acceleratingX*maxXAcceleration);
			}
		}
		else{
			if (Math.abs(sX)>Unit.getMiniRealSpeed()){
				a.setX(-Math.signum(sX)*naturalXBrake);
			}
			else{
				s.setX(0);
			}

		}

		if (acceleratingY>0 && sY<maxYUpSpeed){
			a.setY(acceleratingY*maxYAcceleration);
		}
		else if (!getFloor() && sY>maxYDownSpeed){
			acceleratingY=0;
			falling=true;
			a.setY(Unit.getGravity());
		}

		super.selfUpdate(dT);
	}

	private boolean blockedX(Integer aX) {
		Boolean ans;
		if (aX<0){
			ans = blockedLeft;
		}
		else{
			ans = blockedRight;
		}
		return ans;
	}

	@Override
	public void stopX() {
		acceleratingX=0;
	}

	@Override
	public void stopJump() {
		acceleratingY=0;
	}

	public boolean isDead() {
		return isDead;
	}

	public boolean isBall() {
		return isBall;
	}

	public double getNormalMaxXSpeed() {
		return normalMaxXSpeed;
	}

	@Override
	public Boolean handleSonic(Sonic sonic) {
		System.out.println("Sonic just collided sonic?");
		return false;
	}
}
