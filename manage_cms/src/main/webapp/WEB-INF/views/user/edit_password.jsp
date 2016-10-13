<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script language="javascript">
    
    $(document).ready(function(){  
    	$.formValidator.initConfig({formid:"userForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#password").formValidator().inputValidator({min:6,max:16,onerror:"新密码长度需要在6到16个字符之间！"});
		$("#password2").formValidator().compareValidator({desid:"password",operateor:"=",onerror:"2次密码不一致！"});;
	    
		$("#button").click(function(){
	    	submitForm("userForm");
	    });
	});
</script>

<form id="userForm" action="editPassword.action" target="hiddenFrame"  method="post">
<input type="hidden" name="form.pageUrl" value="${pageUrl}" ></input>
<input type="hidden" name="function" value="${param.function }"></input>
<input type="hidden" name="form.user_id" value="${userid}">
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
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable" >
              <tr>
                <td colspan="2" style="height:8px;"></td>
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
                	<input type="button" name="button" id="button" value="提交" class="bt04"/>&nbsp;
                	<input type="button" name="button" onClick="window.close();" id="close" value="关闭" class="bt04"/>
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
