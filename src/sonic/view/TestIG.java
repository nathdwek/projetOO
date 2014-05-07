package sonic.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sonic.model.Sonic;

import java.awt.Graphics2D;

public class TestIG extends JPanel{
	Sonic s = new Sonic(200,100);

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		double posx =  s.getPosition().getX();
		int posX = (int) posx;
		double posy = s.getPosition().getY();
		int posY = (int) posy;
		g.fillRect(posX, posY,80,80);


	}


}