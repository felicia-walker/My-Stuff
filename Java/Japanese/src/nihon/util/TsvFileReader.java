package nihon.util;

public class TsvFileReader extends DelimitedFileReader {

	public TsvFileReader(String filename) {
		super(filename);
		this.delimiter = "\t";
	}

	private TsvFileReader(String filename, String delimiter) {
		super(filename, delimiter);
		// TODO Auto-generated constructor stub
	}

}
