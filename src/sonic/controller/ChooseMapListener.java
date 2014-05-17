package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import sonic.view.WelcomeBoard;

public class ChooseMapListener implements ActionListener {
	private WelcomeBoard welcomeBoard;
	private Controller controller;

	public ChooseMapListener(WelcomeBoard welcomeBoard, Controller c){
		this.welcomeBoard = welcomeBoard;
		this.controller = c;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser editMap = new JFileChooser(new File("src/sonic/map.xml"));
		int option = editMap.showOpenDialog(welcomeBoard);

		if (option == JFileChooser.APPROVE_OPTION){
			String selectedMap;
			try {
				selectedMap = editMap.getSelectedFile().getCanonicalPath();
				controller.setMap(selectedMap);
			}
			catch (IOException e) {
				System.out.println("error occured when selecting the map");
				e.printStackTrace();
			}
		}
	}

}


