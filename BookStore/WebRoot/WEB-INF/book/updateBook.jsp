<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
	<title>修改图书</title>
  </head>
  
  <body style="text-align: center;">
	<form
		action="${pageContext.request.contextPath }/manager/BookServlet?method=update" method="post">
		<input type="hidden" name="image" value="${book.image }">
		<input type="hidden" name="id" value="${book.id }">
		<table frame="border" width="50%">
			<tr>
				<td>图书名称：</td>
				<td><input type="text" name="name" value="${book.name }"></td>
			</tr>
			<tr>
				<td>图书作者</td>
				<td>
					<input type="text" name=author value="${book.author }">
				</td>
			</tr>
			<tr>
				<td>图书售价</td>
				<td>
					<input type="text" name="price" value="${book.price }">
				</td>
			</tr>
			<tr>
				<td>图片</td>
				<td>
					<img src="${pageContext.request.contextPath }/image/${book.image}" width="50%">	<br>
				</td>
			</tr>
			<tr>
				<td>图书描述：</td>
				<td>
				<textarea rows="10" cols="30" name="description">${book.description }</textarea>
				</td>
			</tr>
			<tr>
				<td>图书类型</td>
				<td>
					<select name="category_id">
						<c:forEach items="${list }" var="c">
							<option value="${c.id }" ${book.category_id==c.id?'selected="selected"':'' }>${c.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="修改图书"></td>
			</tr>
		</table>
	</form>
</body>

</html>
