package sonic.model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import sonic.view.Drawable;

public class Block extends Hittable implements Drawable{

	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
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

	@Override
	public Point normalAt(int side) {
		return normals[side];
	}

	public void paint(Graphics g, JPanel p, Point center){
		int posX =  this.getPosition().getX().intValue();
		int posY = this.getPosition().getY().intValue();
		int left = posX-getSize(2).intValue()-center.getX().intValue();
		int width = Double.valueOf(getSize(2)+getSize(0)).intValue();
		int top = posY+getSize(1).intValue()-center.getY().intValue();
		int height = Double.valueOf(getSize(1)+getSize(3)).intValue();
		g.setColor(new Color(139, 69, 19));
		g.fillRect(left,700-top+5,width,height-5);
		g.setColor(new Color(0,139,0));
		g.fillRect(left,700-top-5, width, 10);



	}
}
