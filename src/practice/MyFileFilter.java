package practice;

import java.io.File;
import java.io.FileFilter;

public class MyFileFilter implements FileFilter{

	String suffix;
	
	MyFileFilter(String suffix){
		this.suffix = suffix;
	}
	
	public boolean accept(File pathname) {
		return pathname.toString().endsWith(suffix);
	}

}
