package chapter3;

public class One {

	public static void main(String[] args) {
		int num = 1 + (int)(6 * Math.random());
		
		String foodie = "";
		switch (num) {
			case 1:
				foodie = "biscuits and gravy";
				break;
				
			case 2:
				foodie = "miso and rice";
				break;
				
			case 3:
				foodie = "fruit loops";
				break;
				
			case 4:
				foodie = "pizza";
				break;
				
			case 5:
				foodie = "bento surprise";
				break;
				
			case 6:
				foodie = "candy";
				break;
				
			default:
				foodie = "ERROR!";
		}
		
		System.out.println("For breakfast we have " + foodie);
	}
}
