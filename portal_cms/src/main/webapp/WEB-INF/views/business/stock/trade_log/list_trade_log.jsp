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

function deleteAllFunction(funcitonId){
	if($(":input[name='function']").length>0){
		$(":input[name='function']").val(funcitonId);
		if(window.confirm("确定删除所有数据？")){
			$("#qryparm").submit();
		}
	}
}
</script>
<form id="qryparm" name="qryparm" action="tradeLogManage.action">
  <input type="hidden" name="form.pageUrl" value="${pageUrl}">
  </input>
  <input type="hidden" name="function" value="${param.function }">
  </input>
  <input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }">
  </input>
  <input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }">
  </input>
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
                <div class="label"> <a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> <%--<a href="#" class="FourFont" onClick="deleteAllFunction('deleteAll')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除全部</a>--%> </div>
                <div class="search">
                  <div class="space"></div>
                  <div>
                    <div style="float:left;"> <span>资金帐号：
                      <input type="text" name="fund_account" value="${param.fund_account}" class="input01"/>
                      </span> <span>开始时间：</span> <span>
                      <input type="text" name="starttime" id="starttime" class="input01" value="${param.starttime}" readonly />
                      </span> <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'starttime'})" /></span> <span>结束时间：</span> <span>
                      <input type="text" name="endtime" id="endtime" class="input01" value="${param.endtime}" readonly />
                      </span> <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'endtime'})" /></span> <span>
                      <input type="submit" name="button" id="searchEnter" value="查询"  class="bt01" />
					  <%--<input type="button" value="数据导出" class="bt01"  onClick="GreateExl(this.form)">--%>
                      </span> </div>
                  </div>
                </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="tdhead" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead" style="width:110px;">资金帐号</td>
                      <td class="tdhead" style="width:110px;">客户名称</td>
                      <td class="tdhead" style="width:120px;">IP地址</td>
                      <td class="tdhead" style="width:180px;">发生时间&nbsp;</td>
                      <td class="tdhead" style="width:110px;">功能名称</td>
                      <td class="tdhead">详细说明</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr>
                        <td><input type="checkbox" name="id" id="check_<c:out value='${item.logid}'/>" value="<c:out value='${item.logid}'/>" onClick="setCheck(this)">
                        </td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.clientid}" /></td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.clientname}" /></td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.ip}" /></td>
                        <td>&nbsp;
                          <c:out value="${item.createdtime}" /></td>
                        <td>&nbsp;
                          <c:out value="${item.operate}" /></td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.description}" /></td>
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