package sonic.model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Sonic extends Unit implements Controllable {

	private Integer life;
	private Integer coins;

	private Boolean isBall;

	private Double maxXSpeed;
	private static final Double normalMaxXSpeed=10.0;
	private static final Double ballMaxXSpeed=20.0;

	private Double maxXBrake = 10.0;
	private Integer brakingX;
	private Integer acceleratingX;
	private Double maxXAcceleration = 10.0;

	private Double maxYUpSpeed = 10.0;
	private Double maxYDownSpeed = -20.0;

	private Boolean floor;
	private Boolean falling;

	private Integer acceleratingY;
	private Double maxYAcceleration = 10.0;
	private Double gravity = -10.0;

	private Double[] hitbox = new Double[]{10.0,10.0,10.0,10.0};
	private Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};

	public Sonic(int posX, int posY) {
		super(new Point(posX,posY), new Point (0,0));
		this.life=0;
		this.coins=0;
		this.beNormal();
		this.acceleratingY=0;
		this.acceleratingX=0;
		this.brakingX = 0;
		this.floor=false;
		this.falling=false;
	}

	public void getCoins(){
		coins+=1;
	}

	public void getLife(){
		life+=1;
	}

	public void goRight() {
		acceleratingX=1;
	}
	public void goLeft(){
		acceleratingX=-1;
	}

	public void jump() {
		acceleratingY=1;
	}

	public void beBall() {
		maxXSpeed=ballMaxXSpeed;
		isBall=true;
	}
	public void beNormal(){
		maxXSpeed=normalMaxXSpeed;
		isBall=false;
	}

	public void stopRight() {
		if (acceleratingX>0){
			acceleratingX=0;
		}
		if (getSpeed().getX() > 0){
			brakingX=-1;
		}
	}

	public void stopLeft() {
		if (acceleratingX<0){
			acceleratingX=0;
		}
		if (getSpeed().getX() < 0){
			brakingX=1;
		}
	}

	public void stopJump() {
		acceleratingY=0;
		falling=true;
	}

	public void handleCollision(Hittable otherHittable, Point normal) {
		switch (otherHittable.getType()){
		case "Block":
			handleBlock(normal);
			break;

		}
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
		Point s=getSpeed();
		Point a=getAcceleration();

		Double sX=s.getX();
		Double sY=s.getY();

		if (floor){
			if (sY<0){
				s.setY(0);
			}
			falling = false;
		}

		if (!falling && acceleratingY>0){
			if (sY < maxYUpSpeed){
				a.setY(acceleratingY*maxYAcceleration);
			}
			else{
				falling = true;
			}
		}

		if (falling && sY > maxYDownSpeed){
			a.setY(gravity);
		}

		if (brakingX*sX < 0){
			a.setX(brakingX*maxXBrake);
		}
		else{
			brakingX=0;
			if (acceleratingX*sX < maxXSpeed){
				a.setX(acceleratingX*maxXAcceleration);
			}
			else{
				acceleratingX=0;
			}
		}

		super.selfUpdate(dT);
		System.out.println(getPosition()+" "+getSpeed());
	}

	public void stepReset(){
		super.stepReset();
		floor = false;
	}

	@Override
	public double getSize(int side) {
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

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(10,10,80,80);

	}




}
