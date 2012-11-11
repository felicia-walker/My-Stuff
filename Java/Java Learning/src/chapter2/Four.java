package chapter2;

import static java.lang.Math.*;

public class Four {

	public static void main(String[] args) {
		final int SUN_DIAMETER = 865000;
		final int EARTH_DIAMETER = 7600;
		
		double earth_vol = (4/3) * PI * pow(EARTH_DIAMETER, 3);
		double sun_vol = (4/3) * PI * pow(SUN_DIAMETER, 3);
		float ratio = (float)(sun_vol / earth_vol);
		
		System.out.println("Sun's volume is " + sun_vol);
		System.out.println("Earth's volume is " + earth_vol);
		System.out.println("Ratio is " + ratio);
	}

}
