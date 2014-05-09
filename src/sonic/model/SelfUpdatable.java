package sonic.model;

import java.awt.Graphics;

public interface SelfUpdatable {
	public void selfUpdate(Double dT);

	public void paint(Graphics g);
}