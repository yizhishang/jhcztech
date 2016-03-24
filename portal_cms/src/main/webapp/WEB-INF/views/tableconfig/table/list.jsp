<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">

function updateCols(id){
	window.open("/admin/TableColumnAdmin/doDefault.action?table_id="+id);
} 

function modifyPassword(functionId,id){
	openWindow("table.action?function="+functionId+"&id="+id,500, 180);
}

function openFunction()
{
    if (!isChecked("id"))
    {
        alert("请您选择需要操作的对象！");
        return;
    }
	$(":input[@name='function']").val("open");
	$("#qryparm").submit();
}

function closeFunction()
{
    if (!isChecked("id"))
    {
        alert("请您选择需要操作的对象！");
        return;
    }
    $(":input[@name='function']").val("close");
	$("#qryparm").submit();
}
</script>
<form id="qryparm" name="qryparm" action="table.action" >
<input type="hidden" name="form.pageUrl" value="${pageUrl}"></input>
<input type="hidden" name="function" value="${param.function }"></input>
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
               	<a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a> 
                <a href="#" onClick="deleteFunction('id','delete');"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
              </div>
              <div class="search">
                <div class="space"></div>
                <div>
              
                  <div style="float:left;">
                  <span>英文名称：<input type="text" alt="notempty" name="table_name_en" value="${param.table_name_en}" class="input01"/></span> 
                  <span>中文名称：<input type="text" alt="notempty" name="table_name_ch" value="${param.table_name_ch}" class="input01"/></span> 
                  <span>
                    <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                    </span> 
                  </div>
                </div>
               
              </div>
              <div class="space"></div>
              <div class="databox2" style="height: 350px">
                <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="td1"><input type="checkbox" id="check_all"  onclick="checkAll(this,'id');" /></td>
                    <td class="tdhead">id</td>
                    <td class="tdhead">英文名称</td>
                    <td class="tdhead">中文名称</td>
                    <td class="tdhead">可增加</td>
                    <td class="tdhead">可修改</td>
                    <td class="tdhead">可删除</td>
                    <td class="tdhead">可导出</td>
                    <td class="tdhead">可导入</td>
                    <td class="tdhead">操作</td>
                  </tr>
                <c:forEach var="item" items="${data.page.data}">
		              <tr>
	                    <td><input type="checkbox" name="id" id="check_${item.id}" value="${item.id}" onClick="setCheck(this)"/></td>
	                    <td>&nbsp;<c:out value="${item.id}" /></td>
	                    <td>&nbsp;<c:out value="${item.name_en}" /></td>
	                    <td>&nbsp;<c:out value="${item.name_ch}" /></td>
	                    <td >&nbsp;
		                    <c:if test="${item.can_add eq 'N'}">否</c:if>
							<c:if test="${item.can_add eq 'Y'}">是</c:if>
	                    </td>
	                    <td >&nbsp;
		                    <c:if test="${item.can_update eq 'N'}">否</c:if>
							<c:if test="${item.can_update eq 'Y'}">是</c:if>
	                    </td>
	                    <td >&nbsp;
		                    <c:if test="${item.can_del eq 'N'}">否</c:if>
							<c:if test="${item.can_del eq 'Y'}">是</c:if>
	                    </td>
	                    <td >&nbsp;
		                    <c:if test="${item.can_export eq 'N'}">否</c:if>
							<c:if test="${item.can_export eq 'Y'}">是</c:if>
	                    </td>
	                    <td >&nbsp;
		                    <c:if test="${item.can_import eq 'N'}">否</c:if>
							<c:if test="${item.can_import eq 'Y'}">是</c:if>
	                    </td>
	                    <td>&nbsp;<a href="javascript:editFunction('id=${item.id}');">编辑表信息</a> ｜  <a href="javascript:updateCols('${item.id}');">编辑字段信息</a></td>
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

