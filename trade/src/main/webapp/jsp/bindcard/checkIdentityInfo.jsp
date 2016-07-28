<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>校验身份信息</title>
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
	<div style="margin: 0 auto;">
		<form action="bindBankCard.do?method=doSetTradePsd" method="post" id="bindBankCardFrom">
				<input type="hidden" name="method" value="doInputChenckInfo"/>
			 姓名： <input type="text" name="name" id="name"/><span id="span_name"></span></br>
		  身份证号码：<input type="text" name="idCard" id="idCard"/><span id="span_idCard"></span></br>
		  		 <input class="submit" type="submit" value="下一步" id="next_step">
		</form>
	</div>
</body>
</html>
<script>
	$(document).ready(function(){
		
		
		// 字符验证 
		  jQuery.validator.addMethod("stringCheck", function(value, element) { 
		     return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value); 
		  }, "只能包括中文字、英文字母、数字和下划线"); 

		// 身份证号码验证 
		jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
			//var idCard =/^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/;
			var idCard = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		  	return this.optional(element) || idCard.test(value);     
		}, "请正确输入您的身份证号码"); 
		//护照编号验证
		 jQuery.validator.addMethod("passport", function(value, element) { 
		  return this.optional(element) || checknumber(value);     
		}, "请正确输入您的护照编号"); 

		// 手机号码验证 
		jQuery.validator.addMethod("isMobile", function(value, element) { 
		  var length = value.length; 
		  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/; 
		  return this.optional(element) || (length == 11 && mobile.test(value)); 
		}, "请正确填写您的手机号码"); 

		// 电话号码验证 
		jQuery.validator.addMethod("isTel", function(value, element) { 
		  var tel = /^\d{3,4}-?\d{7,9}$/; //电话号码格式010-12345678 
		  return this.optional(element) || (tel.test(value)); 
		}, "请正确填写您的电话号码"); 

		// 联系电话(手机/电话皆可)验证 
		jQuery.validator.addMethod("isPhone", function(value,element) { 
		  var length = value.length; 
		  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/; 
		  var tel = /^\d{3,4}-?\d{7,9}$/; 
		  return this.optional(element) || (tel.test(value) || mobile.test(value)); 

		}, "请正确填写您的联系电话"); 

		// 邮政编码验证 
		jQuery.validator.addMethod("isZipCode", function(value, element) { 
		  var tel = /^[0-9]{6}$/; 
		  return this.optional(element) || (tel.test(value)); 
		}, "请正确填写您的邮政编码"); 

		
		
		//form表单校验
		 $("#bindBankCardFrom").validate({
			 submitHandler:function(form){
				 form.submit();
			}, 
			rules: {
				name:{
					required:true,
					minlength:2
					
				},
				idCard:{
					required:true,
					isIdCardNo:true
				}
				
			},
			//放错误信息的位置
			errorPlacement: function(error, element) {
				$( element ).closest( "form" ).find( "span[id='span_"+element.attr("id")+"']" ).append( error );
			},
			errorElement:"span",
			messages:{
				name:{
					required:"姓名不能为空",
					minlength:"姓名长度不能小于2个字符"
				},
				idCard:{
					required:"身份证号不能为空",
					isIdCardNo:"请填写正确的身份证号"
				}
			}
			
		});
		 
	});

</script>