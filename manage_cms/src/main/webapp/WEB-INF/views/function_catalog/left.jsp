<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<link type="text/css" rel="stylesheet" href="${ctxPath }/admin/xtree/css/xtree.css">
<script type="text/javascript" src="${ctxPath }/admin/xtree/js/xtree.js"></script>
<script type="text/javascript" src="${ctxPath }/admin/xtree/js/xloadtree.js"></script>
<link rel="stylesheet" href="${ctxPath }/admin/scripts/bootstrapMenu/css/bootstrap.min.css">
<script type="text/javascript" src="${ctxPath }/admin/scripts/bootstrapMenu/js/BootstrapMenu.min.js"></script>
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
	
	function addFunction()
    {
        hideContextMenu();
        hiddenForm.action = "doAdd.action";
        hiddenForm.submit();
    }

    function deleteFunction()
    {
       	if(!confirm("是否确定删除？")) {
    		return;
    	}   
        hideContextMenu();
        if (tree.getSelected())
        {
           if(tree.getSelected().hasChildren())
			{
				alert("不能删除还有子栏目的目录");
				return;
			}
            //var bResult = window.confirm("不能删除还有子栏目的目录，您确定要继续吗?");
			var bResult = true;
            if (bResult)
            {
				//删除成功后刷新父节点
				tree.setSelected(tree.getSelected().getParent());
				hiddenForm.action = "delete.action";
                hiddenForm.submit();
            }
			
        }
    }
	
	function editChildrenCatalog()
	{
		if (tree.getSelected())
        {
			// 删除成功后刷新父节点
			tree.setSelected(tree.getSelected().getParent());
			hiddenForm.action = "editChildrenNum";
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
				tree.getSelected().setSrc("showChild.action?parentNo=" + tree.getSelected().value);
			}
            tree.getSelected().reload();
        }
    }

    function editFunction()
    {
        hideContextMenu();
        if (tree.getSelected())
        {
            hiddenForm.action = "doEdit.action";
            hiddenForm.submit();
        }
    }

    function showContextMenu()
    {
        hideContextMenu();
        var value = tree.getSelected().value;
        hiddenForm.catalogId.value = value;

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
		if(event.clientY > ($("#" + menuId).height() + 10))
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
        childMenu.style.visibility = "hidden";
        rootMenu.style.visibility = "hidden";
    }

    
    document.oncontextmenu = function oncontextmenu(event)
    {
    	autoOncontextmenu();
    }
    

    //重定向鼠标左键事件的处理过程为自定义程序
    document.body.onclick = hideContextMenu;
</script>
<div class="Frame_tree">
<script type="text/javascript">
    var tree = new WebFXLoadTree("<a href='javascript:void(0);'>目录结构</a>", "showChild.action");
    tree.write();
</script>
</div>

<!--根目录右键菜单-->
<div id="rootMenu" class="RightClickMenu" style="position:absolute; left:0px; top:-1px; width:136px; z-index:1000; visibility: hidden">
	<ul>
    	<li class="no"><a href="#" onClick="reloadChildrenCatalog()">重新读入子目录</a></li>
        <li class="no"><a href="#" onClick="window.location.reload()">刷新目录树</a></li>
        <li class="no"><a href="#" onClick="addFunction();">添加子目录</a></li>
		<li class="no"><a href="#" onClick="deleteFunction();">删除本目录</a></li>
		<!-- <li class="no"><a href="#" onClick="editChildrenCatalog()">更新子目录数量</a></li> -->
        <!-- <li class="no"><a href="#" onClick="editFunction();">目录属性</a></li> -->
    </ul>
</div>
<!--根目录右键菜单-->

<!--子目录右键菜单-->
<div id="childMenu" class="RightClickMenu" style="position:absolute; left:0px; top:-1px; width:136px; z-index:1000; visibility: hidden">
	<ul>
    	<li class="no"><a href="#" onClick="reloadChildrenCatalog()">重新读入子目录</a></li>
		<li class="no"><a href="#" onClick="editChildrenCatalog()">更新子目录数量</a></li>
        <li class="no"><a href="#" onClick="window.location.reload()">刷新目录树</a></li>
        <li class="no"><a href="#" onClick="addFunction();">添加子目录</a></li>
        <li class="no"><a href="#" onClick="deleteFunction();">删除本目录</a></li>
        <li class="no"><a href="#" onClick="editFunction();">目录属性</a></li>
    </ul>
</div>
<!--子目录右键菜单-->
<!--右键菜单-->
<form name="hiddenForm" action="default.action" target="catalogRightFrame">
    <input type="hidden" name="function" value="">
    <input type="hidden" name="catalogId" value="">
</form>
</html>
