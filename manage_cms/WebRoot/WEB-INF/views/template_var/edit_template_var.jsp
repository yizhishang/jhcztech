<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#item_name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"变量名称不能为空,请确认"});
		$("#item_value").formValidator().inputValidator({min:1,onerror:"变量值不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="edit.action" method="post" target="hiddenFrame" id="addForm">
  <input type="hidden" name="function" value="edit"/>
  <input type="hidden" name="form.id" value="${param.id}"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />编辑模板变量</p>
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
                  <td width="120" class="label"><span class="DetailTagText">变量名称</span>：</td>
                  <td><input type="text" name="form.item_name" id="item_name"" class="input02" style="width:200px;" value="<c:out value='${form.item_name}'/>"/>
                    <font color="#FF0000">*</font></td>
                </tr>
                <tr>
                  <td width="120" class="label"><span class="DetailTagText">变量描述</span>：</td>
                  <td><input type="text" name="form.description" id="description"" class="input02" style="width:200px;" value="<c:out value='${form.description}'/>"/></td>
                </tr>
                <tr>
                  <td width="120" class="label"><span class="DetailTagText">状态</span>：</td>
                  <td><input type="radio" name="form.state" value="0">
                    无效
                    <input type="radio" name="form.state" value="1" checked="checked">
                    有效
                    <script language="javascript">setRadioChecked("form.state", "<c:out value='${form.state}'/>")</script></td>
                </tr>
                <tr>
                  <td width="120" class="label"><span class="DetailTagText">变量值</span>：</td>
                  <td><textarea name="form.item_value" id="item_value" class="input02" style="width:400px;height:150px;"><c:out value="${form.item_value}"/>
</textarea>
                    <font color="#FF0000">*</font></td>
                </tr>
                <tr>
                  <td class="label">&nbsp;</td>
                  <td><input type="button" name="enterForm" id="enterForm" onclick="saveData();" value="提交" class="bt04"/>
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