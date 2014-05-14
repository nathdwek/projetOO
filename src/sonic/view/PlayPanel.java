package sonic.view;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JOptionPane;


import java.util.LinkedList;

import javax.swing.JPanel;

import sonic.model.Model;
import sonic.model.Point;



public class PlayPanel extends JPanel{

	private LinkedList<Drawable> drawables;
	private Point center;
	private Image background = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicBackround.png");

	public PlayPanel(Model m){

		drawables = m.getDrawables();
		center = m.getPlayPanelCenter();



	}

	public void paint(Graphics g){
		super.paint(g);
		Integer windowWidth = this.getWidth();
		Integer windowHeight = this.getHeight();
		Integer left = center.getX().intValue() - windowWidth/2;
		Integer top = center.getY().intValue()- windowHeight/2 + 150;
		g.drawImage(background , 0,0, windowWidth,windowHeight,  this);
		for (Drawable d : drawables){
			d.draw(g, this, left,top, windowWidth, windowHeight);
		}
	}
}