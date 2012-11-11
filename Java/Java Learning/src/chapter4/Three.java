package chapter4;

public class Three {

	public static void main(String[] args) {
		String myText = "This is my paragraph, so if you don't like it you can " +
				"suck it, chumpy! Jasper is at the window being a buddy dog.  Miso " +
				"is under the table being the Noo that she is.  Oh happy day!";
		
		String[] splitText = myText.split("[., !?;:]", 0);

		// Iterate through the string many times until sorted
		boolean sorted = false;
		do {
			
			sorted = true;
			// Go through all the words and bubble sort
			for (int i = 0; i < splitText.length - 1; i++) {
				String curWord = splitText[i];
				String nextWord = splitText[i+1];
				
				// Swap if next word is less
				if (curWord.compareToIgnoreCase(nextWord) > 0) {
					splitText[i] = nextWord;
					splitText[i+1] = curWord;
					sorted = false;
				}
			}
		} while (!sorted);
		
		for (String curWord : splitText) {
			if (curWord.length() > 0) {
				System.out.println(curWord);
			}
		}
	}
}
