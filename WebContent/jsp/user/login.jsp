<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String msg = (String) request.getAttribute("loginFailed");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>登录</title>
		
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/normalize.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/user/login.css">
	</head>
	<body>
		<div align="center" id="bg">
			<div id="logo">
			</div>
			<div id="content">
				<div id="left">
					<img src="<%=path%>/img/bg/a1.jpg" >
				</div>
				<div id="center">
					<div id="c_title">
						用户登录
					</div>
					<div id="login">
						<form action="<%=path%>/user/login.action" method="post">
							<ul>
								<li>
									<label class="item">用户名：</label>
									<input name="user.name" />
								</li>
								<li>
									<label class="item">密&nbsp;&nbsp;码：</label>
									<input type="password" name="user.pwd" />
								</li>
								<li>
									<label class="item">验证码：</label>
									<input size="10" id="verifyCode" name="verifyCode"/>
									<span><img src="<%=path%>/common/verifyCode" id='img_verifyCode'/></span>
								</li>
							</ul>
							<p id="msg">${msg}</p>
							<input type="submit" value="登录" />
							<input id="btn_register" type="button" value="注册"/>

						</form>
					</div>
				</div>
				<div id="right"></div>
			</div>
			<%@include file="/jsp/common/bottom.jsp"%>
			<script type="text/javascript" src="<%=path%>/js/login/login.js"></script>
		</div>
	</body>
</html>