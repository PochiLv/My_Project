package com.pochi.dao;

import java.util.List;

import com.pochi.bean.Category;

public interface CategoryDao {

	public abstract void Add(Category category);

	public abstract void delete(String id);

	public abstract void update(Category category);

	public abstract Category find(String id);

	public abstract List<Category> getAll();

}