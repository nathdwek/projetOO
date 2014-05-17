package sonic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import sonic.controller.Controller;
import sonic.controller.EditMapListener;
import sonic.controller.GameStartListener;


public class WelcomeBoard extends JPanel {
	private JButton start;
	private JButton editMap;
	private Image background = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicAcceuil3.jpg");

	public WelcomeBoard(Controller c, int w , int h){
		this.setLayout(null);
		editMap = new JButton(new ImageIcon("src/sonic/sprites/editMapButton.png"));
		start = new JButton(new ImageIcon("src/sonic/sprites/startButton.jpg"));
		editMap.setBounds(335,7*h/8, 130,30);
		start.setBounds(3*w/8,4*h/5, 200, 40);
		editMap.addActionListener(new EditMapListener(this));
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
