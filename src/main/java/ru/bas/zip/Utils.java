package ru.bas.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import ru.bas.entity.Book;

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
	
	
// link on "/download/{downloadId}"
//	gets path and fileName from DB and find the book in file system, put in response stream 
	public static void addBookToResponse(HttpServletResponse response, Book theBook) {
		//unpack on server
		String path = theBook.getPath();
		String fileName = theBook.getFileName();
		System.out.println("fileName: "+fileName);
		System.out.println("path: "+path);
		if(path.endsWith(".zip")) {
			List<File> list = UnZip.unpack(path, "c:/temp/", fileName);
			if(list.get(0)!=null) {
				System.out.println("===>I have a file: "+list.get(0));
				
				addFileToStream(response, "C:/temp/"+fileName, theBook.getFileName());
				//remove file from temp folder
				new File("C:/temp/"+fileName).delete();
				
			}		
		}else if(path.endsWith(".fb2")) {
			addFileToStream(response, path, theBook.getFileName());
		}
	}


	//helper method, create stream to response with file .fb2
	private static void addFileToStream(HttpServletResponse response, String path, String fileName) {
		try (FileInputStream fileIn = new FileInputStream(path)){
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
			"attachment;filename="+fileName);
			
			ServletOutputStream out = response.getOutputStream();

			byte[] outputByte = new byte[4096];
			//copy binary contect to output stream
			while(fileIn.read(outputByte, 0, 4096) != -1)
			{
				out.write(outputByte, 0, 4096);
			}
			fileIn.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
