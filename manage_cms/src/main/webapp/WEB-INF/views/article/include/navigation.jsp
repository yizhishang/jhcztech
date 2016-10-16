<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/taglibs.jsp" %>
<%@ include file="/admin/common/meta.jsp" %>
<script type="text/javascript">
function saveFunction()
{	
	var title = $("#title").val();
	if($.trim(title).length == 0)
	{
		alert("文章标题不能为空,请确认！");
		$("#title").focus();
		return false;
	}
	
	var pictureUrl = $("#pictureUrl").val();
	if($("#pictureNews").attr("checked"))
	{
		if($.trim(pictureUrl).length == 0)
		{
			alert("您选择了图片新闻，你输入图片地址！");
			showTab(1);
			$("#pictureUrl").focus();
			return false;
		}
		
		var isOk=false;
		var arrExts=new Array(".bmp",".jpg",".jpeg",".png",".gif");
		for(var i=0;i<arrExts.length;i++)
		{
			if(pictureUrl.lastIndexOf(arrExts[i])>3)
			{
				isOk=true;
				break;
			}
		}
		if(!isOk)
		{
			alert("图片新闻只能上传 .BMP .JPG .JPEG .PNG .GIF 文件！");
			showTab(1);
			$("#pictureUrl").focus();
			openDeleteFileDialog($('#pictureUrl'));
			return false;
		}

	}
	saveData();
}

function publishFunction()
{
	$("#state").val("3");
	$("#enterForm").submit();
}

function previewFunction()
{
	$("#isPreview").val("1");
	$("#enterForm").submit();
}
</script>
<div class="space"></div>
<div class="label">
	<c:if test="${catalogRights.all eq '1' or catalogRights.add eq '1'}">
		<a href="#" onClick="saveFunction();"><img src="${ctxPath }/admin/images/ico08.gif" border="0"/>保存</a>
	</c:if>
	<c:if test="${catalogRights.all eq '1' or catalogRights.publish eq '1'}">
		<a href="#" onclick="publishFunction();"><img src="${ctxPath }/admin/images/ico10.gif" border="0"/>发布</a>
	</c:if>
	<a href="#" onclick="previewFunction();"><img src="${ctxPath }/admin/images/ico11.gif" border="0"/>预览</a>
	<a href="javascript:void(0);" onClick="window.close();"><img src="${ctxPath }/admin/images/ico15.gif" border="0"/>关闭</a>
</div>
