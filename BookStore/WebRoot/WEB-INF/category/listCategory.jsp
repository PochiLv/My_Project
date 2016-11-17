<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
	<title>分类列表</title>
	<script type="text/javascript">
		function deleteConfirm(id){
			var flag=window.confirm("您确定删除这个分类吗？")
			if(flag){
				window.location.href="${pageContext.request.contextPath }/manager/CategoryServlet?method=delete&id="+id;
			}
		}
	</script>
	
  </head>
  
  <body style="text-align: center;">
    <table frame="border" width="50%">
    	<tr>
    		<td>分类名称</td>
    		<td style="text-align: center;">分类描述</td>
    		<td>分类操作</td>
    	</tr>
    	<c:forEach items="${list }" var="c">
    		<tr>
    			<td>${c.name }</td>
    			<td>${c.description }</td>
    			<td>
    				<a href="${pageContext.request.contextPath }/manager/CategoryServlet?method=updateUI&id=${c.id}">修改</a>
    				<a href="javascript:deleteConfirm('${c.id }')">删除</a>
    			</td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>

