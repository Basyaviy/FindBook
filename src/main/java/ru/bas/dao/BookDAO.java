package ru.bas.dao;

import java.util.List;

import ru.bas.entity.Book;

public interface BookDAO {
	//use spring mvc
	//use transient
	//get book by id
	//CRUD Create Read Update Delete
	
	//saveBook
	//getBook
	//updateBook
	//deleteBook
	
	public List<Book> getBooks();

	public Book getBook(int theId);

	public void saveBook(Book theBook);

	public void saveBooks(List<Book> bookList);
	

}
