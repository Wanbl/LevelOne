package coordinates;

public class Point2D {
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Point2D(int x, int y) {
		this.setX(x);
		this.setY(y);
		
	}
	
	public String toString() {
		return ("Point de coordonnées : "+this.getX()+","+this.getY());
	}
	
	public boolean equals(Object o) {
		if(o instanceof Point2D) {
			Point2D t = (Point2D) o;
			if(t.getX() == this.getX() && t.getY() == this.getY()) {
				return true;
			}
		}
		return false;
	}
	
	public void translateX(int x) {
		this.setX(this.getX()+x);
	}
	
	public void translateY(int y) {
		this.setY(this.getY()+y);
	}
}
