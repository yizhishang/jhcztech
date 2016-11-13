<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
function deleteFunction(name,funcitonId){
	if(isChecked(name)){
		var source_function = $("#functionname").val();
		$("#functionname").val(funcitonId);
		$("#qryparm").submit();
		$("#functionname").val(source_function);
	}else{
		alert("请选择需要删除的数据！");
	}
}
 
function editFunction(functionId,pkcol){
	window.open("${ctxPath }/admin/commonAdmin/doEdit.action?tableName=${data.tableinfo.name_en }&pkcol="+pkcol);
}

function addFunction(functionId){
	window.open("${data.tableinfo.action_url }?function="+functionId);
}

function openFunction()
{
    if (!isChecked("pkcol"))
    {
        alert("请您选择需要操作的对象！");
        return;
    }
	$(":input[@name='function']").val("open");
	$("#qryparm").submit();
}

function closeFunction()
{
    if (!isChecked("pkcol"))
    {
        alert("请您选择需要操作的对象！");
        return;
    }
    $(":input[@name='function']").val("close");
	$("#qryparm").submit();
}

function importFunction(functionId){
	window.open("${data.tableinfo.action_url }?function="+functionId);
}

function exportFunction(functionId){
	window.open("${data.tableinfo.action_url }?function="+functionId);
}
</script>
<form id="qryparm" name="qryparm" action="${ctxPath }/admin/commonAdmin/doDefault.action?tableName=${data.tableinfo.name_en }" >
<input type="hidden" name="pageUrl" value="${pageUrl}"></input>
<input type="hidden" name="tableName" id="tableName" value="${data.tableinfo.name_en }"></input>
<input type="hidden" name="function" id="functionname" value="doDefault"></input>
<input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"></input>
<input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"></input>
<div class="c_ie6_out">
  <div class="c_ie6_in">
    <div class="Wrapper">
      <div class="inner">
        <div class="innercontent">
          <div class="cl"></div>
          <div class="contentbox">
            <div class="content">
              <!-- 包含导航代码  -->
              <jsp:include flush="true" page="/WEB-INF/views/include/menubar.jsp"></jsp:include>
              <div class="label">
              	<c:if test="${data.tableinfo.can_add eq 'Y'}">
               	<a href="#" onClick="addFunction('add');"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a> 
               	</c:if>
               	<c:if test="${data.tableinfo.can_del eq 'Y'}">
                <a href="#" onClick="deleteFunction('pkcol','delete');"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
                </c:if>
                <c:if test="${data.tableinfo.can_import eq 'Y'}">
               	<a href="#" onClick="importFunction('ImportExcel');"><img src="${ctxPath }/admin/images/ico02.gif" border="0"/>导入</a> 
               	</c:if>
               	<c:if test="${data.tableinfo.can_export eq 'Y'}">
                <a href="#" onClick="exportFunction('ExportExcel');"><img src="${ctxPath }/admin/images/ico13.gif" border="0"/>导出</a>
                </c:if>
              </div>
              <c:if test="${not empty data.searchcols}">
              <div class="search">
                <div class="space"></div>
                <div>
                  <div style="width:980px;">
                  	<c:forEach var="colitem" items="${data.searchcols}">
                    <span style="float:left;width:186px;display:block;text-align:right;">
                    <yizhishang:dynsearchinput colname="${colitem.name_en }" param="${param }" colitem="${colitem }"/></span>
                    </c:forEach>
                  </div>
                  <span><input type="submit" name="button" id="button" value="查询"  class="bt01" /></span> 
                </div>
               </c:if>
              </div>
              <div class="space"></div>
              <div class="databox">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="td1" width="60px;"><input type="checkbox" id="check_all"  onclick="checkAll(this,'pkcol');" /></td>
                    <c:forEach var="colitem" items="${data.cols}">
                    <td class="tdhead">${colitem.name_ch }</td>
                    </c:forEach>
                    <td class="tdhead">操作</td>
                  </tr>
                </table>
                </div>
                <div class="databox2">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                <c:forEach var="item" items="${data.page.data}">
		              <tr>
	                    <td  width="60px;"><input type="checkbox" name="pkcol" id="check_<yizhishang:printvalue colname='${data.tableinfo.pk_column }' item='${item }' colitem='${colitem }' is_pk='Y'/>" value="<yizhishang:printvalue colname='${data.tableinfo.pk_column }' item='${item }' colitem='${colitem }' is_pk='Y'/>" onClick="setCheck(this)"/></td>
	                    <c:forEach var="colitem" items="${data.cols}">
	                    <td style="text-align:${colitem.align_in_list }">&nbsp;<yizhishang:printvalue colname="${colitem.name_en }" item="${item }" colitem="${colitem }"/></td>
	                    </c:forEach>
	                    <td>&nbsp;
	                    	<c:if test="${data.tableinfo.can_update eq 'Y'}">
			                <a href="javascript:editFunction('edit','<yizhishang:printvalue colname='${data.tableinfo.pk_column }' item='${item }' is_pk='Y' />');">编辑</a>
			                </c:if>
		              </tr>
       			 </c:forEach>
                </table>
                </div>
                <!-- 包含分页代码  -->
                <jsp:include flush="true" page="/WEB-INF/views/include/page.jsp"></jsp:include>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</form>
</body>
</html>

