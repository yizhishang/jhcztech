<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
function showTab(index)
{
	$(".menu li").each(function(i){
		$(this).removeClass();
		if(i == index)
		{
			$("#tab" + i).show();
			$(this).addClass("cur");
		}
		else
		{
			$("#tab" + i).hide();
			$(this).addClass("no");
		}		
	});
	
    //异步查询自定义字段
	if(index == 2)
	{
		if($.trim($("#tab2").html()).length == 0)
		{
			var jsonVal={catalogId:${form.catalogId},articleId:'${form.id}'};
			$("#tab2").loadData("ajaxReadCatalogTree.action",jsonVal);
		}
	}
	
	//异步查询自定义字段
	if(index == 3)
	{
		if($.trim($("#tab3").html()).length == 0)
		{
			var jsonVal={catalogId:${form.catalogId},articleId:'${form.id}'};
			$("#tab3").loadData("ajaxExtendFieldInfo.action",jsonVal);
		}
	}
}
</script>
<div class="space"></div>
<ul class="menu">
  <li class="cur"><a href="#" onClick="showTab(0);"><img src="${ctxPath }/admin/images/ico16.gif" border="0"/>基本信息</a></li>
  <li class="no"><a href="#" onClick="showTab(1);"><img src="${ctxPath }/admin/images/ico17.gif" border="0"/>文章属性</a></li>
  <li class="no"><a href="#" onClick="showTab(2);"><img src="${ctxPath }/admin/images/ico17.gif" border="0"/>文章复制</a></li>
  <li class="no"><a href="#" onClick="showTab(3);"><img src="${ctxPath }/admin/images/ico18.gif" border="0"/>自定义字段</a></li>
  <!--<li class="no"><a href="#" onClick="showTab(3);"><img src="images/ico19.gif" border="0"/>文档权限</a></li>-->
</ul>
