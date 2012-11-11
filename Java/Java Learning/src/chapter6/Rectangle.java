package chapter6;

public class Rectangle extends Shape {

	private int end_x = 0;
	private int end_y = 0;
	
	public Rectangle() {
		super();
	}

	public Rectangle(int x, int y, int end_x, int end_y) {
		super(x, y);
		this.end_x = end_x;
		this.end_y = end_y;
	}
	
	public void move(int new_x, int new_y) {
		int amt_x = this.end_x - this.x;
		int amt_y = this.end_y - this.y;
		
		super.move(new_x, new_y);
		this.end_x = this.x + amt_x;
		this.end_y = this.y + amt_y;
	}

	public void show() {
		System.out.printf("Rectangle from upper left (%d, %d) to lower right (%d, %d).\n", this.x, this.y, this.end_x, this.end_y);
	}

	public String toString() {
		StringBuffer tmpString = new StringBuffer();
		tmpString.append(this.x);
		tmpString.append(",");
		tmpString.append(this.y);
		tmpString.append(",");
		tmpString.append(this.end_x);
		tmpString.append(",");
		tmpString.append(this.end_y);
		
		return tmpString.toString();
	}
}
