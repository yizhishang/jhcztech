<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<jsp:include page="/WEB-INF/views/catalog/include/menu.jsp" flush="true">
<jsp:param name="type" value="1"/></jsp:include>
<form action="attachPublish.action" method="post" id="attachForm">
  <input type="hidden" name="catalogId" value="${param.catalogId}"/>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="editortable" id="tab2">
    <tr>
      <td colspan="2"><div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
          <div class="treebox">
            <ul class="tree" style="margin-left: 15px;">
              ${data.tree}
            </ul>
          </div>
        </div></td>
    </tr>
    <tr>
      <td class="label">&nbsp;</td>
      <td><input type="submit" id="attachSubId" value="提交" class="bt04"/></td>
    </tr>
  </table>
</form>
