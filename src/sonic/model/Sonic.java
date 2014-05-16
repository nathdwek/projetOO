package sonic.model;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

import javax.swing.JPanel;

import sonic.controller.Controllable;


public class Sonic extends Unit implements Controllable {

	private Integer life;
	private Integer coins;

	private Boolean isBall;

	private Double maxXSpeed;
	private static final Double normalMaxXSpeed=300.0;
	private static final Double ballMaxXSpeed=600.0;

	private Double naturalXBrake = 1000.0;
	private Integer acceleratingX;
	private Double maxXAcceleration = 1500.0;

	private Double maxYUpSpeed = 400.0;
	private Double maxYDownSpeed = -800.0;

	private Boolean blockedRight;
	private Boolean blockedLeft;
	private Boolean falling;

	private Integer acceleratingY;
	private Double maxYAcceleration = 2000.0;

	private Double[] hitbox = new Double[]{20.0,20.0,20.0,20.0};
	private Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};

	private Image sonicBallR = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicBallR.gif");
	private Image sonicSpinning = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicSpinning.gif");
	private Image sonicFastL = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicFastL.gif");
	private Image sonicFastR = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicFastR.gif");
	private Image sonicWalkL = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicWalkL.gif");
	private Image sonicWalkR = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicWalkR.gif");
	private Image sonicWaitingR = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicWaitingR.gif");
	private Image sonicState ;
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
	public String getType() {
		String type;
		if (isBall){
			type = "SonicBall";
		}
		else{
			type = "Sonic";
		}
		return type;
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
		Boolean dead;
		switch (otherHittable.getType()){
		case "Block":
			dead = handleBlock(normal);
			break;
		case "Spikes":
			dead = isDead = true;
		case "SlopeBlock" :
			dead = handleSlopeBlock(normal);
			break;
		case "Coin":
			dead = handleCoin();
			break;
		case "Monster" :
			dead = handleMonster();
			break;
		default:
			dead = false;
		}
		return false;
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

	private Boolean handleMonster() {
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

	private Boolean handleCoin() {
		this.coins+=1;
		return false;
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
	private Image checkState(){
		Double vX = getSpeed().getX();

		if (isBall){

			if (Math.abs(vX)<=Unit.getMiniRealSpeed()){

				sonicState = sonicSpinning;
			}else{
				sonicState = sonicBallR;
			}
		}else{

			if(Math.abs(vX)<=Unit.getMiniRealSpeed()){
				sonicState = sonicWaitingR;

			}
			else if (Math.abs(vX)<normalMaxXSpeed/1.1) {

				if ( vX > 0 ){
					sonicState = sonicWalkR;
				}
				else{
					sonicState = sonicWalkL;
				}
			}
			else{
				if (vX>0){
					sonicState = sonicFastR;
				}
				else{
					sonicState = sonicFastL;

				}
			}
		}


		return sonicState;
	}

	@Override
	public void draw(Graphics g, JPanel p,Integer left, Integer top, Integer windowWidth , Integer windowHeight) {
		int posX =  this.getPosition().getX().intValue();
		int posY = this.getPosition().getY().intValue();
		int thisLeft = (posX-getSize(2).intValue()-left);
		int width = Double.valueOf(getSize(2)+getSize(0)).intValue();
		int thisTop = posY+getSize(1).intValue()-top;
		int height = Double.valueOf(getSize(1)+getSize(3)).intValue();
		g.drawImage(this.checkState(),thisLeft  , windowHeight - thisTop,width,height, p);
		g.finalize();

	}

	public boolean isDead() {
		return isDead;
	}


}
