package chapter5_measures;

public class TkgWeight {
	public static final int G_IN_KG = 1000;
	public static final double LB_IN_KG = 2.2;
	public static final int LB_IN_T = 2000;
	public static final double KG_IN_T = (double)(LB_IN_T / LB_IN_KG);
	public static final double G_IN_T = (double)(KG_IN_T * G_IN_KG);
	
	private double _weight_t = 0;
	private double _weight_kg = 0;
	private int _weight_g = 0;
	
	public TkgWeight () {
	}
	
	public TkgWeight (int g) {
		this._weight_g = g;
		this._weight_kg = g / (double)G_IN_KG;
		this._weight_t = g / (double)G_IN_T;
	}
	
	public TkgWeight (double kg) {
		this._weight_g = (int)(kg * G_IN_KG);
		this._weight_kg = this._weight_g / (double)G_IN_KG;
		this._weight_t = this._weight_g / (double)G_IN_T;
	}
	
	// Getters
	public double getWeightTons() {
		return this._weight_t;
	}
	
	public double getWeightKilograms() {
		return this._weight_kg;
	}
	
	public int getWeightGrams() {
		return this._weight_g;
	}
	
	// Public methods
	public TkgWeight add(TkgWeight obj) {
		TkgWeight tmpObj = new TkgWeight(this._weight_g + obj.getWeightGrams());
		
		return tmpObj;
	}

	public TkgWeight subtract(TkgWeight obj) {
		int tmpValue = this._weight_g - obj.getWeightGrams();
		TkgWeight tmpObj;
		
		if (tmpValue < 0) {
			System.out.println("Length is negative!");
			tmpObj = new TkgWeight();			
		} else {
			tmpObj = new TkgWeight(tmpValue);
		}
		
		return tmpObj;
	}

	public TkgWeight multiply(int value) {
		TkgWeight tmpObj = new TkgWeight(this._weight_g * value);
		
		return tmpObj;
	}

	public TkgWeight divide(int value) {
		TkgWeight tmpObj;

		if (value == 0) {
			System.out.println("Trying to divide by zero");
			tmpObj = new TkgWeight();			
		} else {
			int tmpValue = Math.round(this._weight_g / (float)value);
			tmpObj = new TkgWeight(tmpValue);
		}
		
		return tmpObj;
	}
	
	public int compare(TkgWeight obj) {
		int ret_val = -1;
		
		if (this._weight_g < obj.getWeightGrams()) {
			ret_val = -1;
		} else if (this._weight_g == obj.getWeightGrams()) {
			ret_val = 0;
		} else {
			ret_val = 1;
		}
		
		return ret_val;
	}
}
