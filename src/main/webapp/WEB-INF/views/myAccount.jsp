<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的钱包</title>
</head>
<body>
	<input type="button" value="返回" onclick="window.location.href='home'">
	<hr>
	<p>
		ID：<span>${account_id}</span>
	</p>
	<p>
		余额：<span>${balance}</span> 元
	</p>
	<a href="topup">充值</a>
	<a href="withdraw">提现</a>
	<a href="getaccounttransaactions">查看交易记录</a>
</body>
</html>