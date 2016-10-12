<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<ul class="menu">
        <c:choose>
      	 	<c:when test="${not empty subMenus }">
	       		<c:forEach var="menuItem" items="${subMenus }">
	       			<c:set var="mBarClass" value="no"></c:set>
	     				<%-- 当前子目录no和参数子目录no相同 则样式为cur --%>
						<c:if test="${param.subManageCatalogId eq  menuItem.id}">
							<c:set var="mBarClass" value="cur"></c:set>
							<c:set var="subMenu" value="${menuItem }"></c:set>
						</c:if>
	       			<c:set var="sep" value="?"></c:set>
			    	<c:if test="${fn:contains(menuItem.linkUrl, '?') }">
			    		<c:set var="sep" value="&"></c:set>
			    	</c:if>
			           <li class="${mBarClass }"><a href="${menuItem.linkUrl }${sep }manageCatalogId=${menu.id }&subManageCatalogId=${menuItem.id }"><img src="${ctxPath }/admin/images/${menuItem.smallImage }" border="0"/>${menuItem.name }</a></li>
		       	</c:forEach>
	       	</c:when>
			<c:otherwise>
				<li class="cur"><a href="#"><img src="${ctxPath }/admin/images/${menu.smallImage }" border="0"/>${menu.name }</a></li>
			</c:otherwise>
		</c:choose>
</ul>