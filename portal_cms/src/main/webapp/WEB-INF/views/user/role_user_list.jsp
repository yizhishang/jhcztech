<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<base target="_self">
<script type="text/javascript">
	$(document).ready(function(){
		$("#saveBtn").click(function(){
			var bool = false;
			$("input[name='id']").each(function(){
				if($(this).attr("checked"))
				{
					bool = true;
					return;
				}
			});
			if(!bool)
			{
				alert("请选择要添加的用户");
			}
			else
			{
				var value = "";
				$(":checkbox").each(function(){
					if($(this).attr("checked") && $.trim($(this).val()).length > 0)
					{
						value = value + $(this).val() + "|";
					}
				});
				window.returnValue = value;
       		    window.close();
			}
		});
		$("#qryparm").submit(function(id){
			return true;
		});
		$("#close").click(function(){
			window.close();
		});
	});
</script>
<body>
<form id="qryparm" name="qryparm" action="userList.action" method="post">
  <input type="hidden" name="function" id="function" value="${param.function }">
  </input>
  <input type="hidden" name="roleId" id="roleId" value="${param.roleId}">
  </input>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />添加用户</p>
        </div></td>
    </tr>
    <tr>
      <td class="edinner"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="5"></td>
          </tr>
          <tr>
            <td><div class="search">
                <div class="space"></div>
                <div> <span>&nbsp;&nbsp;关键词：</span> <span>
                  <input type="text" name="keyword" id="keyword" value="${param.keyword}" class="input01"/>
                  </span> <span>
                  <input type="submit" name="button" id="button" value="查询" class="bt01" />
                  </span> </div>
              </div></td>
          </tr>
          <tr>
            <td height="5"></td>
          </tr>
          <tr>
            <td><div class="databox">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tab1">
                  <tr>
                    <td class="td1"><input type="checkbox" id="check_all" value=""  onclick="checkAll(this,'id');"/></td>
                    <td class="tdhead">用户名</td>
                    <td class="tdhead">用户角色</td>
                    <td class="tdhead">用户状态</td>
                  </tr>
                  <c:forEach var="item" items="${data.page.data}">
                    <tr>
                      <td><input type="checkbox" name="id" id="check_${item.id}" value="${item.id}" onClick="setCheck(this)"/></td>
                      <td>&nbsp;
                        <c:out value="${item.uid}" /></td>
                      <td class="td3">&nbsp;
                        <c:out value="${item.name}" /></td>
                      <td><c:if test="${item.state eq '0'}"><font color="#ff0000">关闭</font></c:if>
                        <c:if test="${item.state eq '1'}">开放</c:if></td>
                    </tr>
                  </c:forEach>
                  <tr>
                    <td colspan="4" class="td2"><jsp:include flush="true" page="/WEB-INF/views/include/minPage.jsp"></jsp:include>
                    </td>
                  </tr>
                  <tr>
                    <td class="label">&nbsp;</td>
                    <td colspan="3"><input type="button" name="saveBtn" id="saveBtn" value="保存" class="bt04"/>
                      &nbsp;
                      <input type="button" name="close" id="close" value="关闭" class="bt04"/>
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