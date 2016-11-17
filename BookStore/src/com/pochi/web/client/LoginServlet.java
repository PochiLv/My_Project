package com.pochi.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pochi.bean.User;
import com.pochi.service.BusinessService;
import com.pochi.service.impl.BusinessServiceImpl;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equalsIgnoreCase("login")) {
			login(request, response);
		}else if(method.equalsIgnoreCase("logout")){
			logout(request,response);
		}
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
		request.getSession().removeAttribute("user");
		request.getRequestDispatcher("/client/head.jsp").forward(request, response);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private void login(HttpServletRequest request, HttpServletResponse response) {
		try {
			BusinessService service = new BusinessServiceImpl();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			User user = service.login(name, password);
			if (user != null) {
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("/client/head.jsp").forward(request, response);

			} else {
				request.setAttribute("message", "密码或用户名错误");
				request.getRequestDispatcher("/client/head.jsp").forward(request, response);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
