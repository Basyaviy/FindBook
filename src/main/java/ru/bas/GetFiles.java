package ru.bas;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.bas.zip.UnZip;

public class GetFiles {
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
		URL url = GetFiles.class.getResource("/testLib");
		File dir = new File(url.getPath());
		listOfFiles(dir);

		Session session = factory.getCurrentSession();

		try {
//			processFB2FileList(listFB2, session);
			processZIPFileList(listZIP, session);
		} finally {
			factory.close();
		}
	}

//	TODO кладёт не туда куда я хочу
	private static void processZIPFileList(ArrayList<File> list, Session session) {
		for (File file : list) {
			// unpack zip
			String unzippedDir = "c:/temp/" + file.getName();
			UnZip.unpack(file.getAbsolutePath(), unzippedDir, null);
			File unzippedFile = new File(unzippedDir);
			System.out.println(unzippedFile.exists());
			System.out.println(Arrays.toString(unzippedFile.list()));
		}
		System.out.println("--------------");

	}

	/**
	 * parse list of 'fb2' files and save to session DB
	 */
	public static void processFB2FileList(List<File> list, Session session) {
		session.beginTransaction();
		for (File file : list) {
			if (file.exists() && file.isFile() && file.getName().endsWith(".fb2")) {
				Book book = ManualParse.getBook(file);
				session.save(book);
			}
		}
		session.getTransaction().commit();
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
