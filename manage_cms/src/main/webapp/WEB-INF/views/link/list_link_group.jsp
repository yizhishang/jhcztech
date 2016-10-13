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
	openWindow("link.action?function=add", 500, 150);
}

function editFunction(id)
{
	openWindow("link.action?function=edit&id="+id, 500, 150);
}

function viewFunction(id)
{
	openWindow("link.action?function=view&id="+id, 500, 250);
}

function listLinksFunction(id)
{
	openMaxWindowWithScroll("link.action?function=listGrouplinks&id="+id);
}
</script>
<form id="qryparm" name="qryparm" action="link.action" >
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
                <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a> <a href="#" onClick="deleteFunction('id','deleteLinkGroup')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> </div>
                <div class="search">
                  <div class="space"></div>
                  <div>
                    <div style="float:left;"> <span>&nbsp;&nbsp;关键词：</span> <span>
                    <input type="text" name="keyword" id="keyword" class="input01" value="${param.keyword}"/>
                    </span>
					  <script language="javascript">setSelectSelected("type", "<c:out value='${param.type}'/>")</script>
                      <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                      </span> </div>
                  </div>
                </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="td1" style="width:45px"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead">友情链接类别名</td>
                      <td class="tdhead" width="280">创建者</td>
                      <td class="tdhead" width="280">创建时间</td>
                      <td class="tdhead" style="width:240px;">&nbsp;&nbsp;操作&nbsp;&nbsp;</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr style="cursor:pointer" onDblClick="javascript:listLinksFunction('<c:out value='${item.id}'/>')" title="双击查看该分类下友情链接">
                        <td>
						<input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)">
                        </td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.name}" /></td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.create_by}" /></td>
                        <td>&nbsp;
                          <c:out value="${item.create_date}" /></td>
                        <td >&nbsp;<a href="#" onClick="editFunction('<c:out value='${item.id}'/>')">编辑</a> ｜ <a href="#" onClick="listLinksFunction('<c:out value='${item.id}'/>')">该分类下友情链接维护</a></td>
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