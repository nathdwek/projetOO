package projetInfo;
import java.awt.event.KeyEvent;

public class Sonic implements movable{
	public void move(){
		x+=vx*t;
		y+=dy
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
		
	public void keyRealesed (.KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT){
			if (vx < 0){
				vx-=1;
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
