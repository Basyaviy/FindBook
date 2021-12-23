package ru.bas;

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
	
	
	public Book() {
	}
	
	
	public Book(String genre, String firstName, String lastName, String bookTitle, String annotation, String keywords,
			String date, String lang) {
		super();
		this.genre = genre;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bookTitle = bookTitle;
		this.annotation = annotation;
		this.keywords = keywords;
		this.date = date;
		this.lang = lang;
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
		this.genre = genre;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", bookTitle=" + bookTitle
				+ ", genre=" + genre + ", annotation=" + annotation + ", keywords=" + keywords + ", date=" + date
				+ ", lang=" + lang + "]";
	}

}
