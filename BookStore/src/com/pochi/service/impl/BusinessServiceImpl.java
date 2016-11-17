package com.pochi.service.impl;

import java.util.List;

import com.pochi.bean.Book;
import com.pochi.bean.Cart;
import com.pochi.bean.CartItem;
import com.pochi.bean.Category;
import com.pochi.bean.Order;
import com.pochi.bean.Page;
import com.pochi.bean.User;
import com.pochi.dao.BookDao;
import com.pochi.dao.CategoryDao;
import com.pochi.dao.OrderDao;
import com.pochi.dao.UserDao;
import com.pochi.service.BusinessService;
import com.pochi.utils.DaoFactory;

public class BusinessServiceImpl implements BusinessService {
	private CategoryDao cDao = DaoFactory.getDaoFactory().getDao(
			"com.pochi.dao.impl.CategoryDaoImpl", CategoryDao.class);
	private BookDao bDao = DaoFactory.getDaoFactory().getDao(
			"com.pochi.dao.impl.BookDaoImpl", BookDao.class);
	private UserDao uDao=DaoFactory.getDaoFactory().getDao("com.pochi.dao.impl.UserDaoImpl", UserDao.class);
	private OrderDao oDao=DaoFactory.getDaoFactory().getDao("com.pochi.dao.impl.OrderDaoImpl", OrderDao.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pochi.service.impl.BusinessService#addCategory(com.pochi.bean.Category
	 * )
	 */
	@Override
	public void addCategory(Category category) {
		cDao.Add(category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pochi.service.impl.BusinessService#deleteCategory(java.lang.String)
	 */
	@Override
	public void deleteCategory(String id) {
		cDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pochi.service.impl.BusinessService#updateCategory(com.pochi.bean.
	 * Category)
	 */
	@Override
	public void updateCategory(Category category) {
		cDao.update(category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pochi.service.impl.BusinessService#findCategory(java.lang.String)
	 */
	@Override
	public Category findCategory(String id) {
		return cDao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pochi.service.impl.BusinessService#getAllCategory()
	 */
	@Override
	public List<Category> getAllCategory() {
		return cDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pochi.service.impl.BusinessService#addBook(com.pochi.bean.Book)
	 */
	@Override
	public void addBook(Book book) {
		bDao.add(book);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pochi.service.impl.BusinessService#deleteBook(java.lang.String)
	 */
	@Override
	public void deleteBook(String id) {
		bDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pochi.service.impl.BusinessService#updateBook(com.pochi.bean.Book)
	 */
	@Override
	public void updateBook(Book book) {
		bDao.update(book);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pochi.service.impl.BusinessService#findBook(java.lang.String)
	 */
	@Override
	public Book findBook(String id) {
		return bDao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pochi.service.impl.BusinessService#getBookPageData(java.lang.String)
	 * 
	 * @Override public Page getBookPageData(String pageNum,String url){ Page
	 * page=null; int totalRecord=bDao.getTotalRecord(); if(pageNum==null){
	 * page=new Page(1, totalRecord); }else{ page=new
	 * Page(Integer.parseInt(pageNum), totalRecord); } page.setUrl(url);
	 * List<Book> list=bDao.getPageData(page.getStartIndex(),
	 * page.getPageSize()); page.setList(list); return page; }
	 */
	@Override
	public Page getBookPageData(String pageNum, String url, String category_id) {
		Page page = null;
		List<Book> list = null;
		int totalRecord = 0;
		if (category_id == null||category_id.trim().equals("")) {
			totalRecord = bDao.getTotalRecord();
		} else {
			totalRecord = bDao.getTotalRecordByC(category_id);
		}
		if (pageNum == null) {
			page = new Page(1, totalRecord);
		} else {
			page = new Page(Integer.parseInt(pageNum), totalRecord);
		}
		page.setUrl(url);
		if (category_id == null||category_id.trim().equals("")) {
			list = bDao.getPageData(page.getStartIndex(), page.getPageSize());
		} else {
			list = bDao.getPageData(page.getStartIndex(), page.getPageSize(),
					category_id);
		}
		page.setList(list);
		return page;

	}
	@Override
	public void addUser(User user){
		uDao.add(user);
	}
	@Override
	public User findUser(String id){
		return uDao.find(id);
	}
	@Override
	public User login(String name,String password){
		return uDao.find(name, password);
	}
	@Override
	public List<User> getAllUser(){
		return uDao.getAll();
	}
	@Override
	public void buyBook(Book book,Cart cart){
		cart.addBook(book);
	}
	@Override
	public void deleteCartItem(String book_id,Cart cart){
		cart.getMap().remove(book_id);
	}
	@Override
	public void clearCart(Cart cart){
		cart.getMap().clear();
	}
	@Override
	public void updateCart(String book_id,String quantity,Cart cart){
		CartItem ci=cart.getMap().get(book_id);
		ci.setQuantity(Integer.parseInt(quantity));
	}
	@Override
	public void createOrder(Order order){
		oDao.add(order);
	}
	@Override
	public void finisheOrder(Order order){
		oDao.update(order);
	}
	@Override
	public Order findOrder(String id){
		return oDao.find(id);
	}
	@Override
	public List<Order> getAllOrder(boolean state){
		return oDao.getAll(state);
	}
	@Override
	public List<Order> getAllOrder(String user_id){
		return oDao.getAll(user_id);
	}
}	
