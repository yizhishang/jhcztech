<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"名称不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="link.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="edit"/>
<input type="hidden" size="30" name="form.id" value="<c:out value='${form.id}'/>">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />查看友情链接类别 </p>
        </div>
    </td>
  </tr>
  <tr>
    <td class="edinner"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
      
      <tr>
        <td>
			<div class="space"></div>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">友情连接类别名</span>：</td>
                <td><c:out value="${form.name}"/></td>
              </tr>		
			  <tr>
                <td width="120" class="label">创建时间：</td>
                <td><c:out value="${form.create_date}"/></td>
              </tr>
			  <tr>
                <td width="120" class="label">创建者用户帐号：</td>
                <td><c:out value="${form.create_by}"/></td>
              </tr>
			  <tr>
                <td width="120" class="label">修改时间：</td>
                <td><c:out value="${form.modified_date}"/></td>
              </tr>
			  <tr>
                <td width="120" class="label">修改者用户帐号：</td>
                <td><c:out value="${form.modified_by}"/></td>
              </tr>	  
			  <tr>
                <td width="120" class="label">站点编号：</td>
                <td><c:out value="${form.siteno}"/></td>
              </tr>
              
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
            </table>
          </td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>
