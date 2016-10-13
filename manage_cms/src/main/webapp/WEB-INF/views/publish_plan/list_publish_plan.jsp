<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
function addPlanFunction()
{
	openWindow("doAdd.action", 850, 610);
}

function editFunction(id)
{
	openWindow("doEdit.action?id=" + id, 400, 200);
}
</script>
<form id="qryparm" name="qryparm" action="doDefault.action" >
  <input type="hidden" name="form.pageUrl" value="${pageUrl}" />
  <input type="hidden" name="function" value="${param.function }" />
  <input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }" />
  <input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }" />
  <input type="hidden" name="successPage" value="doDefault.action?manageCatalogId=${param.manageCatalogId }&catalogId=${param.catalogId}&name=${param.name}" />
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
                	<a href="#" onClick="addPlanFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>添加</a>
                	<a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
                	<a href="#" class="FourFont" onClick="deleteAllFunction('deleteAll')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除全部</a></div>
                <div class="search">
                  <div class="space"></div>
                  <div>
                    <div style="float:left;"> <span>栏目ID：</span> <span>
                      <input type="text" name="catalogId" id="catalogId" class="input01" value="${param.catalogId}"/>
                      </span> <span>栏目名称：</span><input type="text" name="name" id="name" class="input01" value="${param.name}"/>
                      </span></span> <span>
                      <input type="submit" name="button" id="button" value="查询" class="bt01" />
                      </span> </div>
                  </div>
                </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="td1" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead" style="width:130px;">栏目ID</td>
					  <td class="tdhead">栏目名称</td>
                      <td class="tdhead">类型</td>
                      <td class="tdhead" style="width:150px;">时间</td>
                      <td class="tdhead" style="width:200px;">是否发布子栏目</td>
					  <td class="tdhead" style="width:80px;">操作</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr style="cursor:pointer">
                        <td><input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>">
                        </td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.catalog_id}" /></td>
						  <td style="text-align:left">&nbsp;
                          <c:out value="${item.name}" /></td>
                        <td >&nbsp;
                          <c:if test="${item.type eq '0'}">
						  	固定时间发布
						  </c:if>
						  <c:if test="${item.type eq '1'}">
						  	<font color="#FF0000">间隔时间发布</font>
						  </c:if></td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.time}" /> <c:if test="${item.type eq '1'}">分钟</c:if></td>
                        <td>&nbsp;
						  <c:if test="${item.recursion eq '0'}">
						  	否
						  </c:if>
						  <c:if test="${item.recursion eq '1'}">
						  	<font color="#FF0000">是</font>
						  </c:if>
                          </td>
						  <td> <a href="#" onClick="editFunction('${item.id}')">编辑</a></td>
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