package sonic.view;

import java.awt.Graphics;

import javax.swing.JPanel;

public interface Drawable {
	public void draw(Graphics g, JPanel p, Integer left, Integer top, Integer windowWidth , Integer windowHeight);
}
