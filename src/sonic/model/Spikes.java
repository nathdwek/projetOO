package sonic.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Spikes extends GroundBlock{
	
	private static final Image spikes = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/spikes.png");
	
	public Spikes(Double X, Double Y) {
		super(X,X+getDefaultWidth(),Y-10,Y+getDefaultWidth()-10);
	}

	
	public String getType(){
		return "Spikes";
	}
	public void draw(Graphics g, JPanel p,Integer left, Integer top, Integer windowWidth, Integer windowHeight){
		int posX =  this.getPosition().getX().intValue();
		int posY = this.getPosition().getY().intValue();
		int thisLeft = posX-getSize(Hittable.LEFT).intValue()-left;
		int width = Double.valueOf(getSize(Hittable.LEFT)+getSize(Hittable.RIGHT)).intValue();
		int thisTop = posY+getSize(Hittable.TOP).intValue()-top;
		int height = Double.valueOf(getSize(Hittable.TOP)+getSize(Hittable.BOTTOM)).intValue();
		g.drawImage(spikes, thisLeft,windowHeight-thisTop-10, width+1, height+1, p);
	}
}
