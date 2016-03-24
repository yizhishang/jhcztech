<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#item_name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"名称不能为空,请确认"});
		$("#item_value").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"具体值不能为空,请确认"});
		$("#orderline").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"排序值不能为空,请确认"}).regexValidator({regexp:regexEnum.num,onerror:"排序值只能为数字,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
		$("#enterForm").click(function(){
			submitForm('addForm');
		});
		
	});
</script>
<form action="editItem.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="form.item_code" value="<c:out value='${form.item_code}'/>">
<input type="hidden" name="form.enum_type" value="<c:out value='${form.enum_type}'/>">
<input type="hidden" name="form.siteno" value="<c:out value='${form.siteno}'/>">
<input type="hidden" name="form.is_system" value="<c:out value='${form.is_system}'/>">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />编辑枚举值</p>
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
                <td width="120" class="label"><span class="DetailTagText">名称</span>：</td>
                <td><input type="hidden" name="form.item_name" value="<c:out value='${form.item_name}'/>">
                                                    <c:out value="${form.item_name}" /></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">具体值</span>：</td>
                <td><input type="text" name="form.item_value" id="item_value" class="input02" style="width:116px;" value="<c:out value='${form.item_value}'/>"/></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">排序值</span>：</td>
                <td><input type="text" name="form.orderline" id="orderline" class="input02" style="width:116px;" value="<c:out value='${form.orderline}'/>"/></td>
              </tr>
              <tr>
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
