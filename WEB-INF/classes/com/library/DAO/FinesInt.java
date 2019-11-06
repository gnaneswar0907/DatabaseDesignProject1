package com.library.DAO;

import java.util.List;

import com.library.entity.Fines;

public interface FinesInt {

	public List<Fines> getFines(int cardid);
	
	public List<Fines> payFines(int loanid);
}
