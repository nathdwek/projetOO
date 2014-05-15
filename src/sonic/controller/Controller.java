package sonic.controller;

import sonic.model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sonic.view.View;

import javax.swing.Timer;

public class Controller {

	private Model model;
	private View view;
	private static final Short fps = 60;
	private static Integer loopTimeMillis = Double.valueOf((1000.0/fps)).intValue();
	private static final Double loopTime = 1.0/fps;


	public Controller(Model m, View v) {

		model=m;
		view=v;

	}

	public void runGame() {
		Timer timer = new Timer(loopTimeMillis, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!model.gamePaused()){

					if(!model.gameOver()) {
						model.update(loopTime);
						view.refresh();
					}else{
						//view.gameOver();
					}
				}else{
					view.refresh();
				}
			}});
		timer.start();
	}

}
