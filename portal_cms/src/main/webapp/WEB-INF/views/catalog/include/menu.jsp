<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<link href="<%=request.getContextPath()%>/admin/styles/cms.css" rel="stylesheet" type="text/css" />
<link href="/admin/styles/checktree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function attachPublish()
	{
		var jsonVal={catalogId:<c:out value='${param.catalogId}'/>};
		$("#content").loadData("doAttachPublish.action",jsonVal);
	}
	function seo()
	{
		var jsonVal={catalogId:<c:out value='${param.catalogId}'/>};
		$("#content").loadData("doSeo.action",jsonVal);
	}
</script>
<div class="space"></div>
<div class="eb0">
  <ul class="menu">
    <li class="${(param.type eq '0')?'cur':'no'}"><a href="doEdit.action?catalogId=<c:out value='${param.catalogId}'/>" title="栏目属性"><img src="${ctxPath }/admin/images/ico16.gif" border="0"/>栏目属性</a></li>
    <li class="${(param.type eq '1')?'cur':'no'}"><a href="#" onClick="attachPublish();" title="附带发布"><img src="${ctxPath }/admin/images/ico17.gif" border="0"/>附带发布</a></li>
	<li class="${(param.type eq '2')?'cur':'no'}"><a href="#" onClick="seo();" title="SEO设置"><img src="${ctxPath }/admin/images/ico17.gif" border="0"/>SEO设置</a></li>
    <li class="${(param.type eq '3')?'cur':'no'}"><a href="doListField.action?catalogId=${param.catalogId}" title="文档自定义字段"><img src="${ctxPath }/admin/images/ico17.gif" border="0"/>自定义字段</a></li>
  </ul>
</div>
