package sonic.model;

public class Coin extends Hittable{

	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private static Double[] hitbox;
	private String type = "Coin";

	public Coin(Point position, Double sizeX, Double sizeY) {
		super(position);
		hitbox = new Double[]{sizeX/2,sizeY/2,sizeX/2,sizeY/2};
	}

	public Coin(Double sideX1, Double sideX2, Double sideY1, Double sideY2) {
		super(new Point((sideX1+sideX2)/2,(sideY1+sideY2)/2));
		Double halfSizeX=Math.abs(sideX1-sideX2)/2;
		Double halfSizeY=Math.abs(sideY1-sideY2)/2;
		hitbox = new Double[]{halfSizeX,halfSizeY,halfSizeX,halfSizeY};
	}

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
	public double getSize(int side) {
		return hitbox[side];
	}

	@Override
	public Point normalAt(int side) {
		return normals[side];
	}

}
