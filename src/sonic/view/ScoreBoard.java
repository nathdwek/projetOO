package sonic.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;

import sonic.controller.ButtonRestartListener;
import sonic.controller.ButtonStartListener;
import sonic.model.Model;

public class ScoreBoard extends JPanel{
	private JButton buttonStart;
	private JButton buttonRestart;
	private Model model;


	private Image coin;

	public ScoreBoard(Integer height, Model m, PlayPanel playPanel ){
		coin = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/coins.gif");

		model = m;
		buttonStart= new JButton("PAUSE");
		buttonRestart = new JButton("RESTART");
		buttonStart.addActionListener(new ButtonStartListener(model, playPanel));
		buttonRestart.addActionListener(new ButtonRestartListener(model, playPanel));
		this.setPreferredSize(new Dimension(-1,height/12));
		this.setBackground(Color.BLACK);
		this.add(buttonStart);
		this.add(buttonRestart);


	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(coin, 10,15 , 25, 25, this);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.setColor(Color.YELLOW);
		g.drawString("X  "+Integer.toString(model.getCoins()), 45, 35);

	}
}

