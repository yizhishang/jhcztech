<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">

function reloadFunction()
{
	hiddenForm["function"].value = "reload";
    hiddenForm.submit();
}

function enterForm()
{
	$(":input[name='function']").val("");
}
</script>
<form id="qryparm" name="qryparm" action="doDefault.action" >
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
              <div class="label">
               	<a href="#" onClick="addFunction(null, null, '400px', '200px');"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a> 
				<a href="#" onClick="reloadFunction()" class="FourFont"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>更新内存</a>
                <a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
              </div>
              <div class="search">
                <div class="space"></div>
                <div style="float:left;">
	                  <span>关键字：<input type="text" name="keyword" value="${param.keyword}" class="input01"/></span> 
	                  <span><input type="submit" name="button" id="button" value="查询"  class="bt01" onClick="enterForm();"/></span>
                 </div>
              </div>
              <div class="space"></div>
              <div class="databox">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="tdhead" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id')"/></td>
                    <td class="tdhead" style="width:265px;">参数名称</td>
                    <td class="tdhead" style="width:257px;">参数标题</td>
                    <td class="tdhead" style="width:91px;">参数值</td>
                    <td class="tdhead" style="width:296px;">参数描述信息</td>
					<td class="tdhead" style="width:118px;">是否系统参数</td>
				 	<td class="tdhead" style="">操作</td>
                  </tr>
                </table>
                </div>
                <div class="databox2" style="height: 400px">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                <c:forEach var="item" items="${data.page.data}">
		              <tr style="cursor:pointer" onDblClick="javascript:editFunction(<c:out value='${item.id}'/>)" title="双击查看详细内容">
	                    <td style="width:45px;">
							<c:if test="${item.isSystem == 0}">
							<input type="checkbox" name="id" id="check_<c:out value='${item.id}'/>" value="<c:out value='${item.id}'/>" onClick="setCheck(this)">
							</c:if>
							<c:if test="${item.isSystem != 0}">
							<input type="checkbox" disabled="true">
							</c:if>
						</td>						
	                    <td style="width:265px;text-align:left">&nbsp;<c:out value="${item.name}" /></td>
	                    <td style="width:257px;text-align:left" class="td3">&nbsp;<c:out value="${item.caption}" /></td>
	                    <td style="width:91px;text-align:left" >&nbsp;<c:out value="${item.value}" /></td>
	                    <td style="width:296px;text-align:left" > &nbsp;<c:out value="${item.description}" /></td>
						<td style="width:118px;"> &nbsp;<c:if test="${item.isSystem eq '0'}">否</c:if>
                    		<c:if test="${item.isSystem eq '1'}">是</c:if></td>
						<td > &nbsp;<a href="#" onClick="editFunction('id=${item.id}')">编辑</a></td>
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
<div styoe="display:none">
    <iframe name="hiddenFrame" width="0" height="0"></iframe>
    <form name="hiddenForm" action="config.action" target="hiddenFrame">
        <input type="hidden" name="function" value="">
    </form>
</div>
</body>
</html>

