<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="author" content="" />
<c:set var="ctxPath" value="${pageContext.request.contextPath}" scope="request" />
<%
    response.setHeader("pragma", "no-cache");
    response.setHeader("cache-control", "no-cache");
    response.setDateHeader("expires", -10);
    response.setContentType("text/html;charset=UTF-8");
%>