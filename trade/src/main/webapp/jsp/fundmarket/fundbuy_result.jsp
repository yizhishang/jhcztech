<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../common.jsp" %>
<html>
<head>
<title>基金 - 购买</title>
<script src="<%=basePath %>js/validateMethod.js"></script>
</head>
<body>

<c:if test="${success == 1 }">
<div class="peanut_main_box">
	<div class="peanut_main peanut_main_success">
    	<dl class="success">
        	<dt><img class="png" src="${basePath}images/Success.png" /></dt>
            <dd>基金购买申请已提交成功</dd>
        </dl>
        <p class="bankCard_tips">您的申请将于${next_workday}返回确认结果，请注意查收短信或在<br />“<a href="#">我的投资记录</a>”中查询，${workday}前可在“<a href="#">在途交易</a>”中撤销该交易</p>
        <!--<p class="bankCard_btn"><a class="png" href="javascript:void(0);">新增自动充值计划</a></p>-->
        <p class="need mt34 mb0">您可能需要:
            <a href="${basePath}fund/listAllFund">返回首页</a>丨
            <a href="${ctxPath}/servlet/fund/AssetAction">我的资产</a>丨
            <a href="${ctxPath}/servlet/fund/TransitTradeAction">在途交易</a>
        </p>
    </div>
</div>
</c:if>
<c:if test="${success == '0' }">
<div class="peanut_main_box">
	<div class="peanut_main peanut_main_success">
    	<dl class="success">
        	<dt><img class="png" src="${basePath}images/failure.png" /></dt>
            <dd>基金购买申请提交失败</dd>
        </dl>
        <p class="fail_tips mt12">${message}</p>
        <p class="bankCard_tips">
        	如果银行协议（签约）状态有误，您可尝试<a href="#">重新绑卡</a>
        	<br/>
        	您可能需要:
            <a href="${basePath}fund/listAllFund">返回首页</a>丨
            <a href="${ctxPath}/servlet/fund/AssetAction">我的资产</a>丨
            <a href="${ctxPath}/servlet/fund/TransitTradeAction">在途交易</a>
        </p>
        
    </div>
</div>
</c:if>
<c:if test="${success == '2' }">
<div class="peanut_main_box">
	<div class="peanut_main peanut_main_success">
    	<dl class="success">
        	<dt><img class="png" src="${basePath}images/treatment.png" /></dt>
            <dd>交易申请已受理</dd>
        </dl>
        <!--<p class="fail_tips">失败文案:用以描述失败原因，可能时通讯或银行系统问题等</p>
        <p class="service_tel">客服热线：400-800-4800</p>-->
        <p class="waiting">正在等待系统返回处理结果，请稍后查询！</p>
        <p class="waiting_info"><a href="javascript:void(0)">如遇系统通讯不畅，会延后几分钟返回处理结果，请在交易查询中查询处理结果。</a></p>
        <p class="need mt34 mb0">您可能需要:
            <a href="${basePath}fund/listAllFund">返回首页</a>丨
            <a href="${ctxPath}/servlet/fund/AssetAction">我的资产</a>丨
            <a href="${ctxPath}/servlet/fund/TransitTradeAction">在途交易</a>
        </p>

    </div>
</div>
</c:if>
<script type="text/javascript">
var flag = true;
var $password;
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

