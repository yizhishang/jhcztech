<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.opensymphony.xwork.ActionContext"%>

<%@ page import="com.opensymphony.xwork.util.OgnlValueStack"%>
<%@ include file="/admin/common/header.jsp" %>
系统出错了......
<%   
    OgnlValueStack s = ActionContext.getContext().getValueStack();
    Object obj = s.pop();
    System.out.println(obj.getClass().getName());
    obj = s.pop();
    System.out.println(obj.getClass().getName());
%>
<a href="javascript:window.history.go(-1);">返回</a>