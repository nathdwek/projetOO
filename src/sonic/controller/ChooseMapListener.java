package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import sonic.model.Model;
import sonic.view.WelcomeBoard;

public class ChooseMapListener implements ActionListener {
	private WelcomeBoard welcomeBoard;
	private Model model;

	public ChooseMapListener(WelcomeBoard welcomeBoard, Model model){
		this.welcomeBoard = welcomeBoard;
		this.model = model;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser selectMap = new JFileChooser(new File("src/sonic/map.xml"));
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
				"xml files (*.xml)", "xml");
		selectMap.setFileFilter(xmlfilter);

		int option = selectMap.showOpenDialog(welcomeBoard);

		if (option == JFileChooser.APPROVE_OPTION){
			String selectedMap;
			try {
				selectedMap = selectMap.getSelectedFile().getCanonicalPath();
				model.setMap(selectedMap);
			}
			catch (IOException e) {
				System.out.println("error occured when selecting the map");
				e.printStackTrace();
			}
		}
	}

}


