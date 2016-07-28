
<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="common.jsp" %>


<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 多个页面公用  -->
    <link href="../js/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/util.css" rel="stylesheet" type="text/css"/>
    <link href="../css/login.css" rel="stylesheet" type="text/css"/>
    <script src="../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../js/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../js/highchart/highcharts.4.2.3.js" type="text/javascript"></script>
   <!-- [if lte IE 9]>-->
    <script src="../js/html5/respond.min.js"></script>
    <script src="../js/html5/html5shiv.min.js"></script>
    <script src="../js/jquery.validate.min.js"></script>
    <script src="../js/validateMethod.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <script type="text/javascript">
	$(document).keydown(function(evt){
		if(evt.keyCode == 13)
		{
			loginFunc();
		}
	});
	function loginFunc()
	{
		var $login_id = $("input[name='login_id']");
		var $password = $("input[name='password']");
		var $errortime = $("input[name='errortime']");
		var $ticket = $("input[name='ticket']");
		var errortime = $errortime.val();
		var loginid = $login_id.val();
		var password = $password.val();
		var ticket = $ticket.val();
		if(loginid == null || loginid == '' || loginid == '手机/证件号码')
		{
			$login_id.focus();
			$login_id.addClass("input_error");
			$("div.prompt").attr("style","visibility:visible;");
	     	$("div.prompt .prompt_center").html("请输入用户名");
			return false;
		}
		if(password == null || password == '' || password == '登录密码')
		{
			$password.focus();
			$password.addClass("input_error");
			$("div.prompt").attr("style","visibility:visible;");
	     	$("div.prompt .prompt_center").html("请输入密码");
			return false;
		}
		if(errortime >= 3 && (ticket == null || ticket == ''|| ticket == '请输入验证码') )
		{
			$ticket.focus();
			$ticket.addClass("input_error");
			$("div.prompt").attr("style","visibility:visible;");
	     	$("div.prompt .prompt_center").html("请输入验证码");
			return false;
		}
		$('#normalLogin').submit();
	}
	</script>
    <title>基金直销平台</title>
</head>
<body>
<!--顶层导航-->
<div class="header_top">
    <div class="container">
        <ul class="navbar-nav">
            <li>
                <img src="../images/member_index/phone_disabled.png"/>
                <a href="#" class="active">移动版</a>
            </li>
            <li>
                <img src="../images/member_index/weixin_disabled.png"/>
                <a href="#">官方微信</a>
            </li>
            <li>
                <img src="../images/member_index/qq_disabled.png"/>
                <a href="#" target="_blank">在线客服</a>
            </li>
        </ul>
        <ul class="navbar-nav navbar-right" id="login_or_register">
            <li>
                <div>你好，欢迎登录！</div>
            </li>
            <li>
                <a href="../userLogin/login" id="login" class="">[登录]</a>
            </li>
            <li>
                <a href="../userRegister/toRegister" id="register" class="" style="color:#f06e00;">&nbsp;[注册]</a>
            </li>
        </ul>
    </div>
</div>

<!--导航-->
<div class="container header_bottom">
    <a class="logo" href="../index.html"><img src="../images/member_index/logo.png"/></a>
    <div class="login_word">
        <div class="login_title">欢迎登陆</div>
    </div>
</div>

<form action="${basePath}userLogin/login" method="post" id="normalLogin">
    <div class="login_content">
	    <div class="login_content_container">
	        <div class="login_left">
	            <a href="#"><img src="../images/banner_login.gif"/></a>
	        </div>
	        <div class="login_right">
	            <div class="signin">
	                <div class="signin-head_word">快速登陆</div>
	                <div class="form-signin">
	                    <input type="text" id="login_id" name="login_id" class="form-control" placeholder="请输入用户名" required autofocus />
	                    <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码" required />
	                    <label class="checkbox">
	                        <input type="checkbox" checked="true" value="remember-me">记住用户名
	                    </label>
	                    <a href="#" class="forget_pass">忘记登陆密码？</a><input type="hidden" name="errortime" value="" />
						<span id="capslock" class="uppercase_i" style="display:none;"></span>
                		<a class="show_keyboard png fr" href="javascript:void(0)"></a>
	                    <button class="btn btn-lg btn-warning btn-block" type="button" onclick="loginFunc();">登录</button>
	                </div>
	        		${result.error_info }<br/>
	                <div class="free_register"><span >没有账号？<a href="${basePath }userRegister/toRegister" class="free_register_word">免费注册</a></span></div>
	            </div>
	        </div>
	    </div>
	</div>
</form>
    
<!--底层导航-->
<div class="footer">
    <div class="main">
        <div class="navigation">
            <dl>
                <dt>关于我们</dt>
                <dd>
                    <ul>
                        <li>
                            <a class="" href="javascript:void(0);">联系我们</a>
                        </li>
                        <li>
                            <a class="" href="">公司简介</a>
                        </li>
                        <li>
                            <a class="" href="javascript:void(0);">媒体报道</a>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt>新手帮助</dt>
                <dd>
                    <ul>
                        <li>
                            <a class="disable" href="javascript:void(0);">开户演示</a>
                        </li>
                        <li>
                            <a class="disable" href="javascript:void(0);">常见问题</a>
                        </li>
                        <li>
                            <a class="disable" href="javascript:void(0);">账户设置</a>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt>安全保障</dt>
                <dd>
                    <ul>
                        <li>
                            <a class="" href="">法律法规</a>
                        </li>
                        <li>
                            <a class="" href="">风险提示</a>
                        </li>
                        <li>
                            <a class="" href="">隐私条款</a></li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt class="erweima-style">
                    <img src="../images/member_index/jhczerweima.jpg" />
                </dt>
                <dd>
                    <ul>
                        <li>
                            <div class="disable"><span style="color:#7e7e7e;margin-left: 15px;font-size: 12px;">扫一扫,了解更多</span></div>
                        </li>
                    </ul>
                </dd>
            </dl>
            <div class="tellphoneaddr">
                <dl>
                    <dt class="phone24_time">服务热线&nbsp;[&nbsp;9:00-18:00&nbsp;]</dt>
                    <dd>
                        <ul>
                            <li class="phone24">
                                <img src="../images/member_index/member_bottom_tel.png"/>
                                <span class="tellphone">400-800-88600</span>
                            </li>
                            <li>
                                <div class="mail_service">
                                        邮箱客服：<span class="mail">service@jhcztech.com</span>
                                </div>
                            </li>
                        </ul>
                    </dd>
                </dl>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="copyright">
        <div class="span1">Copyright @ 2004-2016 金恒创智JHCZTECH.com版权所有&nbsp;  |&nbsp;  粤 ICP备 15097678号</div>
        <div class="span2">&nbsp;&nbsp;&nbsp;&nbsp;投资有风险，购买需谨慎</div>
    </div>
</div>
    <iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src=""></iframe>
</body>

</html>


