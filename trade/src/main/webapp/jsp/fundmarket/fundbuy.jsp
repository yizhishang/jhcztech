<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../common.jsp" %>
<html>
<head>
<title>购买基金</title>
<script src="<%=basePath %>js/validateMethod.js"></script>
</head>
<body>
<div class="peanut_main_box">
	<div class="peanut_main pt0">
		<p class="pea_common_top">
        	<a href="<%=basePath %>fund/listAllFund">基金</a> &gt; <span>购买</span>
        </p>
    	<div class="peanut_main_in pt0">
    		<form id="fundForm" action="${basePath}fund/doFundBuyConfirm" method="post" autocomplete="off" spellcheck="false">
    		<table>
    		<tr>
    		<td><p class="name">基金名称</p></td>
            <td>${fundInfo.fundName}（${fundInfo.fundCode}）
                <input type="hidden" id="fundname" name="fundname" value="${fundInfo.fundName}"/>
				<input type="hidden" id="fundcode" name="fundcode" value="${fundInfo.fundCode}"/>
				<input type="hidden" id="buytype" name="buytype" value="${buytype}"/>
				<input type="hidden" id="business_code" name="business_code" value="${businessCode}"/>
				<input type="hidden" id="business_code1" name="business_code1" value="${businessCode1}"/>
			</td>
			</tr>
			<tr>
            	<td><p class="name">收费方式</p></td>
            <td>
               	<div class="toll_way clearfix">
               		<span class="toll_select"><i  class="toll_i active"></i>${fundInfo.shareClassName }</span>
                </div>
                <input type="hidden" id="shareclass" name="shareclass" value="${fundInfo.shareClass}"/>
                </td>
            </tr>
            <tr>
            	<td><p class="name">支付来源</p></td>
            <td>
                <select name="paysource" id="paysource" style="widht:300px;">
                <option>-------------请选择-------------</option>
                <c:forEach items="${bankCardList }" var="bankCard">
                	<option value="${bankCard.CASHACCOUNTID }">${bankCard.BANKACCTNAME }|尾号${bankCard.shortBankAcct }</option>
                </c:forEach>
                </select>
                </td>
            </tr>
            <input type="hidden" id="cashAccount" name="cashAccount" value="410015" />
            <input type="hidden" id="tradeAccount" name="tradeAccount" value="710015" />
            <input type="hidden" id="paysourse" name="paysourse" value="0" />
            <input type="hidden" id="cashaccountid" name="cashaccountid" value="" />
            <input type="hidden" id="capital_mode" name="capital_mode" value="X" />
            <input type="hidden" id="pay_mode" name="pay_mode" value="" />
            <input type="hidden" id="bankno" name="bankno" value="" />
            <input type="hidden" id="bankstate" name="bankstate" value="" />
            <input type="hidden" id="is_pay" name="is_pay" value="" />
            <tr>
            <td colspan="2"><p class="sum">可用余额：0.00元 </p></td>
                <input type="hidden" id="cash" name="cash" value="<#if data??>${data.available_assets}<#else>0.00</#if>" />
                <input type="hidden" id="max_amount" name="max_amount" value="" />
            </tr>
            <tr>
            	<td><p class="name">购买金额</p></td>
            <td>
                <input class="w316" id="apply_money" type="text" name="apply_money" placeholder="请输入购买金额" maxLength="12" autocomplete="off" />
            	<span class="yuan">元</span>
                <span id="apply_moneyTips"></span>
                </td>
            </tr>
			<tr>
            	<td><p class="name">大写金额</p></td>
            	<td><p class="value" id="capital">零元整</p></td>
            </tr>
            <c:choose>
            <c:when test="${fundInfo.chargeType=='2' }" >
            <tr>
            	<td><p class="name">购买费</p></td>
            <td>
                <p class="value">${fundInfo.specifyFee}元</p>
                <input type="hidden" id="specifyfee" name="specifyfee" value="${fundInfo.specifyFee}"/></td>
            </tr>
            </c:when>
            <c:otherwise>
            <tr>
            	<td><p class="name">费率优惠</p></td>
            	<td>
                <p class="p_discount">
                	<s id="original_value"></s>
                    <span class="dis_orange" id="original_value_real" ></span>
                    <i id="discount_rate_show" style="width:50px;display:none;"></i>
                </p>
                <input type="hidden" id="discount_rate" name="discount_rate" />
                <!--费率优惠时基金的原始费率-->
                <input type="hidden" id="original_value_1" name="original_value_1"/>
                </td>
            </tr>
            </c:otherwise>
			</c:choose>
            <input type="hidden" id="chargetype" name="chargetype" value="${fundInfo.chargeType}"/>
            <tr><td colspan="2"><a href="javascript:void(0);" class="bank_next png" id="nextStep">下一步</a></td></tr>
            </table>
            <input type="hidden" id="min_value" name="min_value" value="${min_value}" />
            <input type="hidden" id="max_value" name="max_value" value="${max_value}" />
            <input type="hidden" id="buyAmtMutiple" name="buyAmtMutiple" value="${buyAmtMutiple}" />
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
var $apply_money;
var transAcctState = "${transAcctState}";
var taAcctState = "${taAcctState}";
var custAcctState = "${custAcctState}";
$(document).ready(function(){

	var apply_money = $("#apply_money").val();
	var chargetype = $("#chargetype").val();
	if(chargetype == "1" && apply_money != null && apply_money != '' )
	{
		var specifyratefee = $("#feilv").val();
		$("#capital").text(chineseNumber(apply_money));
		var sum = parseFloat(apply_money)*(parseFloat(specifyratefee)/(1+parseFloat(specifyratefee)));
		$("#specifyfee").text(sum.toFixed(2) + "元");
		$("input[name='specifyfee']").val(sum.toFixed(2));
		$("#apply_money").addClass("input_move");
	}
	
	var $banklist = $("div.bankListWrap");
	/* $("a.changeCard").on("click",function(){
		if($banklist.is(":visible")){
			$banklist.hide();
		} else {
			$banklist.show();
		}
	}); */
	
	$apply_money = $("input[name='apply_money']").valid({
		focusClass:"input_move",
		onClass:"input_on316",
		errorClass:"input_error316",
		focusMsg:"<p class=\"on_tipsingle\">请输入购买金额</p>",
		errorMsg:"<p class=\"warn348 png\">购买金额有误</p>",
		defVal:true,
		defCallback:function(value){
			var fundcode = $("#fundcode").val();
			var paysourse = $("input[name='paysourse']").val();
			var max_amount = $("input[name='max_amount']").val();
			if(isNaN(value))
		    {
				$apply_money.tipError('<p class="warn348 png">购买金额有误！</p>');
		    }
		    else if(parseFloat(value) <= 0) {
				$apply_money.tipError("<p class=\"warn348 png\">购买金额必须大于0</p>");
			}
			else if(!(/^(([1-9][\d]*)|0)(\.[\d]{1,2})?$/.test(value))) {
				$apply_money.tipError("<p class=\"warn348 png\">购买金额有误</p>");
			}
			else if(paysourse == '0' && parseFloat(value) > parseFloat($("#cash").val()))
			{
				$apply_money.tipError('<p class="warn348 png">对不起，您的可用余额不足！</p>');
			}
			else if(paysourse == '1' && parseFloat(value) > parseFloat(max_amount))
			{
				$apply_money.tipError('<p class="warn348 png">购买金额不能大于银行卡的单笔限额！</p>');
			}
			else
			{
				var isFirst = $("#isFirst").val();
				var min_value = $("input[name='min_value']").val();
				var max_value = $("input[name='max_value']").val();
				var buyAmtMutiple = $("input[name='buyAmtMutiple']").val();
				if(parseFloat(max_value) > 0 && parseFloat(value) > parseFloat(max_value))
				{
					$apply_money.tipError('<p class="warn348 png">购买金额不能超过' + max_value + '元</p>');
				}
				else if(parseFloat(min_value) > 0 && parseFloat(value) < parseFloat(min_value))
				{
					$apply_money.tipError('<p class="warn348 png">购买金额不得低于'+min_value+'元</p>');
				}
				else if(parseFloat(buyAmtMutiple) > 0 && ((parseFloat(value)/parseFloat(buyAmtMutiple))%1) > 0)
				{
					$apply_money.tipError('<p class="warn348 png">购买金额必须为' + buyAmtMutiple + '的整数倍</p>');
				}
				else
				{
					$apply_money.removeClass("input_error348");
					$apply_money.setState(true);
					$apply_money.setTips('<span class="png yes348"></span>');
				}
			}
		}
	}).keyup(function(){
		var apply_money = $("#apply_money").val();
		checkFloat(apply_money);
		apply_money = $("#apply_money").val();
		var chargetype = $("#chargetype").val();
		$("#capital").text(chineseNumber(apply_money));
		if(chargetype == "1")
		{
			if(apply_money != null && apply_money != '')
			{
				var specifyratefee = $("#feilv").val();
				var sum = parseFloat(apply_money)*(parseFloat(specifyratefee));//(1+parseFloat(specifyratefee)));
				$("#specifyfee").text(sum.toFixed(2) + "元");
				$("input[name='specifyfee']").val(sum.toFixed(2));
			}
			else
			{
				$("#capital").text("零元整");
				$("#specifyfee").text("0元");
			}
		}
		if(chargetype == "0")
		{
			var shareclass = $("#shareclass").val();
			var fundcode = $("#fundcode").val();
			var chargetype = $("#chargetype").val();
			if(isNaN(apply_money))
			{
				$apply_money.setState(false);
				$apply_money.tipError('<p class="warn348 png">请输入正确的购买金额！</p>');
			}
			else if(apply_money < 0)
			{
				$apply_money.setState(false);
				$apply_money.tipError('<p class="warn348 png">购买金额必需大于0！</p>');
			}
			else 
			{
				var business_code = $("input#business_code").val();
				var business_code1 = $("input#business_code1").val();
				var capital_mode = $("input[name='capital_mode']").val();
				var pay_mode = $("input[name='pay_mode']").val();
				var bankno = $("input[name='bankno']").val();
				$.ajax({
					url:"${ctxPath}/servlet/fund/FundApplyBuyAction?function=loadDiscount_rate",
					data:{
						"shareclass":shareclass,
						"apply_money":apply_money,
						"fundcode":fundcode,
						"business_code":business_code,
						"business_code1":business_code1,
						"capital_mode":capital_mode,
						"pay_mode":pay_mode,
						"bankno":bankno
					},
					success:function(data){
						if(data != "" && data != "null" && data != null)
						{
							data = $.parseJSON(data);
							var discount_rate = data.discount_rate;//费率折扣
							var original_value = data.original_value;//原始费率
							var original_value_real = (parseFloat(discount_rate)*parseFloat(original_value)*100).toFixed(3)+"%";//实际费率
							if(parseFloat(discount_rate) >= 1)//不打折
							{
								$("#original_value_real").text((parseFloat(original_value)*100).toFixed(3)+"%");
								$("#discount_rate_show").hide();
								$("#original_value").text("");
								$("#discount_rate_show").text("");
								$("#original_value_1").val(original_value_real);
							}
							else
							{
								$("#original_value").text((parseFloat(original_value)*100).toFixed(3)+"%");
								$("input[name='discount_rate']").val((parseFloat(discount_rate)*100).toFixed(3)+"%");
								$("#discount_rate_show").show();
								$("#original_value_real").text(original_value_real);
								$("#discount_rate_show").text(parseFloat(parseFloat(discount_rate)*10.0).toFixed(2)+"折");
								$("#original_value_1").val(original_value_real);
							}
						}
					}
				});
			}
		}
	});
	
	$("#nextStep").click(function(){
		if(transAcctState != null && transAcctState != '')
		{
			$.alert("您的账户暂时不能进行该操作<br/>详情咨询400-800-4800");
			return false;
		}
		if(taAcctState != null && taAcctState != '')
		{
			$.alert("您的账户暂时不能进行该操作<br/>详情咨询400-800-4800");
			return false;
		}
		if(custAcctState != null && custAcctState != '')
		{
			$.alert("您的账户暂时不能进行该操作<br/>详情咨询400-800-4800");
			return false;
		}
		// 先判断风险测评
		if(!$apply_money.validate()){
			return false;
		}
		var is_pay = $("input[name='is_pay']").val();
		if(is_pay == "0")
		{
			$.alert("暂不支持该银行卡购买，建议使用或新增其他银行卡<br/>带来不便，深表歉意！<br/>详情咨询400-800-4800。");
			return false;
		}
		var cashaccountid = $("input[name='cashaccountid']").val();
		var paysourse = $("input[name='paysourse']").val();
		if((cashaccountid == null || cashaccountid == "") && paysourse !='0')
		{
			$.alert("请重新选择支付来源");			
			return false;
		}
		$("#fundForm").submit();
	});
	
	$(document).keydown(function(evt){
		if(evt.keyCode == 13)
		{
			return false;
		}
	});
	
	$(document).click(function(event){
		if(!jQuery.contains(document.getElementById("bankList_box"),event.target)){
			$(".bankListWrap").hide();
		}
	});
	
});
function submitRiskCallbak(data)
{
	var errorno = data.error_no;
	if(errorno == 0)
	{
		$("#pop_doNot").hide();
		$.alert(data.error_info,function(){
			//$("#submit").submit();
			$("#fundForm").submit();
		});
	} 
	else 
	{
		$.alert(data.error_info);
	}
	$("#hiddenFrame").attr("src","about:blank");
}

//验证份额精度
function checkFloat(apply_money)
{
	
	var floatApplicationvol = apply_money.indexOf('.');
	if(floatApplicationvol == '-1')
	{
	}
	else
	{
		var decimalPart=apply_money.substring(floatApplicationvol+1,apply_money.length);
		if(decimalPart.length>2)
		{   
            $("#apply_money").val(parseFloat(apply_money).toFixed(2));
        }
	}
}


//数字转中文大写
function chineseNumber(dValue) {
	var maxDec = 2;
	// 验证输入金额数值或数值字符串：
	dValue = dValue.toString().replace(/,/g, "");
	dValue = dValue.replace(/^0+/, ""); // 金额数值转字符、移除逗号、移除前导零
	if (dValue == "") {
		return "零元整";
	} // （错误：金额为空！）
	else if (isNaN(dValue)) {
		return "错误：金额不是合法的数值！";
	}
	var minus = ""; // 负数的符号“-”的大写：“负”字。可自定义字符，如“（负）”。
	var CN_SYMBOL = ""; // 币种名称（如“人民币”，默认空）
	if (dValue.length > 1) {
		if (dValue.indexOf('-') == 0) {
			dValue = dValue.replace("-", "");
			minus = "负";
		} // 处理负数符号“-”
		if (dValue.indexOf('+') == 0) {
			dValue = dValue.replace("+", "");
		} // 处理前导正数符号“+”（无实际意义）
	}
	// 变量定义：
	var vInt = "";
	var vDec = ""; // 字符串：金额的整数部分、小数部分
	var resAIW; // 字符串：要输出的结果
	var parts; // 数组（整数部分.小数部分），length=1时则仅为整数。
	var digits, radices, bigRadices, decimals; // 数组：数字（0~9——零~玖）；基（十进制记数系统中每个数字位的基是10——拾,佰,仟）；大基（万,亿,兆,京,垓,杼,穰,沟,涧,正）；辅币（元以下，角/分/厘/毫/丝）。
	var zeroCount; // 零计数
	var i, p, d; // 循环因子；前一位数字；当前位数字。
	var quotient, modulus; // 整数部分计算用：商数、模数。
	// 金额数值转换为字符，分割整数部分和小数部分：整数、小数分开来搞（小数部分有可能四舍五入后对整数部分有进位）。
	var NoneDecLen = (typeof (maxDec) == "undefined" || maxDec == null || Number(maxDec) < 0 || Number(maxDec) > 5); // 是否未指定有效小数位（true/false）
	parts = dValue.split('.'); // 数组赋值：（整数部分.小数部分），Array的length=1则仅为整数。
	if (parts.length > 1) {
		vInt = parts[0];
		vDec = parts[1]; // 变量赋值：金额的整数部分、小数部分
		if (NoneDecLen) {
			maxDec = vDec.length > 5 ? 5 : vDec.length;
		} // 未指定有效小数位参数值时，自动取实际小数位长但不超5。
		var rDec = Number("0." + vDec);
		rDec *= Math.pow(10, maxDec);
		rDec = Math.round(Math.abs(rDec));
		rDec /= Math.pow(10, maxDec); // 小数四舍五入
		var aIntDec = rDec.toString().split('.');
		if (Number(aIntDec[0]) == 1) {
			vInt = (Number(vInt) + 1).toString();
		} // 小数部分四舍五入后有可能向整数部分的个位进位（值1）
		if (aIntDec.length > 1) {
			vDec = aIntDec[1];
		} else {
			vDec = "";
		}
	} else {
		vInt = dValue;
		vDec = "";
		if (NoneDecLen) {
			maxDec = 0;
		}
	}
	if (vInt.length > 44) {
		return "金额值太大！";
	}
	// 准备各字符数组 Prepare the characters corresponding to the digits:
	digits = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); // 零~玖
	radices = new Array("", "拾", "佰", "仟"); // 拾,佰,仟
	bigRadices = new Array("", "万", "亿", "兆", "京", "垓", "杼", "穰", "沟", "涧", "正"); // 万,亿,兆,京,垓,杼,穰,沟,涧,正
	decimals = new Array("角", "分", "厘", "毫", "丝"); // 角/分/厘/毫/丝
	resAIW = ""; // 开始处理
	// 处理整数部分（如果有）
	if (Number(vInt) > 0) {
		zeroCount = 0;
		for (i = 0; i < vInt.length; i++) {
			p = vInt.length - i - 1;
			d = vInt.substr(i, 1);
			quotient = p / 4;
			modulus = p % 4;
			if (d == "0") {
				zeroCount++;
			} else {
				if (zeroCount > 0) {
					resAIW += digits[0];
				}
				zeroCount = 0;
				resAIW += digits[Number(d)] + radices[modulus];
			}
			if (modulus == 0 && zeroCount < 4) {
				resAIW += bigRadices[quotient];
			}
		}
		resAIW += "元";
	}
	// 处理小数部分（如果有）
	for (i = 0; i < vDec.length; i++) {
		d = vDec.substr(i, 1);
		if (d != "0") {
			resAIW += digits[Number(d)] + decimals[i];
		}
	}
	// 处理结果
	if (resAIW == "") {
		resAIW = "零" + "元";
	} // 零元
	if (vDec == "") {
		resAIW += "整";
	} // ...元整
	resAIW = CN_SYMBOL + minus + resAIW; // 人民币/负......元角分/整
	return resAIW;
}

function chargeTypeSelect(data){
	$("#shareclass").val(data);
	if(data == "0"){
		$("#qian").addClass("active");
		$("#hou").removeClass("active");
	}else{
		$("#hou").addClass("active");
		$("#qian").removeClass("active");
	}
}
</script>
</html>