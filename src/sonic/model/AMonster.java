package sonic.model;

public class AMonster extends Monster {

	private static Double[] hitbox = new Double[]{10.0,10.0,10.0,10.0};
	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};

	public AMonster(Point position, Point speed) {
		super(position, speed);
	}

	public AMonster(Double x, Double y, Double vX, Double vY) {
		super(new Point(x,y), new Point(vX,vY));
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleCollision(Hittable otherHittable, Point normal) {
		/*switch (otherHittable.getType()){
			case "Block":

		}*/
		System.out.println("AMonster touché en"+getPosition()+" Normale = "+ normal);

	}

	public void selfUpdate(Double dT) {
		move(this.getSpeed().times(dT));
		System.out.println(getPosition());
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
