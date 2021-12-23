package ru.bas.zip;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class UnZipTest {
	final String resourcePath = getClass().getResource("/").getPath();

	@Test
	final void testMultipleZip() {
//		prepare data for packing
		List<File> listFiles = new ArrayList<>();
		String[] expected = {"listOfFiles.txt", "listOfFilesNec.txt","parsedByTags.txt"};
		for(String str:expected) {
			listFiles.add(new File(resourcePath + str));
		}
		
//		packing
		UnZip.multipleZip(resourcePath+"test.zip", listFiles);
		
//		unpacking
		String unpackDir = "c:/temp/";
		UnZip.unpack(resourcePath+"test.zip", unpackDir);
		
//		get unpacked files for assertion
		File dir = new File(unpackDir);
		File[] files = dir.listFiles();
		String[] actual = new String[files.length];
		for(int i=0;i<files.length;i++) {
			actual[i] = files[i].getName();
		}
		
		assertArrayEquals(expected, actual);
	}

	@Test
	final void testUnpack() {
		String expected = "Gektor_Hyu_Manro_Otkryitaya_dver.fb2";
		String unpackDir = "c:/temp";
		UnZip.unpack(resourcePath+"/testLib/books2.zip", expected, unpackDir);
		
		File file = new File(unpackDir);
//		optimistic
		String actual = file.listFiles()[0].getName();
		assertEquals(expected, actual);
	}
	
	@Test
	final void testUnpackNullOutput() {
		String expected = "Gektor_Hyu_Manro_Otkryitaya_dver.fb2";
		String unpackDir = "c:/temp";
		UnZip.unpack(resourcePath+"/testLib/books2.zip", expected, null);
		
		File file = new File(unpackDir);
//		optimistic
		String actual = file.listFiles()[0].getName();
		assertEquals(expected, actual);
	}

	@Test
	final void testGetCountPack() {
//		public static int getCountPack(String zipPath) {
		int actual = UnZip.getCountPack(resourcePath+"/testLib/books2.zip");
		assertEquals(24, actual);
	}

}
