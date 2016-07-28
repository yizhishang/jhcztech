<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加银行卡</title>
</head>
<body>
	<h1>${msg }</h1><a href="<%=path%>/bindBankCard.do?method=doInputChenckInfo">开户</a>
</body>
</html>