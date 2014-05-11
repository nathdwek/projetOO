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

	private Sonic sonic;
	private LinkedList<Hittable> fixedHittables;
	private Model model;
	private ArrayList<Hittable> movingHittables;
	private Image background = Toolkit.getDefaultToolkit().getImage("C:/Users/junnuo/Desktop/projetsonic/Sonic/image.jpg");

	public TestIG(Sonic s, Model m){
		sonic = s;
		model = m;
		fixedHittables= m.getFixedHittables();
		movingHittables = m.getMovingHittables();
	}

	public void paint(Graphics g){
		g.drawImage(background , 0,0, 800,700,  this);
		sonic.paint(g, this );
		for (Hittable fixedHittable : fixedHittables){
			fixedHittable.paint(g, this);
		}
		for (Hittable movingHittable : movingHittables){
			movingHittable.paint(g, this);
		}

	}
}