<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
function deleteFunction(name,funcitonId){
	if (isChecked(name) && window.confirm("确定删除选中数据？")){
		submitForm("qryparm", funcitonId, true);
	}else{
		alert("请选择需要删除的数据！");
	}
}

function deleteAllFunction(name,funcitonId){
	if (isChecked(name) && window.confirm("确定删除所有数据？")){
		submitForm("qryparm", funcitonId, true);
	}else{
		alert("请选择需要删除的数据！");
	}
}

$(document).ready(function(){  
	
});
</script>
<form id="qryparm" name="qryparm" action="publishlog.action" >
  <input type="hidden" name="form.pageUrl" value="${pageUrl}">
  </input>
  <input type="hidden" name="function" value="${param.function }">
  </input>
  <input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }">
  </input>
  <input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }">
  </input>
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
                	<a href="#" onClick="deleteFunction('id','doDelete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
                	<a href="#" class="FourFont" onClick="deleteAllFunction('id','doDeleteAll')"><img src="/admin/images/ico15.gif" border="0"/>删除全部</a></div>
                <div class="search">
                  <div class="space"></div>
                  <div>
                    <div style="float:left;"> <span>创建时间：</span> <span>
                      <input type="text" name="startDate" id="startDate" class="input01" value="${param.startDate}" readonly/>
                      </span> <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'startDate'})"/></span> <span>到</span> <span>
                      <input type="text" name="endDate" id="endDate" class="input01" value="${param.endDate}" readonly/>
                      </span> <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'endDate'})" /></span></span> <span>
                      <input type="submit" name="button" id="button" value="查询" class="bt01" />
                      </span> </div>
                  </div>
                </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="td1" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead" style="width:130px;">发布队列编号</td>
                      <td class="tdhead">操作描述</td>
                      <td class="tdhead" style="width:150px;">创建人</td>
                      <td class="tdhead" style="width:200px;">创建时间</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr style="cursor:pointer">
                        <td><input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>">
                        </td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.queue_id}" /></td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.description}" /></td>
                        <td>&nbsp;
                          <c:out value="${item.create_by}" /></td>
                        <td>&nbsp;
                          <c:out value="${item.create_date}" /></td>
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