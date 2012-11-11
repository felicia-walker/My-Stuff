package chapter6;

public abstract class Shape implements ShapeInt {

	protected int x = 0;
	protected int y = 0;
	
	Shape() {
	}
	
	Shape(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(int new_x, int new_y) {
		this.x = new_x;
		this.y = new_y;
	}
	

}
