package sonic.model;

public class GroundBlock extends Block{
	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private static final Double WIDTH = 50.0;

	public GroundBlock(Double sideX1, Double sideX2, Double sideY1) {
		super(sideX1, sideX2,sideY1, sideY1-WIDTH);
	}
	public GroundBlock(Double sideX1, Double sideX2, Double sideY1, Double sideY2) {
		super(sideX1, sideX2,sideY1, sideY2);
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
	public Point normalAt(int side) {
		return normals[side];
	}
	public static Double getDefaultWidth() {
		return WIDTH;
	}
	@Override
	public Boolean handleAMonster(Point normal) {
		return false;
	}
}

