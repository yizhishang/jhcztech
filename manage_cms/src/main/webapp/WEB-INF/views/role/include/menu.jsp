<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.jhcz.base.util.RequestHelper"%>
<%@ include file="/admin/common/taglibs.jsp" %>
<div class="space"></div>
<div class="eb0">
  <ul class="menu">
    <li class="${(param.index eq 0)?'cur':'no'}"><a href="doList.action?roleId=${param.roleId}"><img src="${ctxPath }/admin/images/ico16.gif" border="0"/>基本信息</a></li>
    <li class="${(param.index eq 1)?'cur':'no'}"><a href="doEditSiteRoleRight.action?roleId=${param.roleId}"><img src="${ctxPath }/admin/images/ico17.gif" border="0"/>站点权限</a></li>
    <li class="${(param.index eq 2)?'cur':'no'}"><a href="doEditRoleRight.action?roleId=<%=RequestHelper.getString(request,"roleId") %>&siteNo=<%=RequestHelper.getString(request,"siteNo") %>"><img src="${ctxPath }/admin/images/ico17.gif" border="0"/>菜单权限</a></li>
    <li class="${(param.index eq 3)?'cur':'no'}"><a href="doEditArticleRoleRight.action?roleId=<%=RequestHelper.getString(request,"roleId") %>&siteNo=<%=RequestHelper.getString(request,"siteNo") %>"><img src="${ctxPath }/admin/images/ico17.gif" border="0"/>文档权限</a></li>
  </ul>
</div>