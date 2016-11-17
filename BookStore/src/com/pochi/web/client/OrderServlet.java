package com.pochi.web.client;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pochi.bean.Cart;
import com.pochi.bean.CartItem;
import com.pochi.bean.Order;
import com.pochi.bean.OrderItem;
import com.pochi.bean.User;
import com.pochi.service.BusinessService;
import com.pochi.service.impl.BusinessServiceImpl;
import com.pochi.utils.WebUtils;

public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("create")) {
			createOrder(request, response);
		} else if (method.equals("list")) {
			listOrder(request, response);
		} else if (method.equals("detail")) {
			detail(request, response);
		}
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		BusinessService service=new BusinessServiceImpl();
		Order order=service.findOrder(id);
		request.setAttribute("order", order);
		try {
			request.getRequestDispatcher("/client/detailOrder.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			request.setAttribute("message", "订单信息获取失败！");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
			
			
		}
	}

	private void listOrder(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				request.setAttribute("message", "请先登录！");
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			} else {
				String user_id = user.getId();
				BusinessService service = new BusinessServiceImpl();
				List<Order> list = service.getAllOrder(user_id);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/client/listOrder.jsp").forward(
						request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "获取订单信息失败！");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}

		}
	}

	private void createOrder(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				request.setAttribute("message", "请您先登陆！");
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			} else {
				Order order = new Order();
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				order.setId(WebUtils.takeUUID());
				order.setOrderTime(new Date());
				order.setPrice(cart.getPrice());
				order.setState(false);
				order.setUser(user);
				for (Entry<String, CartItem> me : cart.getMap().entrySet()) {
					CartItem ci = me.getValue();
					OrderItem oi = new OrderItem();
					oi.setBook(ci.getBook());
					oi.setId(WebUtils.takeUUID());
					oi.setPrice(ci.getPrice());
					oi.setQuantity(ci.getQuantity());
					order.getSet().add(oi);
				}
				BusinessService service = new BusinessServiceImpl();
				service.createOrder(order);
				request.setAttribute("message", "创建订单成功！");
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "创建订单失败！");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
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
