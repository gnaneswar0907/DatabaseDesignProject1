package com.library.DAO;


import java.util.List;

import com.library.entity.Book;

public interface BookSearch {

	public List<Book> getResults(String keyword);
	
	
}
