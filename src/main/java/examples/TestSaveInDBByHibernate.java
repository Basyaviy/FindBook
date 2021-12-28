package examples;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.bas.entity.Book;
import ru.bas.ManualParse;

public class TestSaveInDBByHibernate {
	public static void main(String[] args) {
		String relPath = "/testLib/Frejd_Zigmund_Infantil'noe_vozvraschenie_totema.fb2";
		String relPath2 = "/testLib/Chekmaev_Sergej_Kogda_ischezli_derev'ja.fb2";

		String url = ManualParse.class.getResource(relPath).getFile();
		String url2 = ManualParse.class.getResource(relPath2).getFile();
		File file = new File(url);
		File file2 = new File(url2);

		Book book = ManualParse.getBook(file);
		Book book2 = ManualParse.getBook(file2);
		System.out.println(book);
		System.out.println(book2);
		
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Book.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session.save(book);
			session.save(book2);
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
		
		
	}

}
