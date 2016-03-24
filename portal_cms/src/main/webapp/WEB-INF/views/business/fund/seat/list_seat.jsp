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
function addFunction()
{
	openWindow("seat.action?function=add", 500, 250);
}

function editFunction(id)
{
	openWindow("seat.action?function=edit&id="+id, 500, 250);
}
</script>
<form id="qryparm" name="qryparm" action="seat.action">
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
			 <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a>
			  <a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> 
			  <a href="#" class="FourFont" onClick="deleteAllFunction('deleteAll')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除全部</a></div>
              <div class="search">
                <div class="space"></div>
                <div>
              
                  <div style="float:left;">
                  <span>机构代码：<input type="text" name="seatno" value="${param.seatno}" class="input01"/>&nbsp;</span> 
                  <span>机构名称：<input type="text" name="seatnm" value="${param.seatnm}" class="input01"/>&nbsp;</span> 
				  <span>机构状态：<select name="status">
                    <option value="0" selected="selected">未选</option>
	                <option value="N">正常</option>
	                <option value="D">删除</option>                         
                   </select>
                  <script language="javascript">setSelectSelected("status",'<c:out value='${param.status}'/>')</script>&nbsp;</span>
                  <span>机构类型：<input type="text" name="seattp" value="${param.seattp}" class="input01"/>&nbsp;</span> 
                  <span>
                    <input type="submit" name="button" id="searchEnter" value="查询"  class="bt01" />
                    </span> </div>
                </div>
               
              </div>
              <div class="space"></div>
              <div class="databox2" style="height: 350px">
                <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="tdhead" ><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                    <td class="tdhead">机构代码</td>
                    <td class="tdhead">机构名称</td>
                    <td class="tdhead">机构状态</td>
                    <td class="tdhead">机构类型</td>
					<td class="tdhead">操作</td>
                  </tr>
                   <c:if test="${empty data.page.data}">
                   <tr>
                    <td colspan="6">&nbsp;没有数据!</td>
                  </tr>
                  </c:if>
                <c:forEach var="item" items="${data.page.data}">
		              <tr id="row_<c:out value='${item.seatno}'/>" ondblclick="editFunction('<c:out value="${item.seatno}"/>')" title="双击查看详细内容">
	                    <td style="width: 5%">
						<input type="checkbox" name="id" id="check_<c:out value='${item.seatno}'/>" value="<c:out value='${item.seatno}'/>" onClick="setCheck(this)">
						</td>						
	                    <td style="width: 15%">&nbsp;<c:out value="${item.seatno}" default="--"/>&nbsp;</td>
	                    <td class="td3" style="text-align: left;width: 30%">&nbsp;<c:out value="${item.seatnm}" default="--"/>&nbsp;</td>
	                   <c:choose>
		                	<c:when test="${item.status == 'N'}">
		                		<td nowrap style="width: 15%">&nbsp;正常</td>
		                	</c:when>
		                	<c:when test="${item.status == 'D'}">
		                		<td nowrap style="width: 15%">&nbsp;删除</td>
		                	</c:when>
		                	<c:otherwise>
		                		<td nowrap style="width: 15%">&nbsp;未知</td>
		                	</c:otherwise>
		                </c:choose>
	                    <td style="width: 25%">&nbsp;<c:out value="${item.seattp}" default="--"/>&nbsp;</td>
						<td style="width: 10%">&nbsp;<a href="#" onclick="editFunction('<c:out value="${item.seatno}"/>')">编辑</a>&nbsp;</td>
		              </tr>
       			 </c:forEach>
                </table>
                </div>
                <!-- 包含分页代码  -->
                <c:if test="${not empty data.page.data }">
                <jsp:include flush="true" page="/WEB-INF/views/include/page.jsp"></jsp:include>
                </c:if>
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

