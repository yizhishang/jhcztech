(function(jQuery) {
	jQuery.fn.checkTree = function(settings)
	{
		settings = jQuery.extend({
			isEffect:true//是否使用点击checkbox时使用js效果
	    }, settings);
		
		var $tree = this;
		$tree.find("li")
		.each(function(){
			 var $arrow = jQuery('<div class="arrow"></div>');
			 if (jQuery(this).is(":has(ul)")) {
			 	$arrow.addClass("expanded");
				$arrow.click(function(){
					jQuery(this).siblings("ul").toggle();
					if(jQuery(this).hasClass("collapsed"))
					{
						jQuery(this)
							.addClass("expanded")
							.removeClass("collapsed");
					}
					else
					{
						jQuery(this)
							.addClass("collapsed")
							.removeClass("expanded");							
					}
				});
			 }
			 
			 jQuery(this).prepend($arrow);
			 
			 
			 var $checkbox = jQuery(this).find("div").siblings(":checkbox");
			 $checkbox.click(function(){
			 	if(jQuery(this).attr("checked"))
				{
			 		jQuery(this).siblings("ul").find(":checkbox").attr("checked","checked");
					jQuery(this).siblings("span").find(":checkbox").attr("checked","checked");
				}
				else
				{
					jQuery(this).siblings("ul").find(":checkbox").attr("checked","");
					jQuery(this).siblings("span").find(":checkbox").attr("checked","");
				}
				
				//这里负责处理上层的选中状态
				var nodeId=jQuery(this).attr("id");
 				var levelCount=nodeId.split("_").length;
 				var pNo=nodeId;
				for (var i = 0; i < levelCount-1; i++)
				{
					pNo=pNo.substring(0,pNo.lastIndexOf("_"));
					if($("#"+pNo).siblings("ul").find(":checkbox:checked").length>0){
						 $("#"+pNo).attr("checked","true");
					}else{
						 $("#"+pNo).attr("checked","");
					}
				}
	 
			 });
			 if(!settings.isEffect){
			 	$checkbox.unbind('click');
			 }
		})
		.end()
		.find("label")
			.click(function(){
				jQuery(this).siblings("div").click();
			})
			.hover(function(){
				jQuery(this).addClass("hover");
			},
			function(){
				jQuery(this).removeClass("hover");
			})
		.end()
		.find(".operate")
			.find(":checkbox").click(function(){
				var $span = jQuery(this).parent().parent();
				var $bool = false;
				$span.find(":checkbox").each(function(){
					if(jQuery(this).attr("checked"))
					{
						 $bool = true;
					}
				})
				if($bool)
				{
					$span.siblings(":checkbox").attr("checked","checked");
				}
				else
				{
					$span.siblings(":checkbox").attr("checked","");
				}
			})
		.end()
	}
})(jQuery);