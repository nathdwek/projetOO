package sonic.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sonic.controller.ButtonRestartListener;
import sonic.controller.ButtonStartListener;
import sonic.model.Model;

public class ScoreBoard extends JPanel{
	private JButton buttonStart;
	private JButton buttonRestart;
	private Model model;

	private JPanel playPanel;


	private Image coin;
	private Image background;
	private Image sonicLife;

	public ScoreBoard(Integer height, Model m, JPanel playPanel){
		coin = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/coins.gif");
		background = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicScoreBack.jpeg");
		sonicLife = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/sonicScoreBack.jpeg");


		model = m;
		this.playPanel = playPanel;
		buttonStart= new JButton("PAUSE");
		buttonRestart = new JButton("RESTART");
		buttonStart.addActionListener(new ButtonStartListener(buttonStart, model, playPanel));
		//buttonRestart.addActionListener(new ButtonRestartListener());
		this.setPreferredSize(new Dimension(-1,height/12));
		this.setBackground(Color.BLACK);
		this.add(buttonStart);
		this.add(buttonRestart);


	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.drawImage(background, 0 ,0,800,250, this);
		g.drawImage(coin, 10,15 , 25, 25, this);
		//g.drawImage(sonicLife , 700 , 15 ,100,100, this);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.setColor(Color.YELLOW);
		g.drawString("X  "+Integer.toString(model.getCoins()), 45, 35);

	}








}

