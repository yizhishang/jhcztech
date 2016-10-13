<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@page import="com.jhcz.base.util.RequestHelper"%>
<%@ include file="/admin/common/header.jsp" %>
<link href="/admin/styles/checktree.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/admin/scripts/jquery.new.checktree.js"></script>
<script language="javascript" src="/admin/scripts/syncReqJs.js"></script>
<script type="text/javascript">
/*
 * 添加角色用户
 */
function addRoleUserFunction()
{
	var returnValue = openDialogWithScroll("/admin/userAdmin/userList.action?roleId=" + '${form.id}', 500, 800);
    if (returnValue != null && returnValue.length > 0)
    {
		 $(":input[name='userIdStr']").val(returnValue);
         submitForm("qryparm", "addRoleUser", true);
    }
}

/**
 * 删除角色下的用户
 */
function deleteRoleUserFunction(name,funcitonId){
	if(isChecked(name) && window.confirm("确定删除选中数据？")){
		submitForm("qryparm", "deleteRoleUser", true);
	}else{
		alert("请选择需要删除的数据！");
	}
}

/*
 * 添加角色
 */
function addRoleFunction()
{
    var returnValue = openDialog("doAdd.action", 500, 230);
    if (returnValue != null && returnValue.length > 0)
    {
	    if(returnValue.errorNo == 0)
	    {
	    	window.parent.location.reload();
	    }
    }
}

/*
 * 删除角色信息
 */
function delRoleFunction()
{
	var roleId = <c:out value='${form.id}' default="0"/>;
	if(roleId != null && roleId > 0){
		if(window.confirm("确定删除角色【${form.roleNo}】？")){
			submitForm("qryparm", "delete");
			window.parent.location.reload();
		}
	}else{
		alert("角色信息不存在！");
	}
}

/*
 * 修改角色信息
 */
function editRoleFunction(id)
{
	openWindow("doEdit.action?id="+id, 500, 230);
}


function search()
{
	
}
</script>
<body>
<form id="qryparm" name="qryparm" action="doList.action" method="post">
  <input type="hidden" name="function" value="${param.function }"/>
  <input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"/>
  <input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"/>
  <input type="hidden" name="roleId" value="<c:out value='${form.id}'/>">
  <input type="hidden" name="userIdStr" value=""/>
  <input type="hidden" name="successPage" value="role.action?function=list&roleId=<c:out value='${form.id}'/>"/>
  <div class="c_ie6_out">
    <div class="c_ie6_in">
      <div class="Wrapper">
        <div class="inner">
          <div class="innercontent">
            <div class="cl"></div>
            <div class="contentbox">
              <div class="content">
                <jsp:include page="${ctxPath }/WEB-INF/views/role/include/menu.jsp" flush="true">
					<jsp:param name="index" value="0"/>
					<jsp:param name="roleId" value="<%=RequestHelper.getString(request,"roleId") %>"/>
					<jsp:param name="siteNo" value="<%=RequestHelper.getString(request,"siteNo") %>"/>
				</jsp:include>
                <div class="space"></div>
                <div id="content">
                  <div class="eb0">
                    <div class="label">
                    	<a href="#" onClick="addRoleFunction();"><img src="/admin/images/ico08.gif" border="0"/>新建</a>
                    	<a href="#" onClick="editRoleFunction(<c:out value='${form.id}'/>);"><img src="/admin/images/ico09.gif" border="0"/>修改</a>
                      <c:if test="${form.isSystem == 0}"><a href="#" onClick="delRoleFunction();"><img src="/admin/images/ico15.gif" border="0"/>删除</a></c:if>
                    </div>
                  </div>
                  <div class="space"></div>
                  <div class="databox">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                      <tr>
                        <td style="text-align:right;width:15%">角色标识：</td>
                        <td class="td3" style="text-align:left;width:35%"><c:out value="${form.roleNo}" default="--"/></td>
                        <td style="text-align:right;width:15%">角色名称：</td>
                        <td class="td3" style="text-align:left;width:35%"><c:out value="${form.name}" default="--"/></td>
                      </tr>
                      <tr>
                        <td style="text-align:right">备注：</td>
                        <td class="td3" colspan="3" style="text-align:left"><c:out value="${form.description}" default="--"/></td>
                      </tr>
                    </table>
                  </div>
                  <div class="space"></div>
                  <div class="eb0">
                    <div>
                                                           关键字：<input type="text" alt="notempty" name="keyword" value="${param.keyword }" class="input01"/>
                      <input type="submit" name="button" id="button" value="查询" onClick="search();" />
                      <input type="button" value="添加用户到角色" onClick="addRoleUserFunction();"/>
                      <input type="button" value="从角色中删除用户" onClick="deleteRoleUserFunction('id','deleteRoleUser')"/>
                    </div>
                  </div>
                  <div class="space"></div>
                  <div class="databox2" style="height: 350px">
                    <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                      <tr>
                        <td class="td1"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                        <td class="tdhead">用户标识</td>
                        <td class="tdhead">用户名称</td>
                        <td class="tdhead">电子邮箱</td>
                        <td class="tdhead">登录次数</td>
                        <td class="tdhead">最后登录时间</td>
                        <td class="tdhead">用户状态</td>
                      </tr>
                      <c:forEach var="item" items="${data.page.data}">
                        <tr>
                          <td><c:if test="${item.isSystem == 0}">
                              <input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)">
                            </c:if>
                            <c:if test="${item.isSystem != 0}"> &nbsp; </c:if></td>
                          <td style="text-align:left">&nbsp;
                            <c:out value="${item.uid}" /></td>
                          <td style="text-align:left" class="td3">&nbsp;
                            <c:out value="${item.name}" /></td>
                          <td>&nbsp;
                            <c:out value="${item.email}" /></td>
                          <td>&nbsp;
                            <c:out value="${item.loginTimes}" /></td>
                          <td>&nbsp;
                            <c:out value="${item.lastTime}" /></td>
                          <td>&nbsp;
                            <c:if test="${item.state eq '0'}"><font color="#ff0000">关闭</font></c:if>
                            <c:if test="${item.state eq '1'}">开放</c:if></td>
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
  </div>
  </div>
</form>
</body>
</html>