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

function commendFunction(name,funcitonId,commend){
	if(isChecked(name)){
		if($(":input[name='function']").length>0){
			$(":input[name='function']").val(funcitonId);
			if(window.confirm("确定删除选中数据？")){
				$("#commend").val(commend);
				$("#qryparm").submit();
			}
		}
	}else{
		alert("请选择需要删除的数据！");
	}
}

function addFunction()
{
	openWindow("expertManage.action?function=add", 700, 550);
}

function editFunction(id)
{
	openWindow("expertManage.action?function=edit&id="+id, 700, 515);
}

function editPlanFunction(queid)
{
	openWindow("expert.action?function=editQuestion&id="+queid, 700, 660);
}

</script>
<form id="qryparm" name="qryparm" action="expert.action">
  <input type="hidden" name="function" value="${param.function }">
  </input>
  <input type="hidden" name="form.pageUrl" value="${pageUrl}">
  </input>
  <input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }">
  </input>
  <input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }">
  </input>
  <input type="hidden" name="commend" id="commend" value=""/>
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
                <div class="label"> <a href="#" onClick="commendFunction('id','commendQuestion','1')"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>推荐</a> <a href="#" onClick="commendFunction('id','commendQuestion','0')" class="FourFont"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>取消推荐</a> <a href="#" onClick="deleteFunction('id','deleteQuestion')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a></div>
                <div class="search">
                  <div class="space"></div>
                  <div>
                    <div style="float:left;"> <span>关键字：
                      <input type="text" name="keyword" value="${param.keyword}" class="input01"/>
                      </span> 
					  <span>客户号：
                      <input type="text" name="clientid" value="${param.clientid}" class="input01"/>
                      </span>
					  <span>客户姓名：
                      <input type="text" name="clientname" value="${param.clientname}" class="input01"/>
                      </span>
					   <span>股票代码：
                      <input type="text" name="stockcode" value="${param.stockcode}" class="input01"/>
                      </span> 
					  <span>类别：
                      <select name="isanswer">
					  	<option value="">请选择</option>
						<option value="1">已回答</option>
						<option value="0">未回答</option>
					  </select>
					  <script type="text/javascript">setSelectSelected("isanswer","${param.isanswer}")</script>
                      </span> <span>
                      <input type="submit" name="button" id="button" value="查询"  class="bt01" />
                      </span> </div>
                  </div>
                </div>
                <div class="space"></div>
                <div class="databox2" style="height: 350px">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                    <tr>
                      <td class="tdhead" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                      <td class="tdhead" style="width:100px;">客户号</td>
                      <td class="tdhead" style="width:100px;">客户名称</td>
					  <td class="tdhead">相关股票</td>
                      <td class="tdhead" style="width:100px;">专家名称</td>
                      <td class="tdhead" style="width:85px;">状态</td>
					  <td class="tdhead" style="width:85px;">推荐</td>
                      <td class="tdhead" style="width:190px;">提问时间</td>
                      <td class="tdhead" style="width:100px;">&nbsp;&nbsp;操作&nbsp;&nbsp;</td>
                    </tr>
                    <c:forEach var="item" items="${data.page.data}">
                      <tr style="cursor:pointer" onDblClick="javascript:editPlanFunction(<c:out value='${item.queid}'/>)" title="双击查看详细内容">
                        <td><input type="checkbox" name="id" id="check_<c:out value='${item.queid}'/>" value="<c:out value='${item.queid}'/>" onClick="setCheck(this)">
                        </td>
                        <td class="td3" style="text-align:left">&nbsp;
                          <c:out value="${item.clientid}" /></td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.clientname}" /></td>
						 <td style="text-align:left">&nbsp;
                          <c:out value="${item.stockcode}" /></td>
                        <td style="text-align:left">&nbsp;
                          <c:out value="${item.expname}" /></td>
                        <td><c:choose>
                            <c:when test="${item.isanswer==1}">已回答</c:when>
                            <c:otherwise><font color="red">未回答</font></c:otherwise>
                          </c:choose></td>
						  <td><c:choose>
                            <c:when test="${item.iscommend==1}"><font color="red">是</font></c:when>
                            <c:otherwise>否</c:otherwise>
                          </c:choose></td>
                        <td>&nbsp;
                          <c:out value="${item.quetime}" /></td>
                        <td >&nbsp;<a href="#" onClick="editPlanFunction(<c:out value='${item.queid}'/>)">编辑</a></td>
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