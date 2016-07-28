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
<script src="<%=basePath %>resources/jquery-easyui-1.4.1/jquery-1.10.2.min.js"></script>
<script src="<%=basePath %>js/jquery.validate.min.js"></script>
<script src="<%=basePath %>js/validateMethod.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置交易密码</title>
</head>
<body>
	<form action="bindBankCard.do?method=doChooseBank" method="post" id="setTradePsdFrom">
		<input type="hidden" name="method" value="doChooseBank"/>
		 交易密码：<input type="text" id="trade_paw" name="trade_paw"></input><span id="span_trade_paw"></span></br>
		  确认交易密码：<input type="text" id="confirm_trade_paw" name="confirm_trade_paw"></input><span id="span_confirm_trade_paw"></span></br>
		  <input type="submit" id="next_step" value="下一步"></input>
	  </form>
</body>
</html>
<script>
	$(document).ready(function(){
		//form表单
		 $("#setTradePsdFrom").validate({
			 submitHandler:function(form){
				 form.submit();
			},
			
			//放错误信息的位置
			 errorPlacement: function(error, element) {
				$(element).closest( "form" ).find( "span[id='span_"+element.attr("id")+"']" ).append( error );
			},
			errorElement:"span",
			
			rules:{
				trade_paw:{
					required:true,
					rangelength:[6,6]
				},
				confirm_trade_paw:{
					required:true,
					equalTo:"#trade_paw",
					rangelength:[6,6]
				}
				
			},
			messages:{
				trade_paw:{
					required:"交易密码不能为空",
					rangelength:"密码长度为6位"
				},
				confirm_trade_paw:{
					required:"确认交易密码不能为空",
					equalTo:"两次输入密码不一致",
					rangelength:"密码长度为6位"
				}
			}
			
		}); 
		
	});
</script>