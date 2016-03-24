<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#item_name").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"名称不能为空,请确认"});
		$("#enum_type").formValidator().inputValidator({min:1,onerror:"类型不能为空,请确认"});
		$("#item_value").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"具体值不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
		$("#enterForm").click(function(){
			$.ajax({
				type:"post",
				url:$("#addForm").attr("action"),
				dataType : "json",
				data : encodeURI($("#addForm").serialize()),
				success: function(data){
					if(data.errorNo == 0)
					{
						if(window.confirm(data.errorInfo)){
							if(!isIE())
							{
								window.opener.location=window.opener.location;
							}
							window.close();
						}
					}else{
						alert(data.errorInfo);
					}
				},
				error: function(data){
					alert(data);
				}
			});
		});
		
	});
</script>
<form action="addItem.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="enum_type" value="${enum_type}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />添加枚举值</p>
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
                <td width="120" class="label"><span class="DetailTagText">名称</span>：</td>
                <td><input type="text" name="form.item_name"  id="item_name" class="input02" style="width:116px;" value=""/>
					<font color="#FF0000">*</font></td>
              </tr>
			  <%--<tr>
                <td width="120" class="label"><span class="DetailTagText">类型</span>：</td>
                <td><select name="form.enum_type" id="enum_type" style="width:120px;">
					<option value="">--请选择--</option>
					<c:forEach var="item" items="${data.typeList}">
						<option value="<c:out value='${item.enum_value}'/>"><c:out value='${item.enum_name}'/></option>
					</c:forEach>
				</select>
				<script language="javascript">setSelectSelected("form.enum_type", "<c:out value='${param.type}'/>")</script>
                  <font color="#FF0000">*</font></td>
              </tr>--%>
			  <tr>
                <td width="120" class="label"><span class="DetailTagText">具体值</span>：</td>
                <td><input type="text" name="form.item_value" id="item_value" class="input02" style="width:116px;" value="<c:out value='${form.item_value}'/>"/>
                  <font color="#FF0000">*</font></td>
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
