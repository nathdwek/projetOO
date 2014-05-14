package sonic.view;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Graphics;

import sonic.controller.SonicListener;
import sonic.model.Model;

public class View {

	private  Model model;
	private  JFrame window;
	private PlayPanel playPanel;
	private ScoreBoard scoreBoard;


	public View(Model m){
		model=m;
		initializeControls();

	}

	private void initializeControls() {
		window = new JFrame("hi");
		window.setSize(800,700);
		window.setLayout(new BorderLayout());
		window.addKeyListener(new SonicListener(model.getControlledHero()));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playPanel = new PlayPanel(model);
		scoreBoard = new ScoreBoard(window.getHeight(), model);
		window.add(playPanel, BorderLayout.CENTER);
		window.add(scoreBoard, BorderLayout.NORTH);


		window.setVisible(true);

	}

	public void refresh() {
		window.repaint();
	}





}
