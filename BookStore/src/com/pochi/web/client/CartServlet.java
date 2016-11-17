package com.pochi.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pochi.bean.Book;
import com.pochi.bean.Cart;
import com.pochi.service.BusinessService;
import com.pochi.service.impl.BusinessServiceImpl;

public class CartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method=request.getParameter("method");
		if(method.equals("buy")){
			buy(request,response);
		}else if(method.equals("update")){
			update(request,response);
		}else if(method.equals("delete")){
			delete(request,response);
		}else if(method.equals("clear")){
			clear(request,response);
		}
	}

	private void clear(HttpServletRequest request, HttpServletResponse response) {
		try{
			Cart cart=(Cart) request.getSession().getAttribute("cart");
			BusinessService service=new BusinessServiceImpl();
			service.clearCart(cart);
			request.getRequestDispatcher("/client/listcart.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "删除错误");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		try{
			String id=request.getParameter("id");
			Cart cart=(Cart) request.getSession().getAttribute("cart");
			BusinessService service=new BusinessServiceImpl();
			service.deleteCartItem(id, cart);
			request.getRequestDispatcher("/client/listcart.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "删除错误");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		try{
			String quantity=request.getParameter("quantity");
			String id=request.getParameter("id");
			Cart cart=(Cart) request.getSession().getAttribute("cart");
			BusinessService service=new BusinessServiceImpl();
			service.updateCart(id, quantity, cart);
			request.getRequestDispatcher("/client/listcart.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "更新购物车错误");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	private void buy(HttpServletRequest request, HttpServletResponse response) {
		try{
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		String id=request.getParameter("book_id");
		BusinessService service=new BusinessServiceImpl();
		Book book=service.findBook(id);
		service.buyBook(book, cart);
		request.setAttribute("message", "成功添加到购物车！");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "添加购物车失败！");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
