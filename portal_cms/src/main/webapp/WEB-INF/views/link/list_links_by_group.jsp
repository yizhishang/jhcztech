<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
function deleteFunction(name,funcitonId){
	if(isChecked(name)){
		if($(":input[name='function']").length>0){
			$(":input[name='function']").val(funcitonId);
			if(window.confirm("确定删除选中数据？")){
				$("#qryparm").submit();
			}
		}
	}else{
		alert("请选择需要删除的数据！");
	}
}

function addFunction()
{
	openWindow("link.action?function=addGrouplinks&id=${param.id}", 500, 200);
}

function editFunction(linkid)
{
	openWindow("link.action?function=edit_link_by_group&linkid="+linkid+"&group_id=<c:out value='${param.id}'/>", 500, 200);
}

function closeFunction()
{
	window.close();
}
$(document).ready(function(){  
	
});
</script>
<form id="qryparm" name="qryparm" action="link.action" >
  <input type="hidden" name="function" value="${param.function }">
  </input>
<input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"></input>
<input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"></input>

  <input type="hidden" name="id" value="${param.id}">
  </input>
  <input type="hidden" name="successPage" value="link.action?function=listGrouplinks&id=<c:out value='${param.id}'/>">
  <div class="c_ie6_out">
    <div class="c_ie6_in">
      <div class="Wrapper">
        <div class="inner">
          <div class="innercontent">
            <div class="cl"></div>
            <div class="contentbox">
              <div class="content">
                <!-- 包含导航代码  -->
                <div class="title">
                  <p><img src="${ctxPath }/admin/images/ico07.gif" />当前位置：友情链接管理<img src="${ctxPath }/admin/images/pic14.gif" />
                    <c:out value='${data.name}' />
                  </p>
                </div>
                <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>添加</a> <a href="#" onClick="deleteFunction('id','deleteGroupLinks')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> <a href="#" onClick="javascript:closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="td1" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead" width="200">站点名称</td>
                      <td class="tdhead">URL</td>
					  <td class="tdhead" width="100">排序值</td>
                      <td class="tdhead" width="200">创建时间</td>
                      <td class="tdhead" width="200">链接状态</td>
                      <td class="tdhead" width="60">&nbsp;&nbsp;操作&nbsp;&nbsp;</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr>
                        <td><input type="checkbox" name="id" id="check_<c:out value='${item.linkid}'/>" value="<c:out value='${item.linkid}'/>" onClick="setCheck(this)">
                        </td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.name}" /></td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.url}" /></td>
						  <td>&nbsp;
                         <c:out value="${item.orderline}" /></td>
                        <td>&nbsp;
                          <c:out value="${item.create_date}" /></td>
                        <td>&nbsp;
                          <c:if test="${item.state == 0}"><font color="#ff0000">无效</font></c:if>
                          <c:if test="${item.state == 1}">有效</c:if></td>
                        <td >&nbsp;<a href="#" onClick="editFunction('<c:out value='${item.linkid}'/>')">编辑</a>&nbsp;</td>
                      </tr>
                    </c:forEach>
                  </table>
                </div>
                <!-- 包含分页代码  -->
                <jsp:include flush="true" page="/WEB-INF/views/include/page.jsp"></jsp:include>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</form>
<!--添加角色开始-->
<form id="addForm" action="role.action" method="post" target="">
  <input type="hidden" name="function" value="add"/>
  <input type="hidden" name="form.roleNo" id="roleNo"/>
  <input type="hidden" name="form.name" id="name"/>
</form>
<!--添加角色结束-->
</body>
</html>