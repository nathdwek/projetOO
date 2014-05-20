package sonic.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import sonic.model.Slope;

public class SlopeSprite implements Drawable {

	private Slope slope;

	public SlopeSprite(Slope slope){
		this.slope = slope;
	}

	public void draw(Graphics g, JPanel p, Integer left, Integer top,
			Integer windowWidth, Integer windowHeight) {
		Polygon grass = new Polygon(new int[]{this.slope.getLeft().intValue(),
				this.slope.getRight().intValue(),
				this.slope.getRight().intValue(),
				this.slope.getLeft().intValue()}
		, new int[]{windowHeight-this.slope.getLeftHeight().intValue()-9,
				windowHeight-this.slope.getRightHeight().intValue()-9,
				windowHeight-this.slope.getRightHeight().intValue()+12,
				windowHeight-this.slope.getLeftHeight().intValue()+12},
				4);
		Polygon mud = new Polygon(new int[]{this.slope.getLeft().intValue(),
				this.slope.getRight().intValue(),
				this.slope.getRight().intValue(),
				this.slope.getLeft().intValue()}
		, new int[]{windowHeight-this.slope.getLeftHeight().intValue()+12,
				windowHeight-this.slope.getRightHeight().intValue()+12,
				windowHeight-this.slope.getRightHeight().intValue()+Slope.getWidth().intValue(),
				windowHeight-this.slope.getLeftHeight().intValue()+Slope.getWidth().intValue()},
				4);

		grass.translate(-left,top);
		mud.translate(-left, top);
		g.setColor(new Color(255,100,100));
		g.fillPolygon(grass);
		g.setColor(new Color(255,20,0));
		g.fillPolygon(mud);

		/*int posX =  this.slope.getPosition().getX().intValue();


		int posY = this.slope.getPosition().getY().intValue();
		int thisLeft = posX-this.slope.getSize(Hittable.LEFT).intValue()-left;
		int width = Double.valueOf(this.slope.getSize(Hittable.LEFT)+this.slope.getSize(Hittable.RIGHT)).intValue();
		int thisTop = posY+this.slope.getSize(Hittable.TOP).intValue()-top;
		int height = Double.valueOf(this.slope.getSize(Hittable.TOP)+this.slope.getSize(Hittable.BOTTOM)).intValue();
		g.setColor(new Color(139, 69, 19));
		g.fillRect(thisLeft,windowHeight-thisTop,width+1,height-10);
		g.setColor(new Color(0,139,0));
		g.fillRect(thisLeft,windowHeight-thisTop-10, width+1, 10);*/

	}
}
