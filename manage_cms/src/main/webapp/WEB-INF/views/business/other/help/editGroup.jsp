<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"enterForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"目录英文名不能为空,请确认"});
	});
</script>
<body>
<div class="c_ie6_out">
  <div class="c_ie6_in">
    <div class="Wrapper">
      <div class="inner">
        <div class="innercontent">
          <div class="cl"></div>
          <div class="contentbox">
            <div class="content">
              <form action="help.action" method="post" id="enterForm">
                <input type="hidden" name="function" value="editGroup"/>
                <input type="hidden" name="form.id" value="<c:out value='${form.id}'/>">
				<input type="hidden" name="successPage" value="help.action?function=editGroup&id=<c:out value='${form.id}'/>">
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td><div class="space"></div></td>
                  </tr>
                  <tr>
                    <td><div class="space"></div>
                      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="editortable">
                        <tr>
                          <td colspan="2"><h5>修改分类名称</h5></td>
                        </tr>
                        <tr>
                          <td colspan="2" style="height:8px;"></td>
                        </tr>
                        <tr>
                          <td width="120" class="label">分类名称：</td>
                          <td><input type="text" name="form.name" id="name" class="input02" value="<c:out value='${form.name}'/>"/> </td>
                        </tr>
                        <tr>
                          <td class="label">&nbsp;</td>
                          <td><input type="submit" name="button" id="button" value="保存" class="bt04"/>
                            &nbsp;
                            <input type="reset" name="button" id="button" value="重置" class="bt04"/>                          </td>
                        </tr>
                      </table></td>
                  </tr>
                </table>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</body>
</html>