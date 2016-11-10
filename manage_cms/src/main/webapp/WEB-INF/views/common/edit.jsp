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
function openSelectPage(id,txtid,colid){
	var selected = $("#"+id).val();
	window.open("${data.tableinfo.action_url }?function=selectPageVal&id="+id+"&txtid="+txtid+"&colid="+colid+"&selected="+selected);
}
function changeRadioTxt(txt,txtid){
	if(txtid){
		$("#"+txtid).val(txt);
	}
}
</script>
<form id="tableForm" action="${data.tableinfo.action_url }" target="hiddenFrame"  method="post">
<input type="hidden" name="function" value="edit"/>
<input type="hidden" name="form.${data.pkcol }" value="${data.pkvalue }"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
           <p><img src="${ctxPath }/admin/images/ico04.gif" />修改${data.tableinfo.name_ch }信息</p>
        </div>
    </td>
  </tr>
  <tr>
  	<td class="edinner" style="width:100%"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
      <tr>
         <td><div class="space"></div>
         <div class="label"><a href="javascript:void(0)" onClick="saveFunction();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>保存</a><a href="javascript:void(0)" onClick="closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div></td>
     </tr>
      <tr>
        <td>
			<div class="space"></div>
			<div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
            <table width="98%" border="0" align="top" cellpadding="0" cellspacing="0" class="editortable">
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
              <c:set value="Y" var="singled"/>
              <c:set value="0" var="multitimes"/>
              <c:forEach var="colitem" items="${data.cols}" varStatus="status">
              <c:choose>
              	<c:when test="${colitem.show_in_info == 'Y'}">
				<c:if test="${colitem.is_single=='N'}">
					<c:if test="${singled=='Y'}">
						<c:set value="0" var="multitimes"/>
						<tr>
					</c:if>
					   <td class="label">${colitem.name_ch }： </td>
	                   <td>
	                   	<yizhishang:dyninput curuser="${data.uid}" id="${colitem.name_en }" name="form.${colitem.name_en }" item="${data.bean}" colitem="${colitem}" colname="${colitem.name_en }"/>
						<c:if test="${colitem.be_null=='N'}"><font color="#ff0000">*</font></c:if>
	                   </td>
	                <c:choose>
	                	<c:when test="${multitimes == '1'}">
	                	</tr>
	                	<c:set value="0" var="multitimes"/>
						<c:set value="Y" var="singled"/>
	                	</c:when>
	                	<c:when test="${multitimes == '0'}">
	                	<c:if test="${status.last}">
							<td>&nbsp;</td><td>&nbsp;</td></tr>
						</c:if>
						<c:set value="1" var="multitimes"/>
						<c:set value="N" var="singled"/>
	                	</c:when>
	                </c:choose>
				</c:if>
				<c:if test="${colitem.is_single=='Y'}">
					<c:if test="${multitimes=='1'}">
						<td>&nbsp;</td><td>&nbsp;</td></tr>
						<c:set value="0" var="multitimes"/>
					</c:if>
					<tr>
						<td class="label">${colitem.name_ch }： </td>
						<td colspan="3">
							<yizhishang:dyninput curuser="${data.uid}" id="${colitem.name_en }" name="form.${colitem.name_en }" item="${data.bean}" colitem="${colitem}" colname="${colitem.name_en }"/>
							<c:if test="${colitem.be_null=='N'}"><font color="#ff0000">*</font></c:if>
						</td>
					</tr>
					<c:set value="Y" var="singled"/>
				</c:if>
				</c:when>
				<c:when test="${colitem.show_in_info == 'N'}">
					<input type="hidden" id="${colitem.name_en }"  name="form.${colitem.name_en }"  value="<yizhishang:printvalue colname='${colitem.name_en }' item='${data.bean}' colitem='${colitem }' is_pk='Y'/>" />
				</c:when>
			  </c:choose>
              </c:forEach>
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
            </table></div>
          </td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>
