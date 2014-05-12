package sonic.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import sonic.model.Sonic;

import java.awt.Graphics;
import java.awt.Point;

import sonic.controller.SonicListener;
import sonic.model.Model;

public class View {

	private  Model model;
	private  JFrame window;
	private PlayPanel playPanel;


	public View(Model m){
		model=m;
		initializeControls();

	}

	private void initializeControls() {
		window = new JFrame("hi");
		window.setSize(800,700);
		window.addKeyListener(new SonicListener(model.getHero()));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		playPanel = new PlayPanel(model);
		window.add(playPanel);

	}
	public Point getWindowDimension(){
		return new Point(window.getWidth(),window.getHeight());
	}

	public void refresh() {
		window.repaint();
	}





}
