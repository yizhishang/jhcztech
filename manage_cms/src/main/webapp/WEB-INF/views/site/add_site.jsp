<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#siteNo").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"站点不能为空,请确认"});
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"站点名称不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="site.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="add"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />添加站点</p>
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
                <td width="120" class="label"><span class="DetailTagText">站点编号</span>：</td>
                <td><input type="text" name="form.siteNo" id="siteNo" class="input02" style="width:116px;" value="<c:out value='${form.siteNo}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">站点名称</span>：</td>
                <td><input type="text" name="form.name"  id="name" class="input02" style="width:116px;" value="<c:out value='${form.name}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">站点标志</span>：</td>
                <td><select name="form.isMain" style="width:116px;">
                                                        <option value="0" selected="selected">非主站</option>
                                                        <option value="1">主站</option>
                                                    </select></td>
              </tr>
              <!--
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">发布路径</span>：</td>
                <td><input type="text" name="form.publishPath" id="publishPath" class="input02" style="width:116px;" value="<c:out value='${form.publishPath}'/>"/></td>
              </tr>
              -->
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">站点描述</span>：</td>
                <td><textarea class="input02" name="form.description"  style="width:350px;height:100px;"><c:out value='${form.description}'/></textarea></td>
              </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td>
                	<input type="button" name="enterForm" id="enterForm" value="提交" class="bt04"/>&nbsp;
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
