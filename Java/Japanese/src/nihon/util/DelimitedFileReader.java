package nihon.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class DelimitedFileReader {

	protected BufferedReader inputstream;
	protected String delimiter;

	// Constructors -------------------------------------------------------
	public DelimitedFileReader(final String filename) {
		File file = new File(filename);
		
		try {
			inputstream = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filename);
			return;
		}
	}
	
	public DelimitedFileReader(final String filename, final String delimiter)
	{
		this(filename);
		this.delimiter = delimiter;
	}

	// Getters and setters ---------------------------------------------------
	public void setDelimiter(final String delimiter)
	{
		this.delimiter = delimiter;
	}
	
	// Public methods -------------------------------------------------------
	public ArrayList<String[]> getArrayList() throws Exception {
		if (delimiter == null || delimiter.length() == 0)
		{
			throw new Exception("Delimiter must be defined.");
		}
		
		ArrayList<String[]> list = new ArrayList<String[]>();

		try {
			String line = inputstream.readLine();
			while (line != null) {
				String[] parts = line.split(delimiter);
				list.add(parts);
				
				line = inputstream.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
