package com.library.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Authors")
public class Author {

	@Id
	@Column(name="author_id")
	private int authorid;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="book_authors", 
				joinColumns=@JoinColumn(name="author_id"),
				inverseJoinColumns=@JoinColumn(name="isbn"))
	private List<Book> books;

	public Author(String name) {
		super();
		this.name = name;
	}
	
	public Author() {
		
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addBook(Book b)
	{
		if(books==null) {
			books = new ArrayList<Book>();
		}
		books.add(b);
	}
}
