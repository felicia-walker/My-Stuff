package chapter7;

import java.lang.Exception;
import java.util.Vector;

public class MyException extends Exception {
	
	private boolean overMax = false;

	public MyException() {
	}

	public MyException(String message) {
		super(message);
	}

	public MyException(int[] array, int index) {
		if (index > array.length) {
			overMax = true;
		} 
	}

	public MyException(Vector v, int index) {
		if (index > v.size()) {
			overMax = true;
		} 
	}

	public MyException(String s, int index) {
		if (index > s.length()) {
			overMax = true;
		} 
	}
	
	public String getMessage() {
		if (overMax) {
			return "Index was larger than the size of the object.";
		} else {
			return "Index was smaller than the lower index of the object.";
		}
	}
}
