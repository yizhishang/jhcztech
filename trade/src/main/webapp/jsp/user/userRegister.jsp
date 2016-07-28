<%@ page pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common.jsp" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 多个页面公用  -->
    <link href="../js/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/util.css" rel="stylesheet" type="text/css"/>
    <link href="../css/login.css" rel="stylesheet" type="text/css"/>
    <link href="../css/register.css" rel="stylesheet" type="text/css"/>
    <script src="../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../js/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../js/highchart/highcharts.4.2.3.js" type="text/javascript"></script>
<!--     <script src="../js/placeholder/placeholder.js" type="text/javascript"></script> -->
   <!-- [if lte IE 9]>-->
    <script src="../js/html5/respond.min.js"></script>
    <script src="../js/html5/html5shiv.min.js"></script>
    <script src="../js/jquery.validate.min.js"></script>
    <script src="../js/validateMethod.js"></script>
    <!--<![endif]-->
    <script type="text/javascript">

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
                <a href="../userLogin/toLogin" id="login" class="">[登录]</a>
            </li>
            <li>
                <a href="#" id="register1" class="" style="color:#f06e00;">&nbsp;[注册]</a>
            </li>
        </ul>
    </div>
</div>

<!--导航-->
<div class="container header_bottom">
    <a class="logo" href="../index.html"><img src="../images/member_index/logo.png"/></a>
    <div class="login_word">
        <div class="login_title">用户注册</div>
    </div>
    <div class="login-register">
        <div class="login-register_title">我已注册，现在就<a href="../userLogin/toLogin">登陆</a></div>
    </div>
</div>

<div class="register_content">
    <div class="container">
        <div class="register_content_container">
            <ul>
                <li>
                    <div class="register_step">
                        <div class="register_int step1 <c:if test='${step == 1 }'>normal</c:if><c:if test='${step > 1 }'>success</c:if>"><div class="number<c:if test='${step > 1 }'> hide_number</c:if>">1</div></div>
                        <div class="register_word">验证手机</div>
                    </div>
                </li>
                <li>
                    <div class="line" style="margin-left: 12px"></div>
                </li>
                <li>
                    <div class="register_step">
                        <div class="register_int step2 <c:if test='${step == 1 }'>disable</c:if><c:if test='${step == 2 }'>normal</c:if><c:if test='${step > 2 }'>success</c:if>"><div class="number<c:if test='${step > 2 }'> hide_number</c:if>">2</div></div>
                        <div class="register_word">设置登录密码</div>
                    </div>
                </li>
                <li>
                    <div class="line" style="margin-right: 16px"></div>
                </li>
                <li>
                    <div class="register_step">
                        <div class="register_int step3 <c:if test='${step < 3 }'>disable</c:if><c:if test='${step == 3 }'>success</c:if>"><div class="number<c:if test='${step > 2 }'> hide_number</c:if>">3</div></div>
                        <div class="register_word">注册成功</div>
                    </div>
                </li>
            </ul>
        </div>
        
	<c:if test="${step == 1 }">
	<form id="mobileForm" action="ckMobileTicket" method="post">
        <div class="register_content_step1">
            <div class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机号</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="mobile" id="mobile" placeholder="请输入手机号"><span id="mobileTips"></span>
                    </div>
                </div>
                <div class="form-group" style="margin-top: 26px;">
                    <label class="col-sm-2 control-label">验证码</label>
                    <div class="col-sm-10">
                        <input type="text" name="ticket" class="form-control yangzhengma" placeholder="请输入验证码">
                        <span class="captchaImage"><img alt="验证码"  id="codeImg" src="<%=basePath %>/ImageServlet"></span><span id="ticketTips"></span>
                        <a href="javascript:void(0)" class="change_other" id="nextImg" style="color: #00abf0">换一张</a>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" checked="true">我已阅读并同意《网上交易系统注册及服务协议》
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <button type="button" id="ckMobileTicket" class="btn btn-default">获取手机校验码</button>
                    </div>
                </div>
            </div>
        </div>
	</form>
    <script type="text/javascript">
		$(document).ready(function(){
			var basePath = '${basePath}ImageServlet';
			$("#codeImg,#nextImg").click(function(){
				$("#codeImg").attr("src",basePath+"?t=" + new Date().getTime());
			});
			
			var mobileVal = $("input[name='mobile']").valid({
				regexVal:true,
				regexStr:"^(13|14|15|17|18)[0-9]{9}$",
				focusMsg:"<p class=\"on_tips png\"><em class=\"tips_l\"></em><em class=\"tips_r\">请输入手机号，可用于登录和找回密码</em></p>",
				errorMsg:"<p class=\"warn348 png\">您填写的手机号格式有误</p>"
			});
			
			var ticketVal = $("input[name='ticket']").valid({
				focusClass:"input_move",
				onClass:"input_on199",
				errorClass:"input_error199",
				focusMsg:"<span class=\"on_tips png\"><em class=\"tips_l\"></em><em class=\"tips_r\">请输入验证码，不区分大小写</em></span>",
				errorMsg:"<p class=\"warn348 png\">您填写的验证码有误</p>"
				//,imgTicket:true
			});
			
			$("#ckMobileTicket").on("click",function(){
				if(!mobileVal.validate()){
					return false;
				}
				if(!ticketVal.validate()){
					return false;
				}
				$("#mobileForm").submit();
			})
			
		});

	</script>
	</c:if>
	
	<c:if test="${step == 2 }">
	<form id="pwdForm" action="registerUser" method="post">
		<div class="register_content_step1">
            <div class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">登录密码</label>
                    <div class="col-sm-10">
                        <input type="password" name="password" id="password1"  class="form-control" placeholder="请输入登录密码">
						<span class="cap_i01"  id="capslockPassword" style="display:none;"></span>
						<span id="passwordTips"></span>
                    </div>
                </div>
                <div class="form-group" style="margin-top: 26px;">
                    <label class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-10">
                        <input type="password" name="password2" id="password2" class="form-control" placeholder="再一次输入密码">
						<span class="cap_i01" id="capslockPassword2" style="display:none;"></span>
						<span id="password2Tips"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <button type="button" id="register" class="btn btn-default">下一步</button><br/>
                        <div class="pre_operator"><a href="#">上一步</a></div>
                    </div>
                </div>
            </div>
        </div>
	</form>
	<script>
	var passStrong_flag ;
	var $password = $("input[name='password']").valid({
		focusMsg:"<p class=\"on_tips png\"><em class=\"tips_l\"></em><em class=\"tips_r\">8-14位，支持数字、字母、符号，区分大小写</em></p>",
		errorMsg:"",
		showCapslock:true,
		capslockid:"#capslockPassword",
		defVal:true,
		defCallback:function(value){
			if(value.length >= 8 && value.length <= 14 && passStrong_flag)
			{
				$password.setState(true);
				$password.setTips('<span class="png yes348"></span>');
			}
			else
			{
				$password.addClass('input_error348');
				$password.setTips('<p class="warn348 png"><span>您输入的密码过于简单，请重新输入支持<br>8-14位数字、字母、符号组合，区分大小写</span></p>');
			}
		},
		inputCallback:passCheckStrong
	});
	
	var $password2 = $("input[name='password2']").valid({
		focusMsg:"<p class=\"on_tips png\"><em class=\"tips_l\"></em><em class=\"tips_r\">请输入与上面相同的密码字符</em></p>",
		errorMsg:"<p class=\"warn348 png\">您输入的密码与第一次不相符</p>",
		showCapslock:true,
		capslockid:"#capslockPassword2",
		defVal:true,
		defCallback:function(value){
			if(value == $password.val())
			{
				$password2.setState(true);
				$password2.setTips('<span class="png yes348"></span>');
			}
			else
			{
				$password2.addClass('input_error348');
				$password2.setTips(' <p class="warn348 png">您输入的密码与第一次不相符</p>');
			}
		}
	});
	$("#register").on("click",function(){
		if(!$password.validate()){
			return false;
		}
		if(!$password2.validate()){
			return false;
		}
		$("#pwdForm").submit();
	});
	
	function passCheckStrong(sValue)
	{
		passStrong_flag = false;
		var className = "strengthLow";
		var modeName = "弱";
		var modes = 0;
		if (sValue.length < 6)
			//return modes;
			modes = 0;
		if (/\d/.test(sValue))
			modes++; // 数字
		if (/[a-z]/.test(sValue))
			modes++; // 小写
		if (/[A-Z]/.test(sValue))
			modes++; // 大写
		if (/\W/.test(sValue))
			modes++; // 特殊字符
		switch (modes) {
			case 1:
				className = "strengthLow";
				modeName = "弱";
				break;
			case 2:
				className = "strengthMid";
				modeName = "中";
				passStrong_flag = true;
				break;
			case 3:
				className = "strengthMid";
				modeName = "中";
				passStrong_flag = true;
				break;
			case 4:
				if(sValue.length < 12 ){
					className = "strengthMid";
					modeName = "中";
				} else {
					className = "strengthHight";
					modeName = "强";
					passStrong_flag = true;
				}
				break;
			default:{
				className = "strengthLow";
				modeName = "弱";
			};
		}
	}
	</script>
	</c:if>
	
	<c:if test="${step == 3 }">
		
		<div class="register_content_step1">
			<c:if test="${success == 1 }">
            <div class="register_content_success">
                <div class="success_img">
                    <img src="../images/member_be_ok.png">
                </div>
                <div class="success_info">
                    	恭喜你，注册成功！
                </div>
                <div class="bind_cards">
	                <button type="submit" class="btn btn-default">绑定银行卡</button>
	                <div class="pre_operator"><a href="#">先逛逛基金精选</a></div>
                </div>
            </div>
            </c:if>
            <c:if test="${success == 0 }">
            	<div class="success_img">
                    <img src="../images/failure.png">
                </div>
                <div class="success_info">
                    	${msg }
                </div>
            </c:if>
		</div>
	
	</c:if>
  </div>
</div>

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
</body>
</html>

