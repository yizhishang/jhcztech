<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#seoForm").submit(function(){
		var title = $("#title").val();
		if($.trim(title).length == 0)
		{
			alert("页面标题不能为空！");
			$("#title").focus();
			return false;
		}
		return true;
	});
});
</script>
<jsp:include page="${ctxPath }/WEB-INF/views/catalog/include/menu.jsp" flush="true">
<jsp:param name="type" value="2"/>
</jsp:include>
<form action="seo.action" method="post" id="seoForm">
  <input type="hidden" name="catalogId" value="${param.catalogId}"/>
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="editortable" id="tab1">
          <tr>
            <td colspan="2" style="height:8px;"></td>
          </tr>
          <tr>
            <td width="140" class="label">页面标题：</td>
            <td><input type="text" name="form.title" id="title" class="input02" value="<c:out value='${form.title}'/>"/> <font color="#FF0000">*</font> </td>
          </tr>
          <tr>
            <td class="label">meta关键字：</td>
            <td><textarea class="input02" name="form.keywords"  style="width:350px;height:70px;"><c:out value='${form.keywords}'/></textarea></td>
          </tr>
         <tr>
            <td class="label">meta描述：</td>
            <td><textarea class="input02" name="form.description"  style="width:350px;height:70px;"><c:out value='${form.description}'/></textarea></td>
          </tr>
          <tr>
            <td class="label">&nbsp;</td>
            <td><input type="submit" name="button" id="button" value="保存" class="bt04"/>    </td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
