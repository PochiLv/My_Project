<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>我的购物车</title>
<script type="text/javascript">
		function changeQuan(input,oldValue,id){
			var newValue=input.value;
			 var pa=/^\+?[1-9][0-9]*$/; 
			 if(!pa.test(newValue)){
				alert("请输入正整数!");
				input.value=oldValue;
				return;
			} 
			var flag=window.confirm("确定修改数量?");
			if(flag){
				window.location.href="${pageContext.request.contextPath}/client/CartServlet?method=update&id="+id+"&quantity="+newValue;
			}else{
				input.value=oldValue;
			}
		}
		function clear(){
			var flag=window.confirm("您确定清空购物车吗？");
			if(flag){
				window.location.href="${pageContext.request.contextPath }/client/CartServlet?method=clear";
			}
		}
		function deleteItem(id){
			var flag=window.confirm("您确定删除该条目吗？");
			if(flag)
			{
				window.location.href="${pageContext.request.contextPath }/client/CartServlet?method=delete&id="+id;
			}
		}
		function confirmOrder(){
			var flag=window.confirm("您确定下单吗？");
			if(flag){
				window.location.href="${pageContext.request.contextPath}/client/OrderServlet?method=create";
			}
		}
	</script>
</head>

<body style="text-align: center;">
	<c:choose>
		<c:when test="${empty(cart.map)}">
			<h3>购物车为空</h3>
		</c:when>
		<c:otherwise>
			<h1>购物车列表</h1>
			<hr>
			<br>
			<br>
			<table frame="border" style="width: 1000px">
				<tr>
					<td>书名</td>
					<td>作者</td>
					<td>单价</td>
					<td>数量</td>
					<td>小计</td>
					<td>操作</td>
				</tr>
				<c:forEach var="me" items="${cart.map}">
					<tr>
						<td>${me.value.book.name }</td>
						<td>${me.value.book.author }</td>
						<td>${me.value.book.price }</td>
						<td>
						<input type="text" name="quantity"
							value="${me.value.quantity }" style="width: 30px"
							onchange="changeQuan(this,${me.value.quantity },'${me.key }')">
						</td>
						<td>${me.value.price }</td>
						<td><a href="javascript:deleteItem('${me.key }')">删除</a></td>
					</tr>

				</c:forEach>

				<tr>
					<td colspan="2"><a href="javascript:clear()">清空购物车</a></td>
					<td colspan="2">合计</td>
					<td colspan="2">${cart.price }</td>

				</tr>
			</table>
			<br>
			<div style="margin-left: 920px">
				<a href="javascript:confirmOrder()" >确认订单</a>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>
