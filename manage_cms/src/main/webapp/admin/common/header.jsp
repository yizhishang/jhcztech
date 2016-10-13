<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>jhczCMS后台管理系统</title>
    <%@ include file="/admin/common/meta.jsp" %>
    <link href="<%=request.getContextPath()%>/admin/styles/cms.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/ajax.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/jquery.wresize.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/jquery.form.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/form_commons.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/formValidator.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/formValidatorRegex.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/datepicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/layer/layer.js"></script>
</head>
<%
	//这是主要包装了pageUrl
	String queryStr= request.getQueryString() ;
	StringBuffer pageUrl=new StringBuffer();
 	if(queryStr!=null&&queryStr.length()>0){
 		String[] queryArr=queryStr.split("&");
 		for(int i=0;i<queryArr.length;i++){
 			if(queryArr[i].startsWith("function=")||queryArr[i].startsWith("pageUrl=")||queryArr[i].startsWith("form.pageUrl=")){
 				continue;
 			}
 			pageUrl.append(queryArr[i]);
 			if(i!=queryArr.length-1){
 				pageUrl.append("&");
 			}
 		}
 		request.setAttribute("pageUrl",pageUrl);
 	}
%>
