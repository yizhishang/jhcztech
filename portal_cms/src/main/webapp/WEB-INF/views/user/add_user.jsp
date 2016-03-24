<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.jhcz.base.util.RequestHelper"%>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
var name="";


$(document).ready(function(){  
	$.formValidator.initConfig({formid:"userForm",alertmessage:false,onerror:function(msg){alert(msg)}});
	$("#uid").formValidator().inputValidator({min:1,onerror:"用户标识不能为空,请确认"});
	$("#name").formValidator().inputValidator({min:1,onerror:"用户名称不能为空,请确认"});
	$("#password").formValidator().inputValidator({min:6,max:16,onerror:"新密码长度需要在6到16个字符之间！"});
	$("#password2").formValidator().compareValidator({desid:"password",operateor:"=",onerror:"2次密码不一致！"});;
	$("#email").formValidator({empty:true}).regexValidator({regexp:regexEnum.email,onerror:"你输入的邮箱格式不正确"});
	$("#phone").formValidator({empty:true}).regexValidator({regexp:regexEnum.tel,onerror:"电话格式不正确"});
	$("#mobile").formValidator({empty:true}).regexValidator({regexp:regexEnum.mobile,onerror:"手机号码格式不正确"});
	//$(".site").formValidator().inputValidator({min:1,onerror:"请选择站点！"});
	
    $("#userForm").submit(function(){
     	    var obj = document.getElementById("s");
	        var siteName = document.getElementById("n");
	        obj.value = checked();
	        siteName.value = name; 
     });
    
    $("#button").click(function(){
    	submitForm("userForm");
    });
});
    
    function checked()
    {
        var obj = document.getElementsByName("form.siteNo");
        var str = "";
        var n = "";
        for(var i = 0; i < obj.length; i++)
        {
            if(obj[i].checked)
            {
                var tmp = obj[i].value.split(",");
                str += tmp[0] + "|";
                n += tmp[1] + "、";
            }
        }
        name = n;
        return str;
    }
</script>
<form id="userForm" action="add.action" target="hiddenFrame"  method="post">
<input type="hidden" name="form.pageUrl" value="${pageUrl}" ></input>
<input type="hidden" name="form.isSystem" value="0"></input>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
           <p><img src="${ctxPath }/admin/images/ico04.gif" />添加用户信息</p>
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
                   <td class="label">用户标识： </td>
                   <td> 
                   		<input type="text" size="30" id="uid"  name="form.uid"  value="<c:out value='${form.uid}'/>" class="input02">
						<font color="#ff0000">*</font>
                   </td>
               </tr>
               <tr>
                   <td  class="label">用户名称： </td>
                   <td>
                       <input type="text" id="name"  size="30" name="form.name" class="input02" value="<c:out value='${form.name}'/>">
					   <font color="#ff0000">*</font>
                       <input type='hidden' name='site' id="s" value=''>
                       <input type='hidden' name='name' id="n" value=''>
                   </td>
               </tr>
               <tr>
                   <td  class="label">用户密码： </td>
                 	<td>
                     	<input type="password" id="password" size="30" name="form.password" class="input02" value="<c:out value='${form.password}'/>">
						<font color="#ff0000">*</font>
                     </td>
                 </tr>
                 <tr>
                 	 <td  class="label">确认密码： </td>
                 	<td>
                     	<input type="password" id="password2" size="30" name="password2" class="input02" value="<c:out value='${form.password}'/>">
						<font color="#ff0000">*</font>
                     </td>
                 </tr>
                <%-- <c:if test="${siteno != 'all' }">
               <tr>
                   <td class="label">所属站点：</td>
                   <td>
                       <table>
                       	<c:set var="count" value="0"/>
                           <c:forEach var="item" items="${data.siteList}">
                                  <c:if test="${count%2==0 }">
                                   <tr>
                                       <td>
                                           ${item.name}<input type="checkbox" class="site" name="form.siteNo" value="${item.siteNo},${item.name}" id="check_${item.siteNo}">
                                       </td>
                                   </c:if>
                           		  <c:if test="${count%2!=0 }">
                                       <td>
                                           ${item.name}<input type="checkbox" class="site" name="form.siteNo" value="${item.siteNo},${item.name}" id="check_${item.siteNo}">
                                       </td>
                                   </tr>
                               	 </c:if>
                               	 <c:set var="count" value="${count+1 }"/>
                           </c:forEach>
                            <c:if test="${count%2!=0 }">
                                   	   <td>
                                       </td>
                                   </tr>
                            </c:if>
                       </table>
                   </td>
               </tr>
              </c:if> --%>
              <%-- <tr>
                   <td class="label">系统管理员权限：</td>
                   <td>
                       <input type="radio" name="form.isSystem"  value="1">分配
                       <input type="radio" name="form.isSystem"  value="0" checked="checked">不分配
                   </td>
               </tr> --%>
               <tr>
                   <td class="label">电子邮箱：</td>
                   <td>
                       <input type="text"  id="email"  size="30" name="form.email" class="input02" value="<c:out value='${form.email}'/>">
                   </td>
               </tr>
               <tr>
                   <td class="label">电话：</td>
                 <td>
                       <input type="text"  id="phone" size="30" name="form.phone" class="input02" value="<c:out value='${form.phone}'/>"><br>(格式：区号-号码，如：0755-8888888)                                                </td>
               </tr>
               <tr>
                   <td class="label">手机号码：</td>
                   <td>
                       <input type="text" id="mobile"  size="30" name="form.mobile" class="input02" value="<c:out value='${form.mobile}'/>">
                   </td>
               </tr>
               <c:if test="${not empty data.branchList }">
               <tr>
                   <td class="label">所在营业部：</td>
                   <td>
                       <select id="branchNo" name="form.branchNo">
                          <c:forEach var="item" items="${data.branchList}">
                            <option value="<c:out value='${item.branchno}'/>"><c:out value="${item.branchname}" /></option>
                          </c:forEach>
                       </select>
                       
                   </td>
               </tr>
               </c:if>
              
              <tr>
                <td class="label">&nbsp;</td>
              <td>
                	<input type="button" name="button" id="button" value="提交" class="bt04"/>&nbsp;
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
<script language="javascript">
    function loadCheckbox(siteno)
    {
        var site = siteno.split("|");
        for(var i = 0; i < site.length; i++)
        {
            var obj = document.getElementsByName("form.siteNo");
            for(var j = 0; j < obj.length; j++)
            {
                var tmp = obj[j].value.split(",");
                if(site[i] == tmp[0])
                {
                    obj[j].checked = "checked";
                }
            }
        }
    }
    loadCheckbox('${siteno}');

    function loadRadio(isSystem)
    {
        var obj = document.getElementsByName("form.isSystem");
        for(var i = 0; i < obj.length; i++)
        {
            if(obj[i].value == isSystem)
            {
                obj[i].checked = true;
            }
        }
    }
    loadRadio('<c:out value='${form.isSystem}'/>');
    
    $("#branchNo").val("<c:out value='${form.branchNo}'/>");
</script>
</html>
