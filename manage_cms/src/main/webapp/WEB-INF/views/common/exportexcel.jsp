<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.yizhishang.base.util.RequestHelper"%>
<%@ include file="/admin/common/header.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/validator.js"></script>
<style>
select{width:120px;}
</style>
<body>
<script type="text/javascript">
$(document).ready(function(){
	var selected = $("input:checked[id^='check_'][id!='check_all']",window.opener.document);
	if(selected.length>0){
		var htmlcontent = "<li><input type=\"radio\" id=\"export_type_selected\" name=\"export_type\" value=\"selected\" style=\"top:-3px;\"/><span style=\"cursor:pointer;\" onclick=\"$('#export_type_selected').click();\">导出当前选中</span></li>";
		$("#tableForm ul li:first").after(htmlcontent);
		var ids = "";
		$(selected).each(function(i){
			if(i==0){
				ids = $(this).attr("value");
			}else{
				ids += ","+$(this).attr("value");
			}
		});
		$("#selected").val(ids);
	}
	var pageids = "";
	$(":checkbox[id^='check_'][id!='check_all']",window.opener.document).each(function(i){
		if(i==0){
			pageids = $(this).attr("value");
		}else{
			pageids += ","+$(this).attr("value");
		}
	});
	$("#pageids").val(pageids);
	$("#tableForm").submit(function(){
		var list_param = $("#qryparm",window.opener.document).serialize();
		$("#qryparam").val(list_param);
	});
});
function saveFunction(){
	$("#tableForm").submit();
}
function closeFunction(){
	self.close();
}
</script>
<form id="tableForm" action="${data.tableinfo.action_url }" name="tableForm" method="post">
<input type="hidden" name="function" value="ExportExcel"/>
<input type="hidden" name="pageids" id="pageids" value=""/>
<input type="hidden" name="selected" id="selected" value=""/>
<input type="hidden" name="qryparam" id="qryparam" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
           <p><img src="${ctxPath }/admin/images/ico04.gif" />${data.tableinfo.name_ch }导出Excel数据</p>
        </div>
    </td>
  </tr>
  <tr>
  	<td class="edinner" style="width:100%"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
      <tr>
         <td><div class="space"></div>
         <div class="label"><a href="javascript:void(0)" onClick="saveFunction();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>导出</a><a href="javascript:void(0)" onClick="closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div></td>
     </tr>
      <tr>
        <td>
			<div class="space"></div>
			<div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
            <table width="98%" border="0" align="top" cellpadding="0" cellspacing="0" class="editortable">
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
              <tr>
              	<td class="label">选择导出范围： </td>
              	<td colspan="3">
					<ul>
						<li><input type="radio" id="export_type_all" name="export_type" value="all" style="top:-3px;" checked="checked" /><span style="cursor:pointer;" onclick="$('#export_type_all').click();">导出全部</span></li>
						<li><input type="radio" id="export_type_page" name="export_type" value="page" style="top:-3px;"/><span style="cursor:pointer;" onclick="$('#export_type_page').click();">导出当前页</span></li>
						<li><input type="radio" id="export_type_search" name="export_type" value="search" style="top:-3px;"/><span style="cursor:pointer;" onclick="$('#export_type_search').click();">导出当前查询范围</span></li>
					</ul>
				</td>
              </tr>
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
            </table>
            </div>
          </td>
      </tr>
    </table></td>
    
  </tr>
</table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>
