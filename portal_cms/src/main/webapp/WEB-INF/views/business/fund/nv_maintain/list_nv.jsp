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
	openWindow("NVMaintain.action?function=add", 600, 360);
}

function editFunction(id)
{
	openWindow("NVMaintain.action?function=edit&id="+id, 600, 360);
}
</script>
<form id="qryparm" name="qryparm" action="NVMaintain.action">
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
				  <span>开始时间：<input type="text" name="starttime" id="starttime" class="input01" value="${param.starttime}" readonly/></span> 
				  <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'starttime'})"/>&nbsp;</span> 
				  <span>结束时间：<input type="text" name="endtime" id="endtime" class="input01" value="${param.endtime}" readonly/></span> 
				  <span><img src="${ctxPath }/admin/images/pic13.gif" width="17" height="15" style="cursor:pointer" onClick="WdatePicker({el:'endtime'})"/>&nbsp;</span> 
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
                    <td class="tdhead">净值日期</td>
                    <td class="tdhead">基金代码</td>
                    <td class="tdhead">基金名称</td>
                    <td class="tdhead">单位净值</td>
					<td class="tdhead">累计净值</td>
					<td class="tdhead">收益率</td>
					<td class="tdhead">周收益率</td>
					<td class="tdhead">日增长率</td>
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
	                    <td>&nbsp;<c:choose><c:when test="${empty item.settledate}">--</c:when><c:otherwise><c:out value="${item.settledate.substring(0,4)}" />-<c:out value="${item.settledate.substring(4,6)}" />-<c:out value="${item.settledate.substring(6,8)}" /></c:otherwise></c:choose>&nbsp;</td>
	                    <td class="td3">&nbsp;<c:out value="${item.fundid}" default="--"/>&nbsp;</td>
	                    <td style="text-align: left">&nbsp;<c:out value="${item.fundnm}" default="--"/>&nbsp;</td>
	                    <td>&nbsp;<c:out value="${item.nav}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.sumofnav}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.incomerate}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.weekincomerate}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.daily_growth_rate}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.value_desc}" default="--"/>&nbsp;</td>
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

