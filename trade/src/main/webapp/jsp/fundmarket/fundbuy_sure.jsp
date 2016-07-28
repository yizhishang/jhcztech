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
    		<form id="fundApplyForm" action="${basePath}fund/doFundBuySubmit" method="post" autocomplete="off" spellcheck="false">
    		<table>
    		<tr>
    		<td><p class="name">购买基金</p></td>
            <td>${data.fundName}（${data.fundCode}）
                <input type="hidden" id="fundname" name="fundname" value="${data.fundName}"/>
				<input type="hidden" id="fundcode" name="fundcode" value="${data.fundCode}"/>
				<input type="hidden" id="buytype" name="buytype" value="${data.buytype}"/>
				<input type="hidden" id="business_code" name="business_code" value="${data.businessCode}"/>
				<input type="hidden" id="business_code1" name="business_code1" value="${data.businessCode1}"/>
			</td>
			</tr>
			<tr>
            	<td><p class="name">购买金额</p></td>
            	<td>
               	<div class="toll_way clearfix">
               		<span class="toll_select">${data.moneyFormat }</span>
                </div>
                <input type="hidden" id="applyMoney" name="applyMoney" value="${data.applyMoney}"/>
                </td>
            </tr>
			<tr>
            	<td><p class="name">金额大写</p></td>
            	<td>
               	<div class="toll_way clearfix">
               		<span class="toll_select">${data.moneyStr }</span>
                </div>
                </td>
            </tr>
            <tr>
            	<td><p class="name">支付来源</p></td>
	            <td>
	            </td>
            </tr>
            <input type="hidden" id="paysourse" name="paysourse" value="0" />
            <input type="hidden" id="cashaccountid" name="cashaccountid" value="" />
            <input type="hidden" id="capital_mode" name="capital_mode" value="X" />
            <input type="hidden" id="pay_mode" name="pay_mode" value="" />
            <input type="hidden" id="bankno" name="bankno" value="" />
            <input type="hidden" id="bankstate" name="bankstate" value="" />
            <input type="hidden" id="is_pay" name="is_pay" value="" />
            <tr>
            <td colspan="2"><p class="sum">可用余额：0.00元 </p></td>
                <input type="hidden" id="cash" name="cash" value="" />
                <input type="hidden" id="max_amount" name="max_amount" value="" />
            </tr>
            <tr>
             <div class="peanut_count clearfix mt30">
            	<td><p class="name">交易密码</p></td>
            	<td>
                <input class="w348" type="password" name="password" maxLength="8" id="password" autocomplete="off" placeholder="请输入交易密码" />
          		<input type="hidden" name="token" value="" />
                <span id="passwordTips"></span>
                </td>
            </div>
            </tr>
            <tr>
            <td colspan="2">
            <p class="p_forget mt20">
            	<a href="${basePath}servlet/account/safe/TradePwdAction?function=forget" title="忘记交易密码">忘记交易密码？</a>
            </p>
            </td>
            </tr>
            <input type="hidden" id="chargetype" name="chargetype" value="${fundInfo.chargeType}"/>
            <tr><td colspan="2">
            <a href="javascript:void(0);" class="bank_next png" id="sure" title="确定">确 定</a></td>
            </tr>
            </table>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
var flag = true;
var $password;
$(document).ready(function(){
	$password = $("input[name='password']").valid({
		focusClass:"input_move",
		onClass:"input_on348",
		errorClass:"input_error348",
		focusMsg:"<p class=\"on_tipsingle\">请输入交易密码</p>",
		successMsg:"",
		//isPassword:true,
		errorMsg:"<p class=\"warn348 png\">交易密码有误</p>"
	});
	
	$(document).keydown(function(evt){
		if(evt.keyCode == 13)
		{
			return false;
		}
	});

	$("a#sure").click(function(){
		if(!$password.validate()){
			return false;
		}
		if(flag)
		{
			var $this = $(this);
			flag = false;
			$this.text("提交中");
			$("#fundApplyForm").submit();
			/* $password.checkPassword(function(){
				$("#fundApplyForm").submit();
			},function(){
				$("a#sure").text("<#if data?? && data.paysourse?? && data.paysourse == '0' >确 定<#else><#if data?? && data.bank?? && data.bank.pay_mode?? && data.bank.pay_mode == '2' >确 定<#else>去银行支付</#if></#if>");
				flag = true; 
			}); */
		}
	})
});
</script>
</html>