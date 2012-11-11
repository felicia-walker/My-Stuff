package chapter9;

import java.io.File;

public class Three {

	public static void main(String[] args) {
		File[] dirs;
		
		switch(args.length) {
			case 0: 
				dirs = File.listRoots();
				System.out.println(dirs.length);
				for (File curdir : dirs) {
					processDir(curdir);
				}
				
				break;
			case 1:
				File dir = new File(args[0]);
				if (dir.exists()) {
					processDir(dir);
				} else {
					System.out.println("Directory does not exist!");
				}
				
				break;
		
			default:
				System.out.println("Too many arguments!");
				System.exit(1);
		}		
	}
	
	private static void processDir(File dir) {
		ThreeFilter filter = new ThreeFilter();
		File[] dirs = dir.listFiles(filter);
		for (File curdir : dirs) {
			System.out.println(curdir.getName());
		}
	}
}
