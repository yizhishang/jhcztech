<%-- ActionError Messages - usually set in Actions --%>
<c:if test="${not empty actionErrors}">
    <table border="0" width="100%">
        <c:forEach var="error" items="${actionErrors}">
            <tr>
                <td align="center"><b><font color="#FFFFFF"><c:out value="${error}" /></font></b></td>
            </tr>
        </c:forEach>
    </table>
    <c:remove var="actionErrors" scope="request" />
</c:if>
