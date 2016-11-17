package com.pochi.service;

import java.util.List;

import com.pochi.bean.Book;
import com.pochi.bean.Cart;
import com.pochi.bean.Category;
import com.pochi.bean.Order;
import com.pochi.bean.Page;
import com.pochi.bean.User;

public interface BusinessService {

	/* (non-Javadoc)
	 * @see com.pochi.service.impl.BusinessService#addCategory(com.pochi.bean.Category)
	 */
	public abstract void addCategory(Category category);

	/* (non-Javadoc)
	 * @see com.pochi.service.impl.BusinessService#deleteCategory(java.lang.String)
	 */
	public abstract void deleteCategory(String id);

	/* (non-Javadoc)
	 * @see com.pochi.service.impl.BusinessService#updateCategory(com.pochi.bean.Category)
	 */
	public abstract void updateCategory(Category category);

	/* (non-Javadoc)
	 * @see com.pochi.service.impl.BusinessService#findCategory(java.lang.String)
	 */
	public abstract Category findCategory(String id);

	/* (non-Javadoc)
	 * @see com.pochi.service.impl.BusinessService#getAllCategory()
	 */
	public abstract List<Category> getAllCategory();

	public abstract void addBook(Book book);

	public abstract void deleteBook(String id);

	public abstract void updateBook(Book book);

	public abstract Book findBook(String id);


/*	public abstract Page getBookPageData(String pageNum, String url);*/

	public abstract Page getBookPageData(String pageNum, String url,
			String category_id);

	void addUser(User user);

	User findUser(String id);

	User login(String name, String password);

	List<User> getAllUser();

	void buyBook(Book book, Cart cart);

	void clearCart(Cart cart);

	void deleteCartItem(String book_id, Cart cart);

	void updateCart(String book_id, String quantity, Cart cart);

	void createOrder(Order order);

	void finisheOrder(Order order);

	Order findOrder(String id);

	List<Order> getAllOrder(boolean state);

	List<Order> getAllOrder(String user_id);

}