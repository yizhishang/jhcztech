<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>金恒创智CMS后台管理系统</title>
    <%@ include file="/admin/common/meta.jsp" %>
    <style type="text/css"> 
	<!-- 
	html,body{height:100%; margin:0px;} 
	#mainbody{width:99%; height:99%;border:1px solid #00bae9;} 
	--> 
	</style> 
    <link href="<%=request.getContextPath()%>/admin/styles/cms.css" rel="stylesheet" type="text/css" />
    <script language="javascript" src="<%=request.getContextPath()%>/admin/scripts/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/common.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/admin/scripts/syncReqJs.js"></script>
    <script type="text/javascript" language="javascript">
$(document).ready(function(){
	$("#componentName").change(function(){
		$("#webpartDIV").hide();
		$("#otherComponentName").hide();
		var componentName = $(this).val();
		if(componentName == 'CommonWebpart')
		{
			$("#otherComponentName").show();
		}
		else
		{
			if(componentName != "")
			{
				$("#webpartDIV").show().loadData("dw_template.action?function=getComponentContent&webpartType=SysWebpart&webpartName=" + componentName,{});
			}
		}

	});
	
	$("#otherComponentName").change(function(){
		$("#webpartDIV").hide();
		var componentName = $(this).val();
		if(componentName != "")
		{
			$("#webpartDIV").show().loadData("dw_template.action?function=getComponentContent&webpartType=CommonWebpart&webpartName=" + componentName,{});
		}
	});
	
	var webpartName = '${webpartName}';
	var otherWebpartName = '${otherWebpartName}';
	var webpartid = '${webpartid}';
	if($.trim(webpartName).length > 0 && $.trim(webpartid).length >0)
	{
		var webpartType = "";
		$("#componentName").val(webpartName).attr("disabled","disabled");
		if(webpartName == 'CommonWebpart')
		{
			$("#otherComponentName").val(otherWebpartName).show().attr("disabled","disabled");
			webpartType = "CommonWebpart";
			webpartName = otherWebpartName;
		}
		else
		{
			webpartType = "SysWebpart"; 
		}
		$("#webpartDIV").show().loadData("dw_template.action?function=getComponentContent&webpartType="+webpartType+"&webpartName=" + webpartName + "&webpartid=" + webpartid,{});
	}
});

function Check()
{
	if($("#webpartDIV").is(":visible") && $("#webpartDIV").find("table").length == 1)
	{
		var message = checkForm();
		return message;
	}
	else
	{
		return "请选择部件";
	}
}
function GetResult()
{
	if($("#webpartDIV").is(":visible") && $("#webpartDIV").find("table").length == 1)
	{
		var webpartInfo = "";
		var componentName = $("#componentName").val();
		var templantContent = $("[name='templateContent']").val();
		if(componentName == 'CommonWebpart')
		{
			webpartInfo = "<web:CommonWebpart";
		}
		else
		{
			webpartInfo = "<web:" + componentName;
		}
		webpartInfo +=  " " + getData();
		if($.trim(templantContent).length > 0)
		{
			webpartInfo += ">" + templantContent + "</web:" + componentName + ">";
		}
		else
		{
			webpartInfo += " />";
		}
		return webpartInfo;
	}
	else
	{
		return "";
	}
}
</script>
</head>
<body>
<div id="mainbody">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable" style="border:0px">
  <tr>
    <td colspan="2"><h5>插入部件信息</h5></td>
  </tr>
  <tr>
    <td colspan="2" style="height:8px;"></td>
  </tr>
  <tr>
    <td width="120" class="label">部件名称：</td>
    <td><select name="componentName" id="componentName" class="select" style="width:117px;">
	    <option value="">请选择</option>
		<c:forEach var="item" items="${sysWebpartList}">
		<option value="${item.name}">${item.text}</option>
		</c:forEach>
		<option value="CommonWebpart">自定义部件</option>
      </select>
	  <select name="otherComponentName" id="otherComponentName" class="select" style="width:117px;display:none">
	    <option value="">请选择</option>
		<c:forEach var="item" items="${commonWebpartList}">
        <option value="${item.name}">${item.text}</option>
		</c:forEach>
      </select>
     </td>
  </tr>
</table>
<div id="webpartDIV" style="display:none">
</div>
<!-- <input type="button" value="确定" onClick="alert(Check());"/>
<input type="button" value="获取数据" onClick="alert(GetResult());"/> -->
</div>
</body>
</html>