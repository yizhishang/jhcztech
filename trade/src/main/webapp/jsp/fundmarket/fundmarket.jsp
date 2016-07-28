<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../common.jsp" %>
<html>
<head>
<title>基金超市</title>
</head>
<body>
<table id="allFund" >
	<tr>
		<th>基金代码</th>
		<th>基金名称</th>
		<th>基金类型</th>
		<th>风险等级</th>
		<th>最新净值</th>
		<th>人气</th>
		<th>基金状态</th>
		<th>购买</th>
	</tr>
	<c:if test="${empty fundList }">
		<tr><td colspan="3">${msg }</td></tr>
	</c:if>
	<c:forEach items="${fundList }" var="fundInfo">
		<tr>
			<td>${fundInfo.fundCode }</td>
			<td>${fundInfo.fundName }</td>
			<td>${fundInfo.fundTypeDesc }</td>
			<td>${fundInfo.fundRiskLevelDesc }</td>
			<td>
			<fmt:formatNumber pattern="##0.000" value="${fundInfo.nav }" />
			</td>
			<td>${fundInfo.populararity }</td>
			<td>${fundInfo.fundStatusDesc }</td>
			<td><input type="button" onclick="buyFund('${fundInfo.fundCode }','${fundInfo.fundRiskLevel }');" value="购买"/></td>
		</tr>
	</c:forEach>
</table>
<!--风险评估未做弹窗开始-->
<div id="pop_doNot" style="display:none">
<div class="popup_box"></div>
<div class="popup_e1_bg"></div>
<div class="popup_e1_pad12">
	<div class="popup_pad12_top clearfix">
    	<a class="close png" href="javascript:void(0)"></a>
    	<h2>风险评估</h2>
    </div>
    <div class="e1_assess_que">
    	<div class="e1_assess_box" style="overflow: auto;">
        	<div class="e1_assess_content">
            	<h6>为了帮助您更加了解自己适合投资什么类型的产品，建议您完成风险承受能力评估问卷</h6>
                <form action="${ctxPath}/servlet/account/info/RiskAction?function=assess" method="post" id="riskForm" target="hiddenFrame" autocomplete="off" >
                	<input type="hidden" name="callback" value="submitRiskCallbak" />
                	<input type="hidden" name="questionno" value="" />
	                <div class="questionnaire_body">
	                	
	                </div>
                </form>
            </div>
            <div class="e1_assess_scroll" style="display:none;">
            	<div class="e1_assess_scroll_in">
            		<span></span>
            	</div>
            </div>
        </div>
        <a href="javascript:;" class="e1_assess_submit" id="riskFormSubmit" title="提交">提 交</a>
    </div>
</div>
</div>
<!--风险评估未做弹窗结束-->
</body>
<script type="text/javaScript">
var clientRiskLevel = '';  //当前客户的风险等级

function buyFund(fundCode,fundRiskLevel){
	if(clientRiskLevel == null || clientRiskLevel == ''){  //客户未做风险等级评测
		alert("为了帮助您更加了解自己适合投资什么类型的产品，建议您完成风险承受能力评估问卷");
	}
	window.location.href = '${basePath}fund/doFundBuy?fundCode='+fundCode;
}

</script>

</html>