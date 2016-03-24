<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#regionname").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"区域名称不能为空,请确认"});
		$("#area").formValidator().inputValidator({min:1,onerror:"所属地区不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="regionManage.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="add"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />添加<span class="ColumnDisplay">区域信息</span></p>
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
                <td width="120" class="label"><span class="DetailTagText">区域名称</span>：</td>
                <td><input type="text" name="form.regionname" id="regionname" class="input02" style="width:116px;" value="<c:out value='${form.regionname}'/>"/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <tr>
                <td width="120" class="label">所属地区：</td>
                <td><select name="form.area" style="width:120px" id="area">
                            <option value="">--请选择--</option>
                            <option value="东北地区">东北地区</option>
                            <option value="华北地区">华北地区</option>
                            <option value="华东地区 ">华东地区 </option>
                            <option value="华中地区">华中地区</option>
                            <option value="华南地区">华南地区</option>
                            <option value="西南地区">西南地区</option>
                            <option value="西北地区">西北地区</option>
                            <option value="中南地区">中南地区</option>
                          </select></td>
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
