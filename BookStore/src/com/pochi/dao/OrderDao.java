package com.pochi.dao;

import java.util.List;

import com.pochi.bean.Order;

public interface OrderDao {

	public abstract void add(Order order);

	public abstract void update(Order order);

	public abstract Order find(String id);

	public abstract List<Order> getAll();

	public abstract List<Order> getAll(boolean state);

	public abstract List<Order> getAll(String user_id);

}