package chapter5;

public class Rectangle {

	private int _topLeftX = 0;
	private int _topLeftY = 0;
	private int _bottomRightX = 0;
	private int _bottomRightY = 0;
	
	Rectangle() {
	}
	
	Rectangle (int x1, int y1, int x2, int y2) {
		this._topLeftX = x1;
		this._topLeftY = y1;
		this._bottomRightX = x2;
		this._bottomRightY = y2;
	}
	
	Rectangle (Rectangle rect) {
		this._topLeftX = rect.getTopLeftX();
		this._topLeftY = rect.getTopLeftY();
		this._bottomRightX = rect.getBottomRightX();
		this._bottomRightY = rect.getBottomRightY();
	}
	
	// Getters and setters
	int getTopLeftX() {
		return _topLeftX;
	}
	
	int getTopLeftY() {
		return _topLeftY;
	}
	
	int getBottomRightX() {
		return _bottomRightX;
	}
	
	int getBottomRightY() {
		return _bottomRightY;
	}
	
	void setTopLeftX(int topLeftX) {
		this._topLeftX = topLeftX;
	}
	
	void setTopLeftY(int topLeftY) {
		this._topLeftY = topLeftY;
	}
	
	void setBottomRightX(int bottomRightX) {
		this._bottomRightX = bottomRightX;
	}
	
	void setBottomRightY(int bottomRightY) {
		this._bottomRightY= bottomRightY;
	}
	
	// Public methods
	Rectangle getEnclosingRect(Rectangle otherRect) {
		Rectangle tmpRect = new Rectangle();
		
		if (this._topLeftX < otherRect.getTopLeftX()) {
			tmpRect.setTopLeftX(this._topLeftX);
		} else {
			tmpRect.setTopLeftX(otherRect.getTopLeftX());
		}
		
		if (this._topLeftY < otherRect.getTopLeftY()) {
			tmpRect.setTopLeftY(this._topLeftY);
		} else {
			tmpRect.setTopLeftY(otherRect.getTopLeftY());
		}
		
		if (this._bottomRightX > otherRect.getBottomRightX()) {
			tmpRect.setBottomRightX(this._bottomRightX);
		} else {
			tmpRect.setBottomRightX(otherRect.getBottomRightX());
		}
		
		if (this._bottomRightY > otherRect.getBottomRightY()) {
			tmpRect.setBottomRightY(this._bottomRightY);
		} else {
			tmpRect.setBottomRightY(otherRect.getBottomRightY());
		}
		
		return tmpRect;
	}
	
	void printCorners() {
		System.out.printf("Corners: (%d,%d), (%d,%d), (%d,%d), (%d,%d)\n", 
				this._topLeftX, this._topLeftY,
				this._bottomRightX, this._topLeftY,
				this._topLeftX, this._bottomRightY,
				this._bottomRightX, this._bottomRightY);
	}
}
