<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<link href="/admin/styles/cms.css" rel="stylesheet" type="text/css" />
<title>jhczCMS后台管理系统</title>
<frameset rows="80,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="/admin/topAdmin/default.action" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
  <frameset cols="150,8,*" frameborder="no" border="0" framespacing="0" name="bottomFrame">
    <frame src="/admin/leftAdmin/default.action" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" class="Frameline"/>
	<frame src="/admin/leftAdmin/bar.action" name="barFrame" scrolling="No" noresize="noresize" class="Frameline"/>
    <frame src="/admin/rightAdmin/default.action" name="mainFrame" id="mainFrame" scrolling="no" noresize="noresize" class="Frameline"/>
  </frameset>
</frameset>
<noframes>
<body>
</body>
</noframes></html>
