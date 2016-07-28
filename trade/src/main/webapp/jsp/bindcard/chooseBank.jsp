<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择开户银行</title>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<script src="<%=basePath %>resources/jquery-easyui-1.4.1/jquery-1.10.2.min.js"></script>
<script src="<%=basePath %>js/jquery.validate.min.js"></script>
<script src="<%=basePath %>js/validateMethod.js"></script>
</head>
<body>
	<div>
		<ul id="bank_ul">
			<li bankNo="001">招商银行</li>
			<li bankNo="002">中国银行</li>
			<li bankNo="003">交通银行</li>
			<li bankNo="004">平安银行</li>
			<li bankNo="005">农业银行</li>
			<li bankNo="006">民生银行</li>
		</ul>
		<!-- form 表单提交 -->
		<form action="bindBankCard.do?method=${method}" method="post" id="bankCardCertificationForm">
			<input type="hidden" name="bankNo" id="bankNo"></input>
			<input type="button" value="下一步" id="next_step"></input>
		</form>
	</div>
</body>
</html>
<script>
	$(document).ready(function(){
		 //银行点击
		 $("#bank_ul").children().each(function(index){
			 	$(this).bind("click",function(){
			 		var bankNo = $(this).attr("bankNo");
					 $("#bankNo").val(bankNo);
			 	});
		 });
		 
		//绑定下一步
		$("#next_step").bind("click",function(){
			var bankNo = $("#bankNo").val();
			if ("" == bankNo) {
				alert("请选择银行");
				return false;
			}else{
				$("#bankCardCertificationForm").submit();
			}
		});
		
		
	});
</script>