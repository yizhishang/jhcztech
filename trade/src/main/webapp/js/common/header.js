/**
 * 头部
 */
$(document).ready(function(){
	ajaxHeader();
});

//页面头部信息
function ajaxHeader(){
	$.ajax({
		type : "POST",
		url : '/trade-0.0.1/include.do?method=findPageHeader',
		dataType : "json",
		data : {
		},
		success : function(msg) {
			
			//获取填充的样例页面
			$.ajax({
				type:"get",
				url:"/trade-0.0.1/view/template/common/header.html",
				dataType:"html",
				success:function(html){
					$("head").append(html);
					//处理数据
					var fundChangeHtml = template('header', msg);
					console.log(fundChangeHtml);
					$("#header1").html(fundChangeHtml);
				}
			});
			
			/*alert(msg.error_no);
			alert(msg.message);
			if (msg.error_no == 0) {
                alert(msg.data);
			}*/
		}
	}); 
}
