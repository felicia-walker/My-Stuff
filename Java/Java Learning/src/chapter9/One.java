package chapter9;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class One {

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
				int period = name.indexOf('.');
				
				// Make sure to deal with null parents
				String parent = newFile.getParent();
				if (parent == null) {
					parent = "";
				}

				// If there is no period, there is no extension
				if (period > -1 ) {
					newFile = new File(parent + name.substring(0, period) + "_old" + name.substring(period));
				} else {
					newFile = new File(parent + name + "_old");
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
