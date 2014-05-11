package sonic.view;

import java.awt.Graphics;

import javax.swing.JPanel;

public interface Drawable {
	public void paint(Graphics g, JPanel p, int sonicPosX);
}
