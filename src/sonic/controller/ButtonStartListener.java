package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sonic.model.Model;



public class ButtonStartListener implements ActionListener {

	private JButton start;
	private Model model;
	private JPanel playPanel;

	public ButtonStartListener(JButton button, Model m ,JPanel playPanel){
		start = button;
		model = m;
		this.playPanel = playPanel;

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		model.setGamePaused(true);
		JOptionPane pausedMessage = new JOptionPane();
		int ans =JOptionPane.showConfirmDialog(playPanel, "QUIT ?", "PAUSED", JOptionPane.OK_CANCEL_OPTION);

		if (ans == JOptionPane.CANCEL_OPTION || ans== JOptionPane.CLOSED_OPTION){
			model.setGamePaused(false);
		}else{
			//page d'acceuil
		}


	}
}




