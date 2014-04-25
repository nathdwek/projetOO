package sonic.model;

public class Point {

	private Double x;
	private Double y;

	public Point(Double x, Double y){
		this.x=x;
		this.y=y;

	}
	public Double getX(){
		return x;
	}
	public Double getY(){
		return y;
	}
	public void setX(double x){
		this.x=x;
	}
	public void setY(double y){
		this.y=y;
	}
}
