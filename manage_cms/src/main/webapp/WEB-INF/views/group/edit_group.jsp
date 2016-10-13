<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#name").formValidator().inputValidator({min:1,onerror:"组名称不能为空,请确认"});
		$("#description").formValidator().inputValidator({min:1,onerror:"组描述不能为空,请确认"});
		
		$("#enterForm").click(function(){
			submitForm("addForm");
		});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="edit.action" method="post" target="hiddenFrame" id="addForm">
  <input type="hidden" name="function" value="edit"/>
  <input type="hidden" size="30" name="form.group_Id" value="<c:out value='${form.group_Id}'/>">
  <input type="hidden" name="form.name" value="<c:out value='${form.name}'/>">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />编辑组</p>
        </div></td>
    </tr>
    <tr>
      <td class="edinner"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><div class="space"></div>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
                <tr>
                  <td colspan="2" style="height:8px;"></td>
                </tr>
                <tr>
                  <td width="120" class="label">组名称：</td>
                  <td><c:out value='${form.name}' /></td>
                </tr>
                <tr>
                  <td width="120" class="label">组描述：</td>
                  <td><input type="text" name="form.description" id="description" class="input02" style="width:116px;" value="<c:out value='${form.description}'/>"/>
                    <font color="#FF0000">*</font></td>
                </tr>
                <tr>
                  <td class="label">&nbsp;</td>
                  <td><input type="button" name="enterForm" id="enterForm" value="提交" class="bt04"/>
                    &nbsp;
                    <input type="button" id="close" name="close"value="关闭" class="bt04"/>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="height:8px;"></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>