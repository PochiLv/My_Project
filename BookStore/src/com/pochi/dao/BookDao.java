package com.pochi.dao;

import java.util.List;

import com.pochi.bean.Book;

public interface BookDao {

	public abstract void add(Book book);

	public abstract void delete(String id);

	public abstract void update(Book book);

	public abstract Book find(String id);

	public abstract int getTotalRecord();
	
	public abstract int getTotalRecordByC(String category_id);

	public abstract List<Book> getPageData(int startIndex, int pageSize);

	public abstract List<Book> getPageData(int startIndex, int pageSize,
			String category_id);

}