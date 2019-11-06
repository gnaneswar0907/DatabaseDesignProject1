package com.library.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="book_loans")
public class BookLoan {

	@Id
	@Column(name="loan_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int loanId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_out")
	private Date dateOut;
	
	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_in")
	private Date dateIn;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="isbn")
	private Book book;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="card_id")
	private Borrower borrower;

	public BookLoan(Date dateOut, Date dueDate, Date dateIn) {
		super();
		this.dateOut = dateOut;
		this.dueDate = dueDate;
		this.dateIn = dateIn;
	}
	
	public BookLoan() {
		
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	
	
}
