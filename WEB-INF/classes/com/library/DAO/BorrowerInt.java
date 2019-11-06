package com.library.DAO;

import com.library.entity.Borrower;

public interface BorrowerInt {

	public void addBorrower(Borrower br);
	
	public boolean checkDuplicates(Borrower br);
	
	public boolean isCardIdValid(int cardid);
}
