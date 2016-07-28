/**
 * 修改基金分红方式
 */
$(document).ready(function(){
	alert("修改基金分红方式");
	ajaxQueryDividend();
});


//查询可分红的基金
function ajaxQueryDividend(){
	
	$.ajax({
		type : "POST",
		url : '/trade-0.0.1/fundDividend.do?method=queryFundDividend',
		dataType : "json",
		data : {
		},
		success : function(msg) {
			
			//获取填充的样例页面
			$.ajax({
				type:"get",
				url:"/trade-0.0.1/view/template/fundManage/modifyFundDividendTemplate.html",
				dataType:"html",
				success:function(html){
					//alert(html);
					$("head").append(html);
					
					//处理数据
					var modifyFundDividendTemplate = template('fundDividend', msg);
					//console.log(modifyFundDividendTemplate);
					$("#fund_dividend_tbody").html(modifyFundDividendTemplate);
				}
			});
		}
	}); 
}


//展示分红信息
function ajaxShowFundDividend(){
	
	$.ajax({
		type : "POST",
		url : '/trade-0.0.1/fundDividend.do?method=queryFundInfo',
		dataType : "json",
		data : {
		},
		success : function(msg) {
			
			//获取填充的样例页面
			$.ajax({
				type:"get",
				url:"/trade-0.0.1/view/template/fundManage/fundDividendDialogTemplate.html",
				dataType:"html",
				success:function(html){
					//alert(html);
					$("head").append(html);
					//处理数据
					var fundDividendDialog = template('fundDividendDialogTemplate', msg);
					console.log(fundDividendDialog);
					$("#fundDividendDialog").html(fundDividendDialog);
				}
			});
		}
	}); 
}

//基金分红
function fundDividendDialog(fund_code){
	ajaxShowFundDividend();
	$("#fundDividendDialog").dialog({
		 width:400,
		 modal: true
	});
}

//修改基金分红方式
function modifyDividend(){
	var fund_code = $("#fundDividendDialog #fund_code").val();//基金代码
	var fund_name = $("#fundDividendDialog #fund_name").val();//基金名称
	var dividend_method = $("#fundDividendDialog #dividend_method").val();
	var modify_dividend_method = $("#fundDividendDialog #modify_dividend_method").val();
	var trade_pwd = $("#fundDividendDialog #trade_pwd").val();
	var params = {"fund_code":fund_code,"fund_name":fund_name,"dividend_method":dividend_method,"modify_dividend_method":modify_dividend_method,"trade_pwd":trade_pwd};
	$.ajax({
		type : "POST",
		url : '/trade-0.0.1/fundDividend.do?method=modifyFundDividend',
		dataType : "json",
		data : params,
		success : function(msg) {
			$("#fundDividendDialog").dialog( "close" );
			alert("修改分红方式成功！");
		}
	});
}