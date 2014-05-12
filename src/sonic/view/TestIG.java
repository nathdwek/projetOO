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



public class TestIG extends JPanel{

	private Model model;
	private Point center;
	private Image background = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicBackround.png");

	public TestIG(Model m){
		model = m;
		center = model.getHeroPosition();
	}

	public void paint(Graphics g){
		Integer windowWidth = this.getWidth();
		Integer windowHeight = this.getHeight();
		Integer left = center.getX().intValue() - windowWidth/2;
		Integer top = center.getY().intValue()- windowHeight/2 + 100;
		g.drawImage(background , 0,0, windowWidth,windowHeight,  this);
		for (Drawable d : model.getDrawables()){
			d.paint(g, this, left,top, windowWidth, windowHeight);
		}

	}
}