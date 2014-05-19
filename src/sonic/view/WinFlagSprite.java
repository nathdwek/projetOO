package sonic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import sonic.model.WinFlag;

public class WinFlagSprite implements Drawable {

	private static final Image sprite = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/winFlag.png");
	private WinFlag winFlag;

	public WinFlagSprite(WinFlag flag){
		this.winFlag = flag;
	}

	@Override
	public void draw(Graphics g, JPanel p, Integer left, Integer top,
			Integer windowWidth, Integer windowHeight) {
		int posX =  winFlag.getPosition().getX().intValue();
		int posY = winFlag.getPosition().getY().intValue();
		int thisLeft = posX-winFlag.getSize(2).intValue()- left;
		int width = Double.valueOf(winFlag.getSize(2)+winFlag.getSize(0)).intValue();
		int thisTop = posY+winFlag.getSize(1).intValue()- top;
		int height = Double.valueOf(winFlag.getSize(1)+winFlag.getSize(3)).intValue();
		g.drawImage(sprite, thisLeft, windowHeight-thisTop,width,height, p);
		g.finalize();
	}

}
