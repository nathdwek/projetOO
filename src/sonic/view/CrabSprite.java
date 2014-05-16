package sonic.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import sonic.model.AMonster;

public class CrabSprite implements Drawable {

	private AMonster aMonster;
	private Image crabe = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/crabe.gif");

	public CrabSprite(AMonster aMonster){
		this.aMonster = aMonster;
	}

	public void draw(Graphics g, JPanel p, Integer left, Integer top, Integer windowWidth , Integer windowHeight){
		int posX =  aMonster.getPosition().getX().intValue();
		int posY = aMonster.getPosition().getY().intValue();
		int thisLeft = posX-aMonster.getSize(2).intValue() - left;
		int width = Double.valueOf(aMonster.getSize(2)+aMonster.getSize(0)).intValue();
		int thisTop = posY+aMonster.getSize(1).intValue()-top;
		int height = Double.valueOf(aMonster.getSize(1)+aMonster.getSize(3)).intValue();
		g.setColor(Color.BLUE);
		g.drawImage(crabe , thisLeft-20, windowHeight-thisTop-20,width+20,height+20, p);
		//redimensionnement et positionement bizarre du crabe à cause de transparent autour du gif.
		g.finalize();
	}


}
