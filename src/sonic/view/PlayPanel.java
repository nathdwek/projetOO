package sonic.view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import sonic.model.Block;
import sonic.model.Hittable;
import sonic.model.Model;
import sonic.model.Point;
import sonic.model.SelfUpdatable;
import sonic.model.Sonic;



public class PlayPanel extends JPanel{

	private Model model;
	private Point center;
	private Point centerV;
	private Image background = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicBackround.png");
	private Point heroPosition;
	private Double loopTimeSecs = 1.0/60;

	public PlayPanel(Model m){
		model = m;
		center = model.getHeroPosition().copy();
		centerV=new Point(0,0);
		heroPosition = model.getHeroPosition();

	}

	public void paint(Graphics g){
		Integer windowWidth = this.getWidth();
		Integer windowHeight = this.getHeight();
		Point centerA=(new Point(heroPosition.getX()-center.getX(), heroPosition.getY()-center.getY()).times(2.0));
		centerA.add(centerV.times(Double.valueOf(-1.5)));
		centerV.add(centerA.times(loopTimeSecs));
		center.add(centerV.times(loopTimeSecs));
		Integer left = center.getX().intValue() - windowWidth/2;
		Integer top = center.getY().intValue()- windowHeight/2 + 100;
		g.drawImage(background , 0,0, windowWidth,windowHeight,  this);
		for (Drawable d : model.getDrawables()){
			d.paint(g, this, left,top, windowWidth, windowHeight);
		}

	}
}