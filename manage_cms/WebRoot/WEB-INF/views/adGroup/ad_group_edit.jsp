<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"广告组名称不能为空,请确认"});
		$("#orderline").formValidator().regexValidator({regexp:regexEnum.intege1,onerror:"排序值只能为数字,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="ad_group.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="edit"/>
<input type="hidden" name="form.id" value="<c:out value='${form.id}'/>">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />修改广告组信息</p>
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
                <td width="120" class="label"><span class="DetailTagText">广告组名称</span>：</td>
                <td><input type="text" name="form.name" id="name" class="input02" style="width:116px;" value="<c:out value='${form.name}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">排序值</span>：</td>
                <td><input type="text" name="form.orderline" id="orderline" class="input02" style="width:116px;" value="<c:out value='${form.orderline}'/>"/>
					<font color="#FF0000">(请输入数字,值大的排前面)</font></td>
              </tr>
              <tr>
                <td class="label">&nbsp;</td>
              	<td>
                	<input type="button" name="enterForm" id="enterForm" onclick="saveData();" value="提交" class="bt04"/>
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
