<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>
	<title>后台</title>
  </head>
  
<frameset rows="20%,*">
	<frame src="${pageContext.request.contextPath }/manager/head.jsp" name="head">
	<frameset cols="15%,*">
		<frame name="left" src="${pageContext.request.contextPath }/manager/left.jsp">
		<frame name="body" src="${pageContext.request.contextPath }/manager/body.jsp">
	</frameset>
</frameset>  
</html>
