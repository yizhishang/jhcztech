<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
function saveDataFunction(url)
{
	var returnValue = openDialogWithScroll(url, 1300, 800);
    if (returnValue != null && returnValue.length > 0)
    {
    	var is_submit = getKeyword("is_submit", returnValue);
    	if(is_submit == "1")
    	{
    		location.reload();
    	}else{
    		url = getKeyword("function", returnValue) + ".action";
    		$.post(url, returnValue,function(data){
    			if(data.errorNo == 0)
    			{
    				if(window.confirm(data.errorInfo)){
    					location.reload();
    				}
    			}else{
    				alert(data.errorInfo);
    			}
    		},"json");
    	}
    }
}
</script>
<form id="qryparm" name="qryparm" action="jobManage.action" method="post">
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
			 <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>新建</a>
			  <a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a> 
			  </div>
              <div class="search">
                <div class="space"></div>
                <div>
                  <div style="float:left;">
                  <span>职位名称：<input type="text" name="position" value="${param.position}"  style="width: 200px" class="input01" maxlength="50"/>&nbsp;</span>
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
                    <td class="tdhead">职位名称</td>
                    <td class="tdhead">招聘部门</td>
                    <td class="tdhead">学历要求</td>
                    <td class="tdhead">薪水</td>
					<td class="tdhead">发布时间</td>
					<td class="tdhead">操作</td>
                  </tr>
                   <c:if test="${empty data.page.data}">
                   <tr>
                    <td colspan="6">&nbsp;没有数据!</td>
                  </tr>
                  </c:if>
                <c:forEach var="item" items="${data.page.data}">
		              <tr id="row_<c:out value='${item.jobid}'/>" onDblClick="editFunction('jobid=<c:out value="${item.jobid}"/>')" title="双击查看详细内容">
	                    <td class="td3">
						<input type="checkbox" name="id" id="check_<c:out value='${item.jobid}'/>" value="<c:out value='${item.jobid}'/>" onClick="setCheck(this)">
						</td>				
	                    <td style="text-align: left">&nbsp;<c:out value="${item.position}" default="--"/>&nbsp;</td>
	                    <td class="td3">&nbsp;<c:out value="${item.wantdept}" default="--"/></td>
	                    <td class="td3">&nbsp;<c:out value="${item.degree}" default="--"/>&nbsp;</td>
	                    <td class="td3">&nbsp;<c:out value="${item.salaryrange}" default="--"/>&nbsp;</td>
	                    <td class="td3">&nbsp;<c:out value="${item.issuedate}" default="--"/>&nbsp;</td>
						<td>&nbsp;<a href="#" onClick="editFunction('jobid=<c:out value="${item.jobid}"/>')">编辑</a>&nbsp;</td>
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