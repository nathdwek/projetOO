package sonic.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import sonic.model.Point;

public interface Drawable {
	public void paint(Graphics g, JPanel p, Point center);
}
