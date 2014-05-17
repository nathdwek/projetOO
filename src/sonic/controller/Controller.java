package sonic.controller;

import sonic.model.Model;



import sonic.view.View;


import javax.swing.Timer;

public class Controller{

	private Model model;
	private View view;
	private Timer mainLoop;
	private static final Short fps = 60;
	private static Integer loopTimeMillis = Double.valueOf((1000.0/fps)).intValue();
	private static final Double loopTime = 1.0/fps;



	public Controller(Model m, View v) {
		this.model = m;
		this.view = v;
		mainLoop = new Timer(loopTimeMillis, new MainLoopListener(m,v,this,loopTime) {
		});
	}

	public void runGame() {
		view.initialize();
		view.welcomeUser(this);
	}

	public void startGame() {
		model.initialize();
		view.startGame(this);
		this.mainLoop.start();
	}

	public void stopGame() {
		view.removeGamePanels();
		view.welcomeUser(this);
		this.mainLoop.stop();
	}
}

