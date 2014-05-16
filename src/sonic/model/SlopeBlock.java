package sonic.model;

public class SlopeBlock extends Block{
	private Point[] normals;

	public SlopeBlock(Double sideX1, Double sideX2, Double sideY1, Double sideY2, Double slopeCoeff){
		super(sideX1,sideX2,sideY1,sideY2);
		this.normals = new Point[]{new Point(1,0),new Point(slopeCoeff,1.0),new Point(-1,0),new Point(0,-1)};
	}

	public String getType(){
		return "SlopeBlock";
	}

	@Override
	public Point normalAt(int side) {
		return normals[side];
	}

	@Override
	public Boolean handleCollision(Hittable otherHittable,Point normal){
		return otherHittable.handleSlopeBlock(normal);
	}

}
