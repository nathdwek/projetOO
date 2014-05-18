package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class ButtonPauseListener implements ActionListener {

	private JPanel playPanel;
	private Controller controller;

	public ButtonPauseListener(JPanel playPanel, Controller c){
		this.playPanel = playPanel;
		this.controller = c;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.setGamePaused(true);
		int ans = JOptionPane.showConfirmDialog(playPanel, "QUIT ?", "PAUSED", JOptionPane.OK_CANCEL_OPTION);

		if (ans == JOptionPane.CANCEL_OPTION || ans== JOptionPane.CLOSED_OPTION){
			controller.setGamePaused(false);
		}else{
			controller.stopGame();
		}
	}
}




