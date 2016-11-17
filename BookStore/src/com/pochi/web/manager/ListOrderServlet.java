package com.pochi.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pochi.bean.Order;
import com.pochi.service.BusinessService;
import com.pochi.service.impl.BusinessServiceImpl;

public class ListOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method=request.getParameter("method");
		if(method.equals("list")){
			list(request, response);
		}else if(method.equals("detail")){
			detail(request,response);
		}else if(method.equals("finish")){
			finishOrder(request,response);
		}
	}

	private void finishOrder(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String id=request.getParameter("id");
			BusinessService service=new BusinessServiceImpl();
			Order order=service.findOrder(id);
			order.setState(true);
			service.finisheOrder(order);
			request.setAttribute("message", "发货成功！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "发货失败！");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		BusinessService service=new BusinessServiceImpl();
		Order order=service.findOrder(id);
		request.setAttribute("order", order);
		try {
			request.getRequestDispatcher("/WEB-INF/order/detailOrder.jsp").forward(request, response);
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

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String state=request.getParameter("state");
		boolean s=false;
		if(state.equals("true")){
			s=true;
		}
		BusinessService service=new BusinessServiceImpl();
		List<Order> list=service.getAllOrder(s);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/order/listOrder.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
