
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../common.jsp" %>
<html>
<head>
<title>基金 - 赎回</title>
</head>
<c:if test="${success == 1 }">

<div class="peanut_main_box">
	<div class="peanut_main peanut_main_success">
    	<dl class="success">
        	<dt><img class="png" src="${basePath}images/Success.png" /></dt>
            <dd>基金赎回申请提交成功！</dd>
        </dl>
        <p class="bankCard_tips">您的申请将于${next_workday}返回确认结果，请注意查收短信或在<br />“<a href="${basePath}/servlet/query/FundInvestQuery">我的投资记录</a>”中查询，${workday}前可在“<a href="${basePath}/servlet/fund/TransitTradeAction">在途交易</a>”中撤销该交易</p>
        <!--<p class="bankCard_btn"><a class="png" href="javascript:void(0)">新增自动充值计划</a></p>-->
        <p class="need mt34 mb0">您可能需要:
            <a href="${basePath}fund.do?metho=listAllFund">返回首页</a>丨
            <a href="${basePath}/servlet/fund/RedeemAction">基金赎回</a>
        </p>
    </div>
</div>
</c:if>
<c:if test="${success == 0 }">
<div class="peanut_main_box">
	<div class="peanut_main peanut_main_success">
    	<dl class="success">
        	<dt><img class="png" src="${basePath }images/failure.png" /></dt>
            <dd>基金赎回申请提交失败！</dd>
        </dl>
        <p class="bankCard_tips">${message}</p>
        <p class="need mt34 mb0">您可能需要:
            <a href="${basePath}fund.do?metho=listAllFund">返回首页</a>丨
            <a href="${basePath}/servlet/fund/RedeemAction">基金赎回</a>
        </p>
    </div>
</div>
</c:if>
</body>
<script>

var url_1 = "${active.url_1}";
var url_2 = "${active.url_2}";
var nspUrl = $("input[name='nspUrl']").val();
$(document).ready(function(){
	$("a#npsRequest1").on("click",function(){
		if(url_1 != null && url_1 != '')
		{
			if(url_1 == nspUrl){
				callNPS(url_1);
				return false;
			}else{
				window.open(url_1);
			}
		}
		return false;

	});
	
$("a#npsRequest2").on("click",function(){
		if(url_2 != null && url_2 != '')
		{
			if(url_2 == nspUrl){
				callNPS(url_2);
				return false;
			}else{
				window.open(url_2);
			}
		}
		return false;

	});
});

function callNPS(nspUrl)
{
			var partnerName = $("input[name='PartnerName']").val();
			var partyNo = $("input[name='PartyNo']").val();
			var clientNo = $("input[name='ClientNo']").val();
			var telNo = $("input[name='TelNo']").val();
			var txnTime = $("input[name='TxnTime']").val();
			var touchPoint = $("input[name='TouchPoint']").val();
			var paSignature = $("input[name='PaSignature']").val();
			var channel = $("input[name='Channel']").val();
			var modelNo = $("input[name='ModelNo']").val();
			var mem_name = $("input[name='MEM_NAME']").val();
			var mem_mf= $("input[name='MEM_MF']").val();
			var url =  nspUrl + "?PartnerName=" + partnerName + "&PartyNo=" + partyNo + "&ClientNo=" + clientNo + 
					"&TelNo=" + telNo + "&TxnTime=" + txnTime + "&TouchPoint=" + touchPoint + "&PaSignature=" + paSignature +
					"&Channel=" + channel + "&ModelNo=" + modelNo + "&MEM_NAME=" + mem_name + "&MEM_MF=" + mem_mf;
			
			window.open(url);
}
</script>
</html>