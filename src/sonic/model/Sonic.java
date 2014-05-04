package sonic.model;

public class Sonic extends Monster implements SelfUpdatable {

	private Integer life;
	private Integer coins;

	private Boolean isBall = false;

	private Double maxXSpeed;
	private static final Double normalMaxXSpeed=10.0;
	private static final Double ballMaxXSpeed=20.0;

	private Double maxYUpSpeed;
	private Double maxYDownSpeed;

	private Boolean floor;

	private Integer brakingX;
	private Integer acceleratingX = 0;

	private Integer acceleratingY = 0;

	public Sonic(Point position, Point speed) {
		super(position,speed);
		//this.speedMax=
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
		if (getSpeed().getY()>=0){
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
			brakingX=-1;
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
		/*switch (otherHittable.getType()){
			case "Block":
				handleBlock

		}


		/*if (otherHittable instanceof Coin){
			this.getCoins();
		}
		if (otherHittable instanceof ExtraLife){
			this.getLife();
		}
		if (otherHittable instanceof Monster){
			if (!isBall && life==0){
				//game over
			}

		}*/
	}


	public void selfUpdate(Double dT){

/*		Double posX=this.getPosition().getX();
		Double posY=this.getPosition().getY();
		Double vX=this.getSpeed().getX();
		Double vY=this.getSpeed().getY();
		Double vMaxX=speedMax.getX();

		if (acceleratingRight){
			if (vX<vMaxX){
				vX+=
			}
		}
		else if (acceleratingLeft){
			if (vX>-vMaxX){
				vX-=
			}
		}else{
			if (vX>0){
				vX-=
			}
			else if(speed.getX<0){
				vX+=
			}
		}



		posX+=vX;
		posY+=vY;
		this.getSpeed().setX(vX);
		this.getSpeed().setY(vY);
		this.getPosition().setX(posX);
		this.getPosition().setX(posY);*/
	}

	@Override
	public double getSize(int side) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point normalAt(int side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

}
