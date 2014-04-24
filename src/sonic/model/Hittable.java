package sonic.model;
public interface Hittable {
	
	public Hitbox getHitbox();
	public String getType();
	public void handleCollision(Hittable hittable, Integer angle);
	
}
