$package('user.login');
user.login = function(){
	return {
		toLogin:function(){
			try{
				var form = $("#loginForm");
				if(form.form('validate')){
					BaseObj.progress('Please waiting','Loading...');
					BaseObj.submitForm(form,function(data){
						BaseObj.closeProgress(); 
						user.login.loadVrifyCode();//刷新验证码
						if("success" == data.result){
							window.location.href = mainServer + "/admin/sys/systemmanager/backmainpage";
				        }else{
				        	BaseObj.alert('提示',data.message,'error');  
				        }
					});
				}
			}catch(e){
				
			}
			return false;
			/*var form = $("#loginForm");
				if(form.form('validate')){
					user.login.progress('Please waiting','Loading...');
					form.ajaxSubmit({
				    type: "post",
				    url: "login/toLogin",
				    dataType: "json",
					success: function(date){
						user.login.closeProgress();
						user.login.loadVrifyCode();//刷新验证码
						if("success" == date.result) {
			    			window.location="login";
						}else
						{
							$.messager.alert("登录提示",date.message,"error");
						}
				    },
					
						//BaseObj.closeProgress();
						//user.login.loadVrifyCode();//刷新验证码
						
					});
				}
			
			return false;*/
		},
		progress:function(title,msg){
			 var win = $.messager.progress({  
	            title: title ||'Please waiting',  
	            msg: msg ||'Loading data...'  
	         }); 
		},
		closeProgress:function(){
			$.messager.progress('close');
		},
		loadVrifyCode:function(){//刷新验证码
			var _url = mainServer + '/ImageServlet?time='+new Date().getTime();
			$(".vc-pic").attr('src',_url);
		},
		init:function(){
			if(window.top != window.self){
				window.top.location =  window.self.location;
			}
			//验证码图片绑定点击事件
			$(".vc-pic").click(user.login.loadVrifyCode);
			
			var form = $("#loginForm");
			form.submit(user.login.toLogin);
		}
	}
}();

$(function(){
	user.login.init();
});		