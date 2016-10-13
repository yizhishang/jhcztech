jQuery.fn.extend({
	loadData:function(aUrl,jsonVal,type,isPost)
	{
		var obj = this;
		
		jsonVal.reqUrl = aUrl;
		var sendType = "post";
		if(!isPost)
		  sendType = "get";
		jQuery.ajax({
			type: sendType,
			url: aUrl,
			cache:false,
			data:jsonVal,
			beforeSend: function(XMLHttpRequest){ 
				obj.showLoading();
			},
			success: function(data, textStatus)
			{
				obj.html(data);
				
			},
			complete: function(XMLHttpRequest, textStatus){
				if(type==true)
				javascript:scroll(0,0); 
			},
			error: function(){
				//请求出错处理
				obj.html("<span style='font-size:10pt'>加载数据失败！</span>");
			}
		});
	},
	showLoading:function()
	{
		this.html("<span style='font-size:10pt'><img src='/admin/images/loading.gif'/>正在加载，请稍侯......</span>");
	}		 
});