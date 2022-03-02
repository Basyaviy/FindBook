package ru.bas.dao;

import java.util.List;

import ru.bas.entity.Book;
import ru.bas.entity.Library;

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
	
//	public List<Library> getLibraries();

	public List<Book> getBooks(String searchString);
	public Book getBook(int theId);

	public void saveBook(Book theBook);

	public void saveBooks(List<Book> bookList);

//	public void saveLibrary(Library theLibrary);

//	public void deleteLibrary(int theId);

	

}
