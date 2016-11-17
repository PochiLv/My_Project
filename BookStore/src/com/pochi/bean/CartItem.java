package com.pochi.bean;

public class CartItem {
	private Book book;
	private int quantity;
	private double price;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public double getPrice() {
		return this.quantity*this.book.getPrice();
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
