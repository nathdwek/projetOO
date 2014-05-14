package sonic.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


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
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playPanel = new PlayPanel(model);
		playPanel.setFocusable(true);
		playPanel.addKeyListener(new SonicListener(model.getHero()));
		scoreBoard = new ScoreBoard(window.getHeight(), model, playPanel);
		window.add(playPanel, BorderLayout.CENTER);
		window.add(scoreBoard, BorderLayout.NORTH);
		window.setVisible(true);

	}

	public void refresh() {
		playPanel.requestFocus(true);
		window.repaint();

	}
	public void gameOver(){
		JOptionPane replayMessage = new JOptionPane();
		int ans = JOptionPane.showConfirmDialog(playPanel, "REPLAY ?", "GAME OVER" , JOptionPane.YES_NO_OPTION);
		if (ans == JOptionPane.YES_OPTION){
			//replay
		}else{
			//retourne à la page d'acceuil
		}
	}






}
