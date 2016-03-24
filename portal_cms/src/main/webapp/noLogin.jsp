<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<script language="javascript">
	if(window.confirm("页面过期，请重新登录"))
	{
		window.location.href="<%=request.getContextPath()%>/loginAdmin/login.action";
	}
</script>	
