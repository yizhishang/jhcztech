<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/admin/common/header.jsp"%>
<link href="${ctxPath }/admin/styles/checktree.css" rel="stylesheet"
	type="text/css" />
<script language="javascript"
	src="${ctxPath }/admin/scripts/jquery.new.checktree.js"></script>
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
	
    $(document).ready(function()
    {
		winResize();
		

        $("#close").click(function()
        {
            window.close();
        });
		
		

        $.ajax({
            url: "catalog.action?function=ajaxCatalogTree&id=${param.id}",
            cache: false,
            success: function(html)
            {
                $(".tree").html(html);
                $(".okbox").show();
                $("ul.tree").checkTree();
				
				$("input[@type=radio]").click(function(){
					//var name = $(this).next("label").html();
					var route = $(this).attr("route");
					var routes = route.split("|");
					var parentCatalogs = "";
					for(var i=0;i<routes.length;i++)
					{
						var labName = $("#lab_" + routes[i]).html();
						labName = (labName == null)?'根目录':labName;
						parentCatalogs += labName;
						if(i < routes.length - 1)
						{
							parentCatalogs += "|";
						}
					}
					var value = $(this).val();
					window.returnValue=parentCatalogs + ":" + value;
					window.close();
				});
            }
        });
    });
</script>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
		height="100%">
  <tr>
    <td><div class="title">
        <p> <img src="${ctxPath }/admin/images/ico04.gif" /> 选择栏目 </p>
      </div></td>
  </tr>
  <tr>
    <td class="edinner"><table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
        <tr>
          <td><div class="space"></div>
            <table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="editortable">
              <tr>
                <td><div id="divContainer"
											style="overflow-y: auto;overflow-x:hidden;width:100%;">
                    <div class="treebox">
                      <ul class="tree" style="margin-left: 15px;">
                        <img src="${ctxPath }/admin/images/loading.gif" /> 正在加载栏目树，请稍候
                      </ul>
                    </div>
                  </div>
                  <div class="okbox" style="display:none" align="center">
                    <input type="button" name="close" id="close" value="关闭" class="bt04" />
                  </div></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
<iframe id="hiddenFrame" name="hiddenFrame" width="0" height="0"
		frameborder="0" marginwidth="0" marginheight="0"
		src="${ctxPath }/null.html"></iframe>
</body>
</html>