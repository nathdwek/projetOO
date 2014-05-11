package sonic.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Coin extends Hittable{

	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private static Double[] hitbox= new Double[]{10.0,10.0,10.0,10.0};
	private String type = "Coin";
	private Image coin = Toolkit.getDefaultToolkit().getImage("C:/Users/junnuo/Desktop/projetsonic/Sonic/coins.gif");

	public Coin(double posX, double posY) {
		super(new Point(posX, posY));

	}

	/*public Coin(Double sideX1, Double sideX2, Double sideY1, Double sideY2) {
		super(new Point((sideX1+sideX2)/2,(sideY1+sideY2)/2));
		Double halfSizeX=Math.abs(sideX1-sideX2)/2;
		Double halfSizeY=Math.abs(sideY1-sideY2)/2;
		hitbox = new Double[]{halfSizeX,halfSizeY,halfSizeX,halfSizeY};
	}*/

	@Override
	public void handleCollision(Hittable otherHittable, Point normal) {
		System.out.println("Coin touché en"+this.getPosition());
		type = "DESTROYED";
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public Double getSize(int side) {
		return hitbox[side];
	}

	@Override
	public Point normalAt(int side) {
		return normals[side];
	}

	@Override
	public void paint(Graphics g, JPanel p) {
		int posX =  this.getPosition().getX().intValue();
		int posY = this.getPosition().getY().intValue();
		int left = posX-getSize(2).intValue();
		int width = Double.valueOf(getSize(2)+getSize(0)).intValue();
		int top = posY+getSize(1).intValue();
		int height = Double.valueOf(getSize(1)+getSize(3)).intValue();
		g.drawImage(coin ,left  , 700-top,width,height, p);
		g.finalize();

	}

}
