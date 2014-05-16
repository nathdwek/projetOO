package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sonic.model.Model;



public class ButtonPauseListener implements ActionListener {

	private Model model;
	private JPanel playPanel;

	public ButtonPauseListener(Model m ,JPanel playPanel){
		model = m;
		this.playPanel = playPanel;

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGamePaused(true);
		int ans = JOptionPane.showConfirmDialog(playPanel, "QUIT ?", "PAUSED", JOptionPane.OK_CANCEL_OPTION);

		if (ans == JOptionPane.CANCEL_OPTION || ans== JOptionPane.CLOSED_OPTION){
			model.setGamePaused(false);
		}else{
		}


	}
}



