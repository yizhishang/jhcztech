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
		//return actChecks();
	});
});
function saveFunction(){
	$("#tableForm").submit();
}
function closeFunction(){
	self.close();
}
function initGuess(){
	if(confirm("确认要自动匹配吗？")){
		$("#config_table input[id^='title_']").each(function(){
			var title_value = $(this).val();
			guess(title_value);
		});
	}
}
function guess(title_value){
	var hidden_id = "hidden_title_"+title_value;
	$("#col_"+title_value+" option").each(function(){
		var op_value = $(this).val();
		var op_txt = $(this).text();
		if(op_value==title_value || op_txt.indexOf(title_value)!=-1){
			$("#"+hidden_id).val(op_value);
			$("#col_"+title_value+" option:selected").attr("selected",false);
			$(this).attr("selected",true);
			return;
		}
	});
}
function changeSelectedOption(obj){
	var fixer = $(obj).attr("withInput");
	var value = $(obj).val();
	var hidden_id = "hidden_title_"+fixer;
	$("#"+hidden_id).val($(obj).val());
}
</script>
<form id="tableForm" action="${data.tableinfo.action_url }" target="hiddenFrame"  method="post">
<input type="hidden" name="function" value="ImportExcelConfig"></input>
<input type="hidden" name="excel_path" value="${data.excel_path }"></input>
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
         <div class="label"><a href="javascript:void(0)" onClick="saveFunction();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>导入</a><a href="javascript:void(0)" onClick="initGuess();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>Guess</a><a href="javascript:void(0)" onClick="closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div></td>
     </tr>
      <tr>
        <td>
			<div class="space"></div>
			<div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
            <table width="98%" border="0" align="top" cellpadding="0" cellspacing="0" align="left" class="editortable" id="config_table">
			  <tr>
                <td colspan="3" style="height:8px;"></td>
              </tr>
              <tr>
                <th align="right">Excel列名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数据库字段&nbsp;&nbsp;[形如“字段中文名(字段英文名)”]</th>
                <th>&nbsp;</th>
              </tr>
              <c:if test="${not empty data.table_cols && not empty data.excel_titles}">
              <c:forEach var="titleitem" items="${data.excel_titles}">
	              <tr>
	              	<th align="right">
	              		<input type="hidden" id="hidden_title_${titleitem.value }" name="${titleitem.value }" value="" />
	              		<input type="text" readonly="readonly" id="title_${titleitem.value }" name="title.${titleitem.value }" value="${titleitem.value }" />&lt;---
	              	</th>
	              	<th align="left">
	              		---&gt;<select id="col_${titleitem.value }" withInput="${titleitem.value }" onchange="changeSelectedOption(this)">
	              			<option value="">---------</option>
	              			<c:forEach var="colitem" items="${data.table_cols}">
	              			<option value="${colitem.name_en }">${colitem.name_ch }(${colitem.name_en })</option>
	              			</c:forEach>
	              		</select>
	              	</th>
	              </tr>
              </c:forEach>
              </c:if>
			  <tr>
                <td colspan="3" style="height:8px;"></td>
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
