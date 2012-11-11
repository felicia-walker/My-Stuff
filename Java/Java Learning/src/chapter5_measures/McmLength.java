package chapter5_measures;

public class McmLength {

	private static final int MM_IN_CM = 10;
	private static final int CM_IN_M = 100;
	private static final int MM_IN_M = 1000;
	
	private int _len_m = 0;
	private int _len_cm = 0;
	private int _len_mm = 0;
	
	public McmLength () {
	}
	
	public McmLength (int mm) {
		this._len_mm = mm;
		this._len_cm = Math.round(mm / (float)MM_IN_CM);
		this._len_m = Math.round(mm / (float)MM_IN_M);
	}
	
	public McmLength (double cm) {
		this._len_mm = (int)(cm * MM_IN_CM);
		this._len_cm = Math.round(this._len_mm / (float)MM_IN_CM);
		this._len_m = Math.round(this._len_mm / (float)MM_IN_M);
	}
	
	// Getters
	public int getLengthCentimeters() {
		return this._len_m;
	}
	
	public int getLengthCM() {
		return this._len_cm;
	}
	
	public int getLengthMillimeters() {
		return this._len_mm;
	}
	
	// Public methods
	public McmLength add(McmLength obj) {
		McmLength tmpObj = new McmLength(this._len_mm + obj.getLengthMillimeters());
		
		return tmpObj;
	}

	public McmLength subtract(McmLength obj) {
		int tmpValue = this._len_mm - obj.getLengthMillimeters();
		McmLength tmpObj;
		
		if (tmpValue < 0) {
			System.out.println("Length is negative!");
			tmpObj = new McmLength();			
		} else {
			tmpObj = new McmLength(tmpValue);
		}
		
		return tmpObj;
	}

	public McmLength multiply(int value) {
		McmLength tmpObj = new McmLength(this._len_mm * value);
		
		return tmpObj;
	}

	public McmLength divide(int value) {
		McmLength tmpObj;

		if (value == 0) {
			System.out.println("Trying to divide by zero");
			tmpObj = new McmLength();			
		} else {
			int tmpValue = Math.round(this._len_mm / (float)value);
			tmpObj = new McmLength(tmpValue);
		}
		
		return tmpObj;
	}
	
	public double area(McmLength obj) {
		return this._len_mm * obj.getLengthMillimeters();
	}
	
	public int compare(McmLength obj) {
		int ret_val = -1;
		
		if (this._len_mm < obj.getLengthMillimeters()) {
			ret_val = -1;
		} else if (this._len_mm == obj.getLengthMillimeters()) {
			ret_val = 0;
		} else {
			ret_val = 1;
		}
		
		return ret_val;
	}
}
