<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<c:choose>
	<c:when test="${not empty actionMessages}">
		<!--成功-->
		<c:forEach var="message" items="${actionMessages}">
           <script type="text/javascript">
		   		
				parent.window.opener.location.href = "article.action?function=list&catalogId=${form.catalogId}";
				//parent.window.opener.location.href = parent.window.opener.location.href;
				<c:choose>
				<c:when test="${not empty param.isPreview and  param.isPreview == '1'}">
				parent.window.open('article.action?function=preview&articleId=${form.id}');
				parent.location.href="article.action?function=edit&id=${form.id}&catalogId=${form.catalogId}";
				</c:when>
				<c:otherwise>
		   		alert('<c:out value="${message}" />');
				parent.location.href="article.action?function=add&catalogId=${form.catalogId}";
				</c:otherwise>
				</c:choose>
				
				
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
<c:remove var="actionErrors" scope="request" />