<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>
	<title>注册页面</title>
  </head>
  
  <body style="text-align: center;">
  	<form action="${pageContext.request.contextPath }/client/RegisterServlet?method=register" method="post">
  		<table frame="border">
  			<tr>
  				<td>用户名</td>
  				<td>
  					<input type="text" name="name" value="${formbean.name }">${formbean.errors.username }
  				</td>
  			</tr>
  			<tr>
  				<td>密码</td>
  				<td>
  					<input type="password" name="password">${formbean.errors.password }
  				</td>
  			</tr>
  			<tr>
  				<td>确认密码</td>
  				<td>
  					<input type="password" name="password2">${formbean.errors.password2 }
  				</td>
  			</tr>
  			<tr>
  				<td>生日</td>
  				<td>
  					<input type="text" name="birthday" value="${formbean.birthday }">${formbean.errors.birthday }
  				</td>
  			</tr>
  			<tr>
  				<td>邮箱</td>
  				<td>
  					<input type="text" name="email" value="${formbean.email }">${formbean.errors.email }
  				</td>
  			</tr>
  			<tr>
  				<td>手机</td>
  				<td>
  					<input type="text" name="cellphone" value="${formbean.cellphone }">${formbean.errors.cellphone }
  				</td>
  			</tr>
  			<tr>
  				<td>收货地址</td>
  				<td>
  					<textarea rows="5" cols="25" name="address">${formbean.address }</textarea>${formbean.errors.address }
  				</td>
  			</tr>
  			
  			<tr>
  				<td colspan="2">
  					<input type="reset" value="重置" >
  					<input type="submit" value="注册">
  				</td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>

