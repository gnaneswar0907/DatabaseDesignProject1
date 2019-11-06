package com.library.entity;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Book")
public class Book {
	
	@Id
	@Column(name="isbn")
	private String isbn;
	
	@Column(name="title")
	private String title;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Book(String isbn, String title) {
		this.isbn = isbn;
		this.title = title;
	}
	
	public Book() {
		
	}
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="book_authors", 
				joinColumns=@JoinColumn(name="isbn"),
				inverseJoinColumns=@JoinColumn(name="author_id"))
	private List<Author> authors;
	
	@OneToMany(mappedBy="book", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BookLoan> bookloans;
	
	public List<BookLoan> getBookloans() {
		return bookloans;
	}
	public void setBookloans(List<BookLoan> bookloans) {
		this.bookloans = bookloans;
	}
	public String getAuthors() {
		
		String aaa ="";
		
		  for(Author a:authors) {
			  aaa = aaa + a.getName() + ", ";
		  }
		 
		
		if(aaa==null || aaa=="") {
			return "no author";
		}
		else {
			aaa = StringUtils.removeStart(aaa, ",");
		return StringUtils.removeEnd(aaa, ", , ");
		}
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Author a) {
		if(authors==null) {
			authors = new ArrayList<Author>();
		}
		authors.add(a);
	}
	
	public void addBookLoan(BookLoan bl) {
		if(bookloans==null) {
			bookloans = new ArrayList<BookLoan>();
		}
		
		bookloans.add(bl);
		
		bl.setBook(this);
		
	}
	
	public BookLoan getBookLoan() {
		
		int x = bookloans.size();
		
		if(x==0) {
			return null;
		}
		else {
			return bookloans.get(x-1);
		}
		
		
	}
	
}