<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#enterForm").click(function(){
			if($.trim($("#name").val()).length == 0)
			{
				alert("模板名称不能为空！")
				$("#name").focus();
				return false;
			}
			
			if($("#type").val() == 4)
			{
				if($.trim($("#filePath").val()).length == 0)
				{
					alert("模板路径不能为空！")
					$("#filePath").focus();
					return false;
				}
			}
			submitForm("addForm",null, false);
		});
		
		$("#type").change(function(){
			if($(this).val() == 4)
			{
				$("#tr_filepath").show();
			}
			else
			{
				$("#filePath").val("");
				$("#tr_filepath").hide();
			}
		});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="add.action" method="post" target="hiddenFrame" id="addForm">
  <input type="hidden" name="function" value="add"/>
  <input type="hidden" name="form.catalogId" value="${param.catalogId}"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td>
      	<div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />添加模板</p>
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
	                  <td width="120" class="label">模板名称：</td>
	                  <td>
	                  	<input type="text" name="form.name" id="name" class="input02" style="width:116px;"/>
	                    <font color="#FF0000">*</font>
	                  </td>
	                </tr>
	                <tr>
	                  <td width="120" class="label">模板类型：</td>
	                  <td><select name="form.type" id="type" class="select" style="width:117px;">
	                      <option value="1">首页模板</option>
	                      <option value="2">信息列表</option>
	                      <option value="3">信息细览</option>
	                      <option value="4">其它模版</option>
	                    </select>
	                    <font color="#FF0000">*</font></td>
	                </tr>
					<tr>
	                  <td width="120" class="label">文件编码：</td>
	                  <td><select name="form.encoding" id="encoding" class="select" style="width:117px;">
					  	  <option value="">请选择</option>
	                      <option value="UTF-8">UTF-8</option>
	                      <option value="GBK">GBK</option>
	                    </select>
	                    <font color="#FF0000">生成文件的编码，缺省为配置文件指定的编码</font></td>
	                </tr>
	                <tr id="tr_filepath" style="display:none">
	                  <td width="120" class="label">发布文件路径：</td>
	                  <td>
	                  	<input type="text" name="form.filePath" id="filePath" class="input02" style="width:300px;"/>
	                    <font color="#FF0000">*<br>(必须指定相对应用根目录的完整路径：例:/main/catalog/index.shtml)</font>
	                   </td>
	                </tr>
	                <tr>
	                  <td width="120" class="label">模板内容：</td>
	                  <td><textarea class="input02" name="form.content" id="content" style="width:580px;height:150px;"></textarea></td>
	                </tr>
	                <tr>
	                  <td class="label">&nbsp;</td>
	                  <td>
	                  	<input type="button" name="enterForm" id="enterForm" value="提交" class="bt04"/>
	                    <input type="button" id="close" name="close"value="关闭" class="bt04"/>
	                  </td>
	                </tr>
	                <tr>
	                  <td colspan="2" style="height:8px;"></td>
	                </tr>
	              </table>
              </td>
          </tr>
        </table>
       </td>
    </tr>
  </table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>