<%@ page language="java" pageEncoding="UTF-8"  %>
<%@ taglib uri="/tags/jstl-core" prefix="c" %>
<%@ taglib uri="/tags/jstl-format" prefix="fmt" %>
<%@ taglib uri="/tags/jstl-function" prefix="fn" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="uri" value="${pageContext.request.requestURI}" scope="request" />
<jsp:useBean id="Constants" class="com.jhcz.plat.Constants"></jsp:useBean>