package com.library.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.entity.BookLoan;
import com.library.entity.Fines;

@Repository
public class FinesImpl implements FinesInt {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public List<Fines> getFines(int cardid) {
		
		Session session = factory.getCurrentSession();
		
		List<BookLoan> loans = session.createQuery("from BookLoan",BookLoan.class).getResultList();
		
		String querytext = "from Fines where bookloan.loanId IN (select loanId from BookLoan where borrower.cardId = :cardid)";
		
		Query<Fines> query = session.createQuery(querytext,Fines.class);
		
		query.setParameter("cardid", cardid);
		
		List<Fines> fines = query.getResultList();
		
		//query1.setParameter("cardid", cardid);
		
		java.util.Date udate = new java.util.Date();
		
		for(BookLoan loan:loans) {
			if(loan.getDateIn()==null) {
				long out = loan.getDueDate().getTime();
				long diff = udate.getTime() - out ;
				long days = diff / (1000 * 60 * 60 * 24);
				float fineamount = (float)(days * 0.25);
				
				if(fineamount>0) {
					
					Fines f = session.get(Fines.class, loan.getLoanId());
					if(f==null) {
						f =new Fines();
						f.setBookloan(loan);
					}
					
					f.setFineAmount(fineamount);
					
					
					session.saveOrUpdate(f);
				}
				
				
			}
			else {
				long out = loan.getDueDate().getTime();
				long in = loan.getDateIn().getTime();
				long diff = in - out ;
				long days = diff / (1000 * 60 * 60 * 24);
				float fineamount = (float)(days * 0.25);
				
				
				if(fineamount>0) {
					
					Fines f = session.get(Fines.class, loan.getLoanId());
					
					if(f==null) {
						f =new Fines();
						f.setBookloan(loan);
					}
					
					f.setFineAmount(fineamount);
					
					if(!(f.isPaid())) {
						session.saveOrUpdate(f);
					}
				}
				
			}
		}
		
			
		 fines = query.getResultList();
		
		return fines;
	}

	@Override
	public List<Fines> payFines(int loanid) {
		
		Session session = factory.getCurrentSession();
		
		Fines f = session.get(Fines.class, loanid);
		
		f.setPaid(true);
		
		session.saveOrUpdate(f);
		
		Query<Fines> query = session.createQuery("from Fines where bookloan.loanId = :loanid",Fines.class);
		
		query.setParameter("loanid", loanid);
		
		List<Fines> fines = query.getResultList();
		
		return fines;
	}

}
