package com.pochi.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pochi.bean.Category;
import com.pochi.bean.Page;
import com.pochi.service.BusinessService;
import com.pochi.service.impl.BusinessServiceImpl;

public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method == null) {
			listBook(request, response);
		} else {
			if (method.equalsIgnoreCase("list")) {
				listBook(request, response);
			}
			/*
			 * else if (method.equals("add")) { addBook(request, response); }
			 * else if (method.equals("addUI")) { addUIBook(request, response);
			 * } else if (method.equals("delete")) { deleteBook(request,
			 * response); } else if (method.equals("updateUI")) {
			 * updateUIBook(request, response); } else if
			 * (method.equals("update")) { updateBook(request, response); }
			 */
			else {
				request.setAttribute("message", "Î´Öª´íÎó");
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			}
		}
	}

	private void listBook(HttpServletRequest request,
			HttpServletResponse response) {
		Page page = null;
		try {
			String category_id = request.getParameter("category_id");
			String pageNum = request.getParameter("pageNum");
			String url = request.getContextPath()
					+ "/client/"
					+ this.getServletName().substring(
							this.getServletName().lastIndexOf(".") + 1);
			BusinessService service = new BusinessServiceImpl();
			List<Category> list = service.getAllCategory();
			page = service.getBookPageData(pageNum, url, category_id);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/client/body.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ÍøÕ¾³õÊ¼»¯´íÎó£¡");
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
