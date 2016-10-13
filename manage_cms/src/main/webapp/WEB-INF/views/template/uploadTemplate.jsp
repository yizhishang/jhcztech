<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#enterForm").submit(function(){
		var uploadPath = $("#uploadPath").val();
		if($.trim(uploadPath).length <= 3)
		{
			alert("请选择需导入的模板文件");
			return false;
		}
		var extension = uploadPath.substr(uploadPath.length - 3,uploadPath.length);
		if(extension.toUpperCase() != "ZIP")
		{
			alert("文件必须是zip格式！");
			openDeleteFileDialog($('#uploadPath'));
			return false;
		}
		if(confirm("导入将覆盖模板中的文件，确认吗？"))
		{
			return true;
		}
		else
		{
			openDeleteFileDialog($('#uploadPath'));
			return false;		
		}
	});
});
</script>
<body>
<form style="margin:0px" action="importTemplate.action" method="post" id="enterForm">
  <div class="editor">
    <div class="edinner">
      <div class="space"></div>
      <div class="editorbox">
        <h5>导入模板</h5>
        <div class="space"></div>
        <div class="eb">
          <div class="eb_l">请选择模板文件：</div>
          <div class="eb_r">
            <p>
              <input type="text" name="uploadPath" id="uploadPath" class="input02" style="width:300px;" readonly="true"  value="<c:out value='${form.smallImage}'/>"/>
              <input type="button" onClick="openUploadFileDialog($('#uploadPath'))" value="上传文件" class="bt02"/>
              <input type="button"onClick="openDeleteFileDialog($('#uploadPath'))" value="删除" class="bt01"/>
            </p>
            <p>（模板文件必须为zip格式）</p>
          </div>
        </div>
        <div class="eb">
          <div class="eb_l">&nbsp;</div>
          <div class="eb_r">
            <input type="submit" name="button" id="button" value="导 入" class="bt01"/>
            &nbsp;
            <input type="button" name="button" id="button" value="关 闭" class="bt01" onClick="window.close();"/>
          </div>
        </div>
		
        <div class="space"></div>
        <div class="eb">
          <div class="eb_l">说明：</div>
          <div class="eb_r">
            <p>1：每次导入之前，系统将自动备份需导入的模板文件，备份地址是：</p>
			<p>&nbsp;&nbsp;&nbsp;服务器工程目录/admin/expxmlbackup/template/年月日/时分秒/模板ID.xml</p>
            <p>2：如果模板ID不存在表中，将会新增一条记录</p>
          </div>
        </div>
        <div class="space"></div>
      </div>
    </div>
  </div>
</form>
</body>
</html>