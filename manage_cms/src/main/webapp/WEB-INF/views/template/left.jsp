<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<link type="text/css" rel="stylesheet" href="${ctxPath }/admin/xtree/css/xtree.css">
<script type="text/javascript" src="${ctxPath }/admin/xtree/js/xtree.js"></script>
<script type="text/javascript" src="${ctxPath }/admin/xtree/js/xloadtree.js"></script>
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
       	if(!confirm("是否确定删除？")) {
    		return;
    	}   
        hideContextMenu();
        if (tree.getSelected())
        {
            var bResult = window.confirm("不能删除还有子栏目的目录，您确定要继续吗?");
            if (bResult)
            {
                hiddenForm["function"].value = "delete";
                hiddenForm.submit();
            }
        }
    }

    function reloadChildrenCatalog()
    {
        hideContextMenu();
        if (tree.getSelected())
        {
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
        if (src)
        {
			showNamingMenu("rightMenu");
        }
    }
	
	/**
	 * 导出模板
	 */
	function exportTemplate()
	{
		hideContextMenu();
        var value = tree.getSelected().value;
        hiddenForm.catalogId.value = value;
		
		if (tree.getSelected())
        {
            hiddenForm["function"].value = "dataExport";
            hiddenForm.submit();
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
        rightMenu.style.visibility = "hidden";
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
         		name: '导出所有模板',
           		onClick: function() {
           			exportTemplate();
           		}
           	}]
       	});	
    }
    

    //重定向鼠标左键事件的处理过程为自定义程序
    document.body.onclick = hideContextMenu;
</script>
<div class="Frame_tree">
<script type="text/javascript">
    var tree = new WebFXLoadTree("<a href='javascript:void(0);'>栏目结构</a>", "doShowChild.action");
    tree.write();
</script>
</div>
<link rel="stylesheet" href="${ctxPath }/admin/scripts/bootstrapMenu/css/bootstrap.min.css">
<script type="text/javascript" src="${ctxPath }/admin/scripts/bootstrapMenu/js/BootstrapMenu.min.js"></script>

<!--右键菜单-->
<div id="rightMenu" class="RightClickMenu" style="position:absolute; left:0px; top:-1px; width:136px; z-index:1000; visibility: hidden">
	<ul>
    	<li class="no"><a href="#" onClick="reloadChildrenCatalog()">重新读入子栏目</a></li>
        <li class="no"><a href="#" onClick="window.location.reload()">刷新栏目树</a></li>
		<li class="no"><a href="#" onClick="exportTemplate()" title="导出当前栏目及子栏目所有模板">导出所有模板</a></li>
    </ul>
</div>
<!--右键菜单-->
<form name="hiddenForm" action="template.action" target="catalogRightFrame">
    <input type="hidden" name="function" value="">
    <input type="hidden" name="catalogId" value="">
</form>
</html>
