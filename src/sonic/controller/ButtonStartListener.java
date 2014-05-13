package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;


public class ButtonStartListener implements ActionListener {

	private JButton start;

	public ButtonStartListener(JButton button){
		start = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		start.setText("START");

	}


}


