package sonic.controller;

import sonic.model.Model;
import sonic.view.View;

public class Controller {
	
	private Model model;
	private View view;
	private static final Short fps = 30;
	private static final Integer loopTime = 1000/fps;

	public Controller(Model m, View v) {
		
		model=m;
		view=v;

	}

	public void runGame() {
		while (!model.gameOver()){
			Long startTime = System.currentTimeMillis();
			//Stuff goes here
			try {
				Thread.sleep(loopTime-(System.currentTimeMillis()-startTime));
			}
			catch (InterruptedException e) {
			}
		}
	}

}
