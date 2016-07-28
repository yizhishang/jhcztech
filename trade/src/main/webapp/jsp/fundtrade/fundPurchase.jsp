<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基金申购</title>
</head>
<body>
	<div><h1>${msg }</h1></div>
	<!-- 基金申购 -->
	<form action="<%=basePath %>fundTrade.do?method=doFundPurchase" method="post" id="fundPurchaseForm">
		<table>
			<tr>
				<td>商户流水号:</td>
				<td><input type="text" name="ecap_serialno" value="${obj.ECAP_SERIALNO }"></input></td>
			</tr>
			<tr>
				<td>网点号:</td>
				<td><input type="text" name="branch_code" value="${obj.BRANCH_CODE }"></input></td>
			</tr>
			<tr>
				<td>交易发起时间:</td>
				<td><input type="text" name="request_time" value="${obj.REQUEST_TIME }"></input></td>
			</tr>
			<tr>
				<td>收费方式:</td>
				<td><input type="text" name="share_class" value="${obj.SHARE_CLASS }"></input></td>
			</tr>
			<tr>
				<td>交易账号:</td>
				<td><input type="text" name="trade_acco" value="${obj.TRADE_ACCO }"></input></td>
			</tr>
			
			<tr>
				<td>资金账号:</td>
				<td><input type="text" name="cash_acco" value="${obj.CASH_ACCO }"></input></td>
			</tr>
			
			<tr>
				<td>资金份额:</td>
				<td><input type="text" name="occut_amount" value="${obj.OCCUT_AMOUNT }"></input></td>
			</tr>
			
			<tr>
				<td>基金代码:</td>
				<td><input type="text" name="fund_code" value="${obj.FUND_CODE }"></input></td>
			</tr>
			
			<tr>
				<td>交易类型:</td>
				<td><input type="text" name="trade_type" value="${obj.TRADE_TYPE }"></input></td>
			</tr>
			
			<tr>
				<td>交易来源:</td>
				<td><input type="text" name="trade_source" value="${obj.TRADE_SOURCE }"></input></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" name="sub_btn" value="申购"></input></td>
			</tr>
		</table>
	</form>
</body>
</html>