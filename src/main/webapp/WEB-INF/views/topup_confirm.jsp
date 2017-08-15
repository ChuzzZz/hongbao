<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认充值信息</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		$("#b1").click(function(){
			paycode = $("#paycode").val();
			
		});
	});
</script>
</head>
<body>
	请确认充值信息
	<hr>
	充值账户：
	<div style="color: red">${account_id}</div>
	充值金额（元）：
	<div style="color: red">${amount}</div>
	<hr>
	<form name="form1" action=""></form>
	<form action="topupresult">
		<input type="hidden" name="account_id" value="${account_id}">
		<input type="hidden" name="amount" value="${amount}"> 
		支付密码：<input type="password" name="paycode" id="paycode"> 
		<input type="button" name="b1" id="b1" value="充值"><br>
		<div style="color: red">${erro}</div>
	</form>
	<input type="button" value="上一步" onclick="window.history.go(-1)">
</body>
</html>