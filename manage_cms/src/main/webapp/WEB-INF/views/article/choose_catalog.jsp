<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/admin/common/header.jsp"%>
<link href="${ctxPath }/admin/styles/checktree.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctxPath }/admin/scripts/jquery.new.checktree.js"></script>
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
		$("#divContainer").css("height",$(window).height() - 88);
	}
	
	function selectFunction()
    {
   		var sCatalogs='';			
		$("input[name='id_fun']").each(function(){
			if($(this).attr("checked"))
			{
				sCatalogs=sCatalogs+$(this).val()+',';
			}
		});
		
    	if(sCatalogs.length>0)
    	{
    		sCatalogs = sCatalogs.substring(0,sCatalogs.length-1);
    		var publishVal = jQuery('#isPublish:checked').val();
    		if(publishVal)
    		{
    			window.returnValue=sCatalogs+";"+jQuery('#isPublish:checked').val();
    		}
    		else
    		{
    			window.returnValue=sCatalogs;
    		}
    		window.close();
    	}
    	else
    	{
    		alert("请选择目标栏目。");
    	}
    }
    
    $(document).ready(function()
    {
		winResize();

        $("#close").click(function()
        {
            window.close();
        });

        $.ajax({
            url: "article.action?function=ajaxShowCatalogRoleRight&id=${param.id}",
            cache: false,
            success: function(html)
            {
                $(".tree").html(html);
                $(".okbox").show();
                $("ul.tree").checkTree();
            }
        });
    });

    function nodecheck(args)
    {
        if (args && args.length > 0)
        {
            for (var i = args.length - 2; i >= 0; i--)
            {
                alert($("#" + args[i]).siblings("ul").find(":checkbox:checked").length);
                if ($("#" + args[i]).siblings("ul").find(":checkbox:checked").length > 0)
                {
                    $("#" + args[i]).attr("checked", "true");
                }
                else
                {
                    $("#" + args[i]).attr("checked", "");
                }
            }
        }
    }
</script>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
		height="100%">
  <tr>
    <td><div class="title">
        <p> <img src="${ctxPath }/admin/images/ico04.gif" /> 复制文章 </p>
      </div></td>
  </tr>
  <tr>
    <td class="edinner">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td><div class="space"></div>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
              <tr>
                <td><div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
                    <div class="treebox">
                      <ul class="tree" style="margin-left: 15px;">
                        <img src="${ctxPath }/admin/images/loading.gif" /> 正在加载栏目树，请稍候
                      </ul>
                    </div>
                  </div>
                  <div class="okbox" style="display:none" align="center"> &nbsp;&nbsp;
                    <input type="checkbox" id="isPublish" name="isPublish" value="1" checked="checked">直接发布
                    &nbsp;&nbsp;
                    <input type="button" name="button" id="button" onClick="selectFunction()" value="选择" class="bt04" />
                    <input type="button" name="close" id="close" value="关闭" class="bt04" />
                  </div></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
<iframe id="hiddenFrame" name="hiddenFrame" width="0" height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>