<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<script type="text/javascript">	
	if(window.frameElement!=null && document.all != null)
	{
		window.frameElement.attachEvent("onresize",winResize);
	}
	else if(document.all != null)
	{
		window.attachEvent("onresize",winResize);
	} 
	else
	{
		window.onresize=winResize;
	}
	function winResize()
	{
		$("#divContainer").css({"height":$(window).height() - 90});
	}
	
	function addFunction()
	{
		$("#enterForm").submit();
	}
	
	function closeFunction()
	{
		window.close();
	}
	
	$(document).ready(function(){
		winResize();
		$.formValidator.initConfig({formid:"enterForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#title").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"标题不能为空,请确认"});
		$("#type").formValidator().inputValidator({min:1,onerror:"请选择类型"});
		$("#branchNo").formValidator().inputValidator({min:1,onerror:"请选择营业部"});
	});
</script>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td><div class="title">
        <p><img src="${ctxPath }/admin/images/ico04.gif" />添加营业部信息</p>
      </div></td>
  </tr>
  <tr>
    <td class="edinner" valign="top" style="width:100%"><form action="branchMaintain.action" method="post" id="enterForm" target="hiddenFrame">
        <input type="hidden" name="function" id="function" value="add"/>
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td></td>
          </tr>
          <tr>
            <td><div class="space"></div>
              <div class="label"> <a href="#" onClick="addFunction();"><img src="${ctxPath }/admin/images/icon018a16.gif" border="0"/>保存</a> <a href="#" onClick="closeFunction();"><img src="${ctxPath }/admin/images/closeimg.gif" border="0"/>关闭</a> </div></td>
          </tr>
          <tr>
            <td valign="top"><div class="space"></div>
              <div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
                <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
                  <tr>
                    <td width="120" class="label"><span class="DetailTagText">标题</span>：</td>
                    <td ><input type="text" name="form.title" id="title" class="input02" style="width:250px;"/>
                      <font color="#FF0000">*</font></td>
                  </tr>
                  <tr>
                    <td class="label"><span class="DetailTagText">类型</span>：</td>
                    <td><select name="form.type" id="type" style="width:150px;">
                        <option value="">--请选择--</option>
                        <option value="1">营业部公告</option>
                        <option value="2">营业部博客</option>
                      </select></td>
                  </tr>
				  <c:choose>
				  <c:when test="${form.isSystemAdmin == 1}">
                  <tr>
                    <td class="label"><span class="DetailTagText">营业部</span>：</td>
                    <td><select name="form.branchno" id="branchNo" style="width:150px;">
                        <option value="">--请选择--</option>
                        <c:forEach var="item" items="${data.branchs}">
                          <option value="${item.branchno}">${item.branchname}</option>
                        </c:forEach>
                      </select></td>
                  </tr>
				  </c:when>
				  <c:otherwise>
				  <input type="hidden" name="form.branchno" value="${form.userBranchNo}"/>
				  </c:otherwise>
				  </c:choose>
                  <tr>
                    <td class="label"><span class="DetailTagText">内容</span>：</td>
                    <td ><input name="form.content" type="hidden" id="content" value="<c:out value='${form.content}'/>" />
                      <iframe id="eWebEditor_trainfo" src="editor/standard/ewebeditor.htm?id=content&style=coolblue" frameborder="0" scrolling="No" width="800" height="500"></iframe></td>
                  </tr>
                  <tr>
                    <td colspan="4" style="height:8px;"></td>
                  </tr>
                </table>
              </div></td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>