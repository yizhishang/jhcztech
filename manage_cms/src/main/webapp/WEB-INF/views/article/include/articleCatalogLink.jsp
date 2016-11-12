<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp" %>
<%@ include file="/admin/common/meta.jsp" %>
<jsp:directive.page import="com.yizhishang.base.util.RequestHelper" />
<jsp:directive.page import="com.yizhishang.base.util.ScriptHelper"/>
<jsp:directive.page import="com.yizhishang.base.util.StringHelper"/>
<jsp:directive.page import="com.yizhishang.base.util.SpringContextHolder"/>
<jsp:directive.page import="com.yizhishang.plat.service.CatalogService" />
<jsp:directive.page import="com.yizhishang.plat.domain.Catalog" />
<jsp:directive.page import="java.util.LinkedList"/>
<%
	int catalogId = RequestHelper.getInt(request, "catalogId");
	if (catalogId > 0)
	{
		CatalogService catalogService = SpringContextHolder.getBean("catalogService");
		Catalog catalog = catalogService.findCatalogById(catalogId);
		if(catalog == null || "".equals(catalog.getRoute()))
		{
			ScriptHelper.alert(response,"栏目不存在，请勿非法操作","close");
			return;
		}
		String route = catalog.getRoute();
		String[] routes = route.split("\\|");
		LinkedList navCatalogs = new LinkedList();
		String temp = "";
		for (int i = 0; i < routes.length; i++)
		{
			temp += routes[i] + "---";
			Catalog paramCatalog = catalogService.findCatalogById(Integer.parseInt(routes[i]));
			if (paramCatalog != null)
			{
				navCatalogs.add(paramCatalog);
			}
		}
		request.setAttribute("navCatalogs", navCatalogs);
		request.setAttribute("catalogId", Integer.valueOf(catalogId));
	}
%>

<div class="title">
  <p><img src="${ctxPath }/admin/images/ico07.gif" />当前位置:
  	<c:forEach items="${navCatalogs}" var="catalogItem">
		${catalogItem.name}&nbsp;<img src="${ctxPath }/admin/images/pic14.gif" />
	</c:forEach>
	${param.catalogName}
  </p>  
</div>
