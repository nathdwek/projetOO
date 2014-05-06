package sonic.view;

import javax.swing.JFrame;

import sonic.controller.SonicListener;
import sonic.model.Model;

public class View {

	private Model model;
	private JFrame window;

	public View(Model m){
		model=m;
		initializeControls();

	}

	private void initializeControls() {
		window = new JFrame("hi");
		window.addKeyListener(new SonicListener(model.getSonic()));
		window.setSize(300,400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void refresh() {
		// TODO Auto-generated method stub

	}

}
