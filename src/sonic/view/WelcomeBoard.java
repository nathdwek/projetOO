package sonic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;

import sonic.controller.Controller;
import sonic.controller.ChooseMapListener;
import sonic.controller.GameStartListener;
import sonic.model.Model;


@SuppressWarnings("serial")
public class WelcomeBoard extends JPanel {
	private JButton start;
	private JButton editMap;
	private Image background = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicAcceuil3.jpg");

	public WelcomeBoard(Model model, Controller c, int w , int h){
		this.setLayout(null);
		editMap = new JButton("Select Map");
		start = new JButton("PLAY!");
		editMap.setBounds(335,7*h/8, 130,30);
		start.setBounds(3*w/8,4*h/5, 200, 40);
		editMap.addActionListener(new ChooseMapListener(this,model));
		start.addActionListener(new GameStartListener(c));
		editMap.setFocusable(true);
		start.setFocusable(true);
		this.add(editMap);
		this.add(start);


	}

	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(background ,0,0 , this.getWidth(), this.getHeight(), this);
		start.requestFocus(true);
		editMap.requestFocus(true);
	}
}
