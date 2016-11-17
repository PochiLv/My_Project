<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
	<title>添加图书</title>
  </head>
  
  <body style="text-align: center;">
	<form
		action="${pageContext.request.contextPath }/manager/BookServlet?method=add" method="post" enctype="multipart/form-data">
		<table frame="border" width="50%">
			<tr>
				<td>图书名称：</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>图书作者</td>
				<td>
					<input type="text" name=author>
				</td>
			</tr>
			<tr>
				<td>图书售价</td>
				<td>
					<input type="text" name="price">
				</td>
			</tr>
			<tr>
				<td>图片</td>
				<td>
					<input type="file" name="image">				
				</td>
			</tr>
			<tr>
				<td>图书描述：</td>
				<td>
				<textarea rows="10" cols="30" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td>图书类型</td>
				<td>
					<select name="category_id">
						<c:forEach items="${list }" var="c">
							<option value="${c.id }">${c.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="reset" value="重置图书">
				<input type="submit" value="添加图书"></td>
			</tr>
		</table>
	</form>
</body>

</html>
