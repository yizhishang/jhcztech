<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"enterForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#title").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"标题不能为空,请确认"});
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
                <input type="hidden" name="function" value="edit"/>
				<input type="hidden" name="form.id" value="${form.id}"/>
                <input type="hidden" name="successPage" value="help.action?function=list&groupId=<c:out value='${form.group_id}'/>">
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td><div class="space"></div></td>
                  </tr>
                  <tr>
                    <td><div class="space"></div>
                      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="editortable">
                        <tr>
                          <td colspan="2"><h5>修改问题</h5></td>
                        </tr>
                        <tr>
                          <td colspan="2" style="height:8px;"></td>
                        </tr>
                        <tr>
                          <td width="120" class="label">标题：</td>
                          <td><input type="text" name="form.title" id="title" class="input02" style="width:250px;" value="<c:out value='${form.title}'/>"/>
                          </td>
                        </tr>
						 <tr>
                          <td width="120" class="label">关键字：</td>
                          <td><input type="text" name="form.keyword" id="keyword" class="input02" style="width:250px;" value="<c:out value='${form.keyword}'/>"/>
                          </td>
                        </tr>
                        <tr>
                          <td width="120" class="label">内容：</td>
                          <td><input name="form.content" type="hidden" id="content" value="<c:out value='${form.content}'/>" />
                            <iframe id="eWebEditor_trainfo" src="editor/standard/ewebeditor.htm?id=content&style=coolblue" frameborder="0" scrolling="No" width="800" height="350"></iframe></td>
                        </tr>
                        <tr>
                          <td class="label">&nbsp;</td>
                          <td><input type="submit" name="button" id="button" value="保存" class="bt04"/>
                            &nbsp;
                            <input type="reset" name="button" id="button" value="重置" class="bt04"/>
                          </td>
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