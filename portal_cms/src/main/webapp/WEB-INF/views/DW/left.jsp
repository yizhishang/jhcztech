<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
<%@ page import="com.jhcz.base.util.RequestHelper"%>
<link type="text/css" rel="stylesheet" href="xtree/css/xtree.css">
<script type="text/javascript" src="xtree/js/xtree.js"></script>
<script type="text/javascript" src="xtree/js/xloadtree.js"></script>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		var menuNo = '<%=RequestHelper.getString(request,"menuNo")%>';
		if('1' == menuNo)
		{
			$("#bottomObj").show();
			$(".Frame_tree").css("height",$(window).height() - 40);
		}
		else
		{
			$("#bottomObj").hide();
			$(".Frame_tree").css("height",$(window).height() - 20);
		}
		
	});
	
    function showContextMenu()
    {
        hideContextMenu();
        /*var value = tree.getSelected().value;
        hiddenForm.catalogId.value = value;

        var src = tree.getSelected().getSrc();
        if (src)
        {
			showNamingMenu("rightMenu");
        }*/
    }

    function hideContextMenu()
    {
        rightMenu.style.visibility = "hidden";
    }
	
	function selectRadioVal()
	{
		var catalogId = $(":radio:checked").val();
		if($.trim(catalogId).length > 0)
		{
			var catalogName = $(":radio:checked").siblings("a").text();
			var arr = new Array(2);
			arr[0] = catalogId;
			arr[1] = catalogName;
			window.returnValue = arr;
			window.close();
		}
		else
		{
			alert("请选择栏目");
		}
	}
    
</script>
<div class="Frame_tree" style="width:225px;position:static;">
<script type="text/javascript">
    var tree = new WebFXLoadTree("<a href='javascript:void(0);'>栏目结构</a>", "dw_template.action?function=showChild&menuNo=<%=RequestHelper.getString(request,"menuNo")%>");
    tree.write();
</script>

</div>
<div class="tdbox" align="center" style="display:none;" id="bottomObj"> 
<input type="button" name="button2" id="button2" value="确定" class="bt01" onClick="selectRadioVal();"/>
<input type="button" name="button2" id="button2" value="关闭" class="bt01" onClick="window.close();"/>
</div>
</html>
