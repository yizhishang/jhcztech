<%-- ActionError Messages - usually set in Actions --%>
<c:if test="${not empty actionErrors}">
     <c:forEach var="error" items="${actionErrors}">
        <script type="text/javascript">
				alert('<c:out value="${error}" />');
		</script>
     </c:forEach>
    <c:remove var="actionErrors" scope="request" />
</c:if>
