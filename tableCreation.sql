# Dropping the database if it exists already
DROP DATABASE IF EXISTS library_system;
#Creating the database
CREATE DATABASE library_system;
#Drop Table if already exists

#Creating Table
USE library_system;
CREATE TABLE Book(
	 isbn VARCHAR(13) PRIMARY KEY NOT NULL,
	 title VARCHAR(100)
);

INSERT INTO BOOK VALUES('9780195153446','Classical Mythology');
INSERT INTO BOOK VALUES('9780002005012','Clara Callan: A Novel');
INSERT INTO BOOK VALUES('9780060973124','Decision In Normandy');
INSERT INTO BOOK VALUES('9780887841743','The Middle Stories');



#Drop Table if already exists
DROP TABLE IF EXISTS Authors;
#Creating Table
CREATE TABLE Authors(
	 author_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	 name VARCHAR(30)
);

INSERT INTO Authors(name) VALUES('Ali'),('John'),('Freid'),('Vinay');

#Drop Table if already exists
DROP TABLE IF EXISTS Book_Authors;
#Creating Table
CREATE TABLE Book_Authors(
	 author_id INT REFERENCES Authors(author_id),
	 isbn VARCHAR(13) REFERENCES Book(isbn),
	 PRIMARY KEY(author_id,isbn)
);


INSERT INTO Book_Authors VALUES(1,'9780002005012'),(4, '9780060973124'),(3,'9780002005012'),(2,'9780887841743'),(1,'9780887841743'),(4,'9780195153446'),(2,'9780195153446'),(3,'9780195153446');

#Drop Table if already exists
DROP TABLE IF EXISTS Borrower;
#Creating Table
CREATE TABLE Borrower(
	card_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	ssn VARCHAR(10) NOT NULL,
	bname VARCHAR(100) NOT NULL,
	address VARCHAR(150) NOT NULL,
	phone VARCHAR(10) NOT NULL
);

#Drop Table if already exists
DROP TABLE IF EXISTS Book_Loans;
#Creating Table
CREATE TABLE Book_Loans(
	loan_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	isbn VARCHAR(13) NOT NULL REFERENCES Book(isbn) ,
	card_id INT NOT NULL REFERENCES Borrower(card_id) ,
	date_out DATE,
	due_date DATE,
	date_in DATE 
);

#Drop Table if already exists
DROP TABLE IF EXISTS Fines;
#Creating Table
CREATE TABLE Fines(
	 loan_id INT NOT NULL PRIMARY KEY REFERENCES Book_Loans(loan_id) ,
	 fine_amt FLOAT(7,2),
	 paid BOOLEAN
);