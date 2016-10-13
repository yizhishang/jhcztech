<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<script type="text/javascript">
 function selectFunction()
 {
 	var value = "";
	$(":checkbox[@checked]").each(function(){
		if($.trim($(this).val()).length > 0)
		{
			value = value + $(this).val() + "|";
		}
	});
	if($.trim(value).length == 0)
	{
		alert("请选择要删除的对象！");
		return;
	}
    window.returnValue = value;
    window.close();
 }
</script>
<base target="_self">
<body>
<form id="qryparm" name="qryparm" action="article.action" method="post">
  <input type="hidden" name="function" id="function" value="${param.function }">
  </input>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />作者维护</p>
        </div>
    </td>
  </tr>
  <tr>
    <td class="edinner"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="5"></td>
      </tr>
      <tr>
        <td>
        	<div class="search">
                <div class="space"></div>
                <div>
                  <span>&nbsp;&nbsp;关键词：</span> <span>
                  <input type="text" name="keyword" id="keyword" class="input01" value="${param.keyword}"/>
                    </span> <span>
                    <input type="submit" name="button" id="button" value="查询" class="bt01" />
                    </span>
					 <span>
                    <input type="button" name="button" id="button" value="删除" class="bt01" onClick="selectFunction();"/>
                    </span>
					<span>
                    <input type="button" name="button" id="button" value="关闭" class="bt01" onClick="window.close();"/>
                    </span>
              </div>
            </div>
        </td>
      </tr>
      <tr>
        <td height="5"></td>
      </tr>
      <tr>
        <td><div class="databox">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
            <tr>
              <td class="td1"><input type="checkbox" id="check_all" value=""  onclick="checkAll(this,'id');"/></td>
              <td class="tdhead" style="width:">作者</td>
              <td class="tdhead">创建时间</td>
            </tr>
			<c:forEach var="item" items="${data.page.data}">
            <tr>
              <td><input type="checkbox" name="id" value="<c:out value='${item.id}'/>" id="check_<c:out value='${item.id}'/>" onClick="setCheck(this)"/></td>
              <td><c:out value="${item.name}" /></td>
              <td class="td3"><c:out value="${item.create_date}" /></td>
            </tr>
            </c:forEach>

            <tr>
              <td colspan="3" class="td2">
			  <jsp:include flush="true" page="/WEB-INF/views/include/minPage.jsp"></jsp:include>
              </td>
            </tr>
          </table>
        </div></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
