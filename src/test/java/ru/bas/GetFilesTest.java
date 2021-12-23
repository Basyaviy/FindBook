package ru.bas;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class GetFilesTest {
	final String resoursePath = getClass().getResource("/").getPath();

//	@Test
//	final void listOfFiles() {
//		File dir = new File(resoursePath + "testLib");
//		ArrayList<File> actual = GetFiles.listOfFiles(dir);
//		File file = new File(resoursePath + "listOfFilesNec.txt");
//		Scanner scanner = null;
//		ArrayList<String> expectedList = new ArrayList<>();
//		try {
//			scanner = new Scanner(file, "UTF-8");
//
//			while (scanner.hasNext()) {
//				String nextLine = scanner.nextLine();
//				expectedList.add(nextLine);
//			}
//			scanner.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		String[] expected = expectedList.toArray(new String[expectedList.size()]);
//		String[] strActual = new String[actual.size()];
//		for(int i = 0;i<actual.size();i++) {
//			strActual[i] = actual.get(i).getName();
//		}
//		
//		assertArrayEquals(expected, strActual);
//	}

}
