package ru.bas.dao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.bas.FileParser;
import ru.bas.dao.BookDAO;
import ru.bas.entity.Book;

@Controller
@RequestMapping("/book")
public class BookController {
	//need to inject the customer dao
	@Autowired
	private BookDAO bookDAO;
	
	@GetMapping("/fillDB")
	public String fillDB() {
		List<Book> bookList = FileParser.getBookList();
		bookDAO.saveBooks(bookList);
		return "list-books";
	}
	
	
	@GetMapping("/list")
	public String listBooks(Model theModel) {
		
//		//get books from the dao
		List<Book> theBooks = bookDAO.getBooks();
//		
//		//add the book to the model (name, value)
		theModel.addAttribute("books", theBooks);
		
		return "list-books";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookId") int theId,
			Model theModel) {
		
		//get the book from database
		Book theBook = bookDAO.getBook(theId);
		
		//set book as a model attribute to pre-populate the form
		theModel.addAttribute("book", theBook);

		//send over to our form
		return "book-form";
	}
	
	@PostMapping("saveBook")
	public String saveBook(@ModelAttribute("book") Book theBook) {
		bookDAO.saveBook(theBook);
		
		return "redirect:/book/list";
	}

}