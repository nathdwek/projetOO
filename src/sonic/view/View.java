package sonic.view;

import javax.swing.JFrame;
import sonic.model.Sonic;
import java.awt.Graphics;
import sonic.controller.SonicListener;
import sonic.model.Model;

public class View {

	private  Model model;
	private  JFrame window;
	private TestIG testig;


	public View(Model m){
		model=m;
		initializeControls();

	}

	private void initializeControls() {
		window = new JFrame("hi");
		window.setSize(500,500);
		window.addKeyListener(new SonicListener(model.getSonic()));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void refresh() {
		testig = new TestIG(model.getSonic());
		window.add(testig);
	}




}
