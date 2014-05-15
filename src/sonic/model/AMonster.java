package sonic.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JPanel;

public class AMonster extends Unit {

	private static Double[] hitbox = new Double[]{20.0,20.0,20.0,20.0};
	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private static final Double gravity= -10.0;
	private Boolean floor = false;
	private Image crabe;

	public AMonster(Point position, Point speed) {
		super(position, speed);
		crabe = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/crabe.gif");
	}

	public AMonster(double x, double y, double vX, double vY) {
		this(new Point(x,y), new Point(vX,vY));
	}

	@Override
	public Boolean handleCollision(Hittable otherHittable, Point normal) {
		Boolean dead;
		switch (otherHittable.getType()){
		case "Block":
			dead=handleBlock(normal);
			break;
		case "Sonic":
			dead = false;
			break;
		case "SonicBall":
			dead = true;
			break;
		default:
			dead=false;
		}
		return dead;
	}

	private Boolean handleBlock(Point normal) {
		floor = floor || normal.getY()>=1;

		if (normal.getX()*getSpeed().getX()<0){
			Point s=getSpeed();
			Double vX = s.getX();
			s.setX(vX+2*normal.getX()*Math.abs(vX));

		}
		return false;
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
	}

	public void stepReset(){
		super.stepReset();
		floor = false;
	}

	public String getType() {
		return "Monster";
	}

	public Double getSize(int side) {
		return hitbox[side];
	}

	public Point normalAt(int side) {
		return normals[side];
	}
	public void draw(Graphics g, JPanel p, Integer left, Integer top, Integer windowWidth , Integer windowHeight){
		int posX =  this.getPosition().getX().intValue();
		int posY = this.getPosition().getY().intValue();
		int thisLeft = posX-getSize(2).intValue() - left;
		int width = Double.valueOf(getSize(2)+getSize(0)).intValue();
		int thisTop = posY+getSize(1).intValue()-top;
		int height = Double.valueOf(getSize(1)+getSize(3)).intValue();
		g.setColor(Color.BLUE);
		g.drawImage(crabe , thisLeft, windowHeight-thisTop,width,height, p);
		g.finalize();
	}

}
