<%@page contentType="text/html;charset=utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dang.pojos.User"%>
<%@page import="java.util.Date"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String p = request.getContextPath(); %>

<link href="../css/book_head090107.css" rel="stylesheet" type="text/css" />

<div class="head_container">
	<div class="head_welcome">
		
		<div class="head_welcome_right">
			<span class="head_welcome_text"> </span>
			<span class="head_welcome_text"> <span class="little_n">
					| <a href="<%=p%>/user/myDangDang.action" name="mydangdang" class="head_black12a">我的当当</a> | <a
					href="#" name="helpcenter" class="head_black12a" target="_blank">帮助</a>
					| </span> </span>
			<div class="cart gray4012">
				<a href="<%=p %>/cart/cartList.action">购物车</a>
			</div>
		</div>
		<span class="head_toutext" id="logininfo">
		<b>您好 <span style="color:red">${user.nickname }</span>，欢迎光临当当网</b>
		<% 
		User user =(User)session.getAttribute("user"); 
		if(user!=null){
			long loginTime = (Long)session.getAttribute("loginTime");
			String ip = (String)session.getAttribute("ip"); 
		%>
			
			[&nbsp;<a href="logout.action" class="b">登出</a>&nbsp;]
			<br/>
			本次登录：<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(loginTime)) %>&nbsp;
			上次登录：<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(user.getLastLoginTime())) %> 
			|
			您的ip：${ip }&nbsp;
			上次登录ip：${user.lastLoginIp }
			
		<%}else{ %>
		
			[&nbsp;<a href="<%=p %>/user/login_form.jsp" class="b">登录</a>|<a
			href="<%=p %>/user/register_form.jsp" class="b">注册</a>&nbsp;]
			
		<%} %>
			
			
		</span>
			
		
	</div>
	<div class="head_head_list">
		<div class="head_head_list_left">
			<span class="head_logo"><a href="#" name="backtobook"><img
						src="../images/booksaleimg/book_logo.gif" /> </a> </span>
		</div>
		<div class="head_head_list_right">

			<div class="head_head_list_right_b">
			</div>
		</div>
	</div>
	<div class="head_search_div">

	</div>
	<div class="head_search_bg"></div>
</div>
