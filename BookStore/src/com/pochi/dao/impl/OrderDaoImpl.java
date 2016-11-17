package com.pochi.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.pochi.bean.Book;
import com.pochi.bean.Order;
import com.pochi.bean.OrderItem;
import com.pochi.bean.User;
import com.pochi.dao.OrderDao;
import com.pochi.utils.JdbcUtils;

public class OrderDaoImpl implements OrderDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pochi.dao.impl.OrderDao#add(com.pochi.bean.Order)
	 */
	@Override
	public void add(Order order) {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			String sql = "INSERT INTO orders (id,price,orderTime,state,user_id) VALUES(?,?,?,?,?)";
			Object[] params = { order.getId(), order.getPrice(),
					order.getOrderTime(), order.isState(),
					order.getUser().getId() };
			runner.update(sql, params);
			Set<OrderItem> set = order.getSet();
			Object[][] params2 = new Object[set.size()][];
			int i = 0;
			for (OrderItem oi : set) {
				params2[i++] = new Object[] { oi.getId(), oi.getQuantity(),
						oi.getPrice(), oi.getBook().getId(), order.getId() };
			}
			sql = "INSERT INTO orderitem (id,quantity,price,book_id,order_id) VALUES(?,?,?,?,?)";
			runner.batch(sql, params2);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pochi.dao.impl.OrderDao#update(com.pochi.bean.Order)
	 */
	@Override
	public void update(Order order) {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			String sql = "UPDATE orders SET state=? WHERE id=?";
			Object[] params = { order.isState(), order.getId() };
			runner.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pochi.dao.impl.OrderDao#find(java.lang.String)
	 */
	@Override
	public Order find(String id) {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			String sql = "SELECT * FROM orders WHERE id=?";
			Order order = runner.query(sql,
					new BeanHandler<Order>(Order.class), id);
			sql = "SELECT * FROM orderitem WHERE order_id=?";
			List<OrderItem> list = runner.query(sql,
					new BeanListHandler<OrderItem>(OrderItem.class), id);
			for (OrderItem oi : list) {
				sql = "SELECT b.* FROM book b,orderitem o WHERE o.id=? AND b.id=o.book_id";
				Book book = runner.query(sql,
						new BeanHandler<Book>(Book.class), oi.getId());
				oi.setBook(book);
			}
			order.getSet().addAll(list);
			sql = "SELECT u.* FROM user u,orders o WHERE o.id=? AND o.user_id=u.id";
			User user = runner
					.query(sql, new BeanHandler<User>(User.class), id);
			order.setUser(user);
			return order;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pochi.dao.impl.OrderDao#getAll()
	 */
	@Override
	public List<Order> getAll() {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			String sql = "SELECT * FROM orders";
			List<Order> list = runner.query(sql, new BeanListHandler<Order>(
					Order.class));
			for (Order order : list) {
				sql = "SELECT u.* FROM user u,orders o WHERE o.id=? AND o.user_id=u.id";
				User user = runner.query(sql,
						new BeanHandler<User>(User.class), order.getId());
				order.setUser(user);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> getAll(boolean state) {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			String sql = "SELECT * FROM orders WHERE state=?";
			List<Order> list = runner.query(sql, new BeanListHandler<Order>(
					Order.class), state);
			for (Order order : list) {
				sql = "SELECT u.* FROM user u,orders o WHERE o.id=? AND o.user_id=u.id";
				User user = runner.query(sql,
						new BeanHandler<User>(User.class), order.getId());
				order.setUser(user);
			}
			return list.size()==0?null:list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> getAll(String user_id) {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			String sql = "SELECT * FROM orders WHERE user_id=?";
			List<Order> list = runner.query(sql, new BeanListHandler<Order>(
					Order.class), user_id);
			for (Order order : list) {
				sql = "SELECT u.* FROM user u,orders o WHERE o.id=? AND o.user_id=u.id";
				User user = runner.query(sql,
						new BeanHandler<User>(User.class), order.getId());
				order.setUser(user);
			}
			return list.size()==0?null:list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
