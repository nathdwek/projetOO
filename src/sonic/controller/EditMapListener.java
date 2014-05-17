package sonic.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

import sonic.view.WelcomeBoard;

public class EditMapListener implements ActionListener {
	private WelcomeBoard welcomeBoard;

	public EditMapListener(WelcomeBoard welcomeBoard){
		this.welcomeBoard = welcomeBoard;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser editMap = new JFileChooser(new File("src/sonic/map.xml"));
		int option = editMap.showOpenDialog(welcomeBoard);

		if (option == JFileChooser.APPROVE_OPTION){
			File selectedFile = editMap.getSelectedFile();
			try
			{

				Desktop.getDesktop().open(selectedFile);
			}
			catch(Exception exception)
			{
				System.out.println("Problem occour when to open the file");
			}
		}
	}

}


