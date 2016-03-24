<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/admin/common/header.jsp" %>
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
		$("#divContainer").css("height",$(window).height() - 170);
	}
	
	function showCatalogTree(type)
	{
		$(".tree").html("<img src=\"${ctxPath }/admin/images/loading.gif\" />正在加载栏目树，请稍候");
		$.ajax({
            url: "ajaxShowCatalogTree.action?type="+type,
            cache: false,
            success: function(html)
            {
                $(".tree").html(html);
                $(".okbox").show();
                $("ul.tree").checkTree({isEffect:false});
            }
        });
	}

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
    $(document).ready(function()
    {
    	winResize();
		
		$("#enterForm").click(function(){
			var time;
			var type = $("input[name='form.type']:checked").val();
			if(type == 0)
			{
				time = $("#fixedHour").val() + ":" + $("#fixedMinute").val();
			}
			else
			{
				time = $("#minute").val();
			}
			$("#time").val(time);
			submitForm("addForm",null, false);
        });

        $("#close").click(function()
        {
            window.close();
        });

        showCatalogTree("0");
		
		$("input[name='form.type']").click(function(){
			if($(this).val() == '0')
			{
				$("#fixedTime").show();
				$("#notFixedTime").hide();
				showCatalogTree("0");
			}
			else
			{
				$("#fixedTime").hide();
				$("#notFixedTime").show();
				showCatalogTree("1");
			}
		});
		
    });
</script>
<body>
<form action="add.action" method="post" target="hiddenFrame" id="addForm">
  <input type="hidden" name="function" value="add"/>
  <input type="hidden" name="form.time" id="time"/>
  <input type="hidden" name="form.state" value="0"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <tr>
      <td><div class="title">
          <p><img src="${ctxPath }/admin/images/ico04.gif" />添加发布计划</p>
        </div></td>
    </tr>
    <tr>
      <td class="edinner"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><div class="space"></div>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="editortable">
                <tr>
                  <td colspan="2" style="height:8px;"></td>
                </tr>
                <tr>
                  <td width="120" class="label">计划类型：</td>
                  <td><input type="radio" name="form.type" value="0" checked="checked">
                    固定时间发布
                    <input type="radio" name="form.type" value="1" >
                    间隔时间发布
                    <script language="javascript">setRadioChecked("form.type", "<c:out value='${form.type}'/>")</script>
                  </td>
                </tr>
                <tr>
                  <td width="120" class="label">时间：</td>
                  <td><div id="fixedTime"><select name="fixedHour" id="fixedHour">
                      <script language="javascript">
				  	for(var i=0;i<24;i++)
					{
						var j = i;
						if(i<10)
							j = "0"+i;
						document.write("<option value='"+j+"'>"+j+"</option>");
					}
				  </script>
                    </select>
					<script language="javascript">setSelectSelected("fixedHour", "08")</script>
                    时
                    <select name="fixedMinute" id="fixedMinute">
                      <script language="javascript">
				  	for(var i=0;i<60;i++)
					{
						var j = i;
						if(i<10)
							j = "0"+i;
						document.write("<option value='"+j+"'>"+j+"</option>");
					}
				  </script>
                    </select>
                    分 </div>
					<div id="notFixedTime" style="display:none">
					<select name="minute" id="minute">
                      <script language="javascript">
				  	for(var i=1;i<=60;i++)
					{
						var j = i;
						if(i<10)
							j = "0"+i;
						document.write("<option value='"+j+"'>"+j+"</option>");
					}
				  </script>
                    </select>
					<script language="javascript">setSelectSelected("minute", "01")</script>
                    分 
					</div>
					</td>
                </tr>
                <tr>
                  <td width="120" class="label">是否发布子栏目：</td>
                  <td><input type="radio" name="form.recursion" value="0" checked="checked">
                    否
                    <input type="radio" name="form.recursion" value="1" >
                    是
                    <script language="javascript">setRadioChecked("form.isinherit", "<c:out value='${form.type}'/>")</script>
                  </td>
                </tr>
                <tr>
                  <td width="120" class="label">选择栏目：</td>
                  <td><div id="divContainer" style="overflow-y: auto;overflow-x:hidden;width:100%;">
                      <div class="treebox">
                        <ul class="tree" style="margin-left: 15px;">
                          <img src="${ctxPath }/admin/images/loading.gif" />正在加载栏目树，请稍候
                        </ul>
                      </div>
                    </div></td>
                </tr>
                <tr>
                  <td class="label">&nbsp;</td>
                  <td><input type="button" name="enterForm" id="enterForm" value="确定" class="bt04"/>
                    &nbsp;
                    <input type="button" id="close" name="close" value="关闭" class="bt04"/>
                  </td>
                </tr>
                <tr>
                  <td colspan="2" style="height:8px;"></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0"  height="0" frameborder="0" marginwidth="0" marginheight="0" src="${ctxPath }/null.html"></iframe>
</body>
</html>