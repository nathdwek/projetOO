package sonic;

import sonic.controller.Controller;
import sonic.model.Model;
import sonic.view.View;

public class Main {
	
	public static void main(String[] args){
	
	Model m = new Model();
	View v = new View(m);
	Controller c = new Controller(m,v);
	
	c.runGame();
	
	}
}
