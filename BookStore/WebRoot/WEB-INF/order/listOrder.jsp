<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>订单列表</title>
</head>

<body style="text-align: center;">
	<c:choose>
		<c:when test="${list==null }">
			<h3>没有需要处理的订单</h3>
		</c:when>
		<c:otherwise>
	<table frame="border" width="60%">
		<tr>
				<td>用户名</td>
				<td>下单时间</td>
				<td>订单总价</td>
				<td>订单状态</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list }" var="order">
				<tr>
					<td>${order.user.name }</td>
					<td>${order.orderTime }</td>
					<td>${order.price }</td>
					<td>${order.state==false?'未发货':'已发货' }</td>
					<td><a
						href="${pageContext.request.contextPath }/manager/ListOrderServlet?method=detail&id=${order.id}">查看</a>
						<a href="#">删除</a></td>
				</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>
