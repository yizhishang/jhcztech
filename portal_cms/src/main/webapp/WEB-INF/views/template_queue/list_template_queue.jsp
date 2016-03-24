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

$(document).ready(function(){  
	
});
</script>
<form id="qryparm" name="qryparm" action="publishQueue.action" >
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
                <div class="label"> <a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> <a href="#" class="FourFont" onClick="deleteAllFunction('deleteAll')"><img src="/admin/images/ico15.gif" border="0"/>删除全部</a> </div>
                <div class="search">
                  <div class="space"></div>
                  <div>
                    <div style="float:left;"> <span>创建时间：</span> <span>
                      <input type="text" name="startDate" id="startDate" class="input01" value="${param.startDate}" readonly/>
                      </span> <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'startDate'})"/></span> <span>到</span> <span>
                      <input type="text" name="endDate" id="endDate" class="input01" value="${param.endDate}" readonly/>
                      </span> <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'endDate'})" /></span></span> <span>
                      <input type="submit" name="button" id="button" value="查询" class="bt01" />
                      </span> </div>
                  </div>
                </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="td1" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead">命令字串</td>
                      <td class="tdhead">显示信息</td>
                      <td class="tdhead">状态</td>
                      <td class="tdhead">创建人</td>
                      <td class="tdhead" style="width:200px;">创建时间</td>
                      <td class="tdhead" style="width:200px;">机器标志</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr style="cursor:pointer">
                        <td><c:choose>
                            <c:when test="${item.state eq '1'}"> &nbsp; </c:when>
                            <c:otherwise>
                              <input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>">
                            </c:otherwise>
                          </c:choose>
                        </td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.cmd_str}" /></td>
                        <td style="text-align:left" class="td3">&nbsp;
                          <c:out value="${item.show_info}" /></td>
                        <td style="text-align:left">&nbsp;
                          <c:if test="${item.state eq '0'}">正在等待</c:if>
                          <c:if test="${item.state eq '1'}">正在被处理</c:if>
                          <c:if test="${item.state eq '2'}">发布完成</c:if></td>
                        <td>&nbsp;
                          <c:out value="${item.create_by}" /></td>
                        <td>&nbsp;
                          <c:out value="${item.create_date}" /></td>
                        <td>&nbsp;
                          <c:out value="${item.machine_id}" /></td>
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