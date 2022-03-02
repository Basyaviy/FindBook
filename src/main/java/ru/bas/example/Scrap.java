package ru.bas.example;

import java.io.File;

public class Scrap {
	
	public static void main(String[] args) {
		System.out.println(Scrap.class.getResource(""));
		System.out.println(Scrap.class.getResource("d:\\BAS\\github\\Java\\findbook\\outLib\\books.zip"));
		File file = new File("d:\\BAS\\github\\Java\\findbook\\outLib\\books.zip");
//		file.toURI()
	}

}
