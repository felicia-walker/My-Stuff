package chapter6;

public class ShapeList {

	private Shape[] _shapeList = new Shape[10];
	private int _lastIndex = 0;
	
	ShapeList() {
	}
	
	ShapeList(Shape firstShape) {
		add(firstShape);
	}
	
	// Getters
	public Shape getShapeAt(int index) {
		return _shapeList[index];
	}
	
	public int length() {
		return _lastIndex;
	}
	
	// Public methods
	public void add(Shape addShape) {
		_shapeList[_lastIndex] = addShape;
		_lastIndex++;
	}
	
	public void removeAt(int index) {
		Shape[] tmpShapeList = new Shape[10];
		
		// Copy up to our delete index
		for (int i = 0; i < index; i++) {
			tmpShapeList[i] = _shapeList[i];
		}

		// Copy everything after the delete index
		if (index + 1 <= _lastIndex) {
			for (int i = index + 1; i < _lastIndex; i++) {
				tmpShapeList[i - 1] = _shapeList[i];
			}
		}
			
		_shapeList = tmpShapeList;
		_lastIndex--;
	}
	
	public void printList() {
		for (int i = 0; i < _lastIndex; i++) {
			_shapeList[i].show();
		}		
	}
}
