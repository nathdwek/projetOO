package sonic.model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import sonic.view.Drawable;

public abstract class Block extends Hittable implements Drawable{

	private Double[] hitbox;

	public Block(Point position, Double sizeX, Double sizeY) {
		super(position);
		hitbox = new Double[]{sizeX/2,sizeY/2,sizeX/2,sizeY/2};
	}

	public Block(Double sideX1, Double sideX2, Double sideY1, Double sideY2) {
		super(new Point((sideX1+sideX2)/2,(sideY1+sideY2)/2));
		Double halfSizeX=Math.abs(sideX1-sideX2)/2;
		Double halfSizeY=Math.abs(sideY1-sideY2)/2;
		hitbox = new Double[]{halfSizeX,halfSizeY,halfSizeX,halfSizeY};
	}

	@Override
	public Boolean handleCollision(Hittable otherHittable, Point normal) {
		return false;
	}

	@Override
	public String getType() {
		return "Block";
	}

	@Override
	public Double getSize(int side) {
		return hitbox[side];
	}

	public void draw(Graphics g, JPanel p,Integer left, Integer top, Integer windowWidth, Integer windowHeight){
		int posX =  this.getPosition().getX().intValue();
		int posY = this.getPosition().getY().intValue();
		int thisLeft = posX-getSize(Hittable.LEFT).intValue()-left;
		int width = Double.valueOf(getSize(Hittable.LEFT)+getSize(Hittable.RIGHT)).intValue();
		int thisTop = posY+getSize(Hittable.TOP).intValue()-top;
		int height = Double.valueOf(getSize(Hittable.TOP)+getSize(Hittable.BOTTOM)).intValue();
		g.setColor(new Color(139, 69, 19));
		g.fillRect(thisLeft,windowHeight-thisTop,width+1,height-10);
		g.setColor(new Color(0,139,0));
		g.fillRect(thisLeft,windowHeight-thisTop-10, width+1, 10);
	}
}
