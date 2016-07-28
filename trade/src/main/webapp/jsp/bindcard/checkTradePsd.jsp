<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>银行卡换卡--验证交易密码</title>
</head>
<body>
		<form action="bindBankCard.do?method=doCheckTradePwd" method="post">
			交易密码：<input type="text" name="tradePwd" id="tradePwd" ></input></br>
			<input type="submit" id="step_next" value="下一步"></input>
		</form>
</body>
</html>