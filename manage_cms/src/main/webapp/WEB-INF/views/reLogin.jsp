<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>页面过期，请重新登录</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/layer/layer.js"></script>
	</head>

	<body>
		<script type="text/javascript">
			$(function() {
				layer.alert('页面过期，请重新登录', {
					closeBtn: 0
				}, function() {
					top.location.href = '<%=request.getContextPath()%>/loginAdmin/login.action';
				});
			})
		</script>
	</body>

</html>