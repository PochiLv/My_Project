<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<title>首页</title>
</head>

<frameset rows="25%,*">
	<frame src="${pageContext.request.contextPath }/client/head.jsp"
		name="head">
	<frame src="${pageContext.request.contextPath }/client/IndexServlet?method=list" name="body">
</frameset>  

</html>


