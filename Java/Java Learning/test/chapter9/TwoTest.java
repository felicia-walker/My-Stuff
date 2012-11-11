package chapter9;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.File;

public class TwoTest {

	@Test
	public void testMain() {	
		String filename1 = "testone.a.b";
		String filename2 = "testone.a_001.b";
		String filename2a = "testone.a_002.b";
		String filename3 = "testone";
		String filename4 = "testone_001";
		String[] myArgs = {filename1};	
		
		Two.main(myArgs);	
		File testFile = new File(filename1);
		assertTrue(testFile.isFile());

		Two.main(myArgs);	
		File testFile2 = new File(filename2);
		assertTrue(testFile2.isFile());	

		Two.main(myArgs);	
		File testFile2a = new File(filename2a);
		assertTrue(testFile2a.isFile());
		
		myArgs[0] = filename3;
		Two.main(myArgs);	
		File testFile3 = new File(filename3);
		assertTrue(testFile3.isFile());

		Two.main(myArgs);	
		File testFile4 = new File(filename4);
		assertTrue(testFile4.isFile());	}
}
