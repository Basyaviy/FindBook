package ru.bas.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.bas.entity.Library;

@Repository
public class LibraryDAOImpl implements LibraryDAO{
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void saveLibrary(Library theLibrary) {
		Session session = sessionFactory.getCurrentSession();
		session.save(theLibrary);
		
	}

	@Override
	@Transactional
	public void deleteLibrary(int theId) {
		Session session = sessionFactory.getCurrentSession();
		Library theLibrary = session.get(Library.class, theId);
		session.delete(theLibrary);
	}

	@Override
	@Transactional
	public List<Library> getLibraries() {
		Session session = sessionFactory.getCurrentSession();
		Query<Library> theQuery = session.createQuery("from Library", Library.class);
		List<Library>  list = theQuery.getResultList();
		System.out.println(list);
		return list;
	}

}
