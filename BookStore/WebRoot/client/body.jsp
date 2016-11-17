<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/pochi.tld" prefix="pochi"%>
<html>
<head>
<title>图书前台</title>
</head>

<body style="text-align: center; width: 80%">
	<div id="content" style="width:85%; margin-left: 20px">
		<div
			style="float: left; border:1px blue solid; height: 300px; width: 150px;"
			align="left" id="category">
			<div style="margin: 10px">图书类型</div>
			<ul style="text-align: left ">
				<c:forEach items="${list }" var="category">
					<li style="margin-bottom: 15px"><a
						href="${pageContext.request.contextPath }/client/IndexServlet?

method=list&category_id=${category.id}">${category.name }</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div style="float: left;   margin-left:100px ; width: 75%"
			id="bookandpage">
			<div id="book">
				<c:forEach items="${page.list }" var="book">
					<div style="float: left;">
						<img src="${pageContext.request.contextPath }/image/${book.image}"
							style="width: 120px ; height:90px">
					</div>
					<div style="float: left" id="bookinfo">
						<ul style="text-align: left">
							<li>书名：${book.name }</li>
							<li>作者：${book.author }</li>
							<li>售价：${book.price }</li>
							<li>描述：${pochi:subStr(book.description,10) }</li>
							<br />
							<a
								href="${pageContext.request.contextPath }/client/CartServlet?method=buy&
book_id=${book.id}">添加到购物车</a>${cartMessage }
						</ul>
					</div>
					<div style="clear: both;"></div>
					<hr>
				</c:forEach>
			</div>
			<div style="" id="page">
				当前第[${ page.pageNum}]页 &nbsp; &nbsp;
				<c:if test="${page.pageNum!=1 }">
					<a
						href="${pageContext.request.contextPath }/client/IndexServlet?method=list&category_id=${param.category_id }&pageNum=

${page.pageNum-1}">上一页</a>
				</c:if>
				<c:forEach begin="${page.startPage }" end="${page.endPage }"
					var="pageNum">[
					<a
						href="${pageContext.request.contextPath }/client/IndexServlet?

method=list&category_id=${param.category_id }&pageNum=${pageNum}">${pageNum }</a>]
				</c:forEach>

				<c:if test="${page.pageNum!=page.totalPage }">
					<a
						href="${pageContext.request.contextPath }/client/IndexServlet?method=list&category_id=${param.category_id }&pageNum=

${page.pageNum+1}">下一页</a>
				</c:if>
				&nbsp;&nbsp; 总共[${page.totalPage }]页
				&nbsp;&nbsp;总共[${page.totalRecord }]条记录
			</div>
		</div>
		<%-- <div>
		<%@ include file="/public/page.jsp" %>
		</div> --%>
	</div>
</body>
</html>