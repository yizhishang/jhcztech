<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<link type="text/css" rel="stylesheet" href="/admin/xtree/css/xtree.css">
<script type="text/javascript" src="/admin/xtree/js/xtree.js"></script>
<script type="text/javascript" src="/admin/xtree/js/xloadtree.js"></script>
<body>
<script type="text/javascript">    
	
	if(window.frameElement!=null && document.all != null)
	{
		window.frameElement.attachEvent("onresize",winResize);
	}
	else if(document.all != null)
	{
		window.attachEvent("onresize",winResize);
	} 
	else
	{
		window.onresize=winResize;
	}
	function winResize()
	{
		$("#treeDiv").css("height",$(window).height() - 25);
	}
	
	
	$(document).ready(function(){
		winResize();
	});
</script>
<div class="sidebar">
  <div id="treeDiv" style="overflow-y:auto;overflow-x:auto;width:149px">
    <script type="text/javascript">
		var tree = new WebFXLoadTree("<a href='javascript:void(0);'>业务管理</a>", "/admin/leftAdmin/showChild.action?parentNo=1742");
		tree.write();
	</script>
  </div>
</div>
</html>