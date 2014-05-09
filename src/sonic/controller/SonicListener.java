package sonic.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sonic.model.Controllable;
import sonic.model.Sonic;

public class SonicListener implements KeyListener {

	private Controllable hero;

	public SonicListener(Controllable h){
		System.out.println(h);
		hero = h;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch (key){
		case KeyEvent.VK_RIGHT:
			hero.goRight();
			break;
		case KeyEvent.VK_LEFT:
			hero.goLeft();
			break;
		case KeyEvent.VK_SPACE:
			hero.jump();
			break;
		case KeyEvent.VK_DOWN:
			hero.beBall();
			break;
		}
	}



	public void keyReleased (KeyEvent e){
		/*int key = e.getKeyCode();
		switch (key){
		case KeyEvent.VK_RIGHT:
			hero.stopRight();
			break;
		case KeyEvent.VK_LEFT:
			hero.stopLeft();
			break;
		case KeyEvent.VK_DOWN:
			hero.beNormal();
			break;
		case KeyEvent.VK_SPACE:
			hero.stopJump();
			break;
		}*/
	}
	public void keyTyped(KeyEvent e){

	}
}
