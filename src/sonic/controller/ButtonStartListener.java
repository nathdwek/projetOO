package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;

import sonic.model.Model;


public class ButtonStartListener implements ActionListener {

	private JButton start;
	private Model model;

	public ButtonStartListener(JButton button, Model m){
		start = button;
		model = m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (start.getText() == "PAUSE"){
			start.setText("START");
			model.setGamePaused(true);

		}else{
			start.setText("PAUSE");
			model.setGamePaused(false);


		}
	}
}




