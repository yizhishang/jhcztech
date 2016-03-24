<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		document.title = "${form.name}";//更改页页的title为模板名称
		
		$("#enterForm").click(function(){
			$("input[name='function']").val("edit");
			submitForm("addForm",null, false);
		});
		
		$("#close").click(function(){
			window.close();
		});
		
		$("#exportHtmlId").click(function(){
			$("input[name='function']").val("doExportHtml");
			$("#addForm").attr("action", "doExportHtml.action").submit();
		});
		
		$("#uploadHtmlPath").bind("propertychange",uploadHtml);
	});
	
	function uploadHtml(){
		var uploadPath = $("#uploadHtmlPath").val();
		$.ajax({
		   type: "POST",
		   url: "doUploadHtml.action",
		   data: "form.id=${form.id}&uploadHtmlPath=" + uploadPath,
		   success: function(html){
		   		if($.trim(html).length > 0)
				{
		   			$("#content").text(html);
				}
		   }
		}); 
	}
	
	/* function openUploadDialog(fieldObj)
	{
		if(fieldObj)
		{
			return window.showModalDialog("/admin/upload/upload.jsp",fieldObj,'dialogHeight: 100px; dialogWidth: 350px;edge: Raised; center: Yes; help: no;scroll:no; resizable: no; status: no;');
		}
	} */

</script>
<form action="edit.action" method="post" target="hiddenFrame" id="addForm">
  <input type="hidden" name="function" value="edit"/>
  <input type="hidden" name="form.id" value="${form.id}"/>
  <input type="hidden" name="form.name" value="${form.name}"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />${form.name}</p>
        </div></td>
    </tr>
    <tr>
      <td class="edinner" valign="top"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><div class="space"></div>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
                <tr>
                  <td colspan="2" style="height:8px;"></td>
                </tr>
                <tr>
                  <td width="5"></td>
                  <td><textarea class="input02" name="form.content" id="content" style="width:770px;height:455px;"><c:out value="${form.content}" escapeXml="true"/></textarea></td>
                </tr>
                <tr>
                  <td colspan="2" align="center"><input type="button" name="enterForm" id="enterForm" value="提交" class="bt04"/>  
					&nbsp;
					<!-- 
					<input type="button" id="exportHtmlId" name="exportHtmlId" value="导出" class="bt04"/>
					&nbsp;
					<input type="hidden" name="uploadHtmlPath" id="uploadHtmlPath"/>
					<input type="button" id="importHtmlId" value="导入" class="bt04" onClick="openUploadFileDialog($('#uploadHtmlPath'));"/>
					 -->
                    &nbsp;
                    <input type="button" id="close" name="close"value="关闭" class="bt04"/>  
				  </td>
                </tr>
                <tr>
                  <td colspan="2" style="height:8px;"></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>