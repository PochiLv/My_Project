package com.pochi.bean;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Order {
	private String id;
	private double price;
	private Date orderTime;
	private boolean state;

	
	private Set<OrderItem>set=new LinkedHashSet<OrderItem>();
	private User user;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Set<OrderItem> getSet() {
		return set;
	}
	public void setSet(Set<OrderItem> set) {
		this.set = set;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
