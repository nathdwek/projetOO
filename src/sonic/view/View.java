package sonic.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import sonic.controller.SonicListener;
import sonic.controller.Controller;
import sonic.model.Model;

public class View {

	private  Model model;
	private  JFrame window;
	private PlayPanel playPanel;
	private ScoreBoard scoreBoard;
	private WelcomeBoard welcomeBoard;


	public View(Model m){
		model=m;


	}

	public void welcomeUser(Controller c) {
		window = new JFrame("Sonic");
		window.setSize(800,700);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomeBoard = new WelcomeBoard(c);
		window.add(welcomeBoard);
		window.setVisible(true);

	}

	public void refresh() {
		playPanel.requestFocus(true);
		window.repaint();
	}

	public void gameOver(){
		int ans = JOptionPane.showConfirmDialog(playPanel, "REPLAY ?", "GAME OVER" , JOptionPane.YES_NO_OPTION);
		if (ans == JOptionPane.YES_OPTION){
			model.initialize();
			playPanel.initialize();
		}else{
			model.initialize();
			playPanel.initialize();
		}

	}


	public void startGame(Model m){

		window.remove(welcomeBoard);
		playPanel = new PlayPanel(model);
		playPanel.setFocusable(true);
		playPanel.addKeyListener(new SonicListener(model.getControlledHero()));
		scoreBoard = new ScoreBoard(window.getHeight(), model, playPanel);
		window.add(playPanel, BorderLayout.CENTER);
		window.add(scoreBoard, BorderLayout.NORTH);
		window.validate();


	}






}
