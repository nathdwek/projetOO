package sonic.model;

import java.util.LinkedList;

import sonic.view.Drawable;
import sonic.view.SlopeSprite;

public class Slope implements HasSprite{
	private final LinkedList<Block> blocks;
	private Drawable sprite;
	private Double left;
	private Double right;
	private Double leftHeight;
	private Double rightHeight;


	private static final Integer step = 5;
	private static final Double WIDTH = GroundBlock.getDefaultWidth()/1.5;
	public Slope(Double left, Double right, Double leftHeight, Double rightHeight){

		this.left = left;
		this.right = right;
		this.leftHeight = leftHeight;
		this.rightHeight = rightHeight;

		this.blocks = new LinkedList<Block>();
		Double slopeCoeff = Double.valueOf((leftHeight - rightHeight)/(left-right));


		this.blocks.add(new GroundBlock(left-2,left,leftHeight,leftHeight-WIDTH));
		this.blocks.add(new GroundBlock(right,right+2,rightHeight,rightHeight-WIDTH));

		Integer i =1;
		while (left+i <= right){
			this.blocks.add(new SlopeBlock(left +i,left+i+Slope.step,leftHeight+slopeCoeff*i,leftHeight+slopeCoeff*i-WIDTH, slopeCoeff));
			i+=Slope.step;
		}

		this.sprite = new SlopeSprite(this);
	}

	public LinkedList<Block> getBlocks(){
		return this.blocks;
	}

	public static Double getWidth(){
		return WIDTH;
	}

	public Double getLeft(){
		return this.left;
	}

	public Double getRight(){
		return this.right;
	}

	public Double getLeftHeight(){
		return this.leftHeight;
	}

	public Double getRightHeight(){
		return this.rightHeight;
	}

	@Override
	public Drawable getSprite() {
		return this.sprite;
	}

	@Override
	public void setSprite(Drawable sprite) {
		this.sprite = sprite;
	}

}

