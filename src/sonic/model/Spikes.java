package sonic.model;

import sonic.view.SpikesSprite;

public class Spikes extends GroundBlock{

	public Spikes(Double X, Double Y) {
		super(X,X+getDefaultWidth(),Y-10,Y+getDefaultWidth()-10);
		setSprite(new SpikesSprite(this));
	}

	public Boolean handleCollision(Hittable otherHittable, Point normal){
		return otherHittable.handleSpikes(normal);
	}
}
