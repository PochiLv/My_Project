package com.pochi.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pochi.bean.Book;
import com.pochi.dao.BookDao;
import com.pochi.utils.JdbcUtils;

public class BookDaoImpl implements BookDao {
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.BookDao#add(com.pochi.bean.Book)
	 */
	@Override
	public void add(Book book){
		try{
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="INSERT INTO book (id,name,author,price,image,description,category_id) VALUES(?,?,?,?,?,?,?)";
			Object[] params={book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getImage(),book.getDescription(),book.getCategory_id()};
			runner.update(sql, params);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.BookDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id){
		try{
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="DELETE FROM book WHERE id=?";
			runner.update(sql, id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.BookDao#update(com.pochi.bean.Book)
	 */
	@Override
	public void update(Book book){
		try{
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="UPDATE book SET name=?,author=?,price=?,image=?,description=?,category_id=? WHERE id=?";
			Object[] params={book.getName(),book.getAuthor(),book.getPrice(),book.getImage(),book.getDescription(),book.getCategory_id(),book.getId()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.BookDao#find(java.lang.String)
	 */
	@Override
	public Book find(String id){
		try{
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="SELECT * FROM book WHERE id=?";
			Book book=runner.query(sql, new BeanHandler<Book>(Book.class), id);
			return book;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.BookDao#getTotalRecord()
	 */
	@Override
	public int getTotalRecord(){
		try{
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="SELECT COUNT(*) FROM book ";
			Long num=runner.query(sql, new ScalarHandler<Long>());
			return num.intValue();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	@Override
	public int getTotalRecordByC(String category_id) {
		try{
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="SELECT COUNT(*) FROM book WHERE category_id=?";
			Long num=runner.query(sql, new ScalarHandler<Long>(), category_id);
			return num.intValue();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.BookDao#getPageData(int, int)
	 */
	@Override
	public List<Book> getPageData(int startIndex,int pageSize){
		try{
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="SELECT * FROM book LIMIT ?,?";
			Object[] params={startIndex,pageSize};
			return runner.query(sql, new BeanListHandler<Book>(Book.class), params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Book> getPageData(int startIndex, int pageSize,
			String category_id) {
		try{
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql="SELECT * FROM book WHERE category_id=? LIMIT ?,? ";
			Object[] params={category_id,startIndex,pageSize};
			return runner.query(sql, new BeanListHandler<Book>(Book.class), params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
