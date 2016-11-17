<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/pochi.tld"  prefix="pochi"%>

<html>
  <head>
	<title>图书列表</title>
	<script type="text/javascript">
		function deleteConfirm(id){
			var flag=window.confirm("您确定删除这本图书吗？")
			if(flag){
				window.location.href="${pageContext.request.contextPath }/manager/BookServlet?method=delete&id="+id;
			}
		}
	</script>
	
  </head>
  
  <body style="text-align: center;">
    <table frame="border" >
    	<tr>
    		<td>图书名称</td>
    		<td>作者</td>
    		<td>售价</td>
    		<td style="text-align: center;">图书描述</td>
    		<td>图书操作</td>
    	</tr>
    	<c:forEach items="${page.list }" var="b">
    		<tr>
    			<td>${pochi:subStr(b.name,10) }</td>
    			<td>${b.author }</td>
    			<td>￥${b.price }</td>
    			<td>${pochi:subStr(b.description,10)}</td>
    			<td>
    				<a href="${pageContext.request.contextPath }/manager/BookServlet?method=updateUI&id=${b.id}">修改</a>
    				<a href="javascript:deleteConfirm('${b.id }')">删除</a>
    			</td>
    		</tr>
    	</c:forEach>
    </table>
    <%@ include file="/public/page.jsp" %>
  </body>
</html>

