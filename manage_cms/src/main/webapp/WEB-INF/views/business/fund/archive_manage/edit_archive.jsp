<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.formValidator.initConfig({formid:"addForm",alertmessage:false,onerror:function(msg){alert(msg)}});
		$("#archivecatalog").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"档案栏目不能为空,请确认"});
		$("#archivenm").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"档案名称不能为空,请确认"});
		$("#displaynm").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"页面显示名称不能为空,请确认"});
		$("#consumepoints").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"消耗积分不能为空,请确认"});
		$("#archivepath").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"上传档案附件不能为空,请确认"});
		$("#fundid").formValidator().regexValidator({regexp:regexEnum.notempty,onerror:"基金代码不能为空,请确认"});
		
		$("#close").click(function(){
			window.close();
		});
		
	});
</script>
<form action="archiveManage.action" method="post" target="hiddenFrame" id="addForm">
<input type="hidden" name="function" value="edit"/>
<input type="hidden" name="form.archiveid" value="<c:out value='${form.archiveid}'/>">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" >
  <tr>
    <td>
        <div class="title">
            <p><img src="${ctxPath }/admin/images/ico04.gif" />修改下载文档</p>
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
                <td width="120" class="label"><span class="DetailTagText">档案栏目</span>：</td>
                <td><input type="text" name="form.archivecatalog" id="archivecatalog" class="input02" style="width:116px;" value="<c:out value='${form.archivecatalog}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">档案名称</span>：</td>
                <td><input type="text" name="form.archivenm" id="archivenm" class="input02" style="width:116px;" value="<c:out value='${form.archivenm}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr id="wfsyl">
                <td width="120" class="label"><span class="DetailTagText">收费类型</span>：</td>
                <td><select name="form.feestp">
                     <option value="0">免费</option>
                     <option value="1">收费</option>
                   </select>
                   <script language="javascript">setSelectSelected("form.feestp",'<c:out value='${form.feestp}'/>')</script>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr id="qrnhsyl">
                <td width="120" class="label"><span class="DetailTagText">客户级别</span>：</td>
                <td><select name="form.custlevel">
                     <option value="0">游客</option>
                     <option value="1">注册客户</option>
                     <option value="2">查询客户</option>
                     <option value="3">交易客户</option>
                  </select>
                     <script language="javascript">setSelectSelected("form.custlevel",'<c:out value='${form.custlevel}'/>')</script>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">所需积分</span>：</td>
                <td><input type="text" name="form.consumepoints" id="consumepoints" class="input02" style="width:116px;" value="<c:out value='${form.consumepoints}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">页面显示名称</span>：</td>
                <td><input type="text" name="form.displaynm" id="displaynm" class="input02" style="width:116px;" value="<c:out value='${form.displaynm}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">对应基金代码</span>：</td>
                <td><input type="text" name="form.fundid" id="fundid" class="input02" style="width:116px;" value="<c:out value='${form.fundid}'/>"/>
                  <font color="#FF0000">*</font></td>
              </tr>
              <tr>
                <td width="120" class="label"><span class="DetailTagText">状态</span>：</td>
                <td> <select name="form.status">
                    <option value="1">可用</option>
                    <option value="0">不可用</option>                         
                    </select>
                     <script language="javascript">setSelectSelected("form.status",'<c:out value='${form.status}'/>')</script>
                  <font color="#FF0000">*</font></td>
              </tr>
               <tr>
                <td width="120" class="label"><span class="DetailTagText">上传文档</span>：</td>
                <td><input type="text" name="form.archivepath" id="archivepath" class="input02" style="width:200px;" value="<c:out value='${form.archivepath}'/>"/>
                <input type="button" onclick="openUploadFileDialog($('#archivepath'))" class="bt02" value="上传文件">
                <input type="button" onclick="openDeleteFileDialog($('#archivepath'))" class="bt02" value="删除">
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
