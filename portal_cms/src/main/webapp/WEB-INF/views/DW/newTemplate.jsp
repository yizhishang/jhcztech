<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<style type="text/css"> 
<!-- 
html,body{height:100%; margin:0px;} 
#mainbody{width:99%; height:99%;border:1px solid #00bae9;} 
--> 
</style> 
<body>
<input type="hidden" name="catalogId" id="catalogId" value=""/>
<div id="mainbody">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable" style="border:0px">
  <tr>
    <td colspan="2"><h5>模板信息</h5></td>
  </tr>
  <tr>
    <td colspan="2" style="height:8px;"></td>
  </tr>
  <tr>
    <td width="120" class="label">栏目名称：</td>
    <td><input type="text" name="catalogName" id="catalogName" class="input02" readonly="true" style="background:#CCCCCC;width:116px;" onClick="getCatalogInfo();"/>
      <font color="#FF0000">*</font></td>
  </tr>
  <tr>
    <td width="120" class="label">模板名称：</td>
    <td><input type="text" name="templateName" id="templateName" class="input02" style="width:116px;"/>
      <font color="#FF0000">*</font></td>
  </tr>
  <tr>
    <td class="label">模板类型：</td>
    <td><select name="type" id="type" class="select" style="width:117px;">
        <option value="1">首页模板</option>
        <option value="2">信息列表</option>
        <option value="3">信息细览</option>
        <option value="4">其它模版</option>
      </select>
      <font color="#FF0000">*</font></td>
  </tr>
  <%--<tr>
    <td class="label">文件编码：</td>
    <td><select name="encoding" id="encoding" class="select" style="width:117px;">
        <option value="">请选择</option>
        <option value="UTF-8">UTF-8</option>
        <option value="UTF-8">UTF-8</option>
      </select>
      <font color="#FF0000"><br/>生成文件的编码，缺省为配置文件指定的编码</font></td>
  </tr>--%>
  <tr id="tr_filepath" style="display:none">
    <td width="120" class="label">发布文件路径：</td>
    <td><input type="text" name="filePath" id="filePath" class="input02" style="width:300px;"/>
      <font color="#FF0000">*<br>
      (必须指定相对应用根目录的完整路径：例:/main/catalog/index.shtml)</font></td>
  </tr>
  <!--<tr>
    <td class="label">&nbsp;</td>
    <td><input type="button" name="button" id="button" value="提交" class="bt04" onClick="alert(Check());"/>
      &nbsp;
      <input type="button" name="button" id="button" value="重置" class="bt04" onClick="alert(GetResult());"/>
    </td>
  </tr>-->
</table>
</div>
<div id="BgDiv" style="display:none"></div>
<div id="MsgDiv" style="display:none"></div>
<script type="text/javascript" language="javascript">
function getCatalogInfo()
{
	var arr = openDialog('dw_template.action?function=left&menuNo=1', 230, 500);
	if(arr != null)
	{
		$("#catalogId").val(arr[0]);
		$("#catalogName").val(arr[1]);
	}
	
}

function Check()
{
	var catalogId = $("#catalogId").val();
	var catalogName = $("#catalogName").val();
	var type = $("#type").val();
	var filePath = $("#filePath").val();
	if($.trim(catalogId).length == 0)
	{
		return "请选择栏目";
	}
	else if($.trim($("#templateName").val()).length == 0)
	{
		return "模板名称不能为空";
	}
	else if(type == 4 && $.trim(filePath).length == 0)
	{
		return "你选择了模板类型为其它模板，必须指定发布文件路径";
	}
	else
	{
    	return "";
	}
}
function GetResult()
{
	var catalogId = $("#catalogId").val();
	var catalogName = $("#catalogName").val();
	var templateName = $("#templateName").val();
	//templateName = encodeURIComponent(templateName);
	templateName = encodeURI(templateName);
	templateName = encodeURI(templateName);
	var type = $("#type").val();
	//var encoding = $("#encoding").val();
	var filePath = $("#filePath").val();
	var returnURL = "";
	returnURL += "&catalogId=" + catalogId;
	returnURL += "&catalogName=" + catalogName;
	returnURL += "&templateName=" + templateName;
	returnURL += "&type=" + type;
	//alert(returnURL);
	//returnURL += "&encoding=" + encoding;
	if(type == 4)
	{
		returnURL += "&filePath=" + filePath;
	}
	
    return returnURL;
}
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

</script>
</body>
</html>