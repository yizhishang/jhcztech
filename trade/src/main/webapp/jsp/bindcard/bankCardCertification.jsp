<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绑定银行卡</title>
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
     <div>
     	<form action="bindBankCard.do?method=doOpenFundAccount" method="post">
     		持卡人：<input type="text" name="cardName" id="cardName"></input></br>
     		银行卡号：<input type="text" name="bankCardID" id="bankCardID"></input></br>
     		开户地：<input type="text" name="openAccountAddress" id="openAccountAddress"></input></br>
     		<div id="city_3">
				<select class="prov" name="prov" id="prov"></select> 
				<select class="city" name="city"disabled="disabled"></select>
			</div>
     		手机号码：<input type="text" name="telphone" id="telphone"></input></br>
     		手机号码校验：<input type="text" name="telCode" id="telCode"></input><input type="button" name="telCodeBtn" id="telCodeBtn" value="免费获取"></input></br>
     		<input type="submit" name="next_step" name="next_step" value="下一步"></input>
     	</form>
     </div>
</body>
</html>
<script>
	$(document).ready(function(){
		$("#city_3").citySelect({prov:"湖南", city:"长沙"});
	});
</script>