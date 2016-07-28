<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交易密码验证</title>
</head>
<body>
	<div>
		<!-- 交易密码验证 -->
		<form action="bindBankCard.do?method=doAddCardChooseBankCard" method="post">
			交易密码：<input type="text" id="trade_psd" name="trade_psd"></input></br>
			<input type="submit" value="下一步" id="next_step" name="next_step"></input>
		</form>
	</div>
</body>
</html>