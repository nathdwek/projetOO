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

	private Model model;

	public TestIG(Model m){
		model = m;
	}

	public void paint(Graphics g){
		for (Drawable d : model.getDrawables()){
			d.paint(g, this);
		}
	}
}