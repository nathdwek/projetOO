package sonic.model;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import sonic.controller.Controllable;


public class Sonic extends Unit implements Controllable {

	private Integer life;
	private Integer coins;

	private Boolean isBall;

	private Double maxXSpeed;
	private static final Double normalMaxXSpeed=100.0;
	private static final Double ballMaxXSpeed=200.0;

	private Double naturalXBrake = 100.0;
	private Integer acceleratingX;
	private Double maxXAcceleration = 200.0;

	private Double maxYUpSpeed = 100.0;
	private Double maxYDownSpeed = -200.0;

	private Boolean floor;
	private Boolean falling;

	private Integer acceleratingY;
	private Double maxYAcceleration = 200.0;
	private Double gravity = -400.0;

	private Double[] hitbox = new Double[]{10.0,10.0,10.0,10.0};
	private Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};

	public Sonic(int posX, int posY) {
		super(new Point(posX,posY), new Point (0,0));
		this.life=0;
		this.coins=0;
		this.falling=true;
		stepReset();
		beNormal();
		this.acceleratingX=0;
		this.acceleratingY=0;
	}

	public void getCoins(){
		coins+=1;
	}

	public void getLife(){
		life+=1;
	}

	public void stepReset(){
		super.stepReset();
		this.floor=false;
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
		return "Sonic";
	}

	@Override
	public void goRight() {
		acceleratingX = 1;
	}

	@Override
	public void goLeft() {
		acceleratingX=-1;
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
	public void handleCollision(Hittable otherHittable, Point normal) {
		switch (otherHittable.getType()){
		case "Block":
			handleBlock(normal);

		}
		//System.out.println("AMonster touché en"+getPosition()+" Normale = "+ normal+ "par"+ otherHittable.getType());
	}

	private void handleBlock(Point normal) {
		floor = floor || normal.getY()>=1;

		/*if (normal.getX()*getSpeed().getX()<0){
			Point s=getSpeed();
			Double vX = s.getX();
			s.setX(vX+2*normal.getX()*Math.abs(vX));

		}*/
	}


	public void selfUpdate(Double dT){

		Point s = getSpeed();
		Double sX = s.getX();
		Double sY = s.getY();

		Point a = getAcceleration();


		if (floor){
			if (sY<0){
				s.setY(0);
			}
			falling=false;
		}

		if (acceleratingX!=0){
			if (acceleratingX*sX<maxXSpeed){
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
		else if (!floor && sY>maxYDownSpeed){
			falling=true;
			a.setY(gravity);
		}

		super.selfUpdate(dT);

		//System.out.println(getPosition()+"  "+getSpeed());



	}

	public void paint(Graphics g, JPanel p) {
		int posX =  this.getPosition().getX().intValue();
		int posY = this.getPosition().getY().intValue();
		int left = posX-getSize(2).intValue();
		int width = Double.valueOf(getSize(2)+getSize(0)).intValue();
		int top = posY+getSize(1).intValue();
		int height = Double.valueOf(getSize(1)+getSize(3)).intValue();
		g.setColor(Color.RED);
		g.fillRect(left,700-top,width,height);
	}

	@Override
	public void stopX() {
		acceleratingX=0;
	}

	@Override
	public void stopJump() {
		acceleratingY=0;
	}

}
