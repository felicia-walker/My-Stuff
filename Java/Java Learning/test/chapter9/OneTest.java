package chapter9;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.File;

public class OneTest {

	@Test
	public void testMain() {	
		String filename1 = "testone";
		String filename2 = "testone_old";
		String[] myArgs = {filename1};	
		
		One.main(myArgs);	
		File testFile = new File(filename1);
		assertTrue(testFile.isFile());

		One.main(myArgs);	
		File testFile2 = new File(filename2);
		assertTrue(testFile.isFile());
	}
}
