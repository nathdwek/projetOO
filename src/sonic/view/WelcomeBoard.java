package sonic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import sonic.controller.ButtonStartGameListener;
import sonic.model.Model;

public class WelcomeBoard extends JPanel {
	private Model model;
	private JButton start;
	private JComboBox level;
	private Image background = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicAcceuil3.jpg");

	public WelcomeBoard(Model m){
		model = m;
		this.setLayout(null);
		String[] levelNumber = {"LEVEL 1","LEVEL 2"};
		start = new JButton("START");
		level = new JComboBox(levelNumber);
		start.setBounds(200,250, 100, 40);
		level.setBounds(200,350, 100,40);
		start.addActionListener(new ButtonStartGameListener(model));
		start.setFocusable(true);
		level.setFocusable(true);
		this.add(start);
		this.add(level);

	}

	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(background ,0,0 , this.getWidth(), this.getHeight(), this);
		start.requestFocus(true);
		level.requestFocus(true);
	}


}
