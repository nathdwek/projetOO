package sonic.controller;

import sonic.model.Model;
import java.awt.Graphics;
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
		//Initialize
		while (!model.gameOver()){
			Long startTime = System.currentTimeMillis();

			model.update(Double.valueOf(loopTime)/1000);
			view.refresh();

			try {
				Thread.sleep(loopTime-(System.currentTimeMillis()-startTime));
			}
			catch (InterruptedException e) {
			}
		}
	}

}
