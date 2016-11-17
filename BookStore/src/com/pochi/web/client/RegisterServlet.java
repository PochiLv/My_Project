package com.pochi.web.client;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.pochi.bean.FormBean;
import com.pochi.bean.User;
import com.pochi.service.BusinessService;
import com.pochi.service.impl.BusinessServiceImpl;
import com.pochi.utils.WebUtils;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equalsIgnoreCase("register")) {
			register(request, response);
		} else {
			throw new RuntimeException("Çë²»ÒªÏ¹¸ã");
		}

	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			BusinessService service = new BusinessServiceImpl();
			FormBean formbean = new FormBean();
			User user = new User();
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			Map<String, String[]> map = request.getParameterMap();

			BeanUtils.populate(formbean, map);
			if (formbean.isValide() == false) {
				request.setAttribute("formbean", formbean);
				request.getRequestDispatcher("/client/register.jsp")
						.forward(request, response);
				return;
			}
			BeanUtils.copyProperties(user, formbean);
			String id = WebUtils.takeUUID();
			user.setId(id);
			service.addUser(user);
			request.setAttribute("message", "×¢²á³É¹¦£¡");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "×¢²áÊ§°Ü£¡");
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
