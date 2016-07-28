//$(document).ready(function(){
var wait = 0;
(function($) {
	$.fn.valid = function(options){
		options = jQuery.extend({
			notEmpty:true,
			imgTicket:false,
			focusClass:"input_move",
			onClass:"input_on348",
			errorClass:"input_error348",
			tokenInput:"token",
			tipsDiv:"",
			focusMsg:"",
			errorMsg:"",
			successMsg:"<span class='png yes348'></span>",
			regexVal:false,
			regexStr:"",
			regexMsg:"",
			ajaxVal:false,
			ajaxCallBack:null,
			defVal:false,
			defCallback:null,
			inputCallback:null,
			focusCallback:null,
			blurCallback:null,
			second:120,
			isMobileTicket:false,
			isMobileRegist:false,
			mobileTicketButton:"",
			mobileTicketType:"",
			isPassword:false,
			passwordType:"1",
			rmScroll:false,
			showCapslock:false,
			capslockid:"#capslock"
		}, options || {});
		
		var _self = $(this);
		_self.flag_ticket = false;
		_self.flag_mobileticket = false;
		_self.state = false;
		_self.isRegist = false;
		_self.mobileTicketFlag = true;
		_self.password_flag = false;
		if(options.tipsDiv == null || options.tipsDiv == ''){
			options.tipsDiv = _self.attr("name") + "Tips";
		}
		
		if(options.mobileTicketButton == null || options.mobileTicketButton == '')
		{
			options.mobileTicketButton = _self.siblings("a.obtain_code");
		} 
		else 
		{
			options.mobileTicketButton = $(options.mobileTicketButton);
		}
		
		var _tips = $("span#" + options.tipsDiv);
		
		_self.on('input propertychange', function(e) {
			if($.syncProcessSign) return;  
	        $.syncProcessSign = true; 
			_self.flag_ticket = false;
			_self.state = false;
			_self.flag_mobileticket = false;
			_self.password_flag = false;
			if(_self.isEmpty())
			{
				//_self.siblings(".P_strength").remove();
				_self.removeClass(options.focusClass);
				//_self.removeClass(options.onClass);
				_self.removeClass(options.errorClass);
			}
			else
			{
				_self.addClass(options.focusClass);
				//_self.addClass(options.onClass);
			}
			if(options.inputCallback){
				options.inputCallback(_self.val(),e);
			}
			
			$.syncProcessSign = false;
		}).on("focus",function(e){
			//_self.addClass(options.focusClass);
			_self.removeClass(options.errorClass);
			_self.addClass(options.onClass);
			_tips.html(options.focusMsg);
			if(options.focusCallback)
			{
				options.focusCallback(_self.val(),e);
			}
		}).on("blur",function(e){
			if(_self.isEmpty())
			{
				_self.removeClass(options.focusClass);
				_self.removeClass(options.onClass);
				_self.removeClass(options.errorClass);
				_self.siblings(".P_strength").remove();
				_tips.html("");
			} 
			else
			{
				_self.removeClass(options.onClass);
				_self.removeClass(options.errorClass);
				_self.addClass(options.focusClass);
				if(options.regexVal)
				{
					if(new RegExp(options.regexStr).test(_self.val()))
					{
						if(_self.isRegist){
							_tips.html("<p class=\"png warn348\">该号码已注册</p>");
						}else{
							_self.state = true;
							_tips.html(options.successMsg);
						}
					}
					else
					{
						_self.addClass(options.errorClass);
						_tips.html(options.errorMsg);
					}
				}
				else if(options.ajaxVal && options.ajaxCallBack)
				{
					options.ajaxCallBack(_self.value());
				}
				else if(options.defVal && options.defCallback)
				{
					options.defCallback(_self.value());
				}
				else if(options.imgTicket)
				{
					_self.checkImgTicket();
				} 
				// else if(options.isMobileTicket)
				// {
				// _self.checkMobileTicket();
				// }
				else if(options.isPassword){
					_self.checkPassword();
				}
				else {
					_self.state = true;
					_tips.html(options.successMsg)
				}
			}
			if(options.blurCallback)
			{
				options.blurCallback(_self.val(),e);
			}
		}).on("change",function(e){
			if(options.rmScroll){
				if($("a.obtain_code") != null && $("a.obtain_code") != ''){
					if(options.regexVal && new RegExp(options.regexStr).test(_self.val()) && $("a.obtain_code").text()!="免费获取"){
						wait = 0;
					}
				}
			}
		});
		
		if(options.showCapslock)
		{
			var $capslock = $(options.capslockid);
			_self.on("keyup",function(event){ 
				var password = _self.val();
				var str = "";
				var capsLockKey = event.keyCode ? event.keyCode : event.which;
				var shifKey = event.shiftKey ? event.shiftKey:((capsLockKey == 16) ? true : false);
				if(((capsLockKey >= 65 && capsLockKey <= 90) && !shifKey)||((capsLockKey >= 97 && capsLockKey <= 122) && shifKey)){
				    if(password.length==1)
				    {
				    	str = password;
				    	if(allCaps(str))
						{
							$capslock.show();
						}
						else
						{
							$capslock.hide();
						}
				    }
				    else if(password.length > 1)
				    {
				    	str = password.substring(password.length-1,password.length);
						if(allCaps(str))
						{
							$capslock.show();
						}
						else
						{
							$capslock.hide(); 
						}
				 	 }
				}
				else if(capsLockKey == 20)
				{
					if($capslock.is(":visible"))
					{
						$capslock.hide(); 
						sum = 1;
					}
					else
					{
						$capslock.show();
						sum = 0;
					}
				}
		    });
		    
			_self.on("blur",function(){
		    	$capslock.hide();
		    });
		    
		    function allCaps(charStr){
				for(var i=0;i<charStr.length;i++){
					var c=charStr.charAt(i);
					if(c<'A' || c>'Z')
						return false;
				}
				return true;
			}
		}
		
		_self.checkPassword = function(success,error){
			var password = _self.val();
			if(password == null || password == '')
			{
				return false;
			}
			$.ajax({
				type: "POST",
		         url: "/servlet/TicketAction?function=AjaxCheckPwd",
		         data: {
		         	"password":password,
		         	"type":options.passwordType
		         },
		         dataType: "json",
		         success: function(data){
		             var errorno = data.error_no;
		             if(errorno == 0)
		             {
		             	_tips.html(options.successMsg)
						_self.password_flag = true;
		             	$("input[name='"+options.tokenInput+"']").val(data.results.token);
		             	if(success) {
		             		success();
		             	}
		             } 
		             else if(errorno == '-999') // 未登录
		             {
		            	 window.location.href = "/servlet/IndexAction";
		             }
		             else
		             {
						_self.addClass(options.errorClass);
						_tips.html("<p class=\"warn348 png\">" + data.error_info + "</p>");
						if(error){
							error();
						}
		             }
		         },
		         error:function(){},
		         complete:function(){}
		     });
		}
		
		_self.checkImgTicket = function(){
			var ticket = _self.val();
			if(ticket == null || ticket == '')
			{
				return false;
			}
			$.ajax({
				type: "POST",
		         url: "/servlet/TicketAction?function=ajaxCkImgTicket",
		         data: {
		         	"ticket":ticket
		         },
		         dataType: "json",
		         success: function(data){
		             var errorno = data.error_no;
		             if(errorno == 0)
		             {
		             	_tips.html(options.successMsg)
						_self.flag_ticket = true;
		             } 
		             else
		             {
						_self.addClass(options.errorClass);
						_tips.html(options.errorMsg);
		             }
		         },
		         error:function(){},
		         complete:function(){}
		     });
		}
		
		_self.checkMobileTicket = function(){
			var ticket = _self.val();
			if(ticket == null || ticket == '')
			{
				return false;
			}
			$.ajax({
				type: "POST",
		         url: "/servlet/TicketAction?function=ajaxCkMobTicket",
		         data: {
		         	"ticket":ticket
		         },
		         dataType: "json",
		         success: function(data){
		             var errorno = data.error_no;
		             if(errorno == 0)
		             {
		             	_tips.html(options.successMsg)
		             	_self.flag_mobileticket = true;
		             } 
		             else
		             {
						_self.addClass(options.errorClass);
						_tips.html(options.errorMsg);
		             }
		         },
		         error:function(){},
		         complete:function(){}
		     });
		}
		
		_self.validate = function(){
			if(options.notEmpty)
			{
				if(_self.isEmpty())
				{
					_self.focus();
				}
				else
				{
					if(options.regexVal)
					{
						if(new RegExp(options.regexStr).test(_self.val()))
						{
							if(_self.isRegist){
								_self.state = false;
								_tips.html("<p class=\"png warn348\">该号码已注册</p>");
							}else{
								_self.state = true;
								_tips.html(options.successMsg);
							}
						}
						else
						{
							_self.addClass(options.errorClass);
							_tips.html(options.errorMsg);
						}
					}
					else if(options.ajaxVal && options.ajaxCallBack)
					{
						options.ajaxCallBack(_self.value());
					} 
					else if(options.defVal && options.defCallback)
					{
						options.defCallback(_self.value());
					}
					else if(options.imgTicket)
					{
						//if(!_self.flag_ticket)
						//{
						//	_self.addClass(options.errorClass);
						//	_tips.html(options.errorMsg);
						//}
						_self.state = _self.flag_ticket;
					}  
					// else if(options.isMobileTicket)
					// {
					// _self.state = _self.flag_mobileticket;
					// }
					else if(options.isPassword)
					{
						//if(!_self.password_flag)
						//{
						//	_self.addClass(options.errorClass);
						//	_tips.html(options.errorMsg);
						//}
						_self.state = _self.password_flag;
					}
					else {
						_self.state = true;
						_tips.html(options.successMsg)
					}
				}
			} else
			{
				_self.state = true;
			}
			return _self.state;
		};
		
		
		_self.isEmpty = function(){
			var value = _self.val();
			return (value != null && value != '') ? false : true;
		};
		
		_self.value = function(){
			return _self.val();
		};
		
		_self.setFlagTicket = function(s){
			_self.flag_ticket = s;
		}
		
		_self.setPasswordFlag = function(s){
			_self.password_flag = s;
		}
		
		_self.setState = function(s)
		{
			if(s)
			{
				_self.removeClass(options.onClass);
				_self.removeClass(options.errorClass);
			}
			_self.state = s;
		}
		
		_self.setTips = function(html)
		{
			_tips.html(html);
		}
				
		_self.tipError = function(msg)
		{
			_self.addClass(options.errorClass);
			if(msg != null && msg != '')
			{
				_tips.html(msg);
			} 
			else
			{
				_tips.html(options.errorMsg);
			}
		}
		
		if(options.isMobileTicket)
		{
			wait = options.second;
			var time;
			// 绑定事件
			options.mobileTicketButton.css("color","#04a5e8");
			options.mobileTicketButton.css("cursor","pointer");
			options.mobileTicketButton.html("免费获取");
			options.mobileTicketButton.on("click",function(){
				// 发手机验证码

				var mtb = $(this);
				var sign_msg = mtb.attr("sign");
				if(_self.mobileTicketFlag)
				{
					var mobile ="",cr="",templateNo="",mobileTicketToken="",ticketType="";
					if($("input[name=mobile]")){// 是否存在mobile属性
						mobile = $("input[name=mobile]").val();
						// 存在mobile属性则必须填mobile属性的值才可发短信
						if(mobile ==""){
							$("input[name='mobile']").addClass("input_on348");
							$("span#mobileTips").html("<p class=\"on_tipsingle png\">请输入手机号码</p>");
							return false;
						}
						cr = $("input[name=mobile]").attr("cr");
						ticketType = $("input[name=mobile]").attr("ticketType");
					}
					if($("input[name=templateNo]")){// 是否存在templateNo属性
						templateNo = $("input[name=templateNo]").val();
					}
					if($("input[name=mobileTicketToken]")){// 是否存在mobileTicketToken属性
						mobileTicketToken = $("input[name=mobileTicketToken]").val();
					}
					_self.mobileTicketFlag = false;
					if(sign_msg) {
						var sign_split = sign_msg.split(",");
						var name = sign_split[0];
						var idcode = sign_split[1];
						var pay_mode = sign_split[2];
						var sign_mode = sign_split[3];
						var bankno = sign_split[4];
						var idtype = sign_split[6];
						var capital_mode = sign_split[7]
						var signFlag = sign_split[8];
						var tlFlag = sign_split[9];
						var mobile = $("input[name='mobile']").val();
						// signFlag 2二次签约 1短信签约
						if(signFlag == 1) {
							var bank_acct = $("input[name='bankcard']").val().replace(/[\s\uFEFF\xA0]/g,'');
						} else if(signFlag == 2){
							//var bank_acct = $("em[name='bankcard']").html();
							var bank_acct = $("em[name='bankcard']").text().replace(/[\s\uFEFF\xA0]/g,'');
							capital_mode = "M";
						}
						//if((sign_mode==6 && pay_mode==2) || (capital_mode=='M' && signFlag ==2)) {
						//&& tlFlag != 'false'
						if((sign_mode==6 && pay_mode==2 )) {
							$.ajax({
								type: "POST",
						         url: "/servlet/TicketAction?function=ajaxTonglian",
						         data:{"mobile_no":mobile,
						        	 	"id_type":idtype,
						        	 	"id_code":idcode,
						        	 	"bank_acct_name":name,
						        	 	"bank_acct":bank_acct,
						        	 	"capital_mode":capital_mode,
						        	 	"bank_no":bankno,
						        	 	"signFlag":signFlag},
						         dataType: "json",
						         success: function(data){
						             var errorno = data.error_no;
						             var sq = data.results;// 把基金流水号传到下一步
						             $("input[name='fundSeqId']").val(sq);
						             if(errorno == 0)
						             {
										timeline(mtb);
						             }
						             else if(errorno == -1) // 验证码验证失败
						             {
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\">服务器忙,请稍后重试</p>");
						             }
						             else if(errorno == -2) // 获取短信验证码失败!
						             {
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\">获取短信验证码失败</p>");
						             }
						             else if(errorno == -3) // 验证码验证失败
						             {
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\"><span>该手机申请验证码过于频繁</br>暂时不能接收验证码</span></p>");
						             }
						             else if(errorno == -4) // 待确定
						             {
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\"><span>短信验证码状态未知</span></p>");
						             }
						             else{
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\">服务器忙,请稍后重试</p>");
						             }
						         },
						         error:function(){},
						         complete:function(){}
						     });
						
							
						} else {
							$.ajax({
								type: "POST",
						         url: "/servlet/TicketAction?function=ajaxMobileTicket",
						         data:{"mobile":mobile,
						        	 	"cr":cr,
						        	 	"templateNo":templateNo,
						        	 	"mobileTicketToken":mobileTicketToken,
						        	 	"ticketType":ticketType},
						         dataType: "json",
						         success: function(data){
						             var errorno = data.error_no;
						             if(errorno == 0)
						             {
										timeline(mtb);
						             }
						             else if(errorno == -1) // 验证码验证失败
						             {
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\">服务器忙,请稍后重试</p>");
						             }
						             else if(errorno == -2) // 手机号码有误!
						             {
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\">您填写的手机号格式有误</p>");
						             }
						             else if(errorno == -3) // 验证码验证失败
						             {
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\"><span>该手机申请验证码过于频繁</br>暂时不能接收验证码</span></p>");
						             }
						             else if(errorno == -4) // 手机号被锁定
						             {
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\">手机号被锁定，请稍后再试</p>");
						             }
						             else if(errorno == -101242) // 手机号被锁定
						             {
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\">手机号被锁定，请稍后再试</p>");
						             }
						             else if(errorno == -5) // 号码已注册
						             {
						            	 _self.mobileTicketFlag = true;
							          	$("span#mobileTips").html("<p class=\"png warn348\">该号码已注册</p>");
							         }
						             else{
						            	 _self.mobileTicketFlag = true;
						             	$("span#ticketTips").html("<p class=\"png warn348\">服务器忙,请稍后重试</p>");
						             }
						         },
						         error:function(){},
						         complete:function(){}
						     });
						}
					} else {

						$.ajax({
							type: "POST",
					         url: "/servlet/TicketAction?function=ajaxMobileTicket",
					         data:{"mobile":mobile,
					        	 	"cr":cr,
					        	 	"templateNo":templateNo,
					        	 	"mobileTicketToken":mobileTicketToken,
					        	 	"ticketType":ticketType},
					         dataType: "json",
					         success: function(data){
					             var errorno = data.error_no;
					             if(errorno == 0)
					             {
									timeline(mtb);
					             }
					             else if(errorno == -1) // 验证码验证失败
					             {
					            	 _self.mobileTicketFlag = true;
					             	$("span#ticketTips").html("<p class=\"png warn348\">服务器忙,请稍后重试</p>");
					             }
					             else if(errorno == -2) // 手机号码有误!
					             {
					            	 _self.mobileTicketFlag = true;
					             	$("span#ticketTips").html("<p class=\"png warn348\">您填写的手机号格式有误</p>");
					             }
					             else if(errorno == -3) // 验证码验证失败
					             {
					            	 _self.mobileTicketFlag = true;
					             	$("span#ticketTips").html("<p class=\"png warn348\"><span>该手机申请验证码过于频繁</br>暂时不能接收验证码</span></p>");
					             }
					             else if(errorno == -4) // 手机号被锁定
					             {
					            	 _self.mobileTicketFlag = true;
					             	$("span#ticketTips").html("<p class=\"png warn348\">手机号被锁定，请稍后再试</p>");
					             }
					             else if(errorno == -101242) // 手机号被锁定
					             {
					            	 _self.mobileTicketFlag = true;
					             	$("span#ticketTips").html("<p class=\"png warn348\">手机号被锁定，请稍后再试</p>");
					             }
					             else if(errorno == -5) // 号码已注册
					             {
					            	 _self.mobileTicketFlag = true;
						          	$("span#mobileTips").html("<p class=\"png warn348\">该号码已注册</p>");
						         }
					             else{
					            	 _self.mobileTicketFlag = true;
					             	$("span#ticketTips").html("<p class=\"png warn348\">服务器忙,请稍后重试</p>");
					             }
					         },
					         error:function(){},
					         complete:function(){}
					     });
					
					}
					
				}
			});
			
			function timeline(o) {
				if (wait == 0) {
					o.css("color","#04a5e8");
					o.css("cursor","pointer");
					o.html("点击重新获取");
					wait = options.second;
					clearTimeout(time);
					_self.mobileTicketFlag = true;
					
				} else { 
					o.css("color","#888888");
					o.css("cursor","text");
					o.html("重新获取(" + wait + ")");
					wait--;
					time = setTimeout(function() {
						timeline(o);
					}, 1000)
				}
			}
		}
		if(options.isMobileRegist)
		{
			var type = _self.attr("type");
			if(type == "text"){//隐藏域类型则不校验
				_self.on("change",function(){
					_self.addClass(options.focusClass);
					_self.removeClass(options.onClass);
					if(options.regexVal)
					{
						if(new RegExp(options.regexStr).test(_self.val()))
						{
							var mobile = _self.val();
							$.ajax({
								type: "POST",
						         url: "/servlet/TicketAction?function=ajaxCkMobile",
						         data: {
						         	"mobile":mobile
						         },
						         dataType: "json",
						         success: function(data){
						             var errorno = data.error_no;
						             if(errorno == 0)
						             {
						             	_tips.html("<span class='png yes348'></span>");
						             	_self.state = true;
						             	_self.isRegist = false;
						             } 
						             else if(errorno==-1)
						             {
						             	_tips.html("<p class=\"warn348 png\">您填写的手机号有误</p>");
						             }else if(errorno==-5){
						            	 _tips.html("<p class=\"warn348 png\">该号码已注册</p>");
						            	 _self.isRegist = true;
						             }else{
						            	_tips.html("<p class=\"warn348 png\">服务器繁忙,请稍候重试</p>");
						             }
						         },
						         error:function(){},
						         complete:function(){}
							});
						}
						else
						{
							$(this).addClass(options.errorClass);
							_tips.html(options.errorMsg);
						}
					}
				});
			}
		}
		return _self;
	}
//});	
})(jQuery);