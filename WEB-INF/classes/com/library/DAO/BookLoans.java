package com.library.DAO;

import java.util.List;

import com.library.entity.BookLoan;;

public interface BookLoans {

	public void createBookLoan(String isbn, int card_id);
	
	public int getLoans(int card_id);
	
	public List<BookLoan> getBookLoans(String key);
	
	public List<BookLoan> updateLoan(int loanid);
	
	public List<BookLoan> getBookLoansByCardid(int key);
}
