package ru.bas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import ru.bas.entity.Book;
import ru.bas.zip.UnZip;
import ru.bas.zip.Utils;

/**
 * That class observe folders and zip files in root directory, 
 * find fb2 files
 *   
 * @author bas
 *
 */

public class FileParser {
	private static ArrayList<File> listFB2 = new ArrayList<>();
	private static ArrayList<File> listZIP = new ArrayList<>();
	

	/*
	 * Look into directory, collect two Lists with 'fb2' and 'zip' extensions after
	 * getting Book object write to DB
	 */
	public static List<Book> getBookList(){
		URL url = FileParser.class.getResource("/testLib");
		File dir = new File(url.getPath());
		listOfFiles(dir);
		List<Book> bookList = new ArrayList<>();
		bookList.addAll(processFB2FileList(listFB2,  null));
		bookList.addAll(processZIPFileList(listZIP));
		return bookList;		
	}

	private static List<Book> processZIPFileList(ArrayList<File> zipList) {
		List<Book> tempBookList = new ArrayList<>();
		
		for (File zipFile : zipList) {
			// unpack zip
			
			String zipPath =zipFile.getAbsolutePath(); //current zip
			String outDirPath = System.getProperty("java.io.tmpdir")+"/" + zipFile.getName()+"/";//put here
			File currentDir = new File(outDirPath);
			boolean createDir = currentDir.mkdir();
//			System.out.println("mkdir:"+createDir);
			String target = null;//unpack all
			//list files in current zip
			List<File> currentList = UnZip.unpack(zipPath, outDirPath, target);
			
			//need two parameters: Path to the file, Size
			//Path to the file = outDirPath + name
			tempBookList.addAll(processFB2FileList(currentList, zipFile));
						
			//remove current directory
			Utils.deleteNonEmptyDirectory(currentDir.getAbsolutePath());
			currentDir.delete();
		}
		return tempBookList;
	}

	/**
	 * parse list of 'fb2' files 
	 */
	private static List<Book> processFB2FileList(List<File> list, File zipFile) {
		List<Book> tempBookList = new ArrayList<>();
		for (File file : list) {
			if (file.exists() && file.isFile() && file.getName().endsWith(".fb2")) {
				Book book = ManualParse.getBook(file);
				book.setSize(getSize(file));
				book.setFileName(file.getName());
				if(zipFile==null) {
					book.setPath(file.getAbsolutePath());
				}else {
					book.setPath(zipFile.getAbsolutePath());
				}
				System.out.println(book);
				tempBookList.add(book);
			}
		}
		return tempBookList;
		
	}
	
	private static long getSize(File file){
		long size = 0;
		try {
			size = Files.size(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return size;
	}

	public static void listOfFiles(File dir) {
		if (dir.exists() && dir.isDirectory()) {
			File[] arrFiles = dir.listFiles();
			for (File file : arrFiles) {
				if (file.isDirectory()) {
//					System.out.println("directory - "+file.getName());
					listOfFiles(file);
				} else {
					if (file.getName().endsWith(".fb2")) {
//						System.out.println("fb2 - "+file.getName());
						listFB2.add(file);
					} else if (file.getName().endsWith(".zip")) {
//						System.out.println("zip - "+file.getName());
						listZIP.add(file);
					} else {
//						System.out.println("etc - "+file.getName());
					}
				}
			}
		}
	}

}
