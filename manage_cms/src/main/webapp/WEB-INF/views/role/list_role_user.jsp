<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
function addFunction()
{
	var returnValue = openDialogWithScroll("user.action?function=userList", 500, 800);
    if (returnValue != null && returnValue.length > 0)
    {
		 $(":input[name='userIdStr']").val(returnValue);
		 $(":input[name='function']").val("addRoleUser");
         $("#qryparm").submit();
    }
}

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

function exportFunction()
{
	$(":input[name='function']").val("exportListRoleUser");
    $("#qryparm").submit();
}

function closeFunction()
{
	window.close();
}

$(document).ready(function(){  
	
});
</script>
<form id="qryparm" name="qryparm" action="role.action" method="post">
<input type="hidden" name="function" value="${param.function }"></input>
<input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"></input>
<input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"></input>
<input type="hidden" name="roleId" value="<c:out value='${param.roleId}'/>">
<input type="hidden" name="userIdStr" value=""/>
 <input type="hidden" name="successPage" value="role.action?function=listRoleUser&roleId=<c:out value='${param.roleId}'/>">
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
				  <p><img src="${ctxPath }/admin/images/ico07.gif" />当前位置：角色管理<img src="${ctxPath }/admin/images/pic14.gif" />角色用户<img src="${ctxPath }/admin/images/pic14.gif" /><c:out value='${data.roleName}' />
				  </p>  
				</div>
              <div class="label">
               	<a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>添加</a> <a href="#" onClick="deleteFunction('id','deleteRoleUser')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> <a href="#" onClick="javascript:closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a>  <a href="#" onClick="javascript:exportFunction();"><img src="${ctxPath }/admin/images/ico02.gif" border="0"/>导出</a> 
              </div>
              <div class="search">
                <div class="space"></div>
                <div>
              
                  <div style="float:left;">
                  <span>关键字：<input type="text" alt="notempty" name="keyword" value="${param.keyword}" class="input01"/></span> 
                  <span>
                    <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                    </span> </div>
                    <!--
                  <div style="float:right; width:100px; text-align:right;">
                    <input type="checkbox" name="checkbox" id="checkbox"/>
                    高级搜索 </div>
                    -->
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
	                    <td>
						<c:if test="${item.isSystem == 0}">
						<input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)">
						</c:if>
						<c:if test="${item.isSystem != 0}">
						&nbsp;
						</c:if>
						</td>						
	                    <td style="text-align:left">&nbsp;<c:out value="${item.uid}" /></td>
	                    <td style="text-align:left" class="td3">&nbsp;<c:out value="${item.name}" /></td>
	                    <td>&nbsp;<c:out value="${item.email}" /></td>
	                    <td> &nbsp;<c:out value="${item.loginTimes}" /></td>
						<td> &nbsp;<c:out value="${item.lastTime}" /></td>
						<td> &nbsp;<c:if test="${item.state eq '0'}"><font color="#ff0000">关闭</font></c:if>
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
</form>
</body>
</html>

