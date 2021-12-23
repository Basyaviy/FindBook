package ru.bas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
//		String relPath = "/testLib/Chekmaev_Sergej_Kogda_ischezli_derev'ja.fb2";

		String url = ManualParse.class.getResource(relPath).getFile();
		File file = new File(url);
		String expected = "Book [id=0, lastName=Фрейд, firstName=Зигмунд, bookTitle=Инфантильное "
				+ "возвращение тотема, genre=sci_psychology, annotation=Зигмунд Фрейд. "
				+ "Инфантильное возвращение тотема>, keywords=Freud, Фрейд, психоанализ, психотерапия, "
				+ "невроз, невротик, истерия, date=, lang=ru]";

		Book book = ManualParse.getBook(file);
		assertEquals(expected, book.toString());
	}
	
	

}
