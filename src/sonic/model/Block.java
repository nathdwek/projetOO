package sonic.model;

public class Block extends Hittable{

	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private Double[] hitbox;

	public Block(Point position, Double sizeX, Double sizeY) {
		super(position);
		hitbox = new Double[]{sizeX/2,sizeY/2,sizeX/2,sizeY/2};
	}

	public Block(Double x, Double y, Double sizeX, Double sizeY) {
		super(new Point(x,y));
		hitbox = new Double[]{sizeX/2,sizeY/2,sizeX/2,sizeY/2};
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleCollision(Hittable otherHittable, Point normal) {
		System.out.println("Block touché en"+this.getPosition());
	}

	@Override
	public String getType() {
		return "Block";
	}

	@Override
	public double getSize(int side) {
		return hitbox[side];
	}

	@Override
	public Point normalAt(int side) {
		return normals[side];
	}

}
