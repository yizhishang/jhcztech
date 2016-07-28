<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<script src="<%=basePath %>resources/jquery-easyui-1.4.1/jquery-1.10.2.min.js"></script>
<script src="<%=basePath %>js/jquery.validate.min.js"></script>
<script src="<%=basePath %>js/validateMethod.js"></script>
<script src="<%=basePath %>js/city.min.js"></script>
<script src="<%=basePath %>js/jquery.cityselect.js"></script>
</head>
<body>
	<form action="<%=basePath %>/custInfo.do?method=doCustInfoUpdate" method="post">
		<table>
			<tr>
				<td>通讯地址：</td>
				<td><input type="text" name="address"/></td>
			</tr>
			<tr>
				<td>电子箱：</td>
				<td><input type="text" name="mail"/></td>
			</tr>
			<tr>
				<td>对账单寄送方式：</td>
				<td><input type="text" name="deliver_type"/></td>
			</tr>
			<tr>
				<td>预留验证信息：</td>
				<td><input type="text" name="reserve_info"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交"/></td>
			</tr>
		</table>
	</form>
</body>
</html>