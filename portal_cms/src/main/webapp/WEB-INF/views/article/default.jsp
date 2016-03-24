<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
</head>

<frameset cols="188,8,*" name="articleFrameset" frameborder="NO" border="0" framespacing="0">
    <frame src="doLeft.action?menuNo=<%=request.getParameter("menuNo")%>" name="articleLeftFrame" scrolling="No" >
	<frame src="doBar.action" name="barFrame" scrolling="No" noresize="noresize" class="Frameline"/>
    <frame src="/admin/rightAdmin/default.action" name="articleRightFrame">
</frameset>
<noframes><body>
</body></noframes>
</html>
