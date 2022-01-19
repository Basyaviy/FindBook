package ru.bas.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.bas.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO{
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	@Transactional
	public List<Book> getBooks() {
		Session session = sessionFactory.getCurrentSession();
		Query<Book> theQuery = session.createQuery("from Book", Book.class);
		List<Book>  list = theQuery.getResultList();
		return list;
	}
	
	@Override
	@Transactional
	public List<Book> getBooks(String searchString) {
		Session session = sessionFactory.getCurrentSession();
		Query<Book> theQuery = session.createQuery("from Book where book_title like :searchString "
				+ "or first_name like :searchString "
				+ "or last_name like :searchString ", Book.class);
		theQuery.setParameter("searchString", "%"+searchString+"%");
		List<Book>  list = theQuery.getResultList();
		return list;
	}


	@Override
	@Transactional
	public Book getBook(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Book theBook = session.get(Book.class, theId);
		
		return theBook;
	}


	@Override
	@Transactional
	public void saveBook(Book theBook) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("From saveBook==>" + theBook);
		session.saveOrUpdate(theBook);
	}


	@Override
	@Transactional
	public void saveBooks(List<Book> bookList) {
		Session session = sessionFactory.getCurrentSession();
		//truncateDB
		session.createSQLQuery("truncate table Book").executeUpdate();
		System.out.println("From saveBooks==>" + bookList.get(0));
		//fillDB
		for(Book book:bookList) {
			session.save(book);
		}
		
	}

}
