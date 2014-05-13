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
	private Image background;
	private Image sonicLife;

	public ScoreBoard(Integer height, Model m){
		coin = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/coins.gif");
		background = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicBackground3.jpg");
		sonicLife = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicLife.jpg");

		model = m;
		buttonStart= new JButton("PAUSE");
		buttonRestart = new JButton("RESTART");
		buttonStart.addActionListener(new ButtonStartListener(buttonStart, model));
		//buttonRestart.addActionListener(new ButtonRestartListener());
		this.setPreferredSize(new Dimension(-1,height/5));
		this.add(buttonStart);
		this.add(buttonRestart);

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0 ,0, 800, 140, this);
		g.drawImage(coin, 10,15 , 25, 25, this);
		g.drawImage(sonicLife , 700 , 15 ,25,25, this);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.setColor(Color.YELLOW);
		g.drawString("X  "+Integer.toString(model.getCoins()), 45, 35);

	}








}

