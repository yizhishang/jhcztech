<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"友情链接站点名不能为空,请确认"});
		$("#url").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"友情链接站点URL不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="link.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="${param.function}"/>
<input type="hidden" name="group_id" value="<c:out value='${param.id}'/>" />
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />添加友情链接</p>
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
                <td width="120" class="label"><span class="DetailTagText">友情链接站点名</span>：</td>
                <td><input type="text" name="form.name" id="name" class="input02" style="width:116px;" value=""/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">友情链接站点URL</span>：</td>
                <td><input type="text" name="form.url" id="url" class="input02" style="width:116px;" value=""/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">友情链接站点状态</span>：</td>
                <td> 有效:
                                            <input type="radio" checked name="form.state" class="DetailInputBox" value="1" />
                                            无效:
                                            <input type="radio" name="form.state" class="DetailInputBox" value="0" />
					<font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td>
                	<input type="submit" name="enterForm" id="enterForm" value="提交" class="bt04"/>&nbsp;
                <input type="button" id="close" name="close"value="关闭" class="bt04"/>
                </td>
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
