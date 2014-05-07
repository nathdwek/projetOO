package sonic.view;

import javax.swing.JFrame;

import sonic.controller.SonicListener;
import sonic.model.Model;

public class View {

	private static Model model;
	private static JFrame window;

	public View(Model m){
		model=m;
		initializeControls();

	}

	private static void initializeControls() {
		JFrame win = new JFrame("hi");
		TestIG play = new TestIG();
		win.add(play);
		win.addKeyListener(new SonicListener(model.getSonic()));
		win.setSize(500,500);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);
	}

	public void refresh() {
		// TODO Auto-generated method stub
	}
	public static void main(String[] args){
		initializeControls();
	}



}
