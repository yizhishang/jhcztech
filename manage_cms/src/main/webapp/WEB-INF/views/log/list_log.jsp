<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<form id="qryparm" name="qryparm" action="doDefault.action" method="post">
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
			  <div class="label">
                <a href="#" onClick="deleteFunction('id','delete')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除</a>
				<a href="#" class="FourFont" onClick="deleteAllFunction('deleteAll')"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>删除全部</a>
              </div>
              <div class="space"></div>
              <div class="search" style="float:left;">
                  <span>用户标识：<input type="text" name="uid" value="${param.uid}" class="input01"/></span> 
				  <span>&nbsp;操作名称：<input type="text" name="keyword" value="${param.keyword}" class="input01"/></span> 
                  <span><input type="submit" name="button" id="searchEnter" value="查询"  class="bt01" onClick="enterForm();"/></span>
              </div>
              <div class="space"></div>
              <div class="databox">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="tdhead" style="width:45px;"><input name="tk_selcnt" type="checkbox" id="checkbox2" onClick="checkAll(this,'id');"/></td>
                    <td class="tdhead" style="width:100px;">用户标识</td>
                    <td class="tdhead" style="width:150px;">操作名称</td>
                    <td class="tdhead">操作描述</td>
                    <td class="tdhead" style="width:130px;">IP地址</td>
					<td class="tdhead" style="width:180px;">操作时间</td>
                  </tr>
                </table>
                </div>
                <div class="databox2">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                <c:forEach var="item" items="${data.page.data}">
		              <tr>
	                    <td style="width:45px;">
						<input type="checkbox" name="id" id="check_${item.id}" value="${item.id}" onClick="setCheck(this)">
						</td>						
	                    <td style="width:100px;text-align:center">${item.createBy}</td>
	                    <td style="width:150px;text-align:center" class="td3">${item.operate}</td>
	                    <td style="text-align:left">&nbsp;<c:out value="${item.description}" /></td>
	                    <td style="width:130px;">&nbsp;<c:out value="${item.ip}" /></td>
						<td style="width:180px;">&nbsp;<c:out value="${item.createDate}" /></td>
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