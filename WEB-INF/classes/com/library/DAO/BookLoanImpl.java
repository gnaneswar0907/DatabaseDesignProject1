package com.library.DAO;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;
import com.library.entity.BookLoan;
import com.library.entity.Borrower;


@Repository
public class BookLoanImpl implements BookLoans {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public void createBookLoan(String isbn, int card_id) {
		
		Session session = factory.getCurrentSession();
		
		//String insertString = "INSERT INTO BookLoan(isbn,card_id,date_out,due_date) values(:isbn,:cardid,curdate(),DATE_ADD(date_out,INTERVAL 14 DAY))";
		
		
		java.util.Date udate = new java.util.Date();
		
		java.sql.Date dateout = new java.sql.Date(udate.getTime());
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(udate);
		
		c.add(Calendar.DAY_OF_MONTH, 14);
		
		java.sql.Date duedate = new java.sql.Date(c.getTime().getTime());
		
		BookLoan bl = new BookLoan();
		
		Book b = session.get(Book.class, isbn);
		
		//bl.setBook(b);
		
		b.addBookLoan(bl);
		
		bl.setDateOut(dateout);
		bl.setDueDate(duedate);
		bl.setDateIn(null);
		
		Borrower br =  session.get(Borrower.class, card_id);
		
		bl.setBorrower(br);
		
		session.save(bl);
	}

	@Override
	public int getLoans(int card_id) {
		
		Session session = factory.getCurrentSession();
		
		Query<BookLoan> query2 = session.createQuery("from BookLoan where (card_id = :cardid) AND (dateIn IS NULL)" , BookLoan.class);
		
		query2.setParameter("cardid", card_id);
		
		int x = query2.getResultList().size();
		
		return x;
		
	}

	@Override
	public List<BookLoan> getBookLoans(String key) {
		
		Session session = factory.getCurrentSession();
		
		String querytext = "from BookLoan where (isbn=:key) OR (borrower.cardId = (select cardId from Borrower where bName LIKE CONCAT('%',:key,'%')))";
		
		Query<BookLoan> query = session.createQuery(querytext, BookLoan.class);
		
		query.setParameter("key", key); 
		
		List<BookLoan> loans = query.getResultList();
		
		return loans;
	}

	@Override
	public List<BookLoan> updateLoan(int loanid) {
		
		Session session = factory.getCurrentSession();
		
		BookLoan loan = session.get(BookLoan.class, loanid);
		
		int cardid = loan.getBorrower().getCardId();
		
		java.util.Date udate = new java.util.Date();
		
		java.sql.Date datein = new java.sql.Date(udate.getTime());
		
		loan.setDateIn(datein);
		
		session.saveOrUpdate(loan);
		
		Query<BookLoan> query = session.createQuery("from BookLoan where borrower.cardId =:cardid",BookLoan.class);
		
		query.setParameter("cardid", cardid);
		
		List<BookLoan> loans = query.getResultList();
		
		return loans;
	}

	@Override
	public List<BookLoan> getBookLoansByCardid(int key) {
		
		Session session = factory.getCurrentSession();
		
		String querytext = "from BookLoan where (borrower.cardId = :key) ";
		
		Query<BookLoan> query = session.createQuery(querytext, BookLoan.class);
		
		query.setParameter("key", key);
		
		List<BookLoan> loans = query.getResultList();
		
		return loans;
	}

}
