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
	
	 function addFunction()
    {
        hideContextMenu();
        hiddenForm["function"].value = "add";
        hiddenForm.submit();
    }

    function deleteFunction()
    {
        hideContextMenu();
        if (tree.getSelected())
        {
			if(tree.getSelected().hasChildren())
			{
				alert("不能删除还有子栏目的目录");
				return;
			}

		    if(!confirm("警告：删除之后不能恢复，请您确认是否删除?"))
		    {
    		   return;
    	    }

			var bResult = true;
            if (bResult)
            {
				// add by 2010-1-20 删除成功后刷新父节点
				tree.setSelected(tree.getSelected().getParent());
                hiddenForm["function"].value = "delete";
                hiddenForm.submit();
            }
			
			
        }
    }
	
	function editChildrenCatalog()
	{
		if (tree.getSelected())
        {
			// add by 2010-1-20 删除成功后刷新父节点
			tree.setSelected(tree.getSelected().getParent());
			hiddenForm["function"].value = "editChildrenNum";
            hiddenForm.submit();
		}
	}

    function recuDeleteFunction()
    {
        hideContextMenu();
        if (tree.getSelected())
        {
			if(!tree.getSelected().hasChildren())
			{
				alert("当前栏目没有子目录！");
				return;
			}

    	    if(!confirm("警告：您选择了删除当前栏目的所有子栏目，删除之后数据不能恢复\n请您再次确认是否删除?"))
		    {
    		   return;
    	    }

			var bResult = true;
            if (bResult)
            {
				// add by 2010-1-20 删除成功后刷新父节点
				tree.setSelected(tree.getSelected().getParent());
                hiddenForm["function"].value = "recuDelete";
                hiddenForm.submit();
            }
        }
    }
    
    function reloadChildrenCatalog()
    {
        hideContextMenu();
        if (tree.getSelected())
        {
			if(!tree.getSelected().hasChildren())
			{
				tree.getSelected().setSrc("catalog.action?function=showChild&catalogId=" + tree.getSelected().value);
			}
            tree.getSelected().reload();
        }
    }

    function editFunction()
    {
        hideContextMenu();
        if (tree.getSelected())
        {
            hiddenForm["function"].value = "edit";
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
    var tree = new WebFXLoadTree("栏目结构", "deptManage.action?function=showChild");
    tree.write();
</script>
</div>


<!--根目录右键菜单-->
<div id="rootMenu" class="RightClickMenu" style="position:absolute; left:0px; top:-1px; width:136px; z-index:1000; visibility: hidden">
	<ul>
    	<li class="no"><a href="#" onClick="reloadChildrenCatalog()">重读子栏目</a></li>
        <li class="no"><a href="#" onClick="window.location.reload()">刷新栏目树</a></li>
		<li class="no"><a href="#" onClick="editChildrenCatalog()">更新子目录数量</a></li>
        <li class="no"><a href="#" onClick="addFunction();">添加子栏目</a></li>
        <li class="no"><a href="#" onClick="deleteFunction();">删除本栏目</a></li>
        <!--<li class="no"><a href="#" onClick="recuDeleteFunction();">删除本栏目所有子栏目</a></li>-->
        <li class="no"><a href="#" onClick="editFunction();">栏目属性</a></li>
    </ul>
</div>
<!--根栏目右键菜单-->
<!--子栏目右键菜单-->
<!--右键菜单-->
<form name="hiddenForm" action="deptManage.action" target="catalogRightFrame">
    <input type="hidden" name="function" value="">
    <input type="hidden" name="catalogId" value="">
</form>
</html>
