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
	openWindow("seatService.action?function=add", 750, 540);
}

function editFunction(id)
{
	openWindow("seatService.action?function=edit&id="+id, 750, 540);
}
</script>
<form id="qryparm" name="qryparm" action="seatService.action">
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
                  <span>机构代码：<select name="seatno">
                      <option value="-1" selected="selected">未选</option>
                      <c:forEach var="item" items="${data.seatlist}">
                       <option value="<c:out value='${item.seatno}'/>"><c:out value='${item.seatnm}'/>
                     </c:forEach>
                    </select>
                  <script language="javascript">setSelectSelected("seatno",'<c:out value='${param.seatno}'/>')</script>&nbsp;</span> 
                  <span>代销基金：<select name="fundid">
                     <option value="-1" selected="selected">未选</option>
                      <c:forEach var="item" items="${data.fundlist}">
                       <option value="<c:out value='${item.fundid}'/>"><c:out value='${item.fundnm}'/></option>
                      </c:forEach>
                     </select>
                   <script language="javascript">setSelectSelected("fundid",'<c:out value='${param.fundid}'/>')</script>&nbsp;</span> 
				  <span>开通业务：<select name="startserv">
                     <option value="-1" selected="selected">未选</option>
                     <c:forEach var="item" items="${data.servicelist}">
                      <option value="<c:out value='${item.dict_value}'/>"><c:out value='${item.value_desc}'/></option>
                     </c:forEach>
                     </select>
                    <script language="javascript">setSelectSelected("startserv",'<c:out value='${param.startserv}'/>')</script>&nbsp;</span>
                  <span>
                    <input type="submit" name="button" id="searchEnter" value="查询"  class="bt01" />
                    </span> </div>
                </div>
               
              </div>
              <div class="space"></div>
              <div class="databox2" style="height: 350px">
                <table width="99%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="tdhead" style="width:80px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                    <td class="tdhead">机构名称</td>
                    <td class="tdhead">代销基金</td>
                    <td class="tdhead">开通业务</td>
					<td class="tdhead">操作</td>
                  </tr>
                   <c:if test="${empty data.page.data}">
                   <tr>
                    <td colspan="5">&nbsp;没有数据!</td>
                  </tr>
                  </c:if>
                <c:forEach var="item" items="${data.page.data}">
		              <tr id="row_<c:out value='${item.id}'/>" ondblclick="editFunction('<c:out value="${item.seatno}"/>')" title="双击查看详细内容">
	                    <td>
						<input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)">
						</td>						
	                    <td style="text-align: left">&nbsp;<c:out value="${item.seatnm}" default="--"/>&nbsp;</td>
	                    <td style="text-align: left" class="td3">&nbsp;<c:out value="${item.fundnm}" default="--"/>&nbsp;</td>
	                    <td >&nbsp;<c:out value="${item.value_desc}" default="--"/>&nbsp;</td>
						<td>&nbsp;<a href="#" onclick="editFunction('<c:out value="${item.seatno}"/>')">编辑</a>&nbsp;</td>
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