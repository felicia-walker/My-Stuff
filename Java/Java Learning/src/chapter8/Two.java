package chapter8;

public class Two {

	private static void printStuff(String[] stuff) {
		for (String word : stuff) {
			System.out.println(word);
		}
	}
	
	public static void main(String[] args) {

		TwoParser myParser = new TwoParser();
		TwoParser myParser2 = new TwoParser('/');
				
		System.out.println("Seperator is comma");
		printStuff(myParser.parseKeyboard());
		
		System.out.println("Seperator is slash");
		printStuff(myParser2.parseKeyboard());
		
		System.out.println("Seperator is I");
		myParser.setSeperator('I');
		printStuff(myParser.parseKeyboard());
	}
}
