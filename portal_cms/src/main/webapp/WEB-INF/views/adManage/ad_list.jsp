<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<form id="qryparm" name="qryparm" action="default.action" >
  	<input type="hidden" name="function" value="${param.function }" />
	<input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"></input>
	<input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"></input>
  	<input type="hidden" name="form.pageUrl" value="${pageUrl}"></input>
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
                	<a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>添加</a>
                	<a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a></div>
                <div class="search">
                  <div class="space"></div>
                  <div>
                      <div style="float:left;">
	                      <span>广告组：
		                      <select name="type">
		                        <option value="">--请选择--</option>
		                        <c:forEach var="item" items="${data.groupList}">
		                          <option value="${item.id}">${item.name}</option>
		                        </c:forEach>
		                      </select>
						  	<script language="javascript">setSelectSelected("type", "${param.type}")</script>
	                      </span>
	                      <span>&nbsp;关键字：<input type="text" name="keyword" value="${param.keyword}" class="input01"/></span>
	                      <span><input type="submit" name="button" id="button" value="查询"  class="bt01" /></span>
                      </div>
                  </div>
                </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="td1" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead" >广告名称</td>
                      <td class="tdhead">所属组</td>
                      <td class="tdhead">宽度</td>
                      <td class="tdhead" >高度</td>
                      <td class="tdhead">开始时间</td>
                      <td class="tdhead" >结束时间</td>
                      <td class="tdhead">排序值</td>
                      <td class="tdhead" >链接状态</td>
                      <td class="tdhead">展示状态</td>
                      <td class="tdhead" width="60">&nbsp;&nbsp;操作&nbsp;&nbsp;</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr>
                        <td><input type="checkbox" name="id" id="check_<c:out value='${item.ad_id}'/>" value="<c:out value='${item.ad_id}'/>" onClick="setCheck(this)">
                        </td>
                        <td style="text-align:center">&nbsp;
                          <c:out value="${item.name}" /></td>
                        <td>&nbsp;
                          <c:forEach var="group" items="${data.groupList}">
	                          <c:if test="${group.id == item.group_no }">${group.name }</c:if>
	                      </c:forEach>
                        <td><c:out value="${item.width}" /></td>
                        <td><c:out value="${item.height}" /></td>
                        <td><c:out value="${item.start_time}" /></td>
                        <td><c:out value="${item.end_time}" /></td>
                        <td><c:out value="${item.orderline}" /></td>
                        <td>&nbsp;
                          <c:if test="${item.state == 0}"> <font color="#FF0000">无效</font> </c:if>
                          <c:if test="${item.state == 1}"> 有效 </c:if></td>
                        <td>&nbsp;
                          <c:if test="${item.file_state == 0}"> <font color="#FF0000">无效</font> </c:if>
                          <c:if test="${item.file_state == 1}"> 有效 </c:if></td>
                        <td>&nbsp;<a href="#" onClick="editFunction('id=<c:out value='${item.ad_id}'/>')">编辑</a>&nbsp;</td>
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
<!--添加角色开始-->
<form id="addForm" action="role.action" method="post" target="">
  <input type="hidden" name="function" value="add"/>
  <input type="hidden" name="form.roleNo" id="roleNo"/>
  <input type="hidden" name="form.name" id="name"/>
</form>
<!--添加角色结束-->
</body>
</html>