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
	openMaxWindowWithScroll("branchMaintain.action?function=add");
}

function editFunction(id)
{
	openMaxWindowWithScroll("branchMaintain.action?function=edit&id="+id, 500, 170);
}

</script>
<form id="qryparm" name="qryparm" action="branchMaintain.action" >
<input type="hidden" name="function" value="${param.function }"></input>
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
			  <c:if test="${empty data.branchno}">
              <div class="label">
               	<a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a> 
                <a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
              </div>
              <div class="search">
                <div class="space"></div>
                <div>
              
                  <div style="float:left;">
				  <c:if test="${form.isSystemAdmin == 1}">
				  <span>&nbsp;营业部：<select name="branchNo">
				  <option value="">请选择</option>
				  <c:forEach var="item" items="${data.branchs}">
				  <option value="${item.branchno}">${item.branchname}</option>
				  </c:forEach>
				  </select>
				  <script type="text/javascript">setSelectSelected("branchNo","${param.branchNo}")</script>
				  </span>
				  </c:if>
				  <span>&nbsp;类型：<select name="type">
				  <option value="">--请选择--</option>
				  <option value="1">营业部公告</option>
				  <option value="2">营业部博文</option>
				  </select>
				  <script type="text/javascript">setSelectSelected("type","${param.type}")</script>
				  </span> 
                  <span>&nbsp;关键字：<input type="text" name="title" value="${param.title}" class="input01"/></span> 
                  <span>
                    <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                    </span> </div>
                </div>
               
              </div>
			  </c:if>
              <div class="space"></div>
              <div class="databox2" style="height: 350px">
                <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="tdhead" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                    <c:if test="${form.isSystemAdmin == 1}"><td class="tdhead">营业部名称</td></c:if>
					<td class="tdhead">类型</td>
					<td class="tdhead">标题</td>
					<td class="tdhead">创建时间</td>
					<td class="tdhead" style="width:80px;">&nbsp;&nbsp;操作&nbsp;&nbsp;</td>
                  </tr>
                <c:forEach var="item" items="${data.page.data}">
		              <tr style="cursor:pointer" onDblClick="javascript:editFunction(<c:out value='${item.infoid}'/>)" title="双击查看详细内容">
	                    <td><input type="checkbox" name="id" id="check_<c:out value='${item.infoid}'/>" value="<c:out value='${item.infoid}'/>" onClick="setCheck(this)">						</td>						
	                    <c:if test="${form.isSystemAdmin == 1}"><td style="text-align:left;width:250px">&nbsp;<c:out value="${item.branchname}" /></td></c:if>
						<td style="width:100px;">&nbsp;<c:if test="${item.type eq '0'}">营业部动态</c:if><c:if test="${item.type eq '1'}">营业部公告</c:if><c:if test="${item.type eq '2'}">营业部博文</c:if></td>
						<td class="td3" style="text-align:left">&nbsp;<c:out value="${item.title}" /></td>
						<td style="text-align:left;width:200px">&nbsp;<c:out value="${item.createdtime}" /></td>
						<td > &nbsp;<a href="#" onClick="editFunction(<c:out value='${item.infoid}'/>)">编辑</a></td>
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

