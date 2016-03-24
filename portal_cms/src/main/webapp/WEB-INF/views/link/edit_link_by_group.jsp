<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"友情链接站点名不能为空,请确认"});
		$("#url").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"友情链接站点URL不能为空,请确认"});
		$("#orderline").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"排序值不能为空,请确认"}).regexValidator({regexp:regexEnum.intege1,onerror:"排序值输入错误，请输入数字"});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="link.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="${param.function}"/>
<input type="hidden" name="form.group_id" value="<c:out value='${form.group_id}' />" />
<input type="hidden" name="id" value="<c:out value='${form.linkid}' />" />
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />修改友情链接</p>
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
                <td><input type="text" name="form.name" id="name" class="input02" style="width:116px;" value="${form.name}"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">友情链接站点URL</span>：</td>
                <td><input type="text" name="form.url" id="url" class="input02" style="width:116px;" value="${form.url}"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">排序值</span>：</td>
                <td><input type="text" name="form.orderline" id="orderline" class="input02" style="width:116px;" value="${form.orderline}"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">友情链接站点状态</span>：</td>
                <td> <input type="radio" checked name="form.state" class="DetailInputBox" value="1" />有效&nbsp;&nbsp;
                                            
                                            <input type="radio" name="form.state"  class="DetailInputBox" value="0" />无效
                                            
					 <script language="javascript">setRadioChecked("form.state", "<c:out value='${form.state}'/>")</script></td>
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
