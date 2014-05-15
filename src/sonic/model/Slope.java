package sonic.model;

import java.util.LinkedList;

public class Slope{
	LinkedList<SlopeBlock> blocks;
	Integer step = 1;
	private static final Double WIDTH = GroundBlock.getDefaultWidth();
	public Slope(Double left, Double right, Double leftHeight, Double rightHeight){
		blocks = new LinkedList<SlopeBlock>();

		Integer i =1;
		Double slopeCoeff = Double.valueOf((leftHeight - rightHeight)/(left-right));
		while (left+i <= right){
			blocks.add(new SlopeBlock(left +i,left+i+step,leftHeight+slopeCoeff*i,leftHeight+slopeCoeff*i-WIDTH, slopeCoeff));
			i+=step;
		}
	}

	public LinkedList<SlopeBlock> getBlocks(){
		return this.blocks;
	}
}

