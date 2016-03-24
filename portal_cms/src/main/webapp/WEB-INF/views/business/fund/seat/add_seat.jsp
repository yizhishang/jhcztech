<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#seatno").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"机构代码不能为空,请确认"});
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="seat.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="add"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" >
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />增加代销机构信息</p>
        </div>
    </td>
  </tr>
  <tr>
    <td class="edinner" valign="top"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" >
      
      <tr>
        <td>
			<div class="space"></div>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
			  <tr>
                <td colspan="2" style="height:8px;"></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">机构代码</span>：</td>
                <td><input type="text" name="form.seatno" id="seatno" class="input02" style="width:116px;" value="<c:out value='${form.seatno}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">机构名称</span>：</td>
                <td><input type="text" name="form.seatnm" id="seatnm" class="input02" style="width:116px;" value="<c:out value='${form.seatnm}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">机构状态</span>：</td>
                <td><select name="form.status">
                    <option value="N" selected="selected">正常</option>
                    <option value="D">删除</option>     
                   </select>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr id="wfsyl">
                <td width="120" class="label"><span class="DetailTagText">机构类型</span>：</td>
                <td><input type="text" name="form.seattp" id="seattp" class="input02" style="width:116px;" value="<c:out value='${form.seattp}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td><div class="space"></div>
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
