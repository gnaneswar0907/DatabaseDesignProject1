package com.library.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.entity.Borrower;

@Repository
public class BorrowerImpl implements BorrowerInt {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public void addBorrower(Borrower br) {
		
		Session session = factory.getCurrentSession();
		
		session.save(br);
	}

	@Override
	public boolean checkDuplicates(Borrower br) {
		
		Session session = factory.getCurrentSession();
		
		List<String> ssns = session.createQuery("select ssn from Borrower",String.class).getResultList();
		
		for(String s : ssns) {
			if(s.compareTo(br.getSsn())==0) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean isCardIdValid(int cardid) {
		
		Session session = factory.getCurrentSession();
		
		Borrower br = session.get(Borrower.class, cardid);
		
		if(br==null) {
			return false;
		}
		
		else {
			return true;
		}
		
	}

}
