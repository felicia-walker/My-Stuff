package chapter5;

import chapter5_measures.*;

public class CalculateStuff {
	
	static class CarpetA {
		public static McmLength width = new McmLength(4000);
		public static McmLength height = new McmLength(2090);
		public static double area = width.area(height) / (1000 * 1000);

		public static double unit_weight = 1.25;
		public static TkgWeight total_weight = new TkgWeight(area * unit_weight);
	}

	static class CarpetB {
		public static McmLength width = new McmLength(3570);
		public static McmLength height = new McmLength(5000);
		public static double area = width.area(height) / (1000 * 1000);

		public static double unit_weight = 1.05;
		public static TkgWeight total_weight = new TkgWeight(area * unit_weight);
	}

	public static void main(String[] args) {
		TkgWeight carpetA_weight = CalculateStuff.CarpetA.total_weight.multiply(200);
		TkgWeight carpetB_weight = CalculateStuff.CarpetB.total_weight.multiply(60);
		TkgWeight total_weight = carpetA_weight.add(carpetB_weight);
		
//		System.out.printf("Total weight (tons): %f\n", carpetA_weight.getWeightTons());
//		System.out.printf("Total weight (kg): %f\n", carpetA_weight.getWeightKilograms());
//		System.out.printf("Total weight (g): %d\n", carpetA_weight.getWeightGrams());
//
//		System.out.printf("Total weight (tons): %f\n", carpetB_weight.getWeightTons());
//		System.out.printf("Total weight (kg): %f\n", carpetB_weight.getWeightKilograms());
//		System.out.printf("Total weight (g): %d\n", carpetB_weight.getWeightGrams());

		System.out.printf("Total weight (tons): %f\n", total_weight.getWeightTons());
		System.out.printf("Total weight (kg): %f\n", total_weight.getWeightKilograms());
		System.out.printf("Total weight (g): %d\n", total_weight.getWeightGrams());
	}

}
