<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<%@ include file="/admin/common/meta.jsp" %>
<script type="text/javascript">
	function addFunction()
	{
		<c:choose>
		<c:when test="${param.function == 'add'}">
			if(confirm("文章尚未保存，确认新建文章？"))
			{
				location.href = "article.action?function=add&catalogId=<c:out value='${form.catalogId}'/>";
			}
		</c:when>
		<c:otherwise>
			location.href = "article.action?function=add&catalogId=<c:out value='${form.catalogId}'/>";
		</c:otherwise>
		</c:choose>
	}
	
	function saveFunction()
	{	
		<c:if test="${param.function == 'add'}">
		$("#state").val("0");
		</c:if>
		$("#enterForm").submit();
	}
	
	function publishFunction()
	{
		$("#state").val("3");
		$("#enterForm").submit();
	}
	
	function deleteFunction()
	{
		<c:if test="${param.function == 'add'}">
			alert("文章尚未保存，不能删除！");
			return;
		</c:if>
		if(confirm("是否确定删除？"))
		{
			$("#function").val("deleteById");
			$("#enterForm").submit();
		}
		
	}
	
	function closeFunction()
	{
		window.close();
	}
</script>

<div class="space"></div>
<div class="label"> <a href="#" onClick="javascript:addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a> <a href="#" onClick="javascript:saveFunction();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>保存</a> <a href="#" onClick="javascript:publishFunction();"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>发布</a> <%--<a href="#"><img src="${ctxPath }/admin/images/ico12.gif" border="0"/>复制</a> <a href="#"><img src="${ctxPath }/admin/images/ico13.gif" border="0"/>转移</a> <a href="#" onClick="javascript:deleteFunction();"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> --%><a href="#" onClick="javascript:closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div>
