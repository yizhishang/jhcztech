<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
    function deleteFunction(name, funcitonId)
    {
		if (isChecked(name) && window.confirm("确定删除选中数据？")){
			submitForm("qryparm", funcitonId, true);
		}else{
			alert("请选择需要删除的数据！");
		}
    }

    function editStateFunction(name, funcitonId, state)
    {
        if (isChecked(name) && window.confirm("确定更新选中数据？"))
        {
            $(":input[name='function']").val(funcitonId);
            $(":input[name='state']").val(state);
            submitForm("qryparm", funcitonId, true);
        }
        else
        {
            alert("请选择需要更新的数据！");
        }
    }
	
	function exportFunction(name, funcitonId)
    {
        if (isChecked(name))
        {
            if ($(":input[name='function']").length > 0)
            {
                $(":input[name='function']").val(funcitonId);
                $("#qryparm").attr("action", funcitonId + ".action");
                $("#qryparm").submit();
                //submitForm("qryparm",funcitonId);
            }
        }
        else
        {
            alert("请选择要导出的数据！");
        }
    }

    /**
     *搜索模板
     */
    function searchFunction()
    {
        $("#qryparm").submit();
    }

    function addFunction(catalogId)
    {
        openWindow("doAdd.action?catalogId=" + catalogId, 835, 360);
    }

    function editFunction(id)
    {
        openWindow("doEdit.action?id=" + id, 835, 415);
    }

    function editContentFunction(id)
    {
        openWindow("doEditContent.action?id=" + id, 850, 610);
    }

    /**
     * 预览模板
     * @param id
     */
    function previewFunction(id)
    {
        openMaxNamedWindowWithScroll("doPreview.action?id=" + id, "previewTemplate");
    }

    /**
     * 发布模板
     * @param id
     */
    function publishFunction(id)
    {
        hiddenForm["function"].value = "doPublish";
        hiddenForm["id"].value = id;
        hiddenForm.submit();
    }
</script>
<form id="qryparm" name="qryparm" action="doList.action">
<input type="hidden" name="function" value="${param.function }">
<input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }">
<input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }">
<input type="hidden" name="catalogId" value="${param.catalogId}" />
<input type="hidden" name="createDate" id="createDate" value="${param.createDate}" />
<input type="hidden" name="state" value="" />
<input type="hidden" name="successPage" value="doList.action?catalogId=<c:out value='${param.catalogId}'/>&startDate=<c:out value='${param.startDate}'/>&endDate=<c:out value='${param.endDate}'/>&keyword=<c:out value='${param.keyword}'/>">

<div class="c_ie6_out">
    <div class="c_ie6_in">
        <div class="Wrapper">
            <div class="inner">
                <div class="innercontent">
                    <div class="cl"></div>
                    <div class="contentbox">
                        <div class="content">
                            <!-- 包含导航代码  -->
                            <jsp:include flush="true" page="/WEB-INF/views/template/include/articleCatalogLink.jsp">
                            	<jsp:param name="catalogName" value="显示模板" />
                            </jsp:include>
                            <div class="label">
                                <a href="#" onClick="addFunction('${param.catalogId}')"><img src="${ctxPath }/admin/images/ico08.gif" border="0" />新建</a>
                                <a href="#" onClick="deleteFunction('id','doDelete');"><img src="${ctxPath }/admin/images/ico15.gif" border="0" />删除</a>
                                <a href="#" onClick="editStateFunction('id','editState',1);"><img src="/admin/images/ico02.gif" border="0" />有效</a>
                                <a href="#" onClick="editStateFunction('id','editState',0);"><img src="/admin/images/import/ico003.png" border="0" />无效</a>
								<!-- 
								<a href="#" onClick="exportFunction('id','dataExport');"><img src="/admin/images/import/ico002.png" border="0" />导出</a>
								<a href="doImportTemplate.action" target="_blank"><img src="/admin/images/import/ico001.png" border="0" />导入</a>
								 -->
								<a href="#" onClick="location.reload();"><img src="/admin/images/ico10.gif" border="0" />刷新</a>
                            </div>
                            <div class="search">
                                <div class="space"></div>
                                <div>
                                    <div style="float:left;">
                                    	<span>录入时间：</span>
                                    	<span><input type="text" name="startDate" id="startDate" class="input01" value="${param.startDate}" readonly /></span>
                                        <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'startDate'})" /></span>
                                        <span>到</span>
                                        <span><input type="text" name="endDate" id="endDate" class="input01" value="${param.endDate}" readonly /></span>
                                        <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'endDate'})" /></span>
                                        <span>&nbsp;&nbsp;关键词：</span>
                                        <span><input type="text" name="keyword" id="keyword" class="input01" value="${param.keyword}" /></span>
                    					<span>模板状态：</span>
                                    	<select name="templateState">
                              				<option value="-1">全部</option>
                              				<option value="0">无效</option>
			                              	<option value="1">有效</option>
                            			</select>
                            			<script language="javascript">setSelectSelected("templateState",'<c:out value='${param.templateState}'/>')</script>
                                    	<span><input type="button" name="button" id="button" value="查询" onClick="searchFunction();" class="bt01" /></span>
                    				</div>
                                    <div style="float:right; width:100px; text-align:right;">
                                        <!-- <input type="checkbox" name="checkbox" id="checkbox"/>高级搜索 </div>-->
                                    </div>
                                </div>
                                <div class="space"></div>
                                <div class="databox2">
                                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                                        <tr>
                                            <td class="td1" style="width:45px;">
                                                <input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')" />
                                            </td>
                                            <td class="tdhead" width="280">模板名称</td>
                                            <td class="tdhead">模版类型</td>
                                            <td class="tdhead">创建人</td>
                                            <td class="tdhead" style="width:170px;">创建时间</td>
                                            <td class="tdhead">状态</td>
                                            <td class="tdhead" style="width:200px;">操作</td>
                                        </tr>
                                        <c:forEach var="item" items="${data.page.data}">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)">
                                                </td>
                                                <td class="td3" style="text-align:left">&nbsp;
                                                    <c:out value="${item.name}" /></td>
                                                <td><c:if test="${item.type eq '1'}">首页模板</c:if>
                                                    <c:if test="${item.type eq '2'}">信息列表</c:if>
                                                    <c:if test="${item.type eq '3'}">信息细览</c:if>
                                                    <c:if test="${item.type eq '4'}">其它模版</c:if></td>
                                                <td><c:out value="${item.createBy}" /></td>
                                                <td><c:out value="${item.createDate}" /></td>
                                                <td>
                                                    <c:if test="${item.state eq '0'}"><font color="#FF0000">无效</font></c:if>
                                                    <c:if test="${item.state eq '1'}">有效</c:if>
                                                </td>
                                                <td>
                                                        <a href="#" onClick="editFunction('${item.id}')">编辑</a>｜<a href="#" onClick="editContentFunction('${item.id}')">编辑模板</a>｜<a href="#" onClick="previewFunction('${item.id}')">预览</a><c:if test="${item.type eq '4' and item.state eq '1'}">｜<a href="#" onClick="publishFunction('${item.id}')">发布</a></c:if><!-- <a href="template.action?function=exportTxtById&id=${item.id}" target="_blank">导出</a>-->
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
<div styoe="display:none">
    <iframe name="hiddenFrame" width="0" height="0"></iframe>
    <form name="hiddenForm" action="doPublish.action" target="hiddenFrame">
        <input type="hidden" name="function" value="">
        <input type="hidden" name="id" value="">
    </form>
</div>
</body>
</html>