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
		if (key == KeyEvent.VK_RIGHT){
			if (vx < vmax){
			vx+=1;
			}
		}
		if (key == KeyEvent.VK_LEFT){
			if ( vx >-vmax){
			vx-=1;
			}
		}
			
		if (key == KeyEvent.VK_UP){
			vy+=1;
			
		}
		if (key == KeyEvent.VK_DOWN){
			isBall= true;
		}
		}
		
	public void keyReleased (KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT){
			Sonic.tryAccelerate();
		}
			
		}
		if (key == KeyEvent.VK_LEFT){
			if (vx > 0){
				vx+=1;
			}
		}
		if (key == KeyEvent.VK_UP){
			dy=0;
		}
		if (key == KeyEvent.VK_DOWN){
			isBall= false;
		}
	}
}
