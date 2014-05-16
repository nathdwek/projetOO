package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sonic.model.Model;
import sonic.view.PlayPanel;

public class ButtonRestartListener implements ActionListener {
	private Model model;
	private PlayPanel playPanel;



	public ButtonRestartListener(Model m, PlayPanel p){
		model = m;
		playPanel = p;

	}
	public void actionPerformed(ActionEvent e) {
		model.initialize();
		playPanel.initialize();
	}

}
