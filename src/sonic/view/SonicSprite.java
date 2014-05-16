package sonic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import sonic.model.Sonic;
import sonic.model.Unit;

public class SonicSprite implements Drawable {

	private Sonic hero;
	private Image sonicBallR = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicBallR.gif");
	private Image sonicSpinning = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicSpinning.gif");
	private Image sonicFastL = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicFastL.gif");
	private Image sonicFastR = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicFastR.gif");
	private Image sonicWalkL = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicWalkL.gif");
	private Image sonicWalkR = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicWalkR.gif");
	private Image sonicWaitingR = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicWaitingR.gif");


	public SonicSprite(Sonic hero){
		this.hero = hero;
	}

	public Image checkState(){
		Double vX = hero.getSpeed().getX();
		Image sonicState;
		if (hero.isBall()){

			if (Math.abs(vX)<=Unit.getMiniRealSpeed()){

				sonicState = sonicSpinning;
			}else{
				sonicState = sonicBallR;
			}
		}else{

			if(Math.abs(vX)<=Unit.getMiniRealSpeed()){
				sonicState = sonicWaitingR;

			}
			else if (Math.abs(vX)<hero.getNormalMaxXSpeed()/1.1) {

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

	public void draw(Graphics g, JPanel p, Integer left, Integer top,
			Integer windowWidth, Integer windowHeight) {
		int posX =  hero.getPosition().getX().intValue();
		int posY = hero.getPosition().getY().intValue();
		int thisLeft = (posX-hero.getSize(2).intValue()-left);
		int width = Double.valueOf(hero.getSize(2)+hero.getSize(0)).intValue();
		int thisTop = posY+hero.getSize(1).intValue()-top;
		int height = Double.valueOf(hero.getSize(1)+hero.getSize(3)).intValue();
		g.drawImage(this.checkState(),thisLeft  , windowHeight - thisTop,width,height, p);
		g.finalize();

	}

}
