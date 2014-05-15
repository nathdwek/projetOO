package sonic.model;

import java.util.LinkedList;

public class Slope{
	LinkedList<Block> blocks;

	Integer step = 7;
	private static final Double WIDTH = GroundBlock.getDefaultWidth();
	public Slope(Double left, Double right, Double leftHeight, Double rightHeight){
		blocks = new LinkedList<Block>();
		Double slopeCoeff = Double.valueOf((leftHeight - rightHeight)/(left-right));


		blocks.add(new GroundBlock(left-2,left,leftHeight,leftHeight-WIDTH));
		blocks.add(new GroundBlock(right,right+2,rightHeight,rightHeight-WIDTH));

		Integer i =1;
		while (left+i <= right){
			blocks.add(new SlopeBlock(left +i,left+i+step,leftHeight+slopeCoeff*i,leftHeight+slopeCoeff*i-WIDTH, slopeCoeff));
			i+=step;
		}
	}

	public LinkedList<Block> getBlocks(){
		return this.blocks;
	}
}

