package chapter9;

import java.io.File;
import java.io.FileFilter;

public class ThreeFilter implements FileFilter {

	public ThreeFilter() {
	}

	@Override
	public boolean accept(File file) {
		boolean ret_val = false;
		
		if (file.isDirectory()) {
			ret_val = true;
		}
		
		return ret_val;
	}

}
