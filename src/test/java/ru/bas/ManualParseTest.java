package ru.bas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.bas.zip.UnZip;

class ManualParseTest {

	@BeforeEach
	void setUp() throws Exception {
	}

//	
//	String relPath = "/testLib/Frejd_Zigmund_Infantil'noe_vozvraschenie_totema.fb2";
////	String relPath = "/testLib/Chekmaev_Sergej_Kogda_ischezli_derev'ja.fb2";
//
//	String url = ManualParse.class.getResource(relPath).getFile();
//	File file = new File(url);
//
//	Book book = getBook(file);
//	System.out.println(book);
	@Test
	final void getBookTest() {
		String relPath = "/testLib/Frejd_Zigmund_Infantil'noe_vozvraschenie_totema.fb2";

		String url = ManualParse.class.getResource(relPath).getFile();
		File file = new File(url);
		Book book = ManualParse.getBook(file);
		String expected = "Зигмунд";
		System.out.println("----"+book.getFirstName());
		assertEquals(expected, book.getFirstName());
	}
	
	
	@Test
	final void cutTagBlock() {
		//	private static String cutTagBlock(InputStream targetStream, String tag, String enc)
		URL path = getClass().getResource("/testLib/books2.zip");
		File zipFile = new File(path.getPath());
		UnZip.unpack(zipFile.getAbsolutePath(), "c:/temp/", "brin.fb2");
		
		File file = new File("c:/temp/" + "brin.fb2");

		Book book = ManualParse.getBook(file);
		String expected="ДЭВИД";
		String actual = book.getFirstName();
		
		assertEquals(expected, actual);
	
	}

}
