package sonic.controller;

import sonic.model.Model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import sonic.view.View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.Timer;

public class Controller{

	private Model model;
	private View view;
	private Timer mainLoop;
	private static final Short fps = 60;
	private static Integer loopTimeMillis = Double.valueOf((1000.0/fps)).intValue();
	private static final Double loopTime = 1.0/fps;



	public Controller(Model m, View v) {

		model = m;
		view = v;
		mainLoop = new Timer(loopTimeMillis, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!model.gamePaused()){
					if(!model.gameOver()) {
						model.update(loopTime);
						view.refresh();
					}else{
						view.gameOver();
					}
				}
			}

		});


	}

	public void runGame() {
		view.welcomeUser(this);
	}

	public void startGame() {
		view.startGame(model , this);
		this.mainLoop.start();
	}

	public void stopGame() {
		this.mainLoop.stop();
		view.backToMenu(this);
	}

}

