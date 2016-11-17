package com.pochi.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.pochi.bean.Category;
import com.pochi.dao.CategoryDao;
import com.pochi.utils.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.CategoryDao#Add(com.pochi.bean.Category)
	 */
	@Override
	public void Add(Category category){
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource()); 
		try{
			String sql="INSERT INTO category (id,name,description) VALUES(?,?,?)";
			Object[] params={category.getId(),category.getName(),category.getDescription()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.CategoryDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id){
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource()); 
		try{
			String sql="DELETE FROM category WHERE id=?";
			runner.update(sql, id);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.CategoryDao#update(com.pochi.bean.Category)
	 */
	@Override
	public void update(Category category){
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource()); 
		try{
			String sql="UPDATE category SET name=?,description=? WHERE id=?";
			Object[] params={category.getName(),category.getDescription(),category.getId()};
			runner.update(sql,params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.CategoryDao#find(java.lang.String)
	 */
	@Override
	public Category find(String id){
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource()); 
		try{
			String sql="SELECT * FROM category WHERE id=?";
			Category category=runner.query(sql, new BeanHandler<Category>(Category.class), id);
			return category;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.CategoryDao#getAll()
	 */
	@Override
	public List<Category> getAll(){
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource()); 
		try{
			String sql="SELECT * FROM category";
			List<Category> list=runner.query(sql, new BeanListHandler<Category>(Category.class));
			return list;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}

