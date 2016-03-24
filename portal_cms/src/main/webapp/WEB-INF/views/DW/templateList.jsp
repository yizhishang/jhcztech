<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<style type="text/css"> 
<!-- 
html,body{height:100%; margin:0px;} 
.databox{width:99%; height:98%;border:1px solid #a8d4e8;z-index:1;} 
--> 
</style> 
<body>
  <div class="databox">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1" id="roleTab">
    <tr>
      <td class="td1" style="width:45px;">&nbsp;
      </td>
      <td class="tdhead">模板ID</td>
      <td class="tdhead">模板名称</td>
      <td class="tdhead">模版类型</td>
      <td class="tdhead" style="width:170px;">创建时间</td>
    </tr>
    <c:forEach var="item" items="${data}">
      <tr>
        <td><input type="radio" name="templateInfo" value="${item.name}_${item.id}"/>
        </td>
        <td class="td3" style="text-align:left">&nbsp;${item.id }</td>
        <td class="td3" style="text-align:left">&nbsp;
          <c:out value="${item.name}" /></td>
        <td><c:if test="${item.type eq '1'}">首页模板</c:if>
          <c:if test="${item.type eq '2'}">信息列表</c:if>
          <c:if test="${item.type eq '3'}">信息细览</c:if>
          <c:if test="${item.type eq '4'}">其它模版</c:if></td>
        <td><c:out value="${item.create_date}" /></td>
      </tr>
    </c:forEach>
  </table>
</div>
<script type="text/javascript">
function GetResult()
{
	alert(window.parent.GetResult());
}
</script>
</body>
</html>