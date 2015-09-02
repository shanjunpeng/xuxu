<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/user/register.css">
</head>
<body>
	<div class="main">
		<form action="<%=path%>/user/register.action" method="post">
			<div class="register">
				<ul>
					<li><label class="name">用户名</label><input name="user.name"></li>
					<li><label class="name">邮箱</label><input name="user.email"></li>
					<li><label class="name">密码</label><input type="password" name="user.pwd"></li>
					<li><label class="name">确认密码</label><input type="password" name="repeatPwd"></li>
				</ul>
				<input id="registerBtn" type="submit" value="确认注册">
			</div>
		</form>	
	</div>
	
</body>
</html>