package chapter2;

public class Three {

	public static void main(String[] args) {
		float income = 88642;
		float tax_rate = 35F;
		
		float tax_paid = income * (tax_rate / 100);
		int tax_dollars = (int)tax_paid;
		int tax_cents = (int)((tax_paid - tax_dollars) * 100);
		
		System.out.println("Tax paid is $" + tax_dollars + "." + tax_cents);
	}

}
