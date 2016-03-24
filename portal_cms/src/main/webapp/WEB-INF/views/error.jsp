<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<link href="/admin/styles/cms.css" rel="stylesheet" type="text/css" />
<br/>
<div>
  <table width="100%" height="90%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td align="center" valign="middle"><table width="80%" height="30" border="1" align="center" cellspacing="0" cellpadding="0" bordercolorlight="#C7D1E5" bordercolordark="#FAF8F6">
          <tr class="DetailTableHeader">
            <td align="center">系统信息</td>
          </tr>
          <tr class="DetailTableBody">
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="2">
                      <tr colspan="2">
                        <td align="center"></td>
                      </tr>
                      <tr align="center">
                        <td height="39" align="center" valign="middle" nowrap><c:choose>
                            <c:when test="${not empty data.message}"> <span class="DetailTagText"><c:out value="${data.message}" escapeXml="false"/></span> </c:when>
                            <c:otherwise> <span class="DetailTagText">对不起，系统出错，请与系统管理员联系 </span></c:otherwise>
                          </c:choose>
                        </td>
                      </tr>
                      <tr>
                        <td align="left" nowrap><br>
                          <pre><c:out value="${throwMessage}" /></pre>
                        </td>
                      </tr>
                      <%--<tr>
                          <td colspan="2" align="center" valign="bottom" nowrap>
                              <input type="submit" name="Button" onclick="history.back(-1)" value="返  回" class="Button">
                          </td>
                      </tr>--%>
                    </table></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</div>
<div style="width:100%;">${ex }</div> 