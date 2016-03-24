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
	openWindow("fundReturn.action?function=add", 600, 440);
}

function editFunction(id)
{
	openWindow("fundReturn.action?function=edit&id="+id, 600, 440);
}
</script>
<form id="qryparm" name="qryparm" action="fundReturn.action">
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
                  <span>基金代码：<input type="text" name="fundid" value="${param.fundid}" class="input01"/>&nbsp;</span> 
                  <span>基金名称：<input type="text" name="fundnm" value="${param.fundnm}" class="input01"/>&nbsp;</span> 
				  <span>开始时间：<input type="text" name="startDate" id="startDate" class="input01" value="${param.startDate}" readonly/></span> 
				  <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'startDate'})"/>&nbsp;</span>
				  <span>结束时间：<input type="text" name="endDate" id="endDate" class="input01" value="${param.endDate}" readonly/></span> 
				  <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'endDate'})"/>&nbsp;</span>
                  <span>
                    <input type="submit" name="button" id="searchEnter" value="查询"  class="bt01" />
                    </span> </div>
                </div>
               
              </div>
              <div class="space"></div>
              <div class="databox2" style="height: 350px">
                <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="tdhead" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                    <td class="tdhead">基金名称</td>
                    <td class="tdhead">发布时间</td>
                    <td class="tdhead">最近一周</td>
                    <td class="tdhead">最近一月</td>
					<td class="tdhead">三个月收益</td>
					<td class="tdhead">半年收益</td>
					<td class="tdhead">一年收益</td>
					<td class="tdhead">二年收益</td>
					<td class="tdhead">今年以来</td>
					<td class="tdhead">成立以来</td>
					<td class="tdhead">状态</td>
					<td class="tdhead">操作</td>
                  </tr>
                   <c:if test="${empty data.page.data}">
                   <tr>
                    <td colspan="14">&nbsp;没有数据!</td>
                  </tr>
                  </c:if>
                <c:forEach var="item" items="${data.page.data}">
		              <tr id="row_<c:out value='${item.id}'/>" ondblclick="editFunction('<c:out value="${item.id}"/>')" title="双击查看详细内容">
	                    <td>
						<input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)">
						</td>						
	                    <td style="text-align: left">&nbsp;<c:out value="${item.fundshortnm}" default="--"/>&nbsp;</td>
	                    <td class="td3">&nbsp;<c:out value="${item.publish_date}" default="--"/>&nbsp;</td>
	                    <td >&nbsp;<c:out value="${item.weekly_return}" default="--"/>&nbsp;</td>
	                    <td>&nbsp;<c:out value="${item.monthly_return}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.quarter_return}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.halfyear_return}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.year_return}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.twoyear_yearreturn}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.fromyear_return}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.frombuild_return}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:choose><c:when test="${item.status=='N'}">正常</c:when><c:otherwise>撤销</c:otherwise></c:choose>&nbsp;</td>
						<td>&nbsp;<a href="#" onclick="editFunction('<c:out value="${item.id}"/>')">编辑</a>&nbsp;</td>
		              </tr>
       			 </c:forEach>
                </table>
                </div>
                <!-- 包含分页代码  -->
                <c:if test="${not empty data.page.data}">
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

