<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<html>
<head>
<title>后台管理</title>
<script type="text/javascript">
	function test(e) {
		e.style.display = e.style.display == 'block' ? 'none' : 'block';
	}
</script>
</head>

<body>
	<a href="#" onclick="test(children[0])">分类管理
		<ul>
			<li><a
				href="${pageContext.request.contextPath }/manager/CategoryServlet?method=addUI"
				target="body">添加分类</a><br></li>
			<li><a
				href="${pageContext.request.contextPath }/manager/CategoryServlet?method=list"
				target="body">查看分类</a><br> <br></li>
		</ul>
	</a>
	<br>

	<a href="#" onclick="test(children[0])">图书管理
		<ul>
			<li><a
				href="${pageContext.request.contextPath }/manager/BookServlet?method=addUI"
				target="body">添加图书</a><br></li>
			<li><a
				href="${pageContext.request.contextPath
	}/manager/BookServlet?method=list"
				target="body">查看图书 </a><br>
			<br></li>
		</ul>
	</a>
	<br>


	<a href="#" onclick="test(children[0])">订单管理
		<ul>
			<li><a
				href="${pageContext.request.contextPath }/manager/ListOrderServlet?method=list&state=false"
				target="body">未发货订单</a><br></li>
			<li><a
				href="${pageContext.request.contextPath }/manager/ListOrderServlet?method=list&state=true"
				target="body">已发货订单</a><br>
			<br></li>
		</ul>
	</a>
	<br>
</body>
</html>
