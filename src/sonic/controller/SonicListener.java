package sonic.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sonic.model.Sonic;

public class SonicListener implements KeyListener {
	
	private Sonic sonic;
	
	public SonicListener(Sonic s){
		sonic =s;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch (key){
			case KeyEvent.VK_RIGHT: 
				sonic.accelerateRight();
				break;
			case KeyEvent.VK_LEFT:
				sonic.accelerateLeft();
				break;
			case KeyEvent.VK_SPACE: 
				sonic.jump();
				break;
			case KeyEvent.VK_DOWN:
				sonic.beBall();
		}
	}
		
		
		
	public void keyReleased (KeyEvent e){
		int key = e.getKeyCode();
		switch (key){
		case KeyEvent.VK_RIGHT: 
			sonic.startDecelerateRight();
			break;
		case KeyEvent.VK_LEFT:
			sonic.startDecelerateLeft();
			break;
		case KeyEvent.VK_DOWN:
			sonic.beSonic();
		}
	}
	public void keyTyped(KeyEvent e){
		
	}
}
