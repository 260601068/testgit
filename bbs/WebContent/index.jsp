<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-2.2.1.min.js"></script>
<script type="text/javascript">
function regist() {
	$("#login_form").prop("action","login_regist.action")
}
</script>
<body>
	<form id="login_form" action="login_login.action" method="post">
	<label>${logininfo }</label><br/>
	用户名：<input name="username" type="text"><br/>
	密码：<input name="password" type="password"><br/>
	<input type="submit" value="登录">
	<input type="submit" value="注册" onclick="regist()">
	</form>
</body>
</html>