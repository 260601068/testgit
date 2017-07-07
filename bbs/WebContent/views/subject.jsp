<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:debug></s:debug><hr/>
<span style="float:right">欢迎${session.user.user_name }!</span>
<h1 style="text-align:center">全部主题列表</h1>
<ol>
<s:iterator value="#subjects" var="subject">
<a href="subject_detail?sub_id=${subject.sub_id }"><li>${subject.title } </li></a>
</s:iterator>
</ol>

<a href="views/subject_design.jsp">创建主题</a>
</body>
</html>