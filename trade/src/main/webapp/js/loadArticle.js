(function(){
	
	//获取json数据
	$.get("article.json",{
		catalogId: 2914,
		curPage: 2,
		numPerPage: 3
	},function(data){
		
		//获取填充的样例页面
		$.ajax({
			type:"get",
			url:"autoTemplate.html",
			dataType:"html",
			success:function(script){
				$("head").append(script);
				
				//处理数据
				var templateHtml = template('templateHtml', data.obj);
				$("tbody").html(templateHtml);
			}
		});
	});
})();
