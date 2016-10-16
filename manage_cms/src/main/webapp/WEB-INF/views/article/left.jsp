<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<link type="text/css" rel="stylesheet" href="${ctxPath }/admin/xtree/css/xtree.css">
<script type="text/javascript" src="${ctxPath }/admin/xtree/js/xtree.js"></script>
<script type="text/javascript" src="${ctxPath }/admin/xtree/js/xloadtree.js"></script>
<body>
<script type="text/javascript">
	$(document).ready(function()
	{
	    winResize();
	});
	if (window.frameElement != null && document.all != null)
	{
	    window.frameElement.attachEvent("onresize", winResize);
	}
	else if (document.all != null)
	{
	    window.attachEvent("onresize", winResize);
	}
	else
	{
	    window.onresize = winResize;
	}
	
	function winResize()
	{
	    $(".Frame_tree").css("height", $(window).height() - 20);
	}

	function publishCatalog()
	{
	    hideContextMenu();
	    var value = tree.getSelected().value;
	    hiddenForm.catalogId.value = value;
	    hiddenForm.target = "";
	    hiddenForm.action = "publishCatalog.action";
	    menuOperation();
	}
	
	function previewCatalog()
	{
		hideContextMenu();
	    var value = tree.getSelected().value;
	    hiddenForm.catalogId.value = value;
	    hiddenForm.action = "doPreview.action";
	    hiddenForm.target = "_blank";

	    hiddenForm.submit();
	}
	
	function publishCatalogRecursion()
	{
	    hideContextMenu();
	    var value = tree.getSelected().value;
	    hiddenForm.catalogId.value = value;    
	    hiddenForm.target = "";
	    hiddenForm.action = "publishCatalogRecursion.action";
	    
	    menuOperation();
		//	    hiddenForm.submit();
	}
	
	function publishArticleRecursion()
	{
	    hideContextMenu();
	    var value = tree.getSelected().value;
	    hiddenForm.catalogId.value = value;    
	    hiddenForm.target = "hiddenFrame";
	    hiddenForm["function"].value = "publishCatalogRecursion";
	    hiddenForm.submit();
	}
	
	//重新读入子栏目
	function reloadChildrenCatalog()
	{
	    hideContextMenu();
	    if (tree.getSelected())
	    {
	        tree.getSelected().reload();
	    }
	}
	
	function reloadPublishArticleRecursion()
	{
		hideContextMenu();
	    var value = tree.getSelected().value;
		hiddenForm.catalogId.value = value;    
		hiddenForm.target = "hiddenFrame";
	    hiddenForm["function"].value = "publishArticleRecursio";
	    hiddenForm.submit();
	}
	
	function showContextMenu()
	{
	    hideContextMenu();
	    var value = tree.getSelected().value;
	    hiddenForm.catalogId.value = value;
	
	    var src = tree.getSelected().getSrc();
	    if (src)
	    {
	        showNamingMenu("rootMenu");
	    }
		else if(tree.getSelected().getId())
		{
			showNamingMenu("childMenu");
		}
	}
	
	function showNamingMenu(menuId)
	{
	 	var menuObj = document.getElementById(menuId);
        //获取当前鼠标右键按下后的位置，据此定义菜单显示的位置
		var x;
		var y;
		x = (document.body.clientWidth - $("#" + menuId).width()) / 2 - 10;
		if(event.clientY > ($("#" +　menuId).height() + 10))
		{
			y = event.clientY - $("#" + menuId).height() - 22;
		}
		else
		{
			y = event.clientY + 10;
		}
		
		menuObj.style.top = y;
		menuObj.style.left = x;
		
		        //设置菜单可见
        menuObj.style.visibility = "visible";
        event.returnValue = false;
        event.cancelBubble = true;
        return false;
	}
	
	function hideContextMenu()
	{
	    rootMenu.style.visibility = "hidden";
		childMenu.style.visibility = "hidden";
	}
	
	document.oncontextmenu = function oncontextmenu()
	{
	   	var menu = new BootstrapMenu('.webfx-tree-row', {
			actions: [{
				name: '重新读入子目录',
				onClick: function() {
					reloadChildrenCatalog();
				}
			}, {
				name: '刷新目录树',
				onClick: function() {
					window.location.reload();
				}
			}, {
	     		name: '发布本栏目',
	       		onClick: function() {
	       			publishCatalog();
	       		}
	       	}, {
				name: '发布当前及所有子栏目',
				onClick: function() {
					publishCatalogRecursion();
				}
			}, {
				name: '重新发布子栏目文章',
				onClick: function() {
					reloadPublishArticleRecursion();
				}
			}, {
				name: '预览栏目',
				onClick: function() {
					previewCatalog();
				}
			}]
	   	});	
	}
	
	//重定向鼠标左键事件的处理过程为自定义程序
	document.body.onclick = hideContextMenu;
</script>
<div class="Frame_tree">
    <script type="text/javascript">
        var tree = new WebFXLoadTree("<a href='javascript:void(0);'>栏目结构</a>", "showCatalog.action");
        tree.write();
    </script>
</div>

<link rel="stylesheet" href="${ctxPath }/admin/scripts/bootstrapMenu/css/bootstrap.min.css">
<script type="text/javascript" src="${ctxPath }/admin/scripts/bootstrapMenu/js/BootstrapMenu.min.js"></script>

<!--右键菜单-->
<!--根目录右键菜单-->
<div id="rootMenu" class="RightClickMenu" style="position:absolute; left:0px; top:-1px; width:136px; z-index:1000; visibility: hidden">
	<ul>
    	<li class="no"><a href="#" onClick="reloadChildrenCatalog()">重新读入子栏目</a></li>
        <li class="no"><a href="#" onClick="window.location.reload()">刷新栏目树</a></li>
        <c:if test="${data.isPublish eq '1' }">
        <li class="no"><a href="#" onClick="publishCatalog()">发布本栏目</a></li>
        <li class="no"><a href="#" onClick="publishCatalogRecursion()">发布当前及所有子栏目</a></li>
        </c:if>
        <c:if test="${data.isPublish eq '1' and data.isPublishArticle eq '1' }">
		<li class="no"><a href="#" onClick="reloadPublishArticleRecursion()" title="只对已经发布的文章有效，只取每个栏目前200篇文章进行发布">重新发布子栏目文章</a></li>
		</c:if>
		<li class="no"><a href="#" onClick="previewCatalog();">预览栏目</a></li>
    </ul>
</div>
<!--根栏目右键菜单-->

<!--子目录右键菜单-->
<div id="childMenu" class="RightClickMenu" style="position:absolute; left:0px; top:-1px; width:136px; z-index:1000; visibility: hidden">
	<ul>
		<li class="no"><a href="#" onClick="window.location.reload()">刷新栏目树</a></li>
		<c:if test="${data.isPublish eq '1' }">
    	<li class="no"><a href="#" onClick="publishCatalog()">发布本栏目</a></li>
    	</c:if>
		<li class="no"><a href="#" onClick="previewCatalog();">预览栏目</a></li>
    </ul>
</div>
<!--子栏目右键菜单-->
<!--右键菜单-->
<iframe name="hiddenFrame" width="0" height="0"></iframe>
<form name="hiddenForm" action="article.action" target="articleRightFrame" id="hiddenForm">
    <input type="hidden" name="function" value="">
    <input type="hidden" name="catalogId" value="">
</form>
</html>
