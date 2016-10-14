<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<form id="qryparm" name="qryparm" action="list.action" >
<input type="hidden" name="form.pageUrl" value="${pageUrl}"></input>
<input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"></input>
<input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"></input>
<input type="hidden" name="successPage" value="list.action?type=<c:out value='${param.type}'/>">
  <div class="c_ie6_out">
    <div class="c_ie6_in">
      <div class="Wrapper">
        <div class="inner">
          <div class="innercontent">
            <div class="cl"></div>
            <div class="contentbox">
              <div class="content">
                <!-- 包含导航代码  -->
                <div class="title">
				  <p><img src="${ctxPath }/admin/images/ico07.gif" />当前位置：系统管理<img src="${ctxPath }/admin/images/pic14.gif" />字典类型<img src="${ctxPath }/admin/images/pic14.gif" /><c:out value='${param.type}'/></p>  
				</div>
                <div class="label">
                	<a href="#" onClick="addFunction('type=${param.type}','doAddItem.action');"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a>
                	<a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
                	<a href="#" onClick="location.reload();"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>刷新</a>
                </div>  
                <div class="search">
                  <div class="space"></div>
                  <%-- <div>
                    <div style="float:left;"> <span>类型：
                      <select name="type">
                        <option selected value="">--全部--</option>
                        <c:forEach var="item" items="${data.typeList}">
                          <option value="<c:out value='${item.enum_value}'/>">
                          <c:out value='${item.enum_name}'/>
                          </option>
                        </c:forEach>
                      </select>
                      </span> <span>
					  <script language="javascript">setSelectSelected("type", "<c:out value='${param.type}'/>")</script>
                      <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                      </span> </div>
                  </div> --%>
                </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="td1" style="width:45px"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead">类型值</td>
                      <td class="tdhead">名称</td>
                      <td class="tdhead">具体值</td>
                      <td class="tdhead">排序值</td>
                      <td class="tdhead"  style="width:100px">&nbsp;&nbsp;操作&nbsp;&nbsp;</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr style="cursor:pointer" onDblClick="javascript:editFunction('<c:out value='${item.item_code}'/>')" title="双击查看详细内容">
                        <td>
						<c:if test="${item.is_system eq '0'}">
						<input type="checkbox" name="id" id="check_<c:out value='${item.item_code}'/>" value="<c:out value='${item.item_code}'/>" onClick="setCheck(this)">
						</c:if>
						<c:if test="${item.is_system eq '1'}">
						&nbsp;
						</c:if>
                        </td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.enum_value}" /></td>
                        <td style="text-align:left" >&nbsp;
                          <c:out value="${item.item_name}" /></td>
                        <td >&nbsp;
                          <c:out value="${item.item_value}" /></td>
                        <td>&nbsp;
                          <c:out value="${item.orderline}" /></td>
                        <td>&nbsp;<a href="#" onClick="editFunction('id=<c:out value='${item.item_code}'/>','doEditItem.action')">编辑</a></td>
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