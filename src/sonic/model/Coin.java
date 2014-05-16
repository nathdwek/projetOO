package sonic.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import sonic.view.Drawable;
import sonic.viewµ.CoinSprite;

public class Coin extends Hittable implements HasSprite{

	private static Point[] normals = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};
	private static Double[] hitbox= new Double[]{10.0,10.0,10.0,10.0};
	private String type = "Coin";
	private Drawable sprite;

	public Coin(double posX, double posY) {
		super(new Point(posX, posY));
		this.sprite = new CoinSprite(this);

	}

	@Override
	public Boolean handleCollision(Hittable otherHittable, Point normal) {
		Boolean dead;
		switch (otherHittable.getType()){
		case "Sonic":
		case "SonicBall":
			dead = true;
			break;
		default:
			dead = false;
		}
		return dead;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public Double getSize(int side) {
		return hitbox[side];
	}

	@Override
	public Point normalAt(int side) {
		return normals[side];
	}

	public static Double getWidth(){
		return hitbox[Hittable.LEFT]+hitbox[Hittable.RIGHT];
	}

	public static Double getHeight() {
		return hitbox[Hittable.TOP]+hitbox[Hittable.BOTTOM];
	}

	public Drawable getSprite(){
		return this.sprite;
	}

	@Override
	public void setSprite(Drawable sprite) {
		this.sprite = sprite;
	}

	@Override
	public Boolean handleAMonster(Point normal) {
		return false;
	}
}
