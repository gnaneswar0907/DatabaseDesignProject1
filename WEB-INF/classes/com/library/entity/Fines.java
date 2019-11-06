package com.library.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fines")
public class Fines implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="fine_amt")
	private float fineAmount;
	
	@Column(name="paid")
	private boolean paid;
	
	@Id
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="loan_id")
	private BookLoan bookloan;

	public Fines(float fineAmount, boolean paid) {
		this.fineAmount = fineAmount;
		this.paid = paid;
	}
	
	public Fines() {
		
	}

	public float getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(float fineAmount) {
		this.fineAmount = fineAmount;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public BookLoan getBookloan() {
		return bookloan;
	}

	public void setBookloan(BookLoan bookloan) {
		this.bookloan = bookloan;
	}
	
	
}
