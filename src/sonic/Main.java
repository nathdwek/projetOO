package sonic;

import sonic.controller.Controller;
import sonic.model.Model;
import sonic.view.View;

public class Main {

	public static void main(String[] args){

		Model m;
		View v;
		Controller c = null;

		m = new Model(c);
		v = new View(m);
		c = new Controller(m,v);

		c.runGame();
	}
}
