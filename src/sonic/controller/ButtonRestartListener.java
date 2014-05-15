package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.View;

import sonic.model.Model;

public class ButtonRestartListener implements ActionListener {
	private Model model;



	public ButtonRestartListener(Model m){
		model = m;

	}
	public void actionPerformed(ActionEvent e) {
		model.setRestart(true);
	}

}
