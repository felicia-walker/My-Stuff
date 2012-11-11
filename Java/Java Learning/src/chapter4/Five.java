package chapter4;

public class Five {

	public static void main(String[] args) {
		String myText = "This is my paragraph, so if you don't like it you can " +
				"suck it, chumpy! Jasper is at the window being a buddy dog.  Miso " +
				"is under the table being the Noo that she is.  Oh happy day!";
		
		// Go through a letter at a time making words
		StringBuilder wordBuilder = new StringBuilder();
		for (char curChar: myText.toCharArray()) {
			switch (curChar) {
				case ' ':
				case '\'':
				case '"':
				case '.':
				case ',':
				case '?':
				case '!':
					// End of a word, so reverse what we have so far
					char[] word = wordBuilder.toString().toCharArray();
					for (int i = word.length - 1; i >= 0; i--) {
						System.out.print(word[i]);
					}
					System.out.print(curChar);
					
					// Clear the word builder so we can start again
					wordBuilder.delete(0, wordBuilder.length());
					break;
					
				default:
					// Append the letter to the current word and keep going
					wordBuilder.append(curChar);
			}
		}
	}
}
