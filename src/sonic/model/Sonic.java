package sonic.model;

public class Sonic implements SelfUpdatable {

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
		vMaxX= 
		isBall=true;
		
	}

	public void startDecelerateRight() {
		acceleratingRight=false;
		}
	
	public void startDecelerateLeft() {
		acceleratingLeft=false;
		}
	
	public void beSonic(){
		vMaxX=
		isBall=false
		
	public void contactGround(){
		
		}
	
	
	public void selfUpdate(){
		if (acceleratingRight){
			if (vx<vMaxX){
				vx+=
			}
		}
		else if (acceleratingLeft){
			if (vx>-vMaxX){
				vx-=
			}
		}else{
			if (vx>0){
				vx-=
			}
			else if(vx<0){
				vx+=
			}
		}
		
		
		
		posX+=vx;
		posY+=vy;
	}
}
