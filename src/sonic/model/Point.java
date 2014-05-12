package sonic.model;

public class Point {

	private Double x;
	private Double y;

	public Point(Double x, Double y){
		this.x=x;
		this.y=y;
	}

	public Point(Integer x, Integer y){
		this.x=Double.valueOf(x);
		this.y=Double.valueOf(y);
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

	public Point times(Double dT) {
		return new Point(dT*x,dT*y);
	}

	public void add(Point dR) {
		this.x+=dR.x;
		this.y+=dR.y;
	}
	public void add(double dX, double dY) {
		this.x+=dX;
		this.y+=dY;
	}

	public String toString(){
		return "("+ String.valueOf(x)+","+String.valueOf(y)+")";
	}

	public void setZero() {
		this.x=0.0;
		this.y=0.0;
	}

	public Point copy() {
		return new Point(this.x,this.y);
	}
}
