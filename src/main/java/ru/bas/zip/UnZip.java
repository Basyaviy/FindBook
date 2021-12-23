package ru.bas.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * распаковка zip-архива
 * 
 * @author bas
 *
 */
public class UnZip {
	private static final int BUFFER_SIZE = 8192;
	private static final String CS = "IBM866";
	final static String workDir = "c:/temp/";

	public static void main(String[] args) {
		String path = UnZip.class.getResource("/").getPath();
		String zipPath = path +  "compressed2.zip";
		String filename = "listOfFiles.txt";
		unpack(zipPath, "C:/temp", filename);
	}
	
	/**
	 * make archive on 'zipPath' with list of 'srcFiles'
	 * */
	public static void multipleZip(String zipPath, List<File> srcFiles) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(zipPath);

			ZipOutputStream zipOut = new ZipOutputStream(fos);
			for (File srcFile : srcFiles) {
				//File fileToZip = new File(srcFile);
				FileInputStream fis = new FileInputStream(srcFile);
				ZipEntry zipEntry = new ZipEntry(srcFile.getName());
				zipOut.putNextEntry(zipEntry);

				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0) {
					zipOut.write(bytes, 0, length);
				}
				fis.close();
			}

			zipOut.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public static void unpack(String zipPath) {
		String outDirPath = "c:/temp/";
		unpack(zipPath, outDirPath, null);		
	}
	public static void unpack(String zipPath, String outDirPath) {
		unpack(zipPath, outDirPath, null);		
	}
	
/**
 * @param zipPath - c:/arch.zip
 * @param outDirPath - c:/temp/
 * @param target - example.txt
 * */
	public static List<File> unpack(String zipPath, String outDirPath, String target) {
		List<File> list = new ArrayList<>();
		if(outDirPath==null)
			outDirPath=workDir;
		try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
			ZipEntry entry;
			String name;
			long size;
			while ((entry = zin.getNextEntry()) != null) {

				name = entry.getName(); // получим название файла
				size = entry.getSize(); // получим его размер в байтах
//				System.out.printf("File name: %s \t File size: %d \n", name, size);
				//if target is null, unpack all
				if(target==null || target.equalsIgnoreCase(name)) {
					list.add(new File(outDirPath+name));
					// распаковка
					int len = 0;
					byte[] bArr = new byte[8192];
					FileOutputStream fout = new FileOutputStream(outDirPath + name);
					while ((len = zin.read(bArr)) != -1) {
						fout.write(bArr, 0, len);
					}
					fout.flush();
					zin.closeEntry();
					fout.close();
					
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return list;
	}

	
	
	/**
	 * почти мгновенный подсчёт кол-ва файлов в архиве
	 * System.out.printf("%50s    count:%4d",FULL_PATH, getCountPack(FULL_PATH));
	 * @param zipPath
	 * @return
	 */
	public static int getCountPack(String zipPath) {

		int count = 0;

		// MALFORMED ошибка если не переводить в кодировку 866
		Charset charset = Charset.forName(CS);
		ZipFile file = null;
		try {
			System.out.println("zipPath:" + zipPath);
			file = new ZipFile(zipPath, charset);

			try {
				final Enumeration<? extends ZipEntry> entries = file.entries();
				while (entries.hasMoreElements()) {
					entries.nextElement();
					count++;
					//System.out.printf("%5d %40s %15s%n",count, FILE_NAME , entry.getName());
				}
			} finally {
				file.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return count;
	}

}
