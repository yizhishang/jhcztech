<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@page import="com.yizhishang.base.util.RequestHelper"%>
<%@page import="com.yizhishang.base.util.StringHelper"%>
<%@ include file="/admin/common/header.jsp" %>
<%
	String siteNo = RequestHelper.getString(request,"siteNo");
	if(StringHelper.isEmpty(siteNo))
	{
		siteNo = "main";
	}
%>
<style type="text/css">
.setbox{
	border:2px solid #81c4fc;	
	}
.btn04{
	background:url(../images/bg07.gif) repeat-x;
	height:22px;
	border:1px solid #bbb;
	display:inline-block;
	padding:0px 10px;
	color:#000;
	line-height:22px;
	vertical-align:middle;
	margin-left:5px;}
.settitle{
	background:url(../images/bg22.gif) repeat-x;
	height:26px;
	line-height:26px;
	padding-left:20px;
	font-weight:bold;
	color:#1f447b;
	}
</style>
<script type="text/javascript" language="javascript">
/**
 * format checked string 
 * format：1003|1093[queryData:add:modify:delete:export:editRight]
 */
function formatRoleStr(checkData)
{
	var roleStr = "";
	for(var i in checkData)
	{
		roleStr += "" + i;
		if(checkData[i].length > 0)
		{
			roleStr += "[";
			for(var j=0;j<checkData[i].length;j++)
			{
				roleStr += checkData[i][j];
				if(j < checkData[i].length - 1)
				{
					roleStr += ":";
				}
			}
			roleStr += "]";
		}
		roleStr += "|";
	}
	if(roleStr.indexOf("|") > 0)
	{
		roleStr = roleStr.substr(0,roleStr.length - 1);
	}
	return roleStr;
}
$(function(){
	/**
	 * excute 
	 */
	$("#addRight").click(function(){
		/**
		 * submit
		 */
		var checkData = rightFrame.window.getCheckedData(true);
		if(checkData === null || checkData === undefined || checkData === "")
		{
			alert("还没有选择节点");
		}
		else
		{
			var roleStr = formatRoleStr(checkData);
			checkData = null;
			
			$("#userIdStr").val(roleStr);
			$("#qryparm").submit();
			//submitForm("qryparm", "editArticleRoleRight", true);
		}
	});

	/**
	 * delete
	 */
	$("#delRight").click(function(){
		/**
		 * is or not checked before
		 */
		var checkData = leftFrame.window.getCheckedData(true);
		if(checkData === null || checkData === undefined || checkData === "")
		{
			alert("还没有选择节点");
		}
		else
		{
			/**
			 * submit
			 */
			checkData = leftFrame.window.getCheckedData(false);//获取未勾选的节点
			var roleStr = formatRoleStr(checkData);
			checkData = null;
			$("#userIdStr").val(roleStr);
			$("#qryparm").submit();
		}
	});
	$("#siteNo").change(function(){
		location.href='doEditArticleRoleRight.action?roleId=${param.roleId}&siteNo=' + $(this).val();
	});
	$("#siteNo").val("<%=siteNo%>");
});
</script>
<body>
<form id="qryparm" name="qryparm" action="editArticleRoleRight.action" method="post">
  <input type="hidden" name="function" value="${param.function }"/>
  <input type="hidden" name="manageCatalogId" value="${param.manageCatalogId }"/>
  <input type="hidden" name="subManageCatalogId" value="${param.subManageCatalogId }"/>
  <input type="hidden" name="roleId" value="<%=RequestHelper.getString(request,"roleId") %>">
  <input type="hidden" name="userIdStr" id="userIdStr" value=""/>
  <input type="hidden" name="successPage" value="doEditArticleRoleRight.action?roleId=<%=RequestHelper.getString(request,"roleId") %>&siteNo=<%=siteNo %>"/>
  <div class="c_ie6_out">
    <div class="c_ie6_in">
      <div class="Wrapper">
        <div class="inner">
          <div class="innercontent">
            <div class="cl"></div>
            <div class="contentbox">
              <div class="content">
                <jsp:include page="/WEB-INF/views/role/include/menu.jsp" flush="true">
					<jsp:param name="index" value="3"/>
					<jsp:param name="roleId" value="<%=RequestHelper.getString(request,"roleId") %>"/>
					<jsp:param name="siteNo" value="<%=siteNo %>"/>
				</jsp:include>
                <div class="space"></div>
                <div id="content">
                  <div class="eb0">
                    <div class="label">站点：
                    <select name="siteNo" id="siteNo">
                      <c:forEach var="item" items="${data.siteList}">
                        <option value="${item.siteNo}">${item.name}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <table width="100%" border="0" cellspacing="0" cellpadding="0" >
                    <tr>
                      <td width="43%"><div class="setbox">
                          <div class="settitle">已分配的权限</div>
                          <ul class="children_ul">
                            <iframe name="leftFrame" id="leftFrame" src="doCatalogTree.action?roleId=<%=RequestHelper.getString(request,"roleId") %>&siteNo=<%=siteNo %>&type=0&role_type=1" width="99%" height="380"></iframe>
                          </ul>
                        </div></td>
                      <td  align="center"><div><a class="btn04" href="#" id="addRight">分配<<</a></div>
                        <div style="margin-top:20px;"><a class="btn04" href="#" id="delRight">删除>></a></div></td>
                      <td width="43%"><div class="setbox">
                          <div class="settitle">待分配的权限</div>
                          <ul class="children_ul">
                            <iframe name="rightFrame" id="rightFrame" src="doCatalogTree.action?roleId=<%=RequestHelper.getString(request,"roleId") %>&siteNo=<%=siteNo%>&type=1&role_type=1" width="99%" height="380"></iframe>
                          </ul>
                        </div>
                      </td>
                    </tr>
                  </table>
                </div>
              </div>
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