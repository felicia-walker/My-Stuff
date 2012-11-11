package chapter4;

public class One {

	public static void main(String[] args) {
	
		// Intialize our month array
		String[] months = {"January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
		int numMonths = months.length;
				
		// Make our random array
		float[] nums = new float[numMonths];
		for (int i = 0; i < numMonths; i++) {
			nums[i] = (float)(1000 * Math.random());
		}
		
		// Show em all and make the average too
		float total = 0F;
		for (int i = 0; i < numMonths; i++) {
			System.out.println(months[i] + " " + nums[i]);
			total += nums[i];
		}
		
		float average = (float)(total / numMonths);
		System.out.println("Average is " + average);
	}

}
