<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<form id="qryparm" name="qryparm" action="doDefault.action" >
  <input type="hidden" name="function" value="${param.function }" />
  <input type="hidden" name="form.pageUrl" value="${pageUrl}" />
  <input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }" />
  <input type="hidden" name="state" value=""/>
  <input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }" />
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
                	<a href="#" onClick="addFunction(null, null,'600px','350px');"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a>
                	<a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
                	<a href="#" onClick="editStateFunction('id','editState',1);"><img src="${ctxPath }/admin/images/ico02.gif" border="0"/>有效</a>
                	<a href="#" onClick="editStateFunction('id','editState',0);"><img src="${ctxPath }/admin/images/import/ico003.png" border="0"/>无效</a> </div>
                <div class="search">
                  <div class="space"></div>
                  <div>
                    <div style="float:left;"> <span>关键字：
                      <input type="text" name="keyword" value="${param.keyword}" class="input01"/>
                      </span> <span>
                      <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                      </span> </div>
                  </div>
                </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="tdhead" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead" style="width:200px;">变量名称</td>
                      <td class="tdhead">变量描述</td>
                      <td class="tdhead" style="width:100px;">状态</td>
                      <td class="tdhead" style="width:120px;">创建人</td>
                      <td class="tdhead" style="width:200px;">创建时间</td>
                      <td class="tdhead" style="width:100px;">&nbsp;&nbsp;操作&nbsp;&nbsp;</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr style="cursor:pointer" onDblClick="javascript:editFunction('id=${item.id}')" title="双击查看详细内容">
                        <td><input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)">
                        </td>
                        <td style="text-align:left"  class="td3">&nbsp;
                          <c:out value="${item.item_name}" /></td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.description}" /></td>
                        <td><c:if test="${item.state == 0}"><font color="#ff0000">无效</font></c:if>
                          <c:if test="${item.state == 1}">有效</c:if></td>
                        <td style="text-align:left" ><c:out value="${item.create_by}"/></td>
                        <td>&nbsp;
                          <c:out value="${item.create_date}"/>
                        </td>
                        <td >&nbsp;<a href="#" onClick="editFunction('id=${item.id}')">编辑</a></td>
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