package sonic.model;

public class AMonster extends Monster {

	private static Double[] hitbox = new Double[]{10.0,10.0,10.0,10.0};
	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private Boolean gravity;

	public AMonster(Point position, Point speed) {
		super(position, speed);
		gravity=true;
	}

	public AMonster(Double x, Double y, Double vX, Double vY) {
		super(new Point(x,y), new Point(vX,vY));
		gravity=true;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleCollision(Hittable otherHittable, Point normal) {
		switch (otherHittable.getType()){
			case "Block":
				handleBlock(normal);

		}
		System.out.println("AMonster touché en"+getPosition()+" Normale = "+ normal+ "par"+ otherHittable.getType());
	}

	private void handleBlock(Point normal) {
		if (normal.getY()>=1){
			gravity=false;
			getSpeed().setY(0);
		}
		if (normal.getX()*getSpeed().getX()<0){
			Point s=getSpeed();
			Double vX = s.getX();
			s.setX(vX+2*normal.getX()*Math.abs(vX));

		}
	}

	public void selfUpdate(Double dT) {
		if (gravity){
			this.getAcceleration().setY(-10);
		}
		super.selfUpdate(dT);
		System.out.println(getPosition()+" "+getSpeed());
	}

	public void stepReset(){
		super.stepReset();
		gravity=true;
	}

	public String getType() {
		return "AMonster";
	}

	public double getSize(int side) {
		return hitbox[side];
	}

	public Point normalAt(int side) {
		return normals[side];
	}

}
