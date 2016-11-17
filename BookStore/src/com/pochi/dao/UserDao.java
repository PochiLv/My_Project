package com.pochi.dao;

import java.util.List;

import com.pochi.bean.User;

public interface UserDao {

	public abstract void add(User user);

	public abstract User find(String name, String password);

	public abstract List<User> getAll();

	public abstract User find(String id);

}