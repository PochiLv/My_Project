<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>
	<title>添加分类</title>
  </head>
  
  <body style="text-align: center;">
	<form
		action="${pageContext.request.contextPath }/manager/CategoryServlet?method=add" method="post">
		<table frame="border">
			<tr>
				<td>分类名称：</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>分类描述：</td>
				<td>
				<textarea rows="10" cols="30" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="添加分类"></td>
			</tr>
		</table>
	</form>
</body>

</html>
