<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

当前第[${page.pageNum }]页
	<a href="${page.url }?pageNum=1">首页</a>
	<c:if test="${page.pageNum >1}">
		<a
			href="${page.url}?pageNum=${page.pageNum-1}">上一页</a>
	</c:if>
	<c:forEach begin="${page.startPage }" end="${page.endPage }" var="p">
		[<a
			href="${page.url}?pageNum=${p}">${p }</a>]&nbsp;
	</c:forEach>
	<c:if test="${page.pageNum <page.totalPage}">
		<a
			href="${page.url}?pageNum=${page.pageNum+1}">下一页</a>
	</c:if>
	跳转到第
	<input type="text" style="width: 20px" id="pageNum">页
	<input type="button" value="Go" onclick="go()">
	<script type="text/javascript">
		function go() {
		var input_pageNum = document.getElementById("pageNum");
		var str = input_pageNum.value;
		var pa = /^\+?[1-9][0-9]*$/;
		if (!pa.test(str)) {
			alert("请输入正整数!");
			input_pageNum.value="";
			return;
		}
		if(str>${page.totalPage}){
			alert("输入页码错误！");
			input_pageNum.value="";
			return;
			
		}
		window.location.href=href="${page.url}?pageNum="+str;
	}
	</script>
	<a
		href="${page.url}?pageNum=${page.totalPage}">尾页</a>
	共[${page.totalPage}]页
	共[${page.totalRecord }]条记录
