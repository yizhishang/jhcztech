<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script language="javascript">
    $(document).ready(function(){  
    	/*$.formValidator.initConfig({formid:"userForm",alertmessage:false,tidymode:true,errorfocus:false,onerror:function(msg){alert(msg)}});
		$("#oldPassword").formValidator().inputValidator({min:1,onerror:"旧密码不能为空！"});
		$("#password").formValidator().inputValidator({min:6,max:16,onerror:"新密码长度需要在6到16个字符之间！"});
		$("#password2").formValidator().compareValidator({desid:"password",operateor:"=",onerror:"2次密码不一致！"});*/
	    
		$("#button").click(function(){
			var oldPassword = $("#oldPassword").val();
			  var password = $("#password").val();
			  var password2 = $("#password2").val();
			  
			  if($.trim(oldPassword).length == 0)
			  {
			  	alert("旧密码不能为空！");
				$("#oldPassword").focus();
				return false;
			  }
			  
			  if($.trim(password).length == 0)
			  {
			  	alert("新密码不能为空！");
				$("#password").focus();
				return false;
			  }
			  
			  /*var str=password.replace(/^(?:([a-z])|([A-Z])|([0-9])|(.)){7,}|(.)+$/g, "$1$2$3$4$5").length;
			  if(str<2){
			     alert("您输入的密码强度不够！目前强度为"+str+"，要求的最低强度为2\n要求密码为：长度至少7位，至少包含以下四种元素中的二种 \n1、英文大写字母A～Z\n2、英文小写字母a～z\n3、数字0～9\n4、非字母数字字符(例如 @ $ # %)");
				 $("#password").focus();
				 return false;
			  }
			 */
			  if(password != password2)
			  {
			  	alert("2次输入密码不一到致！");
			 	$("#password2").focus();
				 return false;
			  }
			  submitForm("userForm",null,false);
		});
	});
</script>

<form id="userForm" action="modifyPwd.action" target="hiddenFrame"  method="post">
<input type="hidden" name="form.pageUrl" value="${pageUrl}" ></input>
<input type="hidden" name="function" value="${param.function }"></input>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
           <p><img src="${ctxPath }/admin/images/ico04.gif" />修改用户密码</p>
        </div>
    </td>
  </tr>
  <tr>
    <td class="edinner"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>
        	<div class="space"></div>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
              <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
              <tr>
                   <td class="label">旧密码： </td>
                   <td> 
                   		<input type="password" size="30" id="oldPassword" name="form.oldPassword"  value="<c:out value='${form.oldPassword}'/>" class="input02">
                   </td>
               </tr>
               <tr>
                   <td class="label">新密码： </td>
                   <td> 
                   		<input type="password" size="30" id="password"  name="form.password"  value="<c:out value='${form.password}'/>" class="input02">
                   </td>
               </tr>
                <tr>
                   <td class="label">新密码确认：</td>
                   <td> 
                   		<input type="password" size="30" id="password2" name="form.password2"  value="<c:out value='${form.password2}'/>" class="input02">
                   </td>
               </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td>
                	<input name="button" id="button" value="提交" class="bt04"/>&nbsp;
                	<input type="button" name="button" onClick="window.close();" id="button" value="关闭" class="bt04"/>
                </td>
              </tr>
            </table>
          </td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>
