package sonic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import sonic.model.Hittable;
import sonic.model.Spikes;

public class SpikesSprite implements Drawable {

	private Spikes spikes;
	private Image sprite = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/spikes.png");

	public SpikesSprite(Spikes spikes){
		this.spikes = spikes;
	}

	@Override
	public void draw(Graphics g, JPanel p, Integer left, Integer top,
			Integer windowWidth, Integer windowHeight) {
		int posX =  spikes.getPosition().getX().intValue();
		int posY = spikes.getPosition().getY().intValue();
		int thisLeft = posX-spikes.getSize(Hittable.LEFT).intValue()-left;
		int width = Double.valueOf(spikes.getSize(Hittable.LEFT)+spikes.getSize(Hittable.RIGHT)).intValue();
		int thisTop = posY+spikes.getSize(Hittable.TOP).intValue()-top;
		int height = Double.valueOf(spikes.getSize(Hittable.TOP)+spikes.getSize(Hittable.BOTTOM)).intValue();
		g.drawImage(sprite, thisLeft,windowHeight-thisTop-10, width+1, height+1, p);
	}

}
