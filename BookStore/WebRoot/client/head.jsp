<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>网上书城</title>
</head>

<body style="text-align: center;">
	<h2>瀚海书城</h2>
	<div style="float: left; margin-left: 570px; border: rea solid 1px;">
		<a
			href="${pageContext.request.contextPath }/client/IndexServlet?method=list"
			target="body">首页</a> 
			<a href="${pageContext.request.contextPath }/client/listcart.jsp"target="body">查看购物车</a>
			 <a href="${pageContext.request.contextPath }/client/OrderServlet?method=list"target="body">查看订单</a>
	</div>

	<div style="float: right">
		<c:choose>
			<c:when test="${user!=null }">
				${user.name }：欢迎您 <a href="${pageContext.request.contextPath }/client/LoginServlet?method=logout">注销</a>
			</c:when>
			<c:otherwise>
				<form
					action="${pageContext.request.contextPath }/client/LoginServlet?method=login"
					method="post">
					用户名：<input type="text" name="name" style="width: 100px">
					密码：<input type="password" name="password" style="width: 100px">
					<input type="submit" value="登录"> <input type="button"
						value="注册"
						onclick="javascript:window.parent.body.location.href='${pageContext.request.contextPath}/client/register.jsp'">
				</form>  
				${message }		
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
