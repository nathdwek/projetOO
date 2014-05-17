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

	public void initialize() {
		window = new JFrame("Sonic");
		window.setSize(800,700);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void welcomeUser(Controller c){
		welcomeBoard = new WelcomeBoard(c ,window.getWidth(),window.getHeight());
		window.add(welcomeBoard);
		window.validate();
	}

	public void refresh() {
		playPanel.requestFocus(true);
		window.repaint();
	}

	public void gameOver(Controller c){
		int ans = JOptionPane.showConfirmDialog(playPanel, "REPLAY ?", "GAME OVER" , JOptionPane.YES_NO_OPTION);
		if (ans == JOptionPane.YES_OPTION){
			model.initialize();
			playPanel.initialize();
		}else{
			c.stopGame();
		}
	}


	public void startGame(Controller c){
		window.remove(welcomeBoard);
		playPanel = new PlayPanel(model);
		playPanel.setFocusable(true);
		playPanel.addKeyListener(new SonicListener(model.getControlledHero()));
		scoreBoard = new ScoreBoard(window.getHeight(), model, playPanel, c, this);
		window.add(playPanel, BorderLayout.CENTER);
		window.add(scoreBoard, BorderLayout.NORTH);
		window.validate();
	}


	public void removeGamePanels() {
		window.remove(playPanel);
		window.remove(scoreBoard);
		window.validate();
	}






}
