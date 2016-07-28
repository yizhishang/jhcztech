/**
 * 在途交易查询
 */
$(document).ready(function(){
	alert("在途资金查询");
	//在途交易查询
	transitTradeList();
	//查看详情
	showDetail();
});

/*
 * 在途交易列表
 */
function transitTradeList(){
	$.ajax({
		type : "POST",
		url : '/trade/transitTrade.do?method=queryTransitTrade',
		dataType : "json",
		data : {
		},
		success : function(msg) {
			
			//获取填充的样例页面
			$.ajax({
				type:"get",
				url:"/trade/view/template/fundManage/transitTradeTemplate.html",
				dataType:"html",
				success:function(html){
					$("head").append(html);
					//处理数据
					var transitTradeTemplate = template('transitTradeTemplate', msg);
					//console.log(modifyFundDividendTemplate);
					$("#transitTradeDetailContent").html(transitTradeTemplate);
				}
			});
		}
	}); 
}

/*
 * 查看详情
 */
function showDetail(index){
	$("#transitTradeDetail_"+index).show();
}

/*
 * 弹出撤单对话框
 */
function openKillOrderDialog(index){
	
	$.ajax({
		type : "POST",
		url : '/trade/transitTrade.do?method=queryKillOrderFundInfo',
		dataType : "json",
		data : {
		},
		success : function(msg) {
			
			//获取填充的样例页面
			$.ajax({
				type:"get",
				url:"/trade/view/template/fundManage/transitTradeTemplate.html",
				dataType:"html",
				success:function(html){
					$("head").append(html);
					//处理数据
					var killOrderDialogTemplate = template('killOrderDialogTemplate', msg);
					$("#killOrderDialog").html(killOrderDialogTemplate);
						$("#killOrderDialog").dialog({
							 width:400,
							 modal: true
						});
				}
			});
		}
	}); 
	
	
}

/*
 * 撤单
 */
function killOrder(){
	$.ajax({
		type : "POST",
		url : '/trade/transitTrade.do?method=killOrder',
		dataType : "json",
		data : {
		},
		success : function(msg) {
			alert("撤单成功!");
			$("#killOrderDialog").dialog("close");
		}
	});
}

