<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>银行卡交易明细</title>
</head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<script src="<%=basePath %>resources/jquery-easyui-1.4.1/jquery-1.10.2.min.js"></script>
<script src="<%=basePath %>js/jquery.validate.min.js"></script>
<script src="<%=basePath %>js/validateMethod.js"></script>
<style>
	.flag{
		background-color: red;
	}
</style>
<body>
	<div>
		<p>银行卡:<input type="text" id="bankCard"/></p>
		<div id="trade_date"><p>交易时间:<a href="#" class="flag" value="0">近一周</a>&nbsp;<a href="#" value="0">近一月</a>&nbsp;<a href="#" value="1">近3月</a>&nbsp;<a href="#" value="2">自定义</a>:<input type="text" id="startDate" value="3"></input>&nbsp;<input type="text" id="endDate" value="3"></input></p></div>
		<div id="trade_type">
			<p>交易类型:<a href="#" value="0">全部</a>&nbsp;<a href="#" class="flag" value="1">充值</a>&nbsp;<a href="#" value="2">普通取现</a>&nbsp;<a href="#" value="3">快速取现</a>&nbsp;<a href="#" value="4">认购</a>&nbsp;<a href="#" value="5">申购</a>&nbsp;<a href="#" value="6">赎回</a>&nbsp;<a href="#" value="7">分红</a></p>
		</div>
		<div id="trade_result">
			<p>交易结果:<a href="#" value="0">全部</a>&nbsp;<a href="#" value="1">成功</a>&nbsp;<a href="#" class="flag" value="2">失败</a>&nbsp;<a href="#" value="3">申请成功</a>&nbsp;<a href="#" value="4">撤单成功</a></p>
		</div>
		
	
	
	</div>
</body>
</html>
<script>
	$(document).ready(function(){
		//交易时间
		// $("#trade_date a").each(function(index){
		//	$(this).
		//}); 
		$("#trade_date a").bind("click",function(){
			$(this).addClass("flag").siblings().removeClass();
			trade_date = $(this).val();
			dosearch();
		});
		//交易类型
		//$("#trade_type a").each(function(index){
			//alert(index);
		//});
		$("#trade_type a").bind("click",function(){
			$(this).addClass("flag").siblings().removeClass();
			trade_type = $(this).val();
			dosearch();
		});
		
		//交易结果
		//$("#trade_result a").each(function(index){
			//alert(index);
		//});
		$("#trade_result a").bind("click",function(){
			$(this).addClass("flag").siblings().removeClass();
			trade_result = $(this).val();
			dosearch();
		});
		
		
	});
	
	//搜索
	function dosearch(){
		var trade_date = $("#trade_date a").filter(".flag").attr("value");
		var trade_type = $("#trade_type a").filter(".flag").attr("value");
		var trade_result = $("#trade_result a").filter(".flag").attr("value");
		$.ajax({
			type : "POST",
			url : '<%=basePath%>bindBankCard.do?method=queryBankCardDetail',
			dataType : "json",
			data : {
				"trade_date":trade_date,
				"trade_type":trade_type,
				"trade_result":trade_result
			},
			success : function(msg) {
				if (msg.data == 0) {
	                alert("修改成功~");
				}
			}
		}); 
	}
</script>