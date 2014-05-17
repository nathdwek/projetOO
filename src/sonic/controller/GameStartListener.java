package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStartListener implements ActionListener{
	private Controller controller;

	public GameStartListener(Controller c){
		this.controller = c;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.startGame();
	}

}
