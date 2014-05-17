package sonic.model;

import sonic.view.BlockSprite;
import sonic.view.Drawable;

public abstract class Block extends Hittable implements HasSprite{

	private Double[] hitbox;
	private Drawable sprite;

	public Block(Point position, Double sizeX, Double sizeY) {
		super(position);
		hitbox = new Double[]{sizeX/2,sizeY/2,sizeX/2,sizeY/2};
		this.sprite = new BlockSprite(this);
	}

	public Block(Double sideX1, Double sideX2, Double sideY1, Double sideY2) {
		super(new Point((sideX1+sideX2)/2,(sideY1+sideY2)/2));
		Double halfSizeX=Math.abs(sideX1-sideX2)/2;
		Double halfSizeY=Math.abs(sideY1-sideY2)/2;
		hitbox = new Double[]{halfSizeX,halfSizeY,halfSizeX,halfSizeY};
		this.sprite = new BlockSprite(this);
	}

	@Override
	public Boolean handleCollision(Hittable otherHittable, Point normal) {
		return otherHittable.handleBlock(normal);
	}

	@Override
	public Double getSize(int side) {
		return hitbox[side];
	}

	public void setSprite(Drawable sprite){
		this.sprite = sprite;
	}

	public Drawable getSprite(){
		return this.sprite;
	}

	public Boolean handleGroundMonster(Point normal){
		return false;
	}

	public Boolean handleSonic(Point normal,Sonic sonic){
		return false;
	}

	@Override
	public Boolean handleBlock(Point normal) {
		System.out.println("2 blocks collided?");
		return false;
	}

	@Override
	public Boolean handleSlopeBlock(Point normal) {
		System.out.println("2 blocks collided?");
		return false;
	}
}
