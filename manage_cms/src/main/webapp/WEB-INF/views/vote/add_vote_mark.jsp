<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#uplimit").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"分数上线不能为空,请确认"});
		$("#downlimit").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"分数下线不能为空,请确认"});
		$("#define").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"级别名称不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="voteMark.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="add"/>
<input type="hidden" name="form.sub_id" value="${ param.sub_id }"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />添加主题评价级别</p>
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
                <td width="120" class="label"><span class="DetailTagText">分数下限</span>：</td>
                <td><input type="text" name="form.downlimit" id="downlimit" class="input02" style="width:200px;" value="<c:out value='${form.downlimit}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">分数上限</span>：</td>
                <td><input type="text" name="form.uplimit" id="uplimit" class="input02" style="width:200px;" value="<c:out value='${form.uplimit}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">级别名称</span>：</td>
                <td><input type="text" name="form.define" id="define" class="input02" style="width:200px;" value="<c:out value='${form.define}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">描述</span>：</td>
                <td><textarea name="form.remark"  class="input02" style="height:100px;width:400px;border-top:1px solid;"><c:out value='${form.remark}'/></textarea></td>
              </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td>
                	<input type="submit" name="enterForm" id="enterForm" value="提交" class="bt04"/>&nbsp;
                <input type="button" id="close" name="close"value="关闭" class="bt04"/>                </td>
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
