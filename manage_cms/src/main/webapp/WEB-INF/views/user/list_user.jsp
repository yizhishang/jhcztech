<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	
	function modifyPassword(functionId,id){
		openWindow("doEditPassword.action?id="+id,500, 180);
	}
	
	function operate(functionId)
	{
	    if (!isChecked("id"))
	    {
	        alert("请您选择需要操作的对象！");
	        return;
	    }
	    submitForm("qryparm", functionId, true);	
	}
	
</script>
<form id="qryparm" name="qryparm" action="doDefault.action" >
<input type="hidden" name="form.pageUrl" value="${pageUrl}"></input>
<input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"></input>
<input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"></input>
<div class="c_ie6_out">
  <div class="c_ie6_in">
    <div class="Wrapper">
      <div class="inner">
        <div class="innercontent">
          <div class="cl"></div>
          <div class="contentbox">
            <div class="content">
              <!-- 包含导航代码  -->
              <jsp:include flush="true" page="/WEB-INF/views/include/menubar.jsp"></jsp:include>
              <div class="label">
               	<a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a> 
                <a href="#" onClick="deleteFunction('id','delete');"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
                <a href="#" onClick="operate('open');"><img src="${ctxPath }/admin/images/ico02.gif" border="0"/>开放</a> 
                <a href="#" onClick="operate('close');"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>关闭</a> 
              </div>
              <div class="search">
                <div class="space"></div>
                <div>
              
                  <div style="float:left;">
                  <span>关键字：<input type="text" alt="notempty" name="keyword" value="${param.keyword}" class="input01"/></span> 
                  <span>营业部：<select name="branchno" id="branchNo" >
                  <option value="">请选择</option>
                    <c:forEach var="item" items="${data.branchs}">
                      <option value="${item.branchno}">${item.branchname}</option>
                    </c:forEach>
                  </select>
                  <script type="text/javascript">
                    $("#branchNo").val("<c:out value='${param.branchno}'/>");
                  </script>
                  </span> 
                   <span>所在角色：<select name="roleid" id="roleid" >
                  <option value="">请选择</option>
                    <c:forEach var="item" items="${data.roles}">
                      <option value="${item.role_id}">${item.roleno}</option>
                    </c:forEach>
                  </select>
                  <script type="text/javascript">
                    $("#roleid").val("<c:out value='${param.roleid}'/>");
                  </script>
                  </span> 
                  <span>
                    <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                    </span> </div>
                </div>
               
              </div>
              <div class="space"></div>
              <div class="databox2" style="height: 350px">
                <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="td1"><input type="checkbox" id="check_all"  onclick="checkAll(this,'id');" /></td>
                    <td class="tdhead">用户标识</td>
                    <td class="tdhead">用户名称</td>
                    <td class="tdhead">所属营业部</td>
                    <td class="tdhead">所在角色</td>
                    <td class="tdhead">登录次数</td>
                    <%--<td class="tdhead">所属站点</td>--%>
                    <td class="tdhead">最后登录时间</td>
                    <td class="tdhead">用户状态</td>
                    <td class="tdhead">操作</td>
                  </tr>
                <c:forEach var="item" items="${data.page.data}">
		              <tr>
	                    <td><input type="checkbox" name="id" id="check_${item.user_id}" value="${item.user_id}" onClick="setCheck(this)"/></td>
	                    <td style="text-align:left">&nbsp;<c:out value="${item.uid2}" /></td>
	                    <td style="text-align:left" class="td3">&nbsp;<c:out value="${item.name}" /></td>
	                    <td style="text-align:left" >&nbsp;<c:out value="${item.branchname}" /></td>
	                    <td style="text-align:left" >&nbsp;<c:out value="${item.role_str}" /></td>
	                    <td>&nbsp;<c:out value="${item.login_times}" /></td>
	                    <%--<td>&nbsp;<c:out value="${item.siteName}" /></td>--%>
	                    <td>&nbsp;<c:out value="${item.last_time}" /></td>
	                    <td>
	                    	<c:if test="${item.state == 0}"><font color="#ff0000">关闭</font></c:if>
	                    	<c:if test="${item.state == 1}">开放</c:if>
	                    </td>
	                    <td>&nbsp;<a href="javascript:editFunction('id=${item.user_id}');">编辑</a> ｜  <a href="javascript:modifyPassword('editPassword','${item.user_id}');">修改密码</a></td>
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
