package chapter9;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Two {

	public static void main(String[] args) {
		if (args.length > 1) {
			System.out.println("Too many arguments!");
			System.exit(1);
		}
		
		// Make a file object
		String filename = args[0];		
		File aFile = new File(filename);
		FileOutputStream outputFile = null;
		
		// Process is it exists
		File newFile = aFile;
		if (aFile.isFile()) {			
			do {
				String name = newFile.getName();
				
				// Get the last instance of a period and underscore
				int period = name.lastIndexOf('.');
				int underscore = name.lastIndexOf('_');

				// Make sure to deal with null parents
				String parent = newFile.getParent();
				if (parent == null) {
					parent = "";
				}
				
				// Get the number if it exists
				String number = "001";
				if (underscore > -1) {
					number = name.substring(underscore + 1, underscore + 4);
					Integer intNum = Integer.valueOf(number) + 1;
					if (intNum < 10) {
						number = "00" + intNum.toString();
					} else if (intNum < 100) {
						number = "0" + intNum.toString();
					} else {
						number = intNum.toString();
					}
				} 

				// If there is no period, there is no extension
				if (period > -1 ) {
					if (underscore > -1) {
						newFile = new File(parent + name.substring(0, underscore) + "_" + number + name.substring(period));					
					} else {
						newFile = new File(parent + name.substring(0, period) + "_" + number + name.substring(period));
					}
				} else {
					if (underscore > -1) {
						newFile = new File(parent + name.substring(0, underscore) + "_" + number);
					} else {
						newFile = new File(parent + name + "_" + number);
					}
				}
			} while (newFile.exists());
			
			aFile.renameTo(newFile);
		}
		
		try {
			outputFile = new FileOutputStream(aFile);
			System.out.printf("New file %s created\n", newFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
		}
	}

}
