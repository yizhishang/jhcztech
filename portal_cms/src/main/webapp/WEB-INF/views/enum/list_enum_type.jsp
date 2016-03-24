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
	openWindow("enumType.action?function=add", 500, 215);
}


function editFunction(id)
{
	openWindow("enumType.action?function=edit&id="+id, 500, 215);
}


function listEnumFunction(val)
{
	//openWindow("role.action?function=listRoleUser&id="+73, 800, 700);
	openMaxWindowWithScroll("enum.action?type="+val);
}


</script>
<form id="qryparm" name="qryparm" action="enumType.action" >
<input type="hidden" name="form.pageUrl" value="${pageUrl}"></input>
<input type="hidden" name="function" value="${param.function }"></input>
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
                <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a> <a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> </div>
                <%--<div class="search">
                  <div class="space"></div>
                  <div>
                    <div style="float:left;"> <span>类型：
                      <select name="type">
                        <option selected value="">--全部--</option>
                        <c:forEach var="item" items="${data.typeList}">
                          <option value="<c:out value='${item.enum_value}'/>">
                          <c:out value='${item.enum_name}'/>
                          </option>
                        </c:forEach>
                      </select>
                      </span> <span>
					  <script language="javascript">setSelectSelected("type", "<c:out value='${param.type}'/>")</script>
                      <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                      </span> </div>
                  </div>
                </div>
                --%><div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="td1" style="width:45px"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead">类型名称</td>
                      <td class="tdhead">类型值</td>
                      <td class="tdhead"  style="width:200px">&nbsp;&nbsp;操作&nbsp;&nbsp;</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr style="cursor:pointer" onDblClick="javascript:editFunction('<c:out value='${item.item_code}'/>')" title="双击查看详细内容">
                        <td>
							<input type="checkbox" name="id" id="check_<c:out value='${item.enum_value}'/>" value="<c:out value='${item.enum_value}'/>" onClick="setCheck(this)">
                        </td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.enum_name}" /></td>
                        <td style="text-align:left" >&nbsp;
                          <c:out value="${item.enum_value}" /></td>
                        <td >
                        	&nbsp;<a href="#" onClick="editFunction('<c:out value='${item.enum_value}'/>')">编辑</a>
                        	&nbsp;<a href="#" onClick="listEnumFunction('<c:out value='${item.enum_value}'/>')">数据字典</a>
                        </td>
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