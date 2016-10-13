<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<link type="text/css" rel="stylesheet" href="xtree/css/xtree.css">
<script type="text/javascript" src="xtree/js/xtree.js"></script>
<script type="text/javascript" src="xtree/js/xloadtree.js"></script>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		winResize();
	});
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
		$(".Frame_tree").css("height",$(window).height() - 20);
	}
	
	function addGroupFunction()
	{
		hideContextMenu();
		var value = tree.getSelected().value;
		hiddenForm["function"].value = "addGroup";
		hiddenForm["id"].value = value;
        hiddenForm.submit();
	}
	
	function editGroupFunction()
	{
		hideContextMenu();
		var value = tree.getSelected().value;
		hiddenForm["function"].value = "editGroup";
		hiddenForm["id"].value = value;
        hiddenForm.submit();
	}
	
	function deleteGroupFunction()
	{
		hideContextMenu();
		if(confirm("删除该分类时，会将该分类下所有问题删除，是否确认？"))
		{
			var value = tree.getSelected().value;
			hiddenForm["function"].value = "deleteGroup";
			hiddenForm["id"].value = value;
			hiddenForm.submit();
		}
	}
	
	function reloadChildrenCatalog()
    {
        hideContextMenu();
        if (tree.getSelected())
        {
			if(!tree.getSelected().hasChildren())
			{
				tree.getSelected().setSrc("help.action?function=showChild&parentId=" + tree.getSelected().value);
			}
            tree.getSelected().reload();
        }
    }
	
    function showContextMenu()
    {
        hideContextMenu();
        var value = tree.getSelected().value;
        //hiddenForm.catalogId.value = value;

        var src = tree.getSelected().getSrc();
		if (tree.getSelected().getId())
		{
			showNamingMenu("rootMenu");
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
    }

    
    document.oncontextmenu = function oncontextmenu()
    {
        alert("请选择相应的节点，然后点击右键进行操作！")
        return false;
    }
    

    //重定向鼠标左键事件的处理过程为自定义程序
    document.body.onclick = hideContextMenu;
</script>
<div class="Frame_tree">
<script type="text/javascript">
    var tree = new WebFXLoadTree("栏目结构", "help.action?function=showChild");
    tree.write();
</script>
</div>


<!--根目录右键菜单-->
<div id="rootMenu" class="RightClickMenu" style="position:absolute; left:0px; top:-1px; width:136px; z-index:1000; visibility: hidden">
	<ul>
    	<li class="no"><a href="#" onClick="addGroupFunction()">添加子分类</a></li>
        <li class="no"><a href="#" onClick="editGroupFunction()">修改分类名称</a></li>
		<li class="no"><a href="#" onClick="deleteGroupFunction()">删除分类</a></li>
		<li class="no"><a href="#" onClick="window.location.reload()">刷新目录树</a></li>
    </ul>
</div>
<!--根栏目右键菜单-->
<!--子栏目右键菜单-->
<!--右键菜单-->
<form name="hiddenForm" action="help.action" target="helpRightFrame">
    <input type="hidden" name="function" value="">
    <input type="hidden" name="id" value="">
</form>
</html>
