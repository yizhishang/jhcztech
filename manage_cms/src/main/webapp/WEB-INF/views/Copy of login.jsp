<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<style type="text/css">
body{
	background:url(${ctxPath }/admin/images/loginpagebg.jpg) center top no-repeat;
}
</style>
<script language="javascript">
	function correctPNG()
	{
		for(var i=0; i<document.images.length; i++)
		{
			var img = document.images[i];
			var imgName = img.src.toUpperCase();
			if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
			{
				var imgID = (img.id) ? "id='" + img.id + "' " : "";
				var imgClass = (img.className) ? "class='" + img.className + "' " : "";
				var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' ";
				var imgStyle = "display:inline-block;" + img.style.cssText;
				if (img.align == "left") imgStyle = "float:left;" + imgStyle;
				if (img.align == "right") imgStyle = "float:right;" + imgStyle;
				if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle;
				var strNewHTML = "<span " + imgID + imgClass + imgTitle
				+ " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
				+ "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
				+ "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>";
				img.outerHTML = strNewHTML;
				i = i-1;
			}
		}
	}
	function alphaBackgrounds(){
		var rslt = navigator.appVersion.match(/MSIE (d+.d+)/, '');
		var itsAllGood = (rslt != null && Number(rslt[1]) >= 5.5);
		for (i=0; i<document.all.length; i++){
			var bg = document.all[i].currentStyle.backgroundImage;
			if (bg){
				if (bg.match(/.png/i) != null){
					var mypng = bg.substring(5,bg.length-2);
					//alert(mypng);
					document.all[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+mypng+"', sizingMethod='crop')";
					document.all[i].style.backgroundImage = "url('')";
					//alert(document.all[i].style.filter);
				} 
			}
		}
	}
	
	function showkeyboard ()
	{
	    var imgField = document.getElementById("imgField");
		  skb.style.left = imgField.offsetLeft;
		  skb.style.top = imgField.offsetTop + imgField.offsetHeight;
	     skb.style.display = "block";
	}
	
	//关闭软键盘
	function closekeyboard ()
	{
		skb.style.display = "none";
	}
	
	function addValue (newValue)
	{
		var pwdField = document.getElementById("passwordField");
		pwdField.value += newValue;
	}
	
	//实现BackSpace键的功能
	function setpassvalue ()
	{
		var pwdField = document.getElementById("passwordField");
		var longnum = pwdField.value.length;
		var num;
		num = pwdField.value.substr (0, longnum - 1);
		pwdField.value = num;
	}
	
	//实现清空键的功能
	function clearInput ()
	{
		var pwdField = document.getElementById("passwordField");
		pwdField.value = "";
	}
	
	if (navigator.platform == "Win32" && navigator.appName == "Microsoft Internet Explorer" && window.attachEvent) {
		window.attachEvent("onload", correctPNG);
		window.attachEvent("onload", alphaBackgrounds);
	}
	
    if (window != window.top)
    {
        window.top.location.href = "login.action";
    }
	    
	var randomImgSrc="${ctxPath }/ticketAdmin/getTicket.action?rand=";
	$(document).ready(function() {
		$.formValidator.initConfig({formid:"loginForm",alertmessage:false,onerror:function(msg){alert(msg);}});
		$("#siteno").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"请选择登录站点"});
		$("#name").formValidator().inputValidator({min:1,onerror:"登录号不能为空,请确认"});
		$("#password").formValidator().inputValidator({min:1,onerror:"密码不能为空,请确认"});
		$("#ticket").formValidator().inputValidator({min:1,onerror:"验证码不能为空,请确认"});
		
		$("#name").focus();
		
		$("#ticket").keyup(function(e){
			if(e.keyCode == "13")
			{
				$("#loginBtn").click();
			}
		});
		
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
						window.location.href = "/admin/index.action";
					}else{
						alert(result.errorInfo);
					}
				},
				error: function(data){
					alert(data);
				}
			});
		});
	});
</script>
<body>
<form id="loginForm" style="margin:0px" method="post"  >
<div class="logindiv">
	<div class="Ltop"><img src="${ctxPath }/admin/images/login_top.png" /></div>
    <div class="Lcenter">
    	<div style="float:left;"><img src="${ctxPath }/admin/images/login_left.png" /></div>
    	<div class="lr"><img src="${ctxPath }/admin/images/login2.jpg" /></div>
        <div class="ll">
        	<div class="Lbox">
            	<span>站　点：</span>
           	  <span>
           	  <select name="siteno" id="siteno" style="width:132px;">
           	  <c:forEach var="siteItem" items="${data.list }">
           	    <option value="${siteItem.siteNo }">${siteItem.name }</option>
           	  </c:forEach>
       	      </select>
			  <c:if test="${not empty param.siteno}">
			  <script type="text/javascript">setSelectSelected("siteno","${param.siteno}")</script>
			  </c:if>
           	</span></div>
        	
        	<div class="Lbox">
            	<span>登录号：</span>
                <span><input type="text" id="name" name="name" placeholder="请输入用户名" value="" class="logininput"/></span>
            </div>
            <div class="Lbox">
            	<span>密　码：</span>
                <span><input type="password" id="password"  name="password" class="logininput" value="" /></span>
                <!--<span><img src="${ctxPath }/admin/images/ico01.gif" /></span>-->
            </div>
            <div class="Lbox">
            	<span>验证码：</span>
            	<span><input type="text" id="ticket" name="ticket"  maxLength="4" size="10" class="logininput" style="width:60px;"/></span>
                <span><img id="randomImg" src="${ctxPath }/ticketAdmin/getTicket.action" /></span></div>
            <div style=" padding-left:15px;margin-top:15px;"><input type="button" class="loginbotton" id="loginBtn" value="登录"/>&nbsp;&nbsp;<input type="reset" class="loginbotton" value="取消"/>
            </div>
        </div>
        <div style="float:left;"><img src="${ctxPath }/admin/images/login_right.png" /></div>
    </div>
    <div class="Lbottom"><img src="${ctxPath }/admin/images/login_bottom.png" /></div>
    <div class="Lcopyright">欢迎使用深圳市思迪信息技术有限公司Copyright&copy;2006~2010 All Rights Reserved.</div>
</div>
</form>
<%@ include file="/admin/common/messages.jsp" %>
<%--强行修改登录密码--%>
<c:if test="${not empty data[Constants.POPUP_MODIFYPWD_WINDOW] && data[Constants.POPUP_MODIFYPWD_WINDOW] eq '1'}">
<script type="text/javascript">
var returnValue = openDialogWithScroll('login.action?function=firstModifyPwd&isModelDialog', 500, 190);
if (returnValue != null && returnValue.length > 0)
{
   if(returnValue[0] == "success")
   {
   	   alert("修改密码成功！");
	   location.href="index.action";
   }
}
</script>
</c:if>
</body>
</html>
