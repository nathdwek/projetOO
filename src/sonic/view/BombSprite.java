package sonic.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import sonic.model.GroundMonster;

public class BombSprite implements Drawable{

	private GroundMonster groundMonster;
	private Image sprite = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/bomb.gif");

	public BombSprite(GroundMonster gM){
		this.groundMonster = gM;
	}

	public void draw(Graphics g, JPanel p, Integer left, Integer top, Integer windowWidth , Integer windowHeight){
		int posX =  groundMonster.getPosition().getX().intValue();
		int posY = groundMonster.getPosition().getY().intValue();
		int thisLeft = posX-groundMonster.getSize(2).intValue() - left;
		int width = Double.valueOf(groundMonster.getSize(2)+groundMonster.getSize(0)).intValue();
		int thisTop = posY+groundMonster.getSize(1).intValue()-top;
		int height = Double.valueOf(groundMonster.getSize(1)+groundMonster.getSize(3)).intValue();
		g.setColor(Color.BLUE);
		g.drawImage(sprite , thisLeft-20, windowHeight-thisTop-20,width+20,height+20, p);
		//redimensionnement et positionement bizarre du crabe a cause de la large transparence autour du gif.
		g.finalize();
	}
}
