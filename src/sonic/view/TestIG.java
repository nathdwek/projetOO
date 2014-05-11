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
import sonic.model.SelfUpdatable;
import sonic.model.Sonic;



public class TestIG extends JPanel{

	private Model model;
	private Image background = Toolkit.getDefaultToolkit().getImage("C:/Users/junnuo/Desktop/projetsonic/Sonic/image.jpg");

	public TestIG(Model m){
		model = m;
	}

	public void paint(Graphics g){
		g.drawImage(background , 0,0, 800,700,  this);
		int sonicPosX = model.getHero().getPosition().getX().intValue();
		for (Drawable d : model.getDrawables()){
			d.paint(g, this, sonicPosX);
		}

	}
}