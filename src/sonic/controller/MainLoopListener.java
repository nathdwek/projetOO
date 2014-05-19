package sonic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sonic.model.Model;
import sonic.view.View;

public class MainLoopListener implements ActionListener{

	private Controller controller;
	private Model model;
	private View view;
	private Double loopTime;

	public MainLoopListener(Model m, View v,Controller c, Double loopTime){
		this.controller = c;
		this.model = m;
		this.view = v;
		this.loopTime = loopTime;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!controller.gamePaused()){
			if(model.gameOver()==0) {
				model.update(loopTime);
				view.refresh();
			}else{
				view.gameOver(controller,model.gameOver());
			}
		}
	}

}
