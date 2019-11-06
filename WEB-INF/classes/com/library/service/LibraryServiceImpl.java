package com.library.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.DAO.BookLoans;
import com.library.DAO.BookSearch;
import com.library.DAO.BorrowerInt;
import com.library.entity.Book;
import com.library.entity.BookLoan;
import com.library.entity.Borrower;
import com.library.entity.Fines;
import com.library.DAO.FinesInt;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private BookSearch bookSearchDAO;
	
	@Autowired
	private BookLoans bookLoanDAO;
	
	@Autowired
	private BorrowerInt borrowerDAO;
	
	@Autowired
	private FinesInt finesDAO;

	@Override
	@Transactional
	public List<Book> getResults(String keyword) {
		
		return bookSearchDAO.getResults(keyword);
	}

	@Override
	@Transactional
	public void createBookLoan(String isbn, int card_id) {
		bookLoanDAO.createBookLoan(isbn, card_id);
	}

	@Override
	@Transactional
	public int getLoans(int card_id) {
		
		return bookLoanDAO.getLoans(card_id);
	}

	@Override
	@Transactional
	public void addBorrower(Borrower br) {
		borrowerDAO.addBorrower(br);
	}

	@Override
	@Transactional
	public boolean checkDuplicate(Borrower br) {
		
		return borrowerDAO.checkDuplicates(br);
	}

	@Override
	@Transactional
	public List<BookLoan> getBookLoans(String key) {
		
		return bookLoanDAO.getBookLoans(key);
	}

	@Override
	@Transactional
	public List<BookLoan> updateLoan(int loanid) {
		
		return bookLoanDAO.updateLoan(loanid);
	}

	@Override
	@Transactional
	public boolean isCardIdValid(int cardid) {
		return borrowerDAO.isCardIdValid(cardid);
	}

	@Override
	@Transactional
	public List<Fines> getFines(int cardid) {
		return finesDAO.getFines(cardid);
	}

	@Override
	@Transactional
	public List<Fines> payFines(int loanid) {
		return finesDAO.payFines(loanid);
	}

	@Override
	@Transactional
	public List<BookLoan> getBookLoansByCardid(int key) {
		return bookLoanDAO.getBookLoansByCardid(key);
	}
	
}
