<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#enum_name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"名称不能为空,请确认"});
		//$("#enum_value").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"具体值不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
		$("#enterForm").click(function(){
			submitForm("addType");
		});
		
	});
	
	function submitForm(funcitonId)
	{
		$.ajax({
			type:"post",
			url:funcitonId + ".action",
			data : encodeURI($("#addForm").serialize()),
			success: function(data){
				if(data == "success")
				{
					location.reload();
				}else{
					alert(data);
				}
			},
			error: function(data){
				alert(data);
			}
		});	
	}
</script>
<form action="doDefault.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="edit"/>
<input type="hidden" name="form.enum_value" id="enum_value" class="input02" style="width:116px;" value="<c:out value='${form.enum_value}'/>"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />编辑枚举类型</p>
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
                <td width="120" class="label"><span class="DetailTagText">类型名称</span>：</td>
                <td><input type="text" name="form.enum_name"  id="enum_name" class="input02" style="width:116px;" value="<c:out value='${form.enum_name}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <%--<tr>
                <td width="120" class="label"><span class="DetailTagText">类型值</span>：</td>
                <td><input type="text" name="form.enum_value" id="enum_value" class="input02" style="width:116px;" value="<c:out value='${form.enum_value}'/>" disabled="disabled"/></td>
              </tr>
              --%><tr>
                <td class="label">&nbsp;</td>
              <td>
                	<input type="button" name="enterForm" id="enterForm" value="提交" class="bt04"/>&nbsp;
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
