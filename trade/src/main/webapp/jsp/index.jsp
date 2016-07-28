<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String message = (String)request.getParameter("msg");
String timeout = (String)request.getParameter("timeout");
%>

<!DOCTYPE HTML>
<html lang="zh-CN">
	<head>
	<title>基金登录界面</title>
    <script type="text/javascript" src="<%=basePath%>js/package.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/jquery-easyui-1.4.1/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/jquery-easyui-1.4.1/themes/icon.css">
    <script type="text/javascript" src="<%=basePath%>resources/jquery-easyui-1.4.1/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/user_login.css">
    <script src="<%=basePath%>js/base.js"></script>
    <script src="<%=basePath%>js/jquery.form.js"></script>
	</head>
	<body>
		<h1>欢迎来到金恒创智网上交易系统！</h1>
		<a href="<%=basePath%>userRegister/toRegister">开户</a>
		<a href="<%=basePath%>fund/listAllFund">基金超市</a>
	</body>
</html>
