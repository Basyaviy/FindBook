package ru.bas.zip;

import java.io.File;

import java.io.File;

public class Utils {
	final static private String TEMP_DIRECTORY = "c:/temp/";
	
	
	public static String getTempDirectory() {
		return TEMP_DIRECTORY;
	}
	
	//используется для чистки временного каталога
	public static void deleteNonEmptyDirectory(String dir_path) {
		File dir = new File(dir_path);
		File[] files = dir.listFiles();
		for (File file : files) {
			file.delete();
		}
	}

}
