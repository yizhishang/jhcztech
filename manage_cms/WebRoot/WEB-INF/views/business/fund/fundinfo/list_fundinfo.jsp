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
	openMaxWindowWithScroll("fundInfo.action?function=add");
}

function editFunction(id)
{
	openMaxWindowWithScroll("fundInfo.action?function=edit&id="+id);
}
</script>
<form id="qryparm" name="qryparm" action="fundInfo.action">
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
                  <span>模糊查询：<input type="text" name="key" value="${param.key}" class="input01"/>&nbsp;</span> 
                  <span>状态：<select name="stauts">
                        <option value="N" selected="selected">正常</option>
                        <option value="C">撤销</option>                         
                       </select>
                  <script language="javascript">setSelectSelected("stauts",'<c:out value='${param.stauts}'/>')</script>&nbsp;</span> 
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
                    <td class="tdhead">基金代码</td>
                    <td class="tdhead">基金简称</td>
                    <td class="tdhead">成立日期</td>
                    <td class="tdhead">管理人名称</td>
					<td class="tdhead">托管人名称</td>
					<td class="tdhead">深圳交易所代码</td>
					<td class="tdhead">成立规模</td>
					<td class="tdhead">净值展示类型</td>
					<td class="tdhead">排序</td>
					<td class="tdhead">状态</td>
					<td class="tdhead">操作</td>
                  </tr>
                   <c:if test="${empty data.page.data}">
                   <tr>
                    <td colspan="14">&nbsp;没有数据!</td>
                  </tr>
                  </c:if>
                <c:forEach var="item" items="${data.page.data}">
		              <tr id="row_<c:out value='${item.fundid}'/>" ondblclick="editFunction('<c:out value="${item.fundid}"/>')" title="双击查看详细内容" >
	                    <td>
						<input type="checkbox" name="id" id="check_<c:out value='${item.fundid}'/>" value="<c:out value='${item.fundid}'/>" onClick="setCheck(this)">
						</td>						
	                    <td>&nbsp;<c:out value="${item.fundid}" default="--"/>&nbsp;</td>
	                    <td class="td3" style="text-align: left">&nbsp;<c:out value="${item.fundshortnm}" default="--"/>&nbsp;</td>
	                    <td >&nbsp;<c:choose><c:when test="${empty item.establish_date}">--</c:when><c:otherwise><c:out value="${item.establish_date.substring(0,4)}"/>-<c:out value="${item.establish_date.substring(4,6)}"/>-<c:out value="${item.establish_date.substring(6,8)}"/></c:otherwise></c:choose>&nbsp;</td>
	                    <td style="text-align: left">&nbsp;<c:out value="${item.mgrname}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.custodianname}" default="--"/>&nbsp;</td>
						<td>&nbsp;<c:out value="${item.szexch_code}" default="--"/>&nbsp;</td>
						<td style="text-align: left">&nbsp;<c:out value="${item.fundsize}" default="--"/>&nbsp;</td>
						 <c:choose>
		                	<c:when test="${item.fundshowtp == '1'}">
		                		<td nowrap>&nbsp;股票型</td>
		                	</c:when>
		                	<c:when test="${item.fundshowtp == '2'}">
		                		<td nowrap>&nbsp;债券型</td>
		                	</c:when>
		                	<c:when test="${item.fundshowtp == '3'}">
		                		<td nowrap>&nbsp;货币型</td>
		                	</c:when>
		                	<c:when test="${item.fundshowtp == '4'}">
		                		<td nowrap>&nbsp;LOF基金</td>
		                	</c:when>
		                	<c:otherwise>
		                		<td nowrap>&nbsp;<c:out value="${item.fundshowtp}"/></td>
		                	</c:otherwise>
		                </c:choose>	
						<td>&nbsp;<c:out value="${item.sn}" default="--"/>&nbsp;</td>
						<c:choose>
			                <c:when test="${item.status == 'N'}">
			                	<td nowrap>&nbsp;正常</td>
			                </c:when>
			                <c:when test="${item.status == 'C'}">
			                	<td nowrap>&nbsp;撤销</td>
			                </c:when>
			                <c:otherwise>
		                		<td nowrap>&nbsp;<c:out value="${item.status}"/></td>
		                	</c:otherwise>
		                </c:choose>
						<td>&nbsp;<a href="#" onclick="editFunction('<c:out value="${item.fundid}"/>')">编辑</a>&nbsp;</td>
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

