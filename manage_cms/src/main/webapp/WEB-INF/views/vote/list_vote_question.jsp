<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
function addFunction()
{
	openWindow("vote.action?function=addQuestion&id=${param.id}", 650, 400);
}

function editFunction(id)
{
	openWindow("vote.action?function=editQuestion&id=" + id, 650, 450);
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

function closeFunction()
{
	window.close();
}

$(document).ready(function(){  
	
});
</script>
<form id="qryparm" name="qryparm" action="vote.action" method="post">
<input type="hidden" name="function" value="${param.function }"></input>
<input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"></input>
<input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"></input>

<input type="hidden" name="id" value="<c:out value='${param.id}'/>">
 <input type="hidden" name="successPage" value="vote.action?function=listQuestion&id=<c:out value='${param.id}'/>">
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
				  <p><img src="${ctxPath }/admin/images/ico07.gif" />当前位置：投票调查管理<img src="${ctxPath }/admin/images/pic14.gif" />显示调查问题［<c:out value='${data.subData.name}' />］
				  </p>  
				</div>
              <div class="label">
               	<a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>添加</a> <a href="#" onClick="deleteFunction('id','deleteQuestion')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> <a href="#" onClick="javascript:closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> 
              </div>
              <div class="search">
                <div class="space"></div>
                <div>
              
                  <div style="float:left;">
                  <span>关键字：<input type="text" alt="notempty" name="keyword" value="${param.keyword}" class="input01"/></span> 
                  <span>
                    <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                    </span> </div>
                  <%--<div style="float:right; width:100px; text-align:right;">
                    <input type="checkbox" name="checkbox" id="checkbox"/>
                    高级搜索 </div>--%>
                </div>
               
              </div>
              <div class="space"></div>
              <div class="databox2" style="height: 350px">
                <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="td1" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                    <td class="tdhead">问题名称</td>
					<td class="tdhead" style="width:100px;">排序值</td>
                    <td class="tdhead" style="width:100px;">状态</td>
                    <td class="tdhead" style="width:100px;">投票类型</td>
					<td class="tdhead" style="width:90px;">操作</td>
                  </tr>
                <c:forEach var="item" items="${data.page.data}">
		              <tr>
	                    <td>
						<input type="checkbox" name="id" id="check_<c:out value='${item.que_id}'/>" value="<c:out value='${item.que_id}'/>" onClick="setCheck(this)">
						</td>						
	                    <td style="text-align:left" class="td3">&nbsp;<c:out value="${item.name}" /></td>
						 <td>&nbsp;<c:out value="${item.orderline}" /></td>
	                    <td><c:if test="${item.state eq '0'}"><font color="#FF0000">无效</font></c:if>
                          <c:if test="${item.state eq '1'}">有效</c:if></td>
	                    <td><c:if test="${item.type eq '0'}"><font color="#FF0000">单选</font></c:if>
                          <c:if test="${item.type eq '1'}">多选</c:if></td>
						<td >&nbsp;<a href="#" onClick="editFunction(<c:out value='${item.que_id}'/>)">编辑</a></td>
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

