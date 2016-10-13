<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#name").formValidator().inputValidator({min:1,onerror:"角色名称不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
		$("#enterForm").click(function(){
		    submitForm("addForm");
		});
		
	});
</script>
<form action="edit.action" method="post" target="hiddenFrame" id="addForm">
  <input type="hidden" name="function" value="edit"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />编辑角色</p>
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
                  <td width="120" class="label">角色标识：</td>
                  <td><input type="hidden" size="30" name="form.id" class="DetailInputBox" value="${form.id}">
                    <c:out value='${form.roleNo}'/></td>
                </tr>
                <tr>
                  <td width="120" class="label">角色名称：</td>
                  <td><input type="text" name="form.name" id="name" value="${form.name}" class="input02" style="width:116px;"/>
                    <font color="#FF0000">*</font></td>
                </tr>
				<tr>
                <td width="120" class="label">角色描述：</td>
                <td><textarea name="form.description" cols="25" rows="3">${form.description}</textarea>
					</td>
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