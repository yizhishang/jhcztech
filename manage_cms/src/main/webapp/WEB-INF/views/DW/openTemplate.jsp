<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title></title>
    <script language="javascript" src="${ctxPath }/admin/scripts/jquery.min.js"></script>
	<script type="text/javascript" language="javascript">
		function Check()
		{
			var catalogInfo = $(window.frames["catalogRightFrame"].document).find("input[@type='radio']:checked").val();
			if($.trim(catalogInfo).length == 0)
			{
				return "请选择模板";
			}
			return "";
		}
		function GetResult()
		{
			var catalogInfo = $(window.frames["catalogRightFrame"].document).find("input[@type='radio']:checked").val();
			return catalogInfo;
		}
	</script>
</head>

<frameset cols="250,*" name="articleFrameset" frameborder="NO" border="0" framespacing="0">
    <frame src="dw_template.action?function=left" name="catalogLeftFrame" scrolling="No" >
    <frame src="dw_template.action?function=templateList" name="catalogRightFrame">
</frameset>
<noframes><body>

</body></noframes>

</html>
