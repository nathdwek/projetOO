package sonic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import sonic.model.Sonic;
import sonic.model.Unit;

public class SonicSprite implements Drawable {

	private Sonic hero;
	private Image sonicBallR;
	private Image sonicSpinning;
	private Image sonicFastL;
	private Image sonicFastR;
	private Image sonicWalkL;
	private Image sonicWalkR;
	private Image sonicWaitingR;


	public SonicSprite(Sonic hero){
		this.hero = hero;
		Toolkit tk = Toolkit.getDefaultToolkit();
		sonicBallR = tk.getImage("src/sonic/sprites/sonicBallR.gif");
		sonicSpinning = tk.getImage("src/sonic/sprites/sonicSpinning.gif");
		sonicFastL = tk.getImage("src/sonic/sprites/sonicFastL.gif");
		sonicFastR = tk.getImage("src/sonic/sprites/sonicFastR.gif");
		sonicWalkL = tk.getImage("src/sonic/sprites/sonicWalkL.gif");
		sonicWalkR = tk.getImage("src/sonic/sprites/sonicWalkR.gif");
		sonicWaitingR = tk.getImage("src/sonic/sprites/sonicWaitingR.gif");
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
