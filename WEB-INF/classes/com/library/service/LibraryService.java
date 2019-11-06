package com.library.service;


import java.util.List;

import com.library.entity.*;

public interface LibraryService {

	public List<Book> getResults(String keyword);
	
	public void createBookLoan(String isbn, int card_id);
	
	public int getLoans(int card_id);
	
	public void addBorrower(Borrower br);
	
	public boolean checkDuplicate(Borrower br);
	
	public List<BookLoan> getBookLoans(String key);
	
	public List<BookLoan> updateLoan(int loanid);
	
	public boolean isCardIdValid(int cardid);
	
	public List<Fines> getFines(int cardid);
	
	public List<Fines> payFines(int loanid);
	
	public List<BookLoan> getBookLoansByCardid(int key);
}
