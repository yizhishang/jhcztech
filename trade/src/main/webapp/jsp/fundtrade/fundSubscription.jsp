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
<title>基金认购</title>
</head>
<body>
	<div><h1>${msg }</h1></div>
	<!-- 基金认购 -->
	<form action="<%=basePath %>fundTrade.do?method=doFundSubscription" method="post" id="fundSubscriptionForm">
	<input type="hidden" name="json" value='{"TRADE_SOURCE":"1","ECAP_SERIALNO":"50110020393483900","g_confirmlevel":"0","SHARE_CLASS":"0","FUND_CODE":"400302","g_userpwd":"1","REQUEST_TIME":"20160319160533","g_chkuserid":"8888","g_userway":"0","g_confirmaction":"0","g_sysid":"0","OCCUT_AMOUNT":"100","g_funcid":"200008","g_userid":"8888","BRANCH_CODE":"002001","g_checksno":"0","CASH_ACCO":"40002302043","TRADE_TYPE":"020","g_sessionid":"1000010001","g_menuid":"1","g_stationaddr":"127.0.0.1","TRADE_ACCO":"1011"}'/>
	<input type="hidden" name="serviceName" value="1002400000"/>
		<table>
			<tr>
				<td>商户流水号:</td>
				<td><input type="text" name="ecap_serialno"></input></td>
			</tr>
			<tr>
				<td>网点号:</td>
				<td><input type="text" name="branch_code"></input></td>
			</tr>
			<tr>
				<td>交易发起时间:</td>
				<td><input type="text" name="request_time"></input></td>
			</tr>
			<tr>
				<td>收费方式:</td>
				<td><input type="text" name="share_class"></input></td>
			</tr>
			<tr>
				<td>交易账号:</td>
				<td><input type="text" name="trade_acco"></input></td>
			</tr>
			
			<tr>
				<td>资金账号:</td>
				<td><input type="text" name="cash_acco"></input></td>
			</tr>
			
			<tr>
				<td>资金份额:</td>
				<td><input type="text" name="occut_amount"></input></td>
			</tr>
			
			<tr>
				<td>基金代码:</td>
				<td><input type="text" name="fund_code"></input></td>
			</tr>
			
			<tr>
				<td>交易类型:</td>
				<td><input type="text" name="trade_type"></input></td>
			</tr>
			
			<tr>
				<td>交易来源:</td>
				<td><input type="text" name="trade_source"></input></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" name="sub_btn" value="认购"></input></td>
			</tr>
		</table>
	</form>
</body>
</html>