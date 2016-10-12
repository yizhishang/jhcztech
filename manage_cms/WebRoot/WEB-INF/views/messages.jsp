<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<c:choose>
	<c:when test="${not empty actionErrors}">
		<c:forEach var="error" items="${actionErrors}">
           <script type="text/javascript">
		   		alert('<c:out value="${error}" />');
		   </script>
        </c:forEach>
	</c:when>
	<c:otherwise>
		<!--成功 弹出成功消息-->
		<c:if test="${not empty actionMessages}">
			<c:forEach var="message" items="${actionMessages}">
			   <script type="text/javascript">
					alert('<c:out value="${message}" />');
			   </script>
			</c:forEach>
		</c:if>
		
		<script type="text/javascript">
			//判断当前窗口是否为模式窗口，模式窗口在弹出时URL中追加过isModelDialog
			var parentUrl = parent.location.href;
			if(parentUrl.lastIndexOf("isModelDialog") != -1)
			{
				//alert("模式窗口");
				var arr = new Array(1);
				arr[0] = "success";
				parent.window.returnValue = arr;
				parent.window.close();
			}
			else
			{
				//alert("非模式窗口");
				var url = parent.window.opener.location.href;
				if(url.lastIndexOf("#") != -1)
				{
					url = url.substring(0,url.lastIndexOf("#"));
				}
				parent.window.opener.location.href = url;
				parent.window.close();
			}
		</script>
	</c:otherwise>
</c:choose>
<c:remove var="actionErrors" scope="request" />