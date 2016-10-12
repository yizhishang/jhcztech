<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<head>
    <base target="_self">
    <title>上传文件</title>
   	<base target="_self" />
   	<script type="text/javascript" src="<%=request.getContextPath()%>/admin/scripts/ajaxfileupload.js"></script>
</head>

<body>

<script language="javascript">
    function checkForm(formObj)
    {
        if (formObj.uploadfile.value.length == 0)
        {
            alert("请先选择您需要上传的文件");
            return false;
        }

        document.getElementById("uploadDiv").style.display = "none";
        document.getElementById("waitDiv").style.display = "block";
        return true;
    }
</script>

<div id="uploadDiv" class="treebox">
	<form style="margin:0px" action="/admin/UploadFileAdmin/fileUpload.action" target="my" enctype="multipart/form-data" onSubmit="return checkForm(this);" method="post">
        <input type="hidden" name="function" value="/admin/UploadFileAdmin/fileUpload"/>
        <input type="hidden" name="is_upload" value="1"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td height="10"></td>
            </tr>
            <tr>
                <td align="center"><input type="file" size="30" name="uploadfile" id="uploadfile" class="input02"/></td>
            </tr>
            <tr>
                <td height="10"></td>
            </tr>
            <tr>
                <td align="center">
                	<input type="button" onclick="uploadFile();" name="button" id="button" value="上  传" class="bt01"/>
                    <input type="button" onClick="window.close();" value="关  闭" class="bt01"/>
                    <iframe frameborder="0" width="0" height="0" name="my"></iframe>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="waitDiv" style="display:none">
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center">正在上传，请等待......</td>
        </tr>
    </table>
</div>
</body>