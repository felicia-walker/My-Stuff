package chapter8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.io.IOException;
import java.util.Vector;

public class TwoParser {
	
	final private static char DEFAULT_SEPERATOR = ',';
	
	private StreamTokenizer tokenizer;
	private char seperator;;
	
	TwoParser() {
		this(DEFAULT_SEPERATOR);
	}
	
	TwoParser(char seperator) {
		InputStreamReader tmpInReader = new InputStreamReader(System.in);
		BufferedReader tmpBufReader = new BufferedReader(tmpInReader);
		tokenizer = new StreamTokenizer(tmpBufReader);
		
		setSeperator(seperator);
	}

	// Getters and setters
	public char getSeperator () {
		return this.seperator;
	}
	
	public void setSeperator (char seperator) {
		this.seperator = seperator;
		
		tokenizer.resetSyntax();
		tokenizer.eolIsSignificant(true);
		tokenizer.wordChars((int)'\u0000', (int)(',' - 1));
		tokenizer.wordChars((int)(',' + 1), (int)'\u00FF');
		tokenizer.whitespaceChars((int)seperator, (int)seperator);
		tokenizer.whitespaceChars((int)'\n', (int)'\n');
	}
	
	// Public methods
	public String[] parseKeyboard() {
		int ttype;
		StringBuffer tmpString = new StringBuffer();
		Vector<String> tmpVector = new Vector();
		String[] ret_val = null;
		
		try {		
			while ((ttype = tokenizer.nextToken()) != tokenizer.TT_EOL) {
				if (ttype == tokenizer.TT_WORD) {
					tmpVector.add(tokenizer.sval);
				}
			}

			ret_val = new String[tmpVector.size()];
			tmpVector.copyInto(ret_val);
		} catch (IOException e) {
			System.out.println("IO exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		return ret_val;
	}

}
