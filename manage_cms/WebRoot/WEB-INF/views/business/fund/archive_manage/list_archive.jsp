<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
function deleteFunction(name,funcitonId){
	if(isChecked(name)){
		if($(":input[name='function']").length>0){
			$(":input[name='function']").val(funcitonId);
			if(window.confirm("确定删除选中数据？")){
				$("#qryparm").submit();
			}
		}
	}else{
		alert("请选择需要删除的数据！");
	}
}
function deleteAllFunction(funcitonId){
	if($(":input[name='function']").length>0){
		$(":input[name='function']").val(funcitonId);
		if(window.confirm("确定删除所有数据？")){
			$("#qryparm").submit();
		}
	}
}
function addFunction()
{
	openWindow("archiveManage.action?function=add", 630, 390);
}

function editFunction(id)
{
	openWindow("archiveManage.action?function=edit&id="+id, 630, 390);
}
</script>
<form id="qryparm" name="qryparm" action="archiveManage.action">
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
			 <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a>
			  <a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> 
			 </div>
              <div class="search">
                <div class="space"></div>
                <div>
              
                  <div style="float:left;">
                  <span>档案栏目：<input type="text" name="archivecatalog" value="${param.archivecatalog}" class="input01"/>&nbsp;</span> 
                  <span>档案名称：<input type="text" name="archivenm" value="${param.archivenm}" class="input01"/>&nbsp;</span> 
                  <span>
                    <input type="submit" name="button" id="searchEnter" value="查询"  class="bt01" />
                    </span> </div>
                </div>
               
              </div>
              <div class="space"></div>
              <div class="databox2" style="height: 350px">
                <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="tdhead" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                    <td class="tdhead">档案栏目</td>
                    <td class="tdhead">档案名称</td>
                    <td class="tdhead">收费类型</td>
                    <td class="tdhead">客户级别</td>
					<td class="tdhead">显示名称</td>
					<td class="tdhead">基金代码</td>
					<td class="tdhead">消耗积分</td>
					<td class="tdhead">状态</td>
					<td class="tdhead">操作</td>
                  </tr>
                   <c:if test="${empty data.page.data}">
                   <tr>
                    <td colspan="14">&nbsp;没有数据!</td>
                  </tr>
                  </c:if>
                <c:forEach var="item" items="${data.page.data}">
		              <tr id="row_<c:out value='${item.archiveid}'/>" ondblclick="editFunction('<c:out value="${item.archiveid}"/>')" title="双击查看详细内容">
	                    <td>
						<input type="checkbox" name="id" id="check_<c:out value='${item.archiveid}'/>" value="<c:out value='${item.archiveid}'/>" onClick="setCheck(this)">
						</td>						
	                    <td style="text-align: left">&nbsp;<c:out value="${item.archivecatalog}" default="--"/>&nbsp;</td>
	                    <td style="text-align: left" class="td3">&nbsp;<c:out value="${item.archivenm}" default="--"/>&nbsp;</td>
	                    <td >&nbsp;<c:choose>
							<c:when test="${item.feestp=='0'}">免费</c:when>
							<c:when test="${item.feestp=='1'}">收费</c:when>       
                         </c:choose>&nbsp;</td>
	                    <td>&nbsp;<c:choose>
							<c:when test="${item.custlevel=='0'}">游客</c:when>
							<c:when test="${item.custlevel=='1'}">注册客户</c:when>
							<c:when test="${item.custlevel=='2'}">查询客户</c:when>
							<c:when test="${item.custlevel=='3'}">交易客户</c:when>       
                         </c:choose>&nbsp;</td>
						<td style="text-align: left">&nbsp;<c:out value="${item.displaynm}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.fundid}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.consumepoints}" default="--"/>&nbsp;</td>
						<td>&nbsp;	<c:choose>
							<c:when test="${item.status=='1'}">可用</c:when>
							<c:when test="${item.status=='0'}">不可用</c:when>    
                        </c:choose>&nbsp;</td>
						<td>&nbsp;<a href="#" onclick="editFunction('<c:out value="${item.archiveid}"/>')">编辑</a>&nbsp;</td>
		              </tr>
       			 </c:forEach>
                </table>
                </div>
                <!-- 包含分页代码  -->
                <c:if test="${not empty data.page.data }">
                <jsp:include flush="true" page="/WEB-INF/views/include/page.jsp"></jsp:include>
                </c:if>
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