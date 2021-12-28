package ru.bas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	/*
	 * <?xml version="1.0" encoding="UTF-8"?>
	<title-info> 
		<genre>sci_psychology</genre> 
		<author> 
		<first-name>Зигмунд</first-name> 
		<last-name>Фрейд</last-name> 
		</author> 
		<book-title>Инфантильное возвращение тотема</book-title> 
		<annotation><p><emphasis>Зигмунд Фрейд. <strong>Инфантильное возвращение тотема</strong></emphasis></p><empty-line/></annotation> 
		<keywords>Freud, Фрейд, психоанализ, психотерапия, невроз, невротик, истерия</keywords> 
		<date/> <lang>ru</lang> 
	</title-info> 
	*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="book_title")
	private String bookTitle;
	
	@Column(name="genre")
	private String genre;
	
	@Column(name="annotation")
	private String annotation;
	
	@Column(name="keywords")
	private String keywords;
	
	@Column(name="str_date")
	private String date;
	
	@Column(name="lang")
	private String lang;
	
	@Column(name="path")
	private String path;
	
	@Column(name="file_name")
	private String fileName;

	@Column(name="size")
	private long size;
	
	final transient private int NAME_LENGTH = 45;
	final transient private int TITLE_LENGTH = 100;
	final transient private int GENRE_LENGTH = 45;
	final transient private int ANNOTATION_LENGTH = 200;
	final transient private int KEYWORDS_LENGTH = 200;
	final transient private int STR_DATE_LENGTH = 20;
	final transient private int LANG_LENGTH = 20;
	final transient private int PATH_LENGTH = 399;
	final transient private int FILE_NAME_LENGTH = 200;
	
	public Book() {
	}
	
	public Book(String lastName, String firstName, String bookTitle, String genre, String annotation, String keywords,
			String date, String lang) {
		//inner data
		this.lastName = lastName;
		this.firstName = firstName;
		this.bookTitle = bookTitle;
		this.genre = genre;
		this.annotation = annotation;
		this.keywords = keywords;
		this.date = date;
		this.lang = lang;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		if(fileName.length()>FILE_NAME_LENGTH) {
			this.fileName = fileName.substring(0,FILE_NAME_LENGTH);
		}else
			this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		if(path.length()>PATH_LENGTH) {
			this.path = path.substring(0,PATH_LENGTH);
		}else
			this.path = path;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		if(genre.length()>GENRE_LENGTH) {
			this.genre = genre.substring(0,GENRE_LENGTH);
		}
		this.genre = genre;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		if(firstName.length()>NAME_LENGTH) {
			this.firstName = firstName.substring(0,NAME_LENGTH);
		}else
			this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		if(lastName.length()>NAME_LENGTH) {
			this.lastName = lastName.substring(0,NAME_LENGTH);
		}else
			this.lastName = lastName;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		if(bookTitle.length()>TITLE_LENGTH) {
			this.bookTitle = bookTitle.substring(0,TITLE_LENGTH);
		}else {
			this.bookTitle = bookTitle;
		}
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		if(annotation.length()>ANNOTATION_LENGTH) {
			this.annotation = annotation.substring(0,ANNOTATION_LENGTH);
		}else {
			this.annotation = annotation;
		}
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		if(keywords.length()>KEYWORDS_LENGTH) {
			this.keywords = keywords.substring(0,KEYWORDS_LENGTH);
		}else
			this.keywords = keywords;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		if(date.length()>STR_DATE_LENGTH) {
			this.date = date.substring(0,STR_DATE_LENGTH);
		}else
			this.date = date;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		if(lang.length()>LANG_LENGTH) {
			this.lang = lang.substring(0,LANG_LENGTH);
		}else
			this.lang = lang;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", bookTitle=" + bookTitle
				+ ", genre=" + genre + ", annotation=" + annotation + ", keywords=" + keywords + ", date=" + date
				+ ", lang=" + lang + ", path=" + path + ", fileName=" + fileName + ", size=" + size + "]";
	}

}
