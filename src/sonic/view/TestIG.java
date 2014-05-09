package sonic.view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import sonic.model.Sonic;



public class TestIG extends JPanel{

	private Sonic sonic;

	public TestIG(Sonic s){
		sonic = s;
	}

	public void paint(Graphics g){
		sonic.paint(g, this );
	}
}