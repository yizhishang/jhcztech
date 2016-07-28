<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择新的银行卡</title>
</head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<script src="<%=basePath %>resources/jquery-easyui-1.4.1/jquery-1.10.2.min.js"></script>
<script src="<%=basePath %>js/jquery.validate.min.js"></script>
<script src="<%=basePath %>js/validateMethod.js"></script>

<body>
<form action="bindBankCard.do?method=doBankCardVerify" method="post" id="chooseNewBankCardForm">
		<table>
			<tr>
				<td>原银行卡:</td>
				<td><input type="text" id="oldBankCard" name="oldBankCard"></input><span id="span_oldBankCard"></span></td>
			</tr>
			<tr>
				<td>更换为:</td>
				<td><input type="text" id="newBankCard" name="newBankCard"></input><span id="span_newBankCard"></span></td>
			</tr>
			<tr>
				<td>交易密码:</td>
				<td><input type="password" id="trade_psd" name="trade_psd"></input><span id="span_trade_psd"></span></td>
			</tr>
			<tr>
				<td>短信校验码 :</td>
				<td><input type="telcode" id="telcode" name="telcode"></input><input type="button" id="send_code" value="免费发送"></input><span id="span_telcode"></span></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" id="next_step" value="下一步"></input></td>
			</tr>
		</table>
	</form>
</body>
</html>
<script>
	$(document).ready(function(){
		
		//发送短信验证码
		$("#send_code").bind("click",function(){
			ajaxSendCode({},this);//发送手机校验码
		});
		
		//form表单校验
		 $("#chooseNewBankCardForm").validate({
			 submitHandler:function(form){
				ajaxCheckCode(form);
			}, 
			rules: {
				oldBankCard:{
					required:true
					
				},
				newBankCard:{
					required:true
				},
				trade_psd:{
					required:true
				},
				telcode:{
					required:true
				}
				
			},
			//放错误信息的位置
			errorPlacement: function(error, element) {
				$( element ).closest( "form" ).find( "span[id='span_"+element.attr("id")+"']" ).append( error );
			},
			errorElement:"span",
			messages:{
				oldBankCard:{
					required:"原身份证号码不能为空"
				},
				newBankCard:{
					required:"新身份证号不能为空"
				},
				trade_psd:{
					required:"交易密码不能为空"
				},
				telcode:{
					required:"手机检验码不能为空"
				}
			}
			
		});
		
		//异步发送手机验证码
		function ajaxSendCode(data,obj){
			$.ajax({  
                url : "<%=basePath %>bindBankCard.do?method=sendCode",  
                type : "POST",  
                datatype:"json",  
                data : data,  
                success : function(data, stats) {  
                    if (stats == "success") {  
                      settime(obj);
                    }  
                },  
                error : function(result) {  
                    alert("请求失败"); 
                    settime(obj);
                }  
            });  
		}
		
		//校验手机验证码
		function ajaxCheckCode(from){
			var telcode = $("#telcode").val();
			$.ajax({  
                url : "<%=basePath %>bindBankCard.do?method=checkCode",  
                type : "POST",  
                datatype:"text",  
                data : {"telcode":telcode},  
                success : function(result, stats) { 
                    if (stats == "success") {  
                      if (result.data == 0) {
                    	  from.submit();
                      }else{
                    	  alert("手机验证码不一致！");
                      }
                      return true;
                    }  
                },  
                error : function(data) {  
                    alert("请求失败"); 
                    return false;
                }  
            });  
		}
		
		//发送验证码倒计时
		var countdown=120; 
		function settime(obj) { 
			if (countdown == 0) { 
				obj.removeAttribute("disabled");
				obj.value="免费获取验证码"; 
				countdown = 120; 
				return;
			} else { 
				obj.setAttribute("disabled", true); 
				obj.value="重新发送(" + countdown + ")"; 
				countdown--; 
			} 
			setTimeout(function() { settime(obj)},1000);
		};
	});
</script>