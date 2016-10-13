<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
$(document).ready(function(){ 
	resetArticleWidth();
});

$(window).resize(function(){
	resetArticleWidth();
});

function resetArticleWidth()
{
	var w = $(".databox2").width() - 18;
	w=(w<884)?884:w;
    $(".tab1").width(w);
}

/**
	搜索文章
 */
function searchFunction()
{
	$("#qryparm").submit();
}

/**
	复制文章
 */
function copyFunction(name,type)
{
	if(isChecked(name)){
		var sCatalogs = openDialog("article.action?function=copyArticle", 500, 800);
    	if(sCatalogs)
    	{
	    	var arrStr = sCatalogs.split(";");
			
	    	var isPublish = '';
	    	sCatalogs=arrStr[0];
	    	isPublish = arrStr[1];
	    	if(isPublish == null || 'undefined' == isPublish)
	    	{
	    		isPublish = '0';
	    	}
			jQuery('#copyOrRemove').val(type);
	    	jQuery('#isPublish').val(isPublish);
	    	jQuery('#catalogs').val(sCatalogs);
			$("#qryparm").attr("method","POST");
	        jQuery("input[name='function']").val("copyArticle");
	        $("#qryparm").submit();
    	}
		
	}else{
		alert("请选择需要复制的数据！");
	}
}
</script>
<form id="qryparm" name="qryparm" action="doList.action" method="post">
  <input type="hidden" name="function" value="${param.function }" />
  <input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }" />
  <input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }" />
  <input type="hidden" name="catalogId" value="${param.catalogId}"/>
  <input type="hidden" name="createDate" id="createDate" value="${param.createDate}" />
  <input type="hidden" name="isPublish" id="isPublish" value=""/>
  <input type="hidden" name="catalogs" id="catalogs" value=""/>
  <input type="hidden" name="copyOrRemove" id="copyOrRemove" value=""/>
  <input type="hidden" name="successPage" value="doList.action?catalogId=<c:out value='${param.catalogId}'/>">
  <div class="c_ie6_out">
  <div class="c_ie6_in">
    <div class="Wrapper">
      <div class="inner">
        <div class="innercontent">
          <div class="cl"></div>
          <div class="contentbox">
            <div class="content">
              <!-- 包含导航代码  -->
              <jsp:include flush="true" page="/WEB-INF/views/article/include/articleCatalogLink.jsp">
              <jsp:param name="catalogName" value="显示文章"/>
              </jsp:include>
              <div class="label">
              	<c:if test="${catalogRights.all eq '1' or catalogRights.add eq '1'}">
              		<a href="#" onClick="addFunction('catalogId=${param.catalogId}');"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a>
              	</c:if>
              	<c:if test="${catalogRights.all eq '1' or catalogRights.publish eq '1'}">
              		<a href="#" onClick="manageFunction('id','publish','发布');"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>发布</a>
              	</c:if>
              	<c:if test="${catalogRights.all eq '1' or catalogRights.reject eq '1'}">
              		<a href="#" onClick="manageFunction('id','reject','驳回')"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>驳回</a>
              	</c:if>
              	<c:if test="${catalogRights.all eq '1' or catalogRights.hot eq '1'}">
              		<a href="#" onClick="manageFunction('id','setHot','推荐')"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>推荐</a>
              		<a href="#" onClick="manageFunction('id','cancelHot','取消推荐')" class="FourFont"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>取消推荐</a>
              	</c:if>
				<c:if test="${catalogRights.all eq '1' or catalogRights.head eq '1'}"><a href="#" onClick="manageFunction('id','setHead','置顶')"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>置顶</a>
					<a href="#" onClick="manageFunction('id','cancelHead','取消置顶')" class="FourFont"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>取消置顶</a>
				</c:if>
				<c:if test="${catalogRights.all eq '1' or catalogRights.copy eq '1'}"><a href="#" onClick="copyFunction('id','0')"><img src="${ctxPath }/admin/images/ico12.gif" border="0"/>复制</a>
					<a href="#" onClick="copyFunction('id','1')"><img src="${ctxPath }/admin/images/ico13.gif" border="0"/>转移</a>
				</c:if>
				<c:if test="${catalogRights.all eq '1' or catalogRights.delete eq '1'}">
					<a href="#" onClick="deleteFunction('id','delete');"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
				</c:if>
			  </div>
              <div class="search">
                <div class="space"></div>
                <div>
                  <div style="float:left;">
                  	<span>录入时间：</span>
                  	<span><input type="text" name="startDate" id="startDate" class="input01" value="${param.startDate}" readonly/></span>
                    <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'startDate'})"/></span>
                    <span>到</span>
                    <span><input type="text" name="endDate" id="endDate" class="input01" value="${param.endDate}" readonly/></span>
                    <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'endDate'})"/></span>
                    <span>&nbsp;&nbsp;关键词：</span>
                    <span><input type="text" name="title" id="title" class="input01" value="${param.title}"/></span>
                    <span><input type="button" name="button" id="button" value="查询" onClick="searchFunction();" class="bt01" /></span>
                  </div>
                  <div style="float:right; width:100px; text-align:right;">
                    <!-- <input type="checkbox" name="checkbox" id="checkbox"/>高级搜索 -->
                  </div>
                </div>
                <div class="space"></div>
                <div class="databox2">
                  <table width="970" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="td1"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead" width="320">标题</td>
                      <td class="tdhead">创建者<img src="${ctxPath }/admin/images/ico25.gif" width="16" height="16" /></td>
                      <td class="tdhead" width="175">录入时间<img src="${ctxPath }/admin/images/ico26.gif" width="16" height="16" /></td>
                      <td class="tdhead">类型</td>
                      <td class="tdhead">状态</td>
                      <td class="tdhead">操作</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr style="cursor:pointer" onDblClick="javascript:editFunction('id=${item.id}&catalogId=${item.catalogId}');" title="双击编辑文章">
                        <td><input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)"></td>
                        <td class="td3" style="text-align:left" title="${item.title}">&nbsp;
                          <c:if test="${item.isHot == 1}"><font color="red">[荐]</font></c:if>
						  <c:if test="${item.isHead == 1}"><font color="red">[顶]</font></c:if>
						  <c:if test="${item.isPicture == 1}"><font color="red">[图片]</font></c:if>
                          <c:choose>
						  	<c:when test="${fn:length(item.title) > 40}">
								<c:out value="${fn:substring(item.title,0,40)}"/>...
							</c:when>
							<c:otherwise>
							<c:out value="${item.title}"/>
							</c:otherwise>
						  </c:choose></td>
                        <td style="text-align:left"><c:out value="${item.createBy}" /></td>
                        <td><c:out value="${item.createDate}" /></td>
                        <td><c:if test="${item.type == 3}">详见附件</c:if>
                          <c:if test="${item.type == 0}">普通</c:if>
                          <c:if test="${item.type == 1}">链接</c:if>
                          <c:if test="${item.type == 2}">下载</c:if>
                        </td>
                        <td><c:if test="${item.state == 0}">未发布</c:if>
                          <c:if test="${item.state == 3}"><font color=#ff0000>已发布</font></c:if>
                        </td>
                        <td><a href="javascript:editFunction('id=${item.id}&catalogId=${item.catalogId}');">编辑</a>
                        |
                        <a href="doPreview.action?articleId=<c:out value='${item.id}'/>" target="_blank">预览</a>
                        </td>
                      </tr>
                    </c:forEach>
                  </table>
                </div>
                <jsp:include flush="true" page="/WEB-INF/views/include/page.jsp"></jsp:include>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>
</form>
</body>
</html>