<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<%@ page import="com.jhcz.base.util.RequestHelper"%>
<%@ include file="/admin/common/header.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/validator.js"></script>
<style>
select{width:120px;}
</style>
<body>
<script type="text/javascript">
$(document).ready(function(){  
	$("#tableForm").submit(function(){
		return actChecks();
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
<input type="hidden" name="function" value="ImportExcel"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
           <p><img src="${ctxPath }/admin/images/ico04.gif" />${data.tableinfo.name_ch }导入Excel数据</p>
        </div>
    </td>
  </tr>
  <tr>
  	<td class="edinner" style="width:100%"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
      <tr>
         <td><div class="space"></div>
         <div class="label"><a href="javascript:void(0)" onClick="saveFunction();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>上传</a><a href="javascript:void(0)" onClick="closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div></td>
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
              	<td class="label">上传Excel： </td>
              	<td colspan="3">
					<input type="text" name="excel_path" id="excel_path" class="input02" style="width:300px;" readonly="" value="" isnotempty="Y" title_val="上传Excel" >
					<input type="button" onclick="openUploadFileDialog($('#excel_path'))" value="上传文件" class="bt02">
					<input type="button" onclick="openDeleteFileDialog($('#excel_path'))" value="删除" class="bt01">
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
</body>
</html>
