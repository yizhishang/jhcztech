<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp" %>
<!DOCTYPE html>
<html >
<head>
<meta charset="UTF-8">
<%@ include file="/admin/common/meta.jsp" %>
<title>CMS后台管理系统</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/ajax.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/formValidator.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/formValidatorRegex.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/layer/layer.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/admin/styles/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/admin/styles/alogin.css">

</head>

<body>

<div class="login">
	<h1>登录</h1>
	<form method="post" id="loginForm">
		<select name="siteno" id="siteno">
        <c:forEach var="siteItem" items="${list }">
            <option value="${siteItem.siteNo }">${siteItem.name }</option>
        </c:forEach>
       	</select>
		<input type="text" id="name" name="name" placeholder="请输入用户名"/>
		<input type="password" name="password" placeholder="密码" />
		<div style="float:left;"><input type="txt" id="ticket" name="ticket" placeholder="请输入验证码" maxLength="4" size="10" style="width:200px;"/></div>
		<img id="randomImg" src="${ctxPath }/ticketAdmin/getTicket.action"  style="margin:5px;"/>
		<button type="button" id="loginBtn" class="btn btn-primary btn-block btn-large">登录</button>
	</form>
</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#name").focus();
		$.formValidator.initConfig({formid:"loginForm",alertmessage:false,onerror:function(msg){alert(msg);}});
		$("#siteno").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"请选择登录站点"});
		$("#name").formValidator().inputValidator({min:1,onerror:"登录号不能为空,请确认"});
		$("#password").formValidator().inputValidator({min:1,onerror:"密码不能为空,请确认"});
		$("#ticket").formValidator().inputValidator({min:1,onerror:"验证码不能为空,请确认"});
		
		$("#ticket").keyup(function(e){
			if(e.keyCode == "13")
			{
				$("#loginBtn").click();
			}
		});
		
		var randomImgSrc="${ctxPath }/ticketAdmin/getTicket.action?rand=";
		$("#randomImg").click(function() {
				$("#randomImg").attr("src",randomImgSrc+Math.random());
			}
		);
		$("#loginBtn").click(function(){
			$.ajax({
				type:"post",
				url:"loginValidate.action",
				data : encodeURI($("#loginForm").serialize()),
				dataType : "json",
				success: function(result){
					if(result && result.errorNo == 0)
					{
						window.location.href = "${ctxPath }/admin/index.action";
					}else{
						layer.msg(result.errorInfo, {icon: 5});
					}
				},
				error: function(data){
					layer.msg(data, {icon: 5});
				}
			});
		});
	});
</script>
</html>
