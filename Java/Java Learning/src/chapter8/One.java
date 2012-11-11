package chapter8;

import java.io.StreamTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class One {

	public static void main(String[] args) {
		
		// Set up our readers
		InputStreamReader tmpInReader = new InputStreamReader(System.in);
		BufferedReader tmpBufReader = new BufferedReader(tmpInReader);
		
		// Set up the tokenizer
		StreamTokenizer tokenizer = new StreamTokenizer(tmpBufReader);
		tokenizer.resetSyntax();
		tokenizer.wordChars((int)'\u0000', (int)(',' - 1));
		tokenizer.wordChars((int)(',' + 1), (int)'\u00FF');
		tokenizer.whitespaceChars((int)',', (int)',');
		tokenizer.whitespaceChars((int)'\n', (int)'\n');
		
		// Do this thing
		try {
			int ttype;
			while ((ttype = tokenizer.nextToken()) != tokenizer.TT_EOF) {
				if (ttype == tokenizer.TT_WORD) {
					System.out.println(tokenizer.sval);
				}
			}
			
		} catch (IOException e) {
			System.out.println("IO exception: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

}
