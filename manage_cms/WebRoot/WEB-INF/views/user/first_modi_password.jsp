<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script language="javascript">
   
    
    $(document).ready(function(){  
	    $("#userForm").submit(function(){
			  var password = $("#password").val();
			  var password2 = $("#password2").val();
			  
			  if($.trim(password).length == 0)
			  {
			  	alert("新密码不能为空！");
				$("#password").focus();
				return false;
			  }
			  
			  var str=password.replace(/^(?:([a-z])|([A-Z])|([0-9])|(.)){7,}|(.)+$/g, "$1$2$3$4$5").length;
			  if(str<2){
			     alert("您输入的密码强度不够！目前强度为"+str+"，要求的最低强度为2\n要求密码为：长度至少7位，至少包含以下四种元素中的二种 \n1、英文大写字母A～Z\n2、英文小写字母a～z\n3、数字0～9\n4、非字母数字字符(例如 @ $ # %)");
				 $("#password").focus();
				 return false;
			  }
			 
			  if(password != password2)
			  {
			  	alert("2次输入密码不一致！");
			 	$("#password2").focus();
				 return false;
			  }
			 
			  return true;
	    });
	});
</script>

<form id="userForm" action="login.action" target="hiddenFrame"  method="post">
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
                <td colspan="2" style="height:8px;" align="center"><font color="#FF0000">您是第一次登录本系统，请先修改您的登录密码</font></td>
              </tr>
              
               <tr>
                   <td class="label">新密码： </td>
                   <td> 
                   		<input type="password" size="30" id="password"  name="password"  value="" class="input02">                   </td>
               </tr>
                <tr>
                   <td class="label">新密码确认：</td>
                   <td> 
                   		<input type="password" size="30" id="password2" name="password2"  value="" class="input02">                   </td>
               </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td>
                	<input type="submit" name="button" id="button" value="提交" class="bt04"/>&nbsp;
                	<input type="button" name="button" onClick="window.close();" id="button" value="关闭" class="bt04"/>                </td>
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
