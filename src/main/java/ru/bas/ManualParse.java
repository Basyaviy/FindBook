package ru.bas;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import ru.bas.entity.Book;

/*
 * <?xml version="1.0" encoding="UTF-8"?>
<title-info> 
	<genre>sci_psychology</genre> 
	<author> 
	<first-name>Зигмунд</first-name> 
	<last-name>Фрейд</last-name> 
	</author> 
	<book-title>Инфантильное возвращение тотема</book-title> 
	<annotation><p><emphasis>Зигмунд Фрейд. <strong>Инфантильное возвращение тотема</strong></emphasis></p>
	<empty-line/></annotation> <keywords>Freud, Фрейд, психоанализ, психотерапия, невроз, невротик, истерия</keywords> 
	<date/> <lang>ru</lang> 
</title-info> 
*/

public class ManualParse {
	public static void main(String[] args) throws FileNotFoundException {
		String relPath = "/testLib/Frejd_Zigmund_Infantil'noe_vozvraschenie_totema.fb2";
//		String relPath = "/testLib/Chekmaev_Sergej_Kogda_ischezli_derev'ja.fb2";

		String url = ManualParse.class.getResource(relPath).getFile();
		File file = new File(url);

		Book book = getBook(file);
		System.out.println(book);
		// супер!
	}

	/**
	 * collect data into Book object
	 * */
	public static Book getBook(File file) {
		Book book = new Book();
		String titleInfoBlock = cutTagBlock(file, "title-info");
		book.setGenre(cutTagBlock(titleInfoBlock, "genre"));
		book.setFirstName(cutTagBlock(titleInfoBlock, "first-name"));
		book.setLastName(cutTagBlock(titleInfoBlock, "last-name"));
		String title = cutTagBlock(titleInfoBlock, "book-title");
		if(title==null||title.length()<1)
			title = file.getName();
		book.setBookTitle(title);
		book.setAnnotation(cutTagBlock(titleInfoBlock, "annotation"));
		book.setDate(cutTagBlock(titleInfoBlock, "date"));
		book.setLang(cutTagBlock(titleInfoBlock, "lang"));
		book.setKeywords(cutTagBlock(titleInfoBlock, "keywords"));
		return book;
	}

	/**
	 * Take a String and return inner content from XML-tags
	 * */
	private static String cutTagBlock(String initialString, String tag) {
		String res = null;
		InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
		res = cutTagBlock(targetStream, tag, "UTF-8");
		res = removeTags(res);
		return res;
	}

	/**
	 * 
	 * clear string from unecessary tags
	 * */
	private static String removeTags(String str) {
		char[] chArr = new char[str.length()];
		boolean openBraces = false;
		int k = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '<') {
				openBraces = true;
				continue;
			}
			if (str.charAt(i) == '>' && openBraces) {
				openBraces = false;
				continue;
			}
			if (!openBraces)
				chArr[k++] = str.charAt(i);
		}
		char[] dest = new char[k];
		System.arraycopy(chArr, 0, dest, 0, k);
		return new String(dest);
	}

	private static String cutTagBlock(File file, String tag) {
		InputStream targetStream = null;
		try {
			targetStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String enc = getEncoding(file);
		return cutTagBlock(targetStream, tag, enc);
	}

	private static String cutTagBlock(InputStream targetStream, String tag, String enc) {
		String closeTag = "</" + tag + ">";
		String openTag = "<" + tag + ">";

		Scanner scanner;
		StringBuilder sb = new StringBuilder();
		scanner = new Scanner(targetStream, enc);

		String line = null;
		boolean bInBlock = false;
		while (scanner.hasNext()) {
			line = scanner.nextLine();
			if (bInBlock) {
				if (line.contains(openTag) && line.contains(closeTag)) {
					line = line.substring(closeTag.length(), line.indexOf(closeTag));
					sb.append(line);
					break;
				}
				if (line.contains(closeTag)) {
					line = line.substring(0, line.indexOf(closeTag));
					sb.append(line);
					break;
				}

				sb.append(line);
			}
			if (line.contains(openTag)) {
				if (line.contains(closeTag)) {
					// обрезаем строку по открывающему и закрывающему тегу
					line = line.substring(line.indexOf(openTag) + openTag.length(), line.indexOf(closeTag));
					sb.append(line);
					break;
				}
				if (!bInBlock) {
					// начало блока найдено, обрезаем строку
					line = line.substring(line.indexOf(openTag) + openTag.length());
				}
				sb.append(line);
				bInBlock = true;
			}

		}
		scanner.close();

		return sb.toString();
	}

	/**
	 * Reads first 50 chars of file and try find encoding parameter
	 */
	private static String getEncoding(File file) {
		String text = "";
		String startTag = "encoding=\"";
		String endTag = "\"?>";

		try (FileReader reader = new FileReader(file)) {
			StringBuilder sb = new StringBuilder();

			char[] buf = new char[50];
			int c;
			if ((c = reader.read(buf)) > 0) {
				text = new String(buf);

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (text != null && text.contains(startTag) && text.contains(endTag))
			return text.substring(text.indexOf(startTag) + startTag.length(), text.indexOf(endTag));
		else
			return null;
	}

}
