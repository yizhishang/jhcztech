<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<script type="text/javascript">
</script>
<style>
td img,td input { vertical-align:middle;}   
</style>
<div class="space"></div>
<div class="databox">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1" id="roleTab">
    <c:forEach var="item" items="${data.dataList}" varStatus="status">
      <tr class="${(empty item.isLastTree)?'trlight':''}">
        <td class="td3" style="text-align:left"><c:forEach begin="2" end="${item.rownum}"><img src="/admin/images/I.png"/></c:forEach>
          <img src="/admin/images/T.png"/> <input type="checkbox" name="chooseCopyCatalog" id="chk_${item.route}" value="<c:out value='${item.catalog_id}'/>" 
            
            
            
          <c:if test="${item.isLastTree ne true}">  disabled="disabled"</c:if>
          >
          <c:out value="${item.name}" /></td>
        <td colspan="7">&nbsp;</td>
      </tr>
    </c:forEach>
  </table>
</div>
