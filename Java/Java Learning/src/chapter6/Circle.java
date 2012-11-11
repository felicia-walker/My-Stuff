package chapter6;

public class Circle extends Shape {

	private int radius = 0;
	
	public Circle() {
		super();
	}

	public Circle(int x, int y, int radius) {
		super(x, y);

		if (radius <= 0) {
			System.out.println("Radius must be positive, resetting to 0...");
			this.radius = 0;
		} else {
			this.radius = radius;
		}
	}

	public void show() {
		System.out.printf("Circle with radius %d centered at (%d, %d).\n", this.radius, this.x, this.y);
	}

	public String toString() {
		StringBuffer tmpString = new StringBuffer();
		tmpString.append(this.x);
		tmpString.append(",");
		tmpString.append(this.y);
		tmpString.append(",");
		tmpString.append(this.radius);
		
		return tmpString.toString();
	}
}
