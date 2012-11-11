package chapter2;

public class One {

	public static void main(String[] args) {
		byte var = 1;
		
		for (byte i = 0; i < 8; i++) {
			var <<= 1;
			System.out.println(var);
		}
	}

}
