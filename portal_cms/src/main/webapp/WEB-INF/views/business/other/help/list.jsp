<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
    $(document).ready(function()
    {

    });


    function deleteFunction(name, funcitonId)
    {
        if (isChecked(name))
        {
            if ($(":input[name='function']").length > 0)
            {
                $(":input[name='function']").val(funcitonId);
                if (window.confirm("确定删除选中数据？"))
                {
                    $("#qryparm").submit();
                }
            }
        }
        else
        {
            alert("请选择需要删除的数据！");
        }
    }

    function editStateFunction(name, funcitonId, state)
    {
        if (isChecked(name))
        {
            if ($(":input[name='function']").length > 0)
            {
                $(":input[name='function']").val(funcitonId);
                $(":input[name='state']").val(state);
                if (window.confirm("确定更新选中数据？"))
                {
                    $("#qryparm").submit();
                }
            }
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
                $("#qryparm").submit();
            }
        }
        else
        {
            alert("请选择要导出的数据！");
        }
    }

    /**
     搜索模板
     */
    function searchFunction()
    {
        $("#qryparm").submit();
    }

    function addFunction(groupId)
    {
        //openWindow("help.action?function=add&groupId=" + groupId, 835, 360);
		location.href = "help.action?function=add&groupId=" + groupId;
    }

    function editFunction(id)
    {
        //openWindow("help.action?function=edit&id=" + id, 835, 415);
		location.href = "help.action?function=edit&id=" + id;
    }

    function editContentFunction(id)
    {
        openWindow("template.action?function=editContent&id=" + id, 850, 610);
    }

    /**
     * 预览模板
     * @param id
     */
    function previewFunction(id)
    {
        openMaxNamedWindowWithScroll("template.action?function=preview&id=" + id, "previewTemplate");
    }

    /**
     * 发布模板
     * @param id
     */
    function publishFunction(id)
    {
        hiddenForm["function"].value = "publish";
        hiddenForm["id"].value = id;
        hiddenForm.submit();
    }
</script>
<form id="qryparm" name="qryparm" action="help.action">
  <input type="hidden" name="function" value="${param.function }">
  <input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }">
  <input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }">
  <input type="hidden" name="groupId" value="${param.groupId}" />
  <input type="hidden" name="state" value="" />
  <input type="hidden" name="successPage" value="help.action?function=list&groupId=<c:out value='${param.groupId}'/>">
  <div class="c_ie6_out">
    <div class="c_ie6_in">
      <div class="Wrapper">
        <div class="inner">
          <div class="innercontent">
            <div class="cl"></div>
            <div class="contentbox">
              <div class="content">
                <div class="title">
                  <p><img src="${ctxPath }/admin/images/ico07.gif" />当前位置: 帮助中心</p>
                </div>
                <div class="label"> <a href="#" onClick="addFunction('${param.groupId}')"><img src="${ctxPath }/admin/images/ico08.gif" border="0" />新建</a> <a href="#" onClick="editStateFunction('id','publish','1')"><img src="${ctxPath }/admin/images/ico08.gif" border="0" />发布</a> <a href="#" onClick="editStateFunction('id','publish','0')" class="FourFont"><img src="${ctxPath }/admin/images/ico08.gif" border="0" />取消发布</a> <a href="#" onClick="editStateFunction('id','modifyHot','1')"><img src="${ctxPath }/admin/images/ico08.gif" border="0" />推荐</a> <a href="#" onClick="editStateFunction('id','modifyHot','0')"  class="FourFont"><img src="${ctxPath }/admin/images/ico08.gif" border="0" />取消推荐</a> <a href="#" onClick="deleteFunction('id','delete');"><img src="${ctxPath }/admin/images/ico15.gif" border="0" />删除</a> </div>
                <div class="search">
                  <div class="space"></div>
                  <div>
                    <div style="float:left;"><span>标题：</span> <span>
                      <input type="text" name="title" id="title" class="input01" value="${param.title}" />
                      </span>
					  <span>
                      <input type="button" name="button" id="button" value="查询" onClick="searchFunction();" class="bt01" />
                      </span></div>
                    <div style="float:right; width:100px; text-align:right;">
                      <!-- <input type="checkbox" name="checkbox" id="checkbox"/>
                                       高级搜索 </div>-->
                    </div>
                  </div>
                  <div class="space"></div>
                  <div class="databox">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                      <tr>
                        <td class="td1" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')" />
                        </td>
                        <td class="tdhead" >标题</td>
                        <td class="tdhead"  style="width:100px;">状态</td>
                        <td class="tdhead"  style="width:100px;">创建人</td>
                        <td class="tdhead"  style="width:150px;">创建时间</td>
                        <td class="tdhead"  style="width:100px;">操作</td>
                      </tr>
                      <c:forEach var="item" items="${data.page.data}">
                        <tr>
                          <td><input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)">
                          </td>
                          <td class="td3" style="text-align:left">&nbsp;<c:if test="${item.is_hot == 1}"><font color="red">[荐]</font></c:if><c:out value="${item.title}" /></td>
                          <td><c:if test="${item.state eq '0'}"><font color="#FF0000">未发布</font></c:if>
                            <c:if test="${item.state eq '1'}">已发布</c:if>
                          </td>
                          <td><c:out value="${item.create_by}" /></td>
                          <td><c:out value="${item.create_date}" /></td>
                          <td><a href="#" onClick="editFunction('${item.id}')">编辑</a></td>
                        </tr>
                      </c:forEach>
                    </table>
                  </div>
				  <c:if test="${fn:length(data.page.data) > 0}">
                  <jsp:include flush="true" page="/WEB-INF/views/include/page.jsp"> </jsp:include></c:if>
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
  <form name="hiddenForm" action="template.action" target="hiddenFrame">
    <input type="hidden" name="function" value="">
    <input type="hidden" name="id" value="">
  </form>
</div>
</body>
</html>