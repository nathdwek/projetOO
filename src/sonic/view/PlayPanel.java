package sonic.view;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JPanel;

import sonic.controller.SonicListener;
import sonic.model.HasSprite;
import sonic.model.Model;
import sonic.model.Point;



@SuppressWarnings("serial")
public class PlayPanel extends JPanel{

	private Model model;
	private SonicListener sonicListener;
	private LinkedList<HasSprite> paintables;
	private Point center;
	private Image background = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicBackround.png");

	public PlayPanel(Model m){

		model = m;
		sonicListener = new SonicListener(null);
		initialize();
		this.addKeyListener(sonicListener);
	}

	public void paint(Graphics g){
		super.paint(g);
		Integer windowWidth = this.getWidth();
		Integer windowHeight = this.getHeight();
		Integer left = center.getX().intValue() - windowWidth/2;
		Integer top = center.getY().intValue()- windowHeight/2 + 110;
		g.drawImage(background , 0,0, windowWidth,windowHeight,  this);
		for (HasSprite hS : paintables){
			hS.getSprite().draw(g, this, left,top, windowWidth, windowHeight);
		}
	}

	public void initialize() {
		paintables = model.getPaintables();
		center = model.getPlayPanelCenter();
		sonicListener.setHero(model.getControlledHero());
	}
}