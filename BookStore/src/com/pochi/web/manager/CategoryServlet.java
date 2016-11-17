package com.pochi.web.manager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.pochi.bean.Category;
import com.pochi.service.BusinessService;
import com.pochi.service.impl.BusinessServiceImpl;
import com.pochi.utils.WebUtils;

public class CategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method=request.getParameter("method");
		if(method.equalsIgnoreCase("list")){
			listCategory(request,response);
		}else if(method.equals("add")){
			addCategory(request,response);
		}else if(method.equals("addUI")){
			addUICategory(request,response);
		}else if(method.equals("delete")){
			deleteCategory(request,response);
		}else if(method.equals("updateUI")){
			updateUICategory(request,response);
		}else if(method.equals("update")){
			updateCategory(request,response);
		}
		else{
			request.setAttribute("message", "δ֪����");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}

	}

	private void updateCategory(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			Map<String, String[]> map=request.getParameterMap();
			Category category=new Category();
			BeanUtils.populate(category, map);
			BusinessService service=new BusinessServiceImpl();
			service.updateCategory(category);
			request.setAttribute("message", "�����޸ĳɹ���");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "�����޸�ʧ�ܣ�");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void updateUICategory(HttpServletRequest request,
			HttpServletResponse response) {
		String id=request.getParameter("id");
		try{
			BusinessService service=new BusinessServiceImpl();
			Category category=service.findCategory(id);
			if(category!=null){
				request.setAttribute("category", category);
				request.getRequestDispatcher("/WEB-INF/category/updateCategory.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "��ȡ������Ϣʧ�ܣ�");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void deleteCategory(HttpServletRequest request,
			HttpServletResponse response) {
		try{
		String id=request.getParameter("id");
		BusinessService service=new BusinessServiceImpl();
		service.deleteCategory(id);
		request.setAttribute("message", "����ɾ���ɹ���");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "����ɾ��ʧ�ܣ�");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	private void addUICategory(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/category/addCategory.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			Map<String, String[]> map=request.getParameterMap();
			Category category=new Category();
			BeanUtils.populate(category, map);
			category.setId(WebUtils.takeUUID());
			/**********************************
			 * 		�˴�����Ҳ��Ҫ���������
			 *********************************/
			BusinessService service=new BusinessServiceImpl();
			service.addCategory(category);
			
			request.setAttribute("message", "��ӷ���ɹ���");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("message", "��ӷ���ʧ�ܣ�");
				try {
					request.getRequestDispatcher("/message.jsp").forward(request, response);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();
				}
			}

		
	}

	private void listCategory(HttpServletRequest request,
			HttpServletResponse response) {
		BusinessService service=new BusinessServiceImpl();
		List<Category> list=service.getAllCategory();
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("/WEB-INF/category/listCategory.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			request.setAttribute("message", "��ȡ�����б�ʧ�ܣ�");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			};
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
