<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"主题名称不能为空,请确认"});
		
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="vote.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="edit"/>
<input type="hidden" name="form.sub_id" value="${param.id}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />修改主题信息</p>
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
                <td width="120" class="label"><span class="DetailTagText">主题名称</span>：</td>
                <td><input type="text" name="form.name" id="name" class="input02" style="width:200px;" value="<c:out value='${form.name}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">主题的描述</span>：</td>
                <td><textarea name="form.description"  class="input02" style="height:100px;width:400px;border-top:1px solid;"><c:out value='${form.description}'/></textarea></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">有效状态</span>：</td>
                <td><input type="radio" name="form.state" value="1" checked="checked"/>有效
				<input type="radio" name="form.state" value="0"/>无效
				<script type="text/javascript">setRadioChecked("form.state","${form.state}")</script>
			</td>
              </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td>
                	<input type="submit" name="enterForm" id="enterForm" value="提交" class="bt04"/>&nbsp;
                <input type="button" id="close" name="close"value="关闭" class="bt04"/>                </td>
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
