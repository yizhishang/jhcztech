$(document).ready(function(){
	var $authorSelect = jQuery("<select name=\"author_select\" style=\"width:118px;\"></select>").appendTo(jQuery("#author").parent().append(" "));
	
	var $addAuthorObj = jQuery("<a href='javascript:void(0);'>加入列表</a>").appendTo(jQuery("#author").parent().append(" "));
	
	var $authorManage = jQuery("<a href='javascript:void(0)'>作者维护</a>").appendTo(jQuery("#author").parent().append(" "));
	
	jQuery.fn.extend({
		addOption:function(text,value)
		{
			$authorSelect.get(0).options.add(new Option(text,value));
		},
		showAuthor:function()//显示文章作者
		{
			jQuery(this).empty();
			jQuery(this).addOption("无作者","");
			jsonVal = {"type":"query","rand":(new Date()).getTime()};
			jQuery.getJSON(
					"ajaxAuthorManage.action",
					jsonVal,
					function(result)
					{
						if(result.errorNo == 0 && result.obj)
						{
							var json = result.obj;
							if(json != null && json != "")
							{
								jQuery(json).each(function(i)
								{
									$authorSelect.addOption(json[i].author, json[i].author);
								});		
							}
						}
					}
				);
		}
	});
	
	$authorSelect.change(function(){
		jQuery("input[name='form.author']").val(jQuery(this).val());													
	}).showAuthor();
	
	$addAuthorObj.click(function(){
		var author = jQuery("input[name='form.author']").val();
		author = jQuery.trim(author);
		if(author.length == 0 || author == '不详' || author == '无作者')
		{
			alert("请输入作者名称！");
			jQuery("input[name='form.author']").focus();
			return;
		}
		
		if(author.length >= 15)
		{
			alert("作者名称超过长度限度！");	
			jQuery("input[name='form.author']").focus();
			return;
		}
		
		author = encodeURI(author);
		jsonVal = {"type":"add","author":author,"rand":(new Date()).getTime()};
		jQuery.getJSON(
				"ajaxAuthorManage.action",
				jsonVal,
				function(result)
				{
					if(result && result.errorNo == 0 && result.obj)
					{
						var json = result.obj;
						if(json != null && json != "")
						{
							var error_code = json[0].error_code;
							var error_msg = json[0].error_msg;
							alert(error_msg);
							if(error_code == 0)
							{
								$authorSelect.showAuthor();
							}
						}
					}
				}
			);
	});
	
	//作者管理
	$authorManage.click(function(){
		var returnValue = openDialogWithScroll("showAuthor.action", 500, 650);
			if (!isEmpty(returnValue))
			{
				jsonVal = {"type":"del","ids":returnValue,"rand":(new Date()).getTime()};
				jQuery.getJSON(
						"ajaxAuthorManage.action",
						jsonVal,
						function(result)
						{
							if(result.errorNo == 0 && result.obj)
							{
								var json = result.obj;
								if(json != null && json != "")
								{
									var error_code = json[0].error_code;
									var error_msg = json[0].error_msg;
									alert(error_msg);
									if(error_code == 0)
										$authorSelect.showAuthor();
								}
							}
							
						}
					);
			}
	});
});
