package sonic.model;

import java.awt.Color;
import java.awt.Graphics;

public class AMonster extends Unit {

	private static Double[] hitbox = new Double[]{10.0,10.0,10.0,10.0};
	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private static final Double gravity= -10.0;
	private Boolean floor = false;

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
		System.out.println("AMonster touché en"+getPosition()+" Normale = "+ normal+ "par "+ otherHittable.getType());
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
		System.out.println(getPosition()+" "+getSpeed());
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
	public void paint(Graphics g){
		double posx =  this.getPosition().getX();
		int posX = (int) posx;
		double posy = this.getPosition().getY();
		int posY = (int) posy;
		g.setColor(Color.BLUE);
		g.fillRect(posX, 700 -posY, (int) (this.getSize(0)*2) , (int) (this.getSize(1)*2));
	}

}
