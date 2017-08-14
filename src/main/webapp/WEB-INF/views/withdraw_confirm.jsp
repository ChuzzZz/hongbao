<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认提现信息</title>
</head>
<body>
	请确认提现信息
	<hr>
	提现账户：
	<div style="color: red">${account_id}</div>
	提现金额：
	<div style="color: red">${amount}</div>
	<hr>
	<form name="form1" action=""></form>
	<form action="withdrawresult">
		<input type="hidden" name="account_id" value="${account_id}">
		<input type="hidden" name="amount" value="${amount}"> 支付密码：<input
			type="password" name="paycode"> <input type="submit"
			value="提现"><br>
	</form>
	<table>
		<tr>
			<td><input type="button" value="上一步"
				onclick="window.location.href='withdraw'"></td>
			<td><div style="color: red">${erro}</div></td>
		</tr>
	</table>
</body>
</html>