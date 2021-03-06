<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.yizhishang.base.util.RequestHelper"%>
<%@ include file="/admin/common/header.jsp" %>
<style>
select{width:120px;}
</style>
<body>
<script type="text/javascript">
$(document).ready(function(){  
	$("#can_add").val("${bean.can_add}");
	$("#can_update").val("${bean.can_update}");
	$("#can_del").val("${bean.can_del}");
	$("#can_import").val("${bean.can_import}");
	$("#can_export").val("${bean.can_export}");
});
</script>
<form id="tableForm" action="edit.action" target="hiddenFrame"  method="post">
<input type="hidden" name="function" value="edit"></input>
<input type="hidden" id="id" name="form.id" value="${bean.id}"/>
<input type="hidden" id="name_en" name="form.name_en" value="${bean.name_en}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td>
        <div class="title">
           <p><img src="${ctxPath }/admin/images/ico04.gif" />修改表信息</p>
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
                   <td class="label">英文名称： </td>
                   <td> 
                   		<c:out value='${bean.name_en}'/>
                   </td>
               </tr>
               <tr>
                   <td class="label">中文名称： </td>
                   <td> 
                   		<input type="text" size="30" id="name_ch"  name="form.name_ch"  value="<c:out value='${bean.name_ch}'/>" class="input02" style="width:116px;">
                   </td>
               </tr>
               <tr>
                   <td class="label">ACTION地址： </td>
                   <td> 
                   		<input type="text" size="30" id="action_url"  name="form.action_url"  value="<c:out value='${bean.action_url}'/>" class="input02" style="width:116px;">
                   </td>
               </tr>
               <tr>
                   <td class="label">主键名称： </td>
                   <td> 
                   		<input type="text" size="30" id="pk_column"  name="form.pk_column"  value="<c:out value='${bean.pk_column}'/>" class="input02" style="width:116px;">
                   </td>
               </tr>
               <tr>
                   <td class="label">可增加： </td>
                   <td> 
                   		<select id="can_add" name="form.can_add">
                   			<option value="Y">是</option>
                   			<option value="N">否</option>
                   		</select>
                   </td>
               </tr>
               <tr>
                   <td class="label">可修改： </td>
                   <td> 
                   		<select id="can_update" name="form.can_update">
                   			<option value="Y">是</option>
                   			<option value="N">否</option>
                   		</select>
                   </td>
               </tr>
               <tr>
                   <td class="label">可删除： </td>
                   <td> 
                   		<select id="can_del" name="form.can_del">
                   			<option value="Y">是</option>
                   			<option value="N">否</option>
                   		</select>
                   </td>
               </tr>
               <tr>
                   <td class="label">可导出： </td>
                   <td> 
                   		<select id="can_export" name="form.can_export">
                   			<option value="Y">是</option>
                   			<option value="N">否</option>
                   		</select>
                   </td>
               </tr>
               <tr>
                   <td class="label">可导入： </td>
                   <td> 
                   		<select id="can_import" name="form.can_import">
                   			<option value="Y">是</option>
                   			<option value="N">否</option>
                   		</select>
                   </td>
               </tr>
              <tr>
                <td class="label">&nbsp;</td>
              <td>
                	<input type="button" name="button" id="enterForm" value="提交" class="bt04"/>&nbsp;
                	<input type="button" name="button" id="close" value="关闭" class="bt04"/>
                </td>
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
