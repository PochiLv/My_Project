package com.pochi.web.manager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pochi.bean.Book;
import com.pochi.bean.Category;
import com.pochi.bean.Page;
import com.pochi.service.BusinessService;
import com.pochi.service.impl.BusinessServiceImpl;
import com.pochi.utils.WebUtils;

public class BookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method==null){
			listBook(request, response);
		}else{
			
			if (method.equalsIgnoreCase("list")) {
				listBook(request, response);
			} else if (method.equals("add")) {
				addBook(request, response);
			} else if (method.equals("addUI")) {
				addUIBook(request, response);
			} else if (method.equals("delete")) {
				deleteBook(request, response);
			} else if (method.equals("updateUI")) {
				updateUIBook(request, response);
			} else if (method.equals("update")) {
				updateBook(request, response);
			} else {
				request.setAttribute("message", "未知错误");
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			}
		}
	}

	private void updateBook(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map<String, String[]> map = request.getParameterMap();
			Book book=new Book();
			BeanUtils.populate(book, map);
			BusinessService service=new BusinessServiceImpl();
			service.updateBook(book);
			request.setAttribute("message", "图书修改成功！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "图书修改失败！");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void updateUIBook(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		BusinessService service = new BusinessServiceImpl();
		Book book = service.findBook(id);
		List<Category> list = service.getAllCategory();
		request.setAttribute("book", book);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("/WEB-INF/book/updateBook.jsp")
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			request.setAttribute("message", "初始化修改页面失败！");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void deleteBook(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String id=request.getParameter("id");
			BusinessService service=new BusinessServiceImpl();
			service.deleteBook(id);
			request.setAttribute("message", "图书删除成功！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("message", "图书删除失败！");
				try {
					request.getRequestDispatcher("/message.jsp").forward(request, response);
				} catch (ServletException | IOException e1) {
					e1.printStackTrace();
				}
			}
	}

	private void addUIBook(HttpServletRequest request,
			HttpServletResponse response) {
		BusinessService service = new BusinessServiceImpl();
		List<Category> list = service.getAllCategory();
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher("/WEB-INF/book/addBook.jsp").forward(
					request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			request.setAttribute("message", "初始化页面失败！");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void addBook(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Book book = doUpload(request);
			book.setId(WebUtils.takeUUID());
			BusinessService service = new BusinessServiceImpl();
			service.addBook(book);
			request.setAttribute("message", "添加图书成功！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加图书失败！");
			try {
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private Book doUpload(HttpServletRequest request) {
		Book book = new Book();
		try {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			// 4. 判断是不是正常的表单上传，是就后面不用做了
			if (!ServletFileUpload.isMultipartContent(request)) {
				return null;
			}
			// 5. 使用解析器解析,返回的list中每个FileItem都是一项
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem fi : list) {
				// 如果只是普通的输入项
				if (fi.isFormField()) {
					String name = fi.getFieldName();
					String value = fi.getString("UTF-8");
					BeanUtils.setProperty(book, name, value);
				} else {
					String filename = fi.getName();
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 获得文件名
					String saveFilename = makeFilename(filename);
					String path = this.getServletContext()
							.getRealPath("/image");
					// 获得上传文件输入流
					InputStream in = fi.getInputStream();
					// 创建一个文件输出流
					OutputStream out = new FileOutputStream(path + "\\"
							+ saveFilename);
					byte[] buff = new byte[1024];
					int len = 0;
					while ((len = in.read(buff)) != -1) {
						out.write(buff, 0, len);
					}
					out.close();
					in.close();
					// 删除临时文件
					fi.delete();
					book.setImage(saveFilename);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return book;
	}

	private String makeFilename(String filename) {
		String ext = filename.substring(filename.lastIndexOf("."));
		String saveFilename = UUID.randomUUID().toString() + ext;
		return saveFilename;
	}

	private void listBook(HttpServletRequest request,
			HttpServletResponse response) {
		String url = request.getContextPath()
				+ "/manager/"
				+ this.getServletName().substring(
						this.getServletName().lastIndexOf(".") + 1);
		try {
			String pageNum = request.getParameter("pageNum");
			BusinessService service = new BusinessServiceImpl();
			Page page = service.getBookPageData(pageNum, url,null);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/WEB-INF/book/listBook.jsp").forward(
					request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "获取图书列表失败！");
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
