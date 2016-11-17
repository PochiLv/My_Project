package com.pochi.bean;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {
	private double price;
	private Map<String, CartItem> map=new LinkedHashMap<String, CartItem>();
	
	public void addBook(Book book){
		String book_id=book.getId();
		CartItem ci=map.get(book_id);
		if(ci==null){
			ci=new CartItem();
			ci.setBook(book);
			ci.setQuantity(1);
			this.map.put(book_id, ci);
		}else{
			ci.setQuantity(ci.getQuantity()+1);
		}
	}
	public double getPrice() {
		double totalPrice=0;
		for(Entry<String, CartItem> me:this.map.entrySet()){
			CartItem ci=me.getValue();
			totalPrice+=ci.getPrice();
		}
		this.price=totalPrice;
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	
}
