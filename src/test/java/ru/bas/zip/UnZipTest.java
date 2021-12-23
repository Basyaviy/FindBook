package ru.bas.zip;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class UnZipTest {
	final String resourcePath = getClass().getResource("/").getPath();
	final static String workDir = "c:/temp/";

	@Test
	final void testMultipleZip() {
		Utils.deleteNonEmptyDirectory(workDir);
		File zipFile = new File(workDir+"test.zip");
//		prepare data for packing
		List<File> listFiles = new ArrayList<>();
		String[] expected = {"listOfFiles.txt", "listOfFilesNec.txt","parsedByTags.txt"};
		for(String str:expected) {
			listFiles.add(new File(resourcePath + str));
		}
		
//		packing
		UnZip.multipleZip(zipFile.getAbsolutePath(), listFiles);
		
//		unpacking
		UnZip.unpack(zipFile.getAbsolutePath(), workDir);
		//delete archive
		zipFile.delete();
		
		
//		get unpacked files for assertion
		File dir = new File(workDir);
		File[] files = dir.listFiles();
		String[] actual = new String[files.length];
		for(int i=0;i<files.length;i++) {
			actual[i] = files[i].getName();
		}
		
		assertArrayEquals(expected, actual);
		Utils.deleteNonEmptyDirectory(workDir);
	}

	@Test
	final void testUnpack() {
		Utils.deleteNonEmptyDirectory(workDir);
		String expected = "Gektor_Hyu_Manro_Otkryitaya_dver.fb2";
		UnZip.unpack(resourcePath+"/testLib/books2.zip", workDir, expected);
		
		File file = new File(workDir);
//		optimistic
		String actual = file.listFiles()[0].getName();
		assertEquals(expected, actual);
		Utils.deleteNonEmptyDirectory(workDir);
	}
	
	@Test
	final void testUnpackNullOutput() {
		Utils.deleteNonEmptyDirectory(workDir);
		String expected = "Gektor_Hyu_Manro_Otkryitaya_dver.fb2";
		UnZip.unpack(resourcePath+"/testLib/books2.zip", null, expected);
		
		File file = new File(workDir);
//		optimistic
		String actual = file.listFiles()[0].getName();
		assertEquals(expected, actual);
		Utils.deleteNonEmptyDirectory(workDir);
	}

	@Test
	final void testGetCountPack() {
//		public static int getCountPack(String zipPath) {
		int actual = UnZip.getCountPack(resourcePath+"/testLib/books2.zip");
		assertEquals(24, actual);
	}

}
