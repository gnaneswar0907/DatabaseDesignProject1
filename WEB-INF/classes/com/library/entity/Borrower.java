package com.library.entity;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name="borrower")
public class Borrower {

	@Id
	@Column(name="card_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int cardId;
	
	@Column(name="ssn")
	@NotNull(message="*SSN is required")
	@Pattern(regexp="^\\d{9}$",message="SSN must be a 9 digit number")
	private String ssn;
	
	@Column(name="bname")
	@NotNull(message="*Name is required")
	private String bName;
	
	@Column(name="address")
	@NotNull(message="*Address is required")
	private String address;
	
	@Column(name="phone")
	@NotNull(message="*Phone is required")
	@Pattern(regexp="^\\(?([0-9]{3})\\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})$",message="Phone number must be in correct format with only 10 numbers")
	private String phone;
	
	@OneToMany(mappedBy="borrower", cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<BookLoan> loans;

	public List<BookLoan> getLoans() {
		return loans;
	}

	public void setLoans(List<BookLoan> loans) {
		this.loans = loans;
	}
	
	public void addLoans(BookLoan bl) {
		if(loans==null) {
			loans = new ArrayList<BookLoan>();
		}
		
		loans.add(bl);
		
		bl.setBorrower(this);
	}

	public Borrower(String ssn, String bookName, String address, String phone) {
		super();
		this.ssn = ssn;
		this.bName = bookName;
		this.address = address;
		this.phone = phone;
	}
	
	public Borrower() {
		
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getBName() {
		return bName;
	}

	public void setBName(String bookName) {
		this.bName = bookName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
