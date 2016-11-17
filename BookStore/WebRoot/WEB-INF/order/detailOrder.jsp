<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>订单详情</title>
</head>

<body>
	<h3>订单明细</h3>
	<table frame="border"width="60%">
		<tr>
			<td>书名</td>
			<td>售价</td>
			<td>数量</td>
			<td>小计</td>
		</tr>
		<c:forEach items="${order.set }" var="oi">
			<tr>
				<td>${oi.book.name }</td>
				<td>${oi.book.price }</td>
				<td>${oi.quantity }</td>
				<td>${oi.price }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<h3>收货人信息</h3>
	<table frame="border" width="60%">
		<tr>
			<td>用户</td>
			<td>手机</td>
			<td>收货地址</td>
			<td>邮箱</td>
		</tr>
			<td>${order.user.name }</td>
			<td>${order.user.cellphone }</td>
			<td>${order.user.address }</td>
			<td>${order.user.email }</td>
		<tr>
			
		</tr>
	</table>
	<br><br>
	<a href="${pageContext.request.contextPath }/manager/ListOrderServlet?method=finish&id=${order.id}">确定发货</a>
</body>
</html>
