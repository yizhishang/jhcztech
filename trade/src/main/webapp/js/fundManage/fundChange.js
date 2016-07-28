/**
 * 基金转换
 */
$(document).ready(function(){
	
	//alert("基金转换");
	ajaxQueryTransFund();
	fundTrans();
});

//查询可转换的基金
function ajaxQueryTransFund(){
	
	$.ajax({
		type : "POST",
		url : '/trade-0.0.1/fundTrans.do?method=queryTransFund',
		dataType : "json",
		data : {
		},
		success : function(msg) {
			
			//获取填充的样例页面
			$.ajax({
				type:"get",
				url:"/trade-0.0.1/view/template/fundManage/fundChangeTemplate.html",
				dataType:"html",
				success:function(html){
					//alert(html);
					$("head").append(html);
					
					//处理数据
					var fundChangeHtml = template('fundTrans', msg);
					console.log(fundChangeHtml);
					$("#tbody_tr").html(fundChangeHtml);
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

//弹出基金转换界面
function openFundTransDialog(fund_code){
	$.ajax({
		type : "POST",
		url : '/trade-0.0.1/fundTrans.do?method=queryFundShare',
		dataType : "json",
		data : {
			"fund_code":fund_code
		},
		success : function(msg) {
			$("#openFundTransDialog").dialog({
				 width:400,
				 modal: true
			});
			
		}
	}); 
}

//基金转换
function fundTrans(){
	$("#fundTrans").bind("click",function(){
		$.ajax({
			type : "POST",
			url : '/trade-0.0.1/fundTrans.do?method=fundTrans',
			dataType : "json",
			data : {
			},
			success : function(msg) {
				alert("基金转换成功");
			}
		}); 
	});
}

