package sonic.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class AMonster extends Unit {

	private static Double[] hitbox = new Double[]{20.0,20.0,20.0,20.0};
	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private static final Double gravity= -10.0;
	private Boolean floor = false;
	private Image crabe = Toolkit.getDefaultToolkit().getImage("C:/Users/junnuo/Desktop/projetsonic/Sonic/crabe.gif");

	public AMonster(Point position, Point speed) {
		super(position, speed);
	}

	public AMonster(Double x, Double y, Double vX, Double vY) {
		super(new Point(x,y), new Point(vX,vY));
	}

	@Override
	public void handleCollision(Hittable otherHittable, Point normal) {
		switch (otherHittable.getType()){
		case "Block":
			handleBlock(normal);

		}
		//System.out.println("AMonster touché en"+getPosition()+" Normale = "+ normal+ "par "+ otherHittable.getType());
	}

	private void handleBlock(Point normal) {
		floor = floor || normal.getY()>=1;

		if (normal.getX()*getSpeed().getX()<0){
			Point s=getSpeed();
			Double vX = s.getX();
			s.setX(vX+2*normal.getX()*Math.abs(vX));

		}
	}

	public void selfUpdate(Double dT) {
		if (floor){
			Point s=getSpeed();
			if (s.getY()<0){
				s.setY(0);
			}
		}
		else{
			getAcceleration().setY(gravity);
		}
		super.selfUpdate(dT);
		//System.out.println(getPosition()+" "+getSpeed());
	}

	public void stepReset(){
		super.stepReset();
		floor = false;
	}

	public String getType() {
		return "AMonster";
	}

	public Double getSize(int side) {
		return hitbox[side];
	}

	public Point normalAt(int side) {
		return normals[side];
	}
	public void paint(Graphics g, JPanel p){
		int posX =  this.getPosition().getX().intValue();
		int posY = this.getPosition().getY().intValue();
		int left = posX-getSize(2).intValue();
		int width = Double.valueOf(getSize(2)+getSize(0)).intValue();
		int top = posY+getSize(1).intValue();
		int height = Double.valueOf(getSize(1)+getSize(3)).intValue();
		g.setColor(Color.BLUE);
		//System.out.println(this.getPosition());
		g.drawImage(crabe , left,700-top,width,height, p);
		g.finalize();
	}

}
