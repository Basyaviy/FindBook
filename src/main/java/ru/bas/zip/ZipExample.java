package ru.bas.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipExample {

	public static void main(String[] args) {
		String fileName = "/testLib/Чейз.Ночь отдыха.fb2";
//		String fileName="/testLib/readme.txt";
		InputStream is = UnZip.class.getResourceAsStream(fileName);
		try {
			Reader reader = new InputStreamReader(is, "UTF-8");
			char[] ch = new char[10];
			reader.read(ch,0, 10);
			System.out.println(ch);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(strFilePath);
		String zipPath = "c:/temp/arch.zip";
//		String outDirPath = "c:/temp/";
//		pack(zipPath, outDirPath, strFilePath);
//		UnZip.multipleZip(zipPath, null)

		//oneMorePack(zipPath, strFilePath);
	}

	
	
	
// сработало! стандартный упаковщик сохранил файл с рускоязычным наименованием
	static void oneMorePack(String zipPath, String strFilePath) {
		File srcDir = new File("c:/temp/");
		
		
		System.out.println(strFilePath);
		System.out.println(new File(strFilePath).getName());
		try(FileInputStream fis = new FileInputStream(strFilePath);
				FileOutputStream fos = new FileOutputStream(zipPath);
				ZipOutputStream zos = new ZipOutputStream(fos)){
			
			ZipEntry entry = new ZipEntry("файл.txt");
			zos.putNextEntry(entry);
			
			int size=0;
			byte bArr[] = new byte[8192];
			while((size = fis.read(bArr))!=-1) {
				zos.write(bArr, 0, size);
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		
		
//		ZipOutputStream zout = new ZipOutputStream();
//		zout.setLevel(9);
//		zout.setEncoding("CP866"); // вотъ ано!!!!
//
//		ZipEntry ze = new ZipEntry("русский.txt");
//		zout.putNextEntry(ze);
//		zout.write("просто текстъ въ текстовомъ файле");
//		zout.closeEntry();
//		zout.close();
	}

	/**
	 * @param zipPath    - c:/arch.zip
	 * @param filePath   - example.txt
	 */
	public static void pack(String zipPath, String strFilePath) {
		File filePath = new File(strFilePath);
		Charset charset = Charset.forName("CP866");

		try (FileOutputStream fos = new FileOutputStream(zipPath);
				ZipOutputStream zout = new ZipOutputStream(fos, charset);
				FileInputStream fis = new FileInputStream(filePath.getAbsoluteFile());) {
			ZipEntry entry1 = new ZipEntry(filePath.getName());
			zout.putNextEntry(entry1);
			// считываем содержимое файла в массив byte
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			// добавляем содержимое к архиву
			zout.write(buffer);
			// закрываем текущую запись для новой записи
			zout.closeEntry();
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}

	}
}
