package sonic.view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
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
	private LinkedList<SelfUpdatable> selfupdatable;

	public TestIG(Sonic s, Model m){
		sonic = s;
		model = m;
		fixedHittables= m.getFixedHittables();
		selfupdatable = m.getSelfUpdatables();
	}

	public void paint(Graphics g){
		sonic.paint(g, this );
		for (Hittable hittable : fixedHittables){
			hittable.paint(g);
		}
		for (SelfUpdatable updatable : selfupdatable ){
			updatable.paint(g);
		}
	}
}