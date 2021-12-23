package examples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class ScrapTest {
	public static void main(String[] args) {
		String text = "пишем в байтовый поток. Могу, да?";
		String srcPath = "c:/temp/файл.txt";
		String srcPathBig = "c:\\BAS\\Inbox\\Spring & Hibernate for Beginners (includes Spring Boot)\\142 - Primary Keys - Overview.mp4";
		String targetPath = "c:/temp/файлCopy.txt";
		String targetPathBig = "c:/temp/video2.mp4";


		 String res = bufferedReadLineFromFile("Cp1251");
		 bufferedWriteLineFromFile("файлOut1.txt", res, "UTF-8");
		 bufferedWriteLineFromFile("файлOut2.txt", res, "Cp1251");
		 
		System.out.println("End.");
	}
	
	/**
	 * @param charset - writes file in that charset
	 * */
	static void bufferedWriteLineFromFile(String fileName, String res, String charset) {
		try(FileOutputStream fos = new FileOutputStream("c:/temp/"+fileName);
				OutputStreamWriter osw = new OutputStreamWriter(fos,Charset.forName(charset));
				BufferedWriter bw = new BufferedWriter(osw)){
			bw.write(res);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	static String bufferedReadLineFromFile(String charset) {
		StringBuilder sb = new StringBuilder();
		try(FileInputStream fis = new FileInputStream("c:/temp/файл.txt");
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName(charset));
				BufferedReader br = new BufferedReader(isr);){
			
			String line=null;
			while((line=br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	static void copyFileFast(String srcPath, String targetPath) {
		try(FileInputStream fis = new FileInputStream(srcPath);
		FileOutputStream fos = new FileOutputStream(targetPath)){
			byte[] buffer = new byte[8196];
			int size = -1;
			int counter = 0;
			while((size = fis.read(buffer))!= -1) {
				if(size!=8196) {
					System.out.println("--"+size);
				}else {
					counter++;
				}
				fos.write(buffer, 0, size);
			}
			System.out.println("counter:"+counter);
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}

	// byte. NO BUFFERED. SLOW. 20MB = 1min
	static void copyFile(String srcPath, String targetPath) {
		try (FileInputStream fis = new FileInputStream(srcPath);
				FileOutputStream fos = new FileOutputStream(targetPath)) {
			int b = -1;
			while ((b = fis.read()) != -1) {
				fos.write(b);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	static void writeToFile(String text) {
		Charset charset = Charset.forName("UTF-8");

		try (FileWriter fileWriter = new FileWriter("c:/temp/файл.txt", charset)) {
			for (char ch : text.toCharArray()) {
				fileWriter.write(ch);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// byte stream
	static void writeToFileByte(String text) {
		try (FileOutputStream fileOutputStream = new FileOutputStream("c:/temp/файл.txt")) {
			for (byte b : text.getBytes("Cp1251")) {
				fileOutputStream.write(b);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// read byte stream
	static void readFromFileByte() {
		try (FileInputStream fileInputStream = new FileInputStream("c:/temp/файл.txt")) {
			int c = -1;
			byte[] bArr = new byte[100];
			int count = 0;
			while ((c = fileInputStream.read()) != -1) {
				bArr[count++] = (byte) c;
			}
			byte[] res = new byte[count];
			System.arraycopy(bArr, 0, res, 0, count);
			String str = new String(res, Charset.forName("Cp1251"));
			System.out.println(str + "|");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	static void readFromFile() {
		StringBuilder sb = new StringBuilder();
		try (FileReader fileReader = new FileReader("c:/temp/файл.txt", Charset.forName("Cp1251"))) {
			int ch = -1;
			while ((ch = fileReader.read()) != -1) {
				sb.append((char) ch);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(sb.toString());
	}
}
