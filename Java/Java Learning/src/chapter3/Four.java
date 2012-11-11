package chapter3;

public class Four {

	public static void main(String[] args) {
		final int MAX_CHARS = 50;
		final int B = (int)'B';
		final int Z = (int)'Z';
		
		for (int i = 0; i < MAX_CHARS; i++) {
			char newChar = (char)(B + (int)((Z - B + 1) * Math.random()));
			switch (newChar) {
				case 'E':
				case 'I':
				case 'O':
				case 'U':
					continue;
					
				default:
					System.out.print(newChar);
			}
		}
	}

}
