<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>金恒创智CMS后台管理系统</title>
    <meta http-equiv="Cache-Control" content="no-store" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta name="author" content="" />
	<script language="javascript" src="/admin/scripts/jquery.min.js"></script>
	<link href="/admin/styles/cms.css" rel="stylesheet" type="text/css" />
	<script language="javascript">
		function resizeMenu()
		{
			if($("#resizeIcon_Hidden").css("display") == "none")
			{
				$("#resizeIcon_Show").hide();
				$("#resizeIcon_Hidden").show();
				$("[name='articleFrameset']",window.parent.document).attr("cols","0,8,*");
			}
			else
			{
				$("#resizeIcon_Show").show();
				$("#resizeIcon_Hidden").hide();
				$("[name='articleFrameset']",window.parent.document).attr("cols","188,8,*");
			}
		}
	</script>
</head>
<body>
<div>
<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
<tr valign="top" align="left">
<td style="background-color:#FFFFFF;border-left:1px solid #FFFFFF;border-right:1px solid #FFFFFF;" valign="middle" align="center">
    <span id="resizeIcon_Show" style="cursor:pointer;display:block;" onClick="javascript:resizeMenu()" title="隐藏菜单"><img src="/admin/images/leftmenu/IconArrowLeft.gif" width="5" height="10"></span>
    <span id="resizeIcon_Hidden" style="cursor:pointer;display:none;" onClick="javascript:resizeMenu()" title="显示菜单"><img src="/admin/images/leftmenu/IconArrowRight.gif" width="5" height="10"></span>
</td>
</tr>
</table>
</div>
</body>
</html>