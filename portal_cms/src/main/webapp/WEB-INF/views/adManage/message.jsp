<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<c:choose>
	<c:when test="${not empty actionMessages}">
		<!--成功-->
		<c:forEach var="message" items="${actionMessages}">
           <script type="text/javascript">
		   		alert('<c:out value="${message}" />');
				parent.window.opener.location.href = parent.window.opener.location.href;
				parent.window.close();
		   </script>
        </c:forEach>
	</c:when>
	<c:otherwise>
		<!--失败-->
		<c:forEach var="error" items="${actionErrors}">
           <script type="text/javascript">
		   		alert('<c:out value="${error}" />');
		   </script>
        </c:forEach>
	</c:otherwise>
</c:choose>