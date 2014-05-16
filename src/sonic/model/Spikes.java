package sonic.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import sonic.view.SpikesSprite;

public class Spikes extends GroundBlock{

	public Spikes(Double X, Double Y) {
		super(X,X+getDefaultWidth(),Y-10,Y+getDefaultWidth()-10);
		setSprite(new SpikesSprite(this));
	}


	public String getType(){
		return "Spikes";
	}
	public void draw(Graphics g, JPanel p,Integer left, Integer top, Integer windowWidth, Integer windowHeight){
	}
}
