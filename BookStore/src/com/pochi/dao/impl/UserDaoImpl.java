package com.pochi.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.pochi.bean.User;
import com.pochi.dao.UserDao;
import com.pochi.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.UserDao#add(com.pochi.bean.User)
	 */
	@Override
	public void add(User user){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="INSERT INTO user (id,name,password,birthday,email,cellphone,address) VALUES(?,?,?,?,?,?,?)";
			Object[] params=new Object[]{user.getId(),user.getName(),user.getPassword(),user.getBirthday(),user.getEmail(),user.getCellphone(),user.getAddress()};
			runner.update(sql, params);
		}catch(Exception e ){
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.UserDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public User find(String name,String password){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="SELECT * FROM user WHERE name=? AND password=?";
			Object[] params={name,password};
			User user=runner.query(sql, new BeanHandler<User>(User.class), params);
			return user;
		}catch(Exception e ){
			throw new RuntimeException(e);
		}
		
	}
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.UserDao#getAll()
	 */
	@Override
	public List<User> getAll(){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="SELECT * FROM user";
			List<User>list=runner.query(sql, new BeanListHandler<User>(User.class));
			return list;
		}catch(Exception e ){
			throw new RuntimeException(e);
		}
		
	}
	/* (non-Javadoc)
	 * @see com.pochi.dao.impl.UserDao#find(java.lang.String)
	 */
	@Override
	public User find(String id){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="SELECT * FROM WHERE id=?";
			User user=runner.query(sql, new BeanHandler<User>(User.class), id);
			return user;
		}catch(Exception e ){
			throw new RuntimeException(e);
		}
	}
}
