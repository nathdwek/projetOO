package sonic.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import sonic.model.Sonic;

import java.awt.Graphics;

import sonic.controller.SonicListener;
import sonic.model.Model;

public class View {

	private  Model model;
	private  JFrame window;
	private TestIG testig;


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
		testig = new TestIG(model);
		window.add(testig);
	}
	public void refresh() {
		window.repaint();
	}




}
