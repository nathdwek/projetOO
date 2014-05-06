package sonic.model;

public class Sonic extends Monster implements SelfUpdatable {

	private Integer life;
	private Integer coins;

	private Boolean isBall = false;

	private Double maxXSpeed = normalMaxXSpeed;
	private static final Double normalMaxXSpeed=10.0;
	private static final Double ballMaxXSpeed=20.0;

	private Double maxXBrake = 20.0;
	private Integer brakingX = 0;
	private Integer acceleratingX = 0;
	private Double maxXAcceleration = 10.0;

	private Double maxYUpSpeed = 10.0;
	private Double maxYDownSpeed = -20.0;

	private Boolean floor = false;

	private Integer acceleratingY = 0;
	private Double maxYAcceleration = 10.0;
	private double gravity = -10.0;

	private Double[] hitbox = new Double[]{10.0,10.0,10.0,10.0};
	private Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private boolean falling;

	public Sonic(int posX, int posY) {
		super(new Point(posX,posY), new Point (0,0));
		this.life=0;
		this.coins=0;
	}

	public void getCoins(){
		coins+=1;
	}

	public void getLife(){
		life+=1;
	}

	public void accelerateRight() {
		if (getSpeed().getX()>=0){
			acceleratingX=1;
		}
		else{
			brakingX=-1;
		}
	}

	public void accelerateLeft(){
		if (getSpeed().getX()<=0){
			acceleratingX=-1;
		}
		else{
			brakingX=1;
		}
	}

	public void jump() {
		if (!falling){
			acceleratingY=1;
		}
	}

	public void beBall() {
		maxXSpeed=ballMaxXSpeed;
		isBall=true;
	}

	public void startDecelerateRight() {
		if (getSpeed().getX() > 0){
			brakingX=-1;
		}
	}

	public void startDecelerateLeft() {
		if (getSpeed().getX() < 0){
			brakingX=1;
		}
	}

	public void beSonic(){
		maxXSpeed=normalMaxXSpeed;
		isBall=false;
	}

	public void startDecelerateUp() {
		acceleratingY=0;
	}

	public void handleCollision(Hittable otherHittable, Point normal) {
		switch (otherHittable.getType()){
		case "Block":
			handleBlock(normal);break;

		}
	}


	private void handleBlock(Point normal) {
		floor = floor || normal.getY()>=1;

		if (normal.getX()*getSpeed().getX()<0){
			Point s=getSpeed();
			Double vX = s.getX();
			s.setX(vX+2*normal.getX()*Math.abs(vX));

		}
	}

	public void selfUpdate(Double dT){
		if (acceleratingY>0){
			if (getSpeed().getY() < maxYUpSpeed){
				getAcceleration().setY(acceleratingY*maxYAcceleration);
			}
			else{
				falling = true;
			}
		}
		else if (floor){
			Point s=getSpeed();
			if (s.getY()<0){
				s.setY(0);
			}
			falling = false;
		}
		else if (getSpeed().getY() > maxYDownSpeed){
			getAcceleration().setY(gravity );
		}

		if (acceleratingX*getSpeed().getX() < maxXSpeed){
			getAcceleration().setX(acceleratingX*maxXAcceleration);
		}
		else if (brakingX*getSpeed().getX() < 0){
			getAcceleration().setX(brakingX*maxXBrake);
		}
		super.selfUpdate(dT);
		System.out.println(getPosition()+" "+getSpeed());
	}

	public void stepReset(){
		super.stepReset();
		brakingX = 0;
		acceleratingX = 0;
		floor = false;
		acceleratingY = 0;
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

}
