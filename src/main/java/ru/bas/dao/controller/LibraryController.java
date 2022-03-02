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
import ru.bas.dao.LibraryDAO;
import ru.bas.entity.Book;
import ru.bas.entity.Library;
import ru.bas.entity.LibraryListContainer;

@Controller
@RequestMapping("/lib")
public class LibraryController {
	private static final int NUM_OF_LIBRARIES = 3;

	@Autowired
	private LibraryDAO libraryDAO;
	

	@PostMapping("/saveLibs")
	public String saveLibs(@ModelAttribute("Libs") LibraryListContainer libList) {
		System.out.println("===> saveLibs");
		//Get List of Libs from Model
		List<Library> formList = libList.getLibraries();
		
		//list of libs from DB
		List<Library> currentList = libraryDAO.getLibraries();
		
	
		//form is empty
		for(Library lib:formList) {
			if(lib.getPath()!=null&&lib.getPath().trim().length()>0) {
				continue;
			}
			//fill by testLib
			Library testLib = getTestLib();
			// save library with her books in DB
			libraryDAO.saveLibrary(testLib);
			
		}
		
		
		
		//iterate them
		for(Library library:formList) {
			if(library.getPath()!=null && library.getPath().trim().length()>1)
				
				if(!currentList.contains(library)) {
					//that path is new - need to be loaded
					System.out.println("===>load library: "+library.getPath());
					
					//getBooks
					List<Book> bookList = FileParser.getBookList(library.getPath());
					//wire books with current lib
					for(Book theBook:bookList) {
						library.add(theBook);
					}
					//save lib
					libraryDAO.saveLibrary(library);
					
				}

		}
		
		//delete libraries that not used anymore
		for(Library library:currentList) {
			if(library.getPath()!=null && library.getPath().trim().length()>1) {
				if(!formList.contains(library)) {
					System.out.println("====>delete library"+library);
					//delete Library and all this Books from database
					libraryDAO.deleteLibrary(library.getId());
				}
			}
		}

		return "redirect:/book/list";
	}
	


	@GetMapping("/index")
	public String saveLib(@RequestParam ("libraryPath") String libraryPath) {
		System.out.println("===> lib/index:");
		
		// search books by the libraryPath
		List<Book> bookList = FileParser.getBookList(libraryPath);
		
		
		// return list of Books 
		System.out.println("==>list of books:"+bookList);
		
		Library theLibrary = new Library(libraryPath);
		
		// wire with current Lib
		for(Book theBook:bookList) {
			theLibrary.add(theBook);
		}
		
		// save library with her books in DB
		libraryDAO.saveLibrary(theLibrary);
		
		return "redirect:/lib/list";
	}
	
	@GetMapping("/delete-test")
	public String deleteLibraryTest(@RequestParam("libraryId") int theId,
			Model theModel) {
		
		//delete Library and all this Books from database
		libraryDAO.deleteLibrary(theId);

		//send over to our form
		return"redirect:/book/list";
	}
	
	
	
	@GetMapping("/list")
	public String listLibraries(Model theModel) {
//		//get books from the dao
		//List<Library> libraries = libraryDAO.getLibraries();
		
		//get list of libraries
		List<Library> libs = libraryDAO.getLibraries();
		
		//если их меньше указанного количества добавляем пустые, чтобы создать поля для заполнения в форме
		while(libs.size()<NUM_OF_LIBRARIES) {
			if(libs.size()<1) {
				Library testLib = getTestLib();
				// save library with her books in DB
				libraryDAO.saveLibrary(testLib);
				//that using form
				libs.add(testLib);
			}else {
				libs.add(new Library());
			}
		}
		LibraryListContainer listContainer = new LibraryListContainer();
		listContainer.setLibraries(libs);
		
//		//add the libsContainer to the model (name, value)
		theModel.addAttribute("Libs", listContainer);
		
		return "list-libraries";
	}
	
	
	public void processTestLib() {
		//list of libs clear, add test library
		Library testLib = new Library("/testLib");
		List<Book> bookList = FileParser.getBookList(null);
		//wire books with current lib
		for(Book theBook:bookList) {
			testLib.add(theBook);
		}
		// save library with her books in DB
		libraryDAO.saveLibrary(testLib);
	}
	
	public static Library getTestLib() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
