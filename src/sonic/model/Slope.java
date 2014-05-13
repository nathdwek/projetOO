package sonic.model;

import java.util.LinkedList;

public class Slope{
	LinkedList<SlopeBlock> blocks;
	public Slope(Double left, Double right, Double leftHeight, Double rightHeight, Double width){
		blocks = new LinkedList<SlopeBlock>();
		
		Integer i =1;
		Double slopeCoeff = Double.valueOf((leftHeight - rightHeight)/(left-right));
		while (left+i <= right){
			blocks.add(new SlopeBlock(left +i,left+i+2,leftHeight+slopeCoeff*i,leftHeight+slopeCoeff*i-width, slopeCoeff));
			i+=2;
		}
	}
	public LinkedList<SlopeBlock> getBlocks(){
		return this.blocks;
	}
}
