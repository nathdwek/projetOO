package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sonic.model.Model;

public class ButtonStartGameListener implements ActionListener {
	private Model model;

	public ButtonStartGameListener(Model m){
		model = m;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.setStartGame(true);

	}

}
