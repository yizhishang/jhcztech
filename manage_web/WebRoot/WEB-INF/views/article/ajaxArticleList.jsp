<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/include/taglibs.jsp"%>
<c:choose>
	<c:when test="${not empty page.data}">
	<c:forEach var="item" items="${page.data}" varStatus="status">
		<li class="news_list_table">
			<div class="news_list_table_jpg">
				<a href="${item.url }" target="_blank"><img src="/component/app/images/news/picture${status.index + 1}.jpg" alt=""></a>
				<div class="news_title">${item.title }</div>
				<div class="news_date">${fn:substring(item.publish_date,0,10) }</div>
				<div class="news_profile">
				<c:choose>
					<c:when test="${fn:length(item.brief) > 60}">
						${fn:substring(item.brief,0,60) }...
					</c:when>
					<c:otherwise>
						${item.brief }
					</c:otherwise>
				</c:choose>
				</div>
				<a class="news_selectDetail" href="${item.url}" target="_blank">查看详情<div class="news_iconImg"></div></a>
			</div>
		</li>
		<c:if test="${status.index + 1 < fn:length(page.data) }">
		<div class="news_borderline"></div>
		</c:if>
	</c:forEach>
	</c:when>
	<c:otherwise>
			暂无数据!
	</c:otherwise>
</c:choose>
