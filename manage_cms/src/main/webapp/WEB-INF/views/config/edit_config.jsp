<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#caption").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"参数标题不能为空,请确认"});
		$("#value").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"参数值不能为空,请确认"});
		
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="edit.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="edit"/>
<input type="hidden" name="form.isSystem" value="${form.isSystem}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />修改配置信息</p>
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
                <td width="120" class="label"><span class="DetailTagText">参数名称</span>：</td>
                <td><input type="hidden" name="form.id" value="<c:out value='${form.id}'/>">
                                                    <input type="hidden" name="form.name" value="<c:out value='${form.name}'/>">
                                                    <c:out value="${form.name}" /></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">参数标题</span>：</td>
                <td><input type="text" name="form.caption"  id="caption" class="input02" style="width:116px;" value="<c:out value='${form.caption}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">参数值</span>：</td>
                <td><input type="text" name="form.value"  id="value" class="input02" style="width:116px;" value="<c:out value='${form.value}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">参数描述</span>：</td>
                <td><input type="text" name="form.description" id="description" class="input02" style="width:116px;" value="<c:out value='${form.description}'/>"/></td>
              </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td>
                	<input type="type" id="enterForm" value="提交" class="bt04"/>&nbsp;
                	<input type="button" id="close" name="close"value="关闭" class="bt04"/>
                </td>
              </tr>
			  <tr>
                <td colspan="2" style="height:8px;"></td>
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
