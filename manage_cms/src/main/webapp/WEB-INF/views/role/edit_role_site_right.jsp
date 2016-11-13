<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@page import="com.yizhishang.base.util.RequestHelper"%>
<%@ include file="/admin/common/header.jsp" %>
<body>
<form id="qryparm" name="qryparm" action="editSiteRoleRight.action" method="post">
  <input type="hidden" name="function" value="${param.function }"/>
  <input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"/>
  <input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"/>
  <input type="hidden" name="roleId" value="<%=RequestHelper.getString(request,"roleId") %>">
  <input type="hidden" name="userIdStr" id="userIdStr" value=""/>
  <input type="hidden" name="successPage" value="role.action?function=editSiteRoleRight&roleId=<%=RequestHelper.getString(request,"roleId") %>"/>
  <div class="c_ie6_out">
    <div class="c_ie6_in">
      <div class="Wrapper">
        <div class="inner">
          <div class="innercontent">
            <div class="cl"></div>
            <div class="contentbox">
              <div class="content">
                <jsp:include page="/WEB-INF/views/role/include/menu.jsp" flush="true">
					<jsp:param name="index" value="1"/>
					<jsp:param name="roleId" value="${roleId}"/>
                    <jsp:param name="siteNo" value="${siteNo}"/>
				</jsp:include>
                <div class="space"></div>
                <div id="content">
                  <div class="eb0">
                    <div class="label"> <a href="#" onClick="submitForm('qryparm', null, true);"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>保存</a> </div>
                  </div>
                  <div class="space"></div>
                  <div class="databox">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1" id="roleTab">
                      <tr>
                        <td class="tdhead" style="text-align:left">
                        ${form.name}(${form.roleNo})拥有的站点权限
                        </td>
                      </tr>
                      <c:forEach var="item" items="${data.dataList}" varStatus="status">
                        <tr>
                          <td class="td3" style="text-align:left"><img src="${ctxPath }/admin/images/T.png"/> <input type="checkbox" name="id_fun"  value="<c:out value='${item.siteNo}'/>" 
                            <c:if test="${item.show eq '1'}"> checked="checked"</c:if>
                            >
                            <c:out value="${item.name}" /></td>
                        </tr>
                      </c:forEach>
                    </table>
                  </div>
                </div>
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