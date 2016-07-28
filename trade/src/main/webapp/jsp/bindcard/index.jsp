<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
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
	<a href="<%=basePath%>bindBankCard.do?method=doInputChenckInfo">绑定银行卡</a>
	<a href="<%=basePath%>bindBankCard.do?method=doVerifyTradePassWord">加挂银行卡</a>
	<a href="<%=basePath%>bindBankCard.do?method=doChooseNewBankCard">更换银行卡</a>
	<a href="javascript:void(0);" id="deletBankCard">删除银行卡</a>
</body>
</html>
<script>
	$(document).ready(function(){
		$("#deletBankCard").bind("click",function(){
			$.ajax({  
                url : "<%=basePath%>bindBankCard.do?method=doDeleteBankCard",  
                type : "POST",  
                datatype:"json",  
                data : {},  
                success : function(result, stats) {  
                    if (stats == "success") {  
                    	var data = result.data;
                    	if (data == 0) {
                    		alert("data=="+data);
                    	}else if (data == -1){
                    		alert(data);
                    	}
                    }  
                },  
                error : function(result) {  
                    alert("请求失败"); 
                }  
            });  
			
		});
	});
</script>