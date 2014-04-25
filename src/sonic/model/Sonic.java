package sonic.model;

public class Sonic extends Monster implements SelfUpdatable {
	
	private Point speedMax;
	private int life;
	private int coins;
	boolean acceleratingRight;
	boolean acceleratingLeft;
	boolean acceleratingUp;
	boolean isBall;
	

	public Sonic(Point position, Point speed) {
		super(position,speed);
		//this.speedMax=
		this.life=0;
		this.coins=0;
		this.acceleratingRight=true;
		this.acceleratingLeft=true;
		this.acceleratingUp=true;
		this.isBall=false;
	}
	
	public void getCoins(){
		coins+=1;
	}

	public void getLife(){
		life+=1;
	}

	public void accelerateRight() {
		acceleratingRight=true;
		}
		
	

	public void accelerateLeft(){
		acceleratingLeft=true;
			
		}
		
	

	public void jump() {
		acceleratingUp=true;
			
		}
		
	

	public void beBall() {
		speedMax.setX(20);
		isBall=true;
		
	}

	public void startDecelerateRight() {
		acceleratingRight=false;
		}
	
	public void startDecelerateLeft() {
		acceleratingLeft=false;
		}
	
	public void beSonic(){
		speedMax.setX(25);
		isBall=false;
		}
	public void contactGround(){
		
		}
	
	
	public void selfUpdate(){
		
		Double posX=this.getPosition().getX();
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
		this.getPosition().setX(posY);
	}
	
	
	public void draw(){}
}
