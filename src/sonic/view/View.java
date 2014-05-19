package sonic.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import sonic.controller.SonicListener;
import sonic.controller.Controller;
import sonic.model.Model;

public class View {

	private final Model model;
	private JFrame window;
	private PlayPanel playPanel;
	private ScoreBoard scoreBoard;
	private WelcomeBoard welcomeBoard;


	public View(Model m){
		this.model=m;


	}

	public void initialize() {
		this.window = new JFrame("Sonic");
		this.window.setSize(800,700);
		this.window.setLayout(new BorderLayout());
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setVisible(true);
	}

	public void welcomeUser(Controller c){
		this.welcomeBoard = new WelcomeBoard(this.model,c ,this.window.getWidth(),this.window.getHeight());
		this.window.add(this.welcomeBoard);
		this.window.validate();
	}

	public void refresh() {
		this.playPanel.requestFocus(true);
		this.window.repaint();
	}

	public void gameOver(Controller c, Integer reason){
		String title = (reason<2)? "GAME OVER" : "YOU WIN!";
		int ans = JOptionPane.showConfirmDialog(this.playPanel, "Play again?", title , JOptionPane.YES_NO_OPTION);
		if (ans == JOptionPane.YES_OPTION){
			this.model.initialize();
			this.playPanel.initialize();
		}else{
			c.stopGame();
		}
	}


	public void startGame(Controller c){
		this.window.remove(this.welcomeBoard);
		this.playPanel = new PlayPanel(this.model);
		this.playPanel.setFocusable(true);
		this.playPanel.addKeyListener(new SonicListener(this.model.getControlledHero()));
		this.scoreBoard = new ScoreBoard(this.window.getHeight(), this.model, this.playPanel, c, this);
		this.window.add(this.playPanel, BorderLayout.CENTER);
		this.window.add(this.scoreBoard, BorderLayout.NORTH);
		this.window.validate();
	}


	public void removeGamePanels() {
		this.window.remove(this.playPanel);
		this.window.remove(this.scoreBoard);
		this.window.validate();
	}






}
