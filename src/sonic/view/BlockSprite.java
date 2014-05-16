package sonic.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import sonic.model.Block;
import sonic.model.Hittable;

public class BlockSprite implements Drawable {

	private Block block;

	public BlockSprite(Block block){
		this.block = block;
	}

	@Override
	public void draw(Graphics g, JPanel p, Integer left, Integer top,
			Integer windowWidth, Integer windowHeight) {
		int posX =  block.getPosition().getX().intValue();
		int posY = block.getPosition().getY().intValue();
		int thisLeft = posX-block.getSize(Hittable.LEFT).intValue()-left;
		int width = Double.valueOf(block.getSize(Hittable.LEFT)+block.getSize(Hittable.RIGHT)).intValue();
		int thisTop = posY+block.getSize(Hittable.TOP).intValue()-top;
		int height = Double.valueOf(block.getSize(Hittable.TOP)+block.getSize(Hittable.BOTTOM)).intValue();
		g.setColor(new Color(139, 69, 19));
		g.fillRect(thisLeft,windowHeight-thisTop,width+1,height-10);
		g.setColor(new Color(0,139,0));
		g.fillRect(thisLeft,windowHeight-thisTop-10, width+1, 10);

	}

}
