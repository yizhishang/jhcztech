<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<script type="text/javascript">
$(document).ready(function(){	
	$("#siteno").change(function(){
		articleRoleFunction($(this).val());
	}).val('${param.siteNo}');
	
	$("#docEnterForm :checkbox").click(function(){
		var id = $(this).attr("id");
		var name = $(this).attr("name");
		var bool = $(this).attr("checked");
		//父节点全部选中
		if(bool)
		{
			var route = id.substr(4);
			var arr = route.split("|");
			//排除最后栏目
			if(arr.length > 2)
			{
				for(var i=1;i<arr.length - 1;i++)
				{
					$(":checkbox[value='"+arr[i]+"']").attr("checked",bool);
				}
			}
		}
		if(name == "id_fun")
		{
			$(":checkbox[id*='"+id+"']").attr("checked",bool);
		}
		else
		{
			bool = false;
			$(":checkbox[name='"+name+"']").each(function(){
				if($(this).attr("checked"))
				{
					bool = true;
					return false;
				}
			});
			$(":checkbox[id='"+id+"']:first").attr("checked",bool);
		}
	});
});
</script>
<style>
td img,td input { vertical-align:middle;}   
</style>
<form action="role.action" method="post" id="docEnterForm">
  <input type="hidden" name="function" value="editCatalogRoleRight" />
  <input type="hidden" name="role_id" value="${param.roleId}" />
  <div class="eb0">
    <div class="label"> <a href="#" onClick="docEnterForm.submit();"><img src="images/ico10.gif" border="0"/>保存</a> </div>
    站点：
    <select name="siteno" id="siteno">
      <c:forEach var="item" items="${data.siteList}">
        <option value="${item.siteNo}">${item.name}</option>
      </c:forEach>
    </select>
  </div>
  <div class="space"></div>
  <div class="databox">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1" id="roleTab">
      <tr>
        <td class="tdhead" style="width:50%;">${form.name}(${form.roleNo})拥有的文档权限</td>
        <td class="tdhead" colspan="7">权限属性</td>
      </tr>
      <c:forEach var="item" items="${data.dataList}" varStatus="status">
        <tr class="${(empty item.isLastTree)?'trlight':''}">
          <td class="td3" style="text-align:left"><c:forEach begin="2" end="${item.rownum}"><img src="images/I.png"/></c:forEach><img src="images/T.png"/> <input type="checkbox" name="id_fun" id="chk_${item.route}" value="<c:out value='${item.catalog_id}'/>" 
            
            <c:if test="${item.show eq '1'}"> checked="checked"</c:if>
            >
            <c:out value="${item.name}" /></td>
          <c:choose>
            <c:when test="${not empty item.isLastTree and item.isLastTree eq 'true'}">
              <td><input type="checkbox" name="chk_${item.catalog_id}" id="chk_${item.route}" value="add" 
                  
                
                <c:if test="${item.add eq '1'}"> checked="checked"</c:if>
                >添加</td>
              <td><input type="checkbox" name="chk_${item.catalog_id}" id="chk_${item.route}" value="delete"  
                  
                
                <c:if test="${item.delete eq '1'}"> checked="checked"</c:if>
                >删除</td>
              <td><input type="checkbox" name="chk_${item.catalog_id}" id="chk_${item.route}" value="publish" 
                  
                
                <c:if test="${item.publish eq '1'}"> checked="checked"</c:if>
                >发布</td>
              <td><input type="checkbox" name="chk_${item.catalog_id}" id="chk_${item.route}" value="reject" 
                  
                
                <c:if test="${item.reject eq '1'}"> checked="checked"</c:if>
                >驳回</td>
              <td><input type="checkbox" name="chk_${item.catalog_id}" id="chk_${item.route}" value="hot" 
                  
                
                <c:if test="${item.hot eq '1'}"> checked="checked"</c:if>
                >推荐</td>
              <td><input type="checkbox" name="chk_${item.catalog_id}" id="chk_${item.route}" value="head" 
                  
                
                <c:if test="${item.head eq '1'}"> checked="checked"</c:if>
                >置顶</td>
              <td><input type="checkbox" name="chk_${item.catalog_id}" id="chk_${item.route}" value="copy" 
                  
                
                <c:if test="${item.copy eq '1'}"> checked="checked"</c:if>
                >复制</td>
            </c:when>
            <c:otherwise>
              <td colspan="7">&nbsp;</td>
            </c:otherwise>
          </c:choose>
        </tr>
      </c:forEach>
    </table>
  </div>
  <div class="space"></div>
  <div class="eb0">
    <div class="label"> <a href="javascript:docEnterForm.submit();"><img src="images/ico10.gif" border="0"/>保存</a> </div>
  </div>
</form>
