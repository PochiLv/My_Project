CREATE DATABASE bookstore;
USE bookstore;
CREATE TABLE category(
	id VARCHAR(40) PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	description VARCHAR(200)
);

CREATE TABLE book(
	id VARCHAR(40) PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	author VARCHAR(40) NOT NULL,
	price DOUBLE NOT NULL,
	image VARCHAR(60),
	description VARCHAR(200),
	category_id VARCHAR(40),
	CONSTRAINT category_id_FK FOREIGN KEY (category_id) REFERENCES category(id)
);
CREATE TABLE user(
	id VARCHAR(40) PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	password VARCHAR(20) NOT NULL,
	birthday DATE,
	email VARCHAR(60),
	cellphone VARCHAR(20) NOT NULL,
	address VARCHAR(200) NOT NULL
);

CREATE TABLE orders(
	id VARCHAR(40) PRIMARY KEY,
	price DOUBLE NOT NULL,
	orderTime DATE NOT NULL,
	state BOOLEAN NOT NULL,
	
	user_id VARCHAR(40) NOT NULL,
	CONSTRAINT user_id_FK FOREIGN KEY(user_id) REFERENCES user(id)
);

CREATE TABLE orderitem(
	id VARCHAR(40) PRIMARY KEY,
	quantity INT NOT NULL,
	price DOUBLE NOT NULL,
	book_id VARCHAR(40) NOT NULL,
	order_id VARCHAR(40) NOT NULL,
	CONSTRAINT book_id_FK FOREIGN KEY(book_id) REFERENCES book(id),
	CONSTRAINT order_id_FK FOREIGN KEY(order_id) REFERENCES orders(id)
);