$(function(){
	$("#btn_register").click(function(){
		location="register.jsp";
	});
	$("#img_verifyCode").click(function(){
		document.getElementById('img_verifyCode').src='/xuxu/common/verifyCode?'+Math.random();
	});
});