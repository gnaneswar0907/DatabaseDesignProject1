package com.library.DAO;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;


@Repository
public class BookSearchImpl implements BookSearch {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public List<Book> getResults(String keyword) {
		
		Session session = factory.getCurrentSession();
		
		String searchBook = "from Book where isbn like CONCAT('%',:key,'%') OR title like CONCAT('%',:key,'%')";
		
		Query<Book> query1 = session.createQuery(searchBook, Book.class);
		
		query1.setParameter("key", keyword);
		
		List<Book> books = query1.getResultList();
		
		//HashMap<Integer, Book> hmap = new HashMap<Integer, Book>();
		
		List<Book> allbooks = session.createQuery("from Book",Book.class).getResultList();
		
		
		
		
		
		for(Book b: allbooks) {
			if((b.getAuthors().contains(keyword)) && (!books.contains(b))) {
				
				books.add(b);
			}
		}
		
		/*
		 * for(Book bb:books) { hmap.put(bb.getBookloans().size(), bb); }
		 */
		
		
			
		return books;
	}

}
