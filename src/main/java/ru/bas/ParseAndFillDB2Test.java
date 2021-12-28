package ru.bas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.bas.entity.Book;
import ru.bas.zip.UnZip;
import ru.bas.zip.Utils;

/**
 * That class observe folders and zip files in root directory, 
 * find fb2 files, parse them and fill the table in DB
 *   
 * @author bas
 *
 */

public class ParseAndFillDB2Test {
	private static ArrayList<File> listFB2 = new ArrayList<>();
	private static ArrayList<File> listZIP = new ArrayList<>();
	// init sessionFactory
	private static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Book.class).buildSessionFactory();

	/*
	 * Look into directory, collect two Lists with 'fb2' and 'zip' extensions after
	 * getting Book object write to DB
	 */
	public static void main(String[] args) {
		URL url = ParseAndFillDB2Test.class.getResource("/testLib");
		File dir = new File(url.getPath());
		listOfFiles(dir);

		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			processFB2FileList(listFB2, session, null);
			processZIPFileList(listZIP, session);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

	private static void processZIPFileList(ArrayList<File> zipList, Session session) {
		
		for (File zipFile : zipList) {
			// unpack zip
			
			String zipPath =zipFile.getAbsolutePath(); //current zip
			String outDirPath = "c:/temp/" + zipFile.getName()+"/";//put here
			File currentDir = new File(outDirPath);
			System.out.println("mkdir:"+currentDir.mkdir());
			String target = null;//unpack all
			//list files in current zip
			List<File> currentList = UnZip.unpack(zipPath, outDirPath, target);
			
			//do save to DB here
			//need two parameters: Path to the file, Size
			//Path to the file = outDirPath + name
			processFB2FileList(currentList, session, zipFile);
			
						
			//remove current directory
			Utils.deleteNonEmptyDirectory(currentDir.getAbsolutePath());
			currentDir.delete();
		}
		System.out.println("--------------");

	}

	/**
	 * parse list of 'fb2' files and save to session DB
	 */
	private static void processFB2FileList(List<File> list, Session session, File zipFile) {
		//TODO open session save
		
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
				
				session.save(book);
			}
		}
		
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
