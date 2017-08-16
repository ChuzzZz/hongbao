<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认提现信息</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		$("#b1").click(function() {
			paycode = $("#paycode").val();
			account_id = $("#account_id").val();
			if (paycode.length == 0) {
				$("#d1").html("支付密码不能为空！");
			} else {
				$.post("verifypaycode", {
					paycode : paycode,
					account_id : account_id
				}, function(data) {
					if (data == "支付密码正确") {
						$("#form").submit();
					} else {
						$("#d1").html("支付密码错误！");
					}
				})
			}
		});
	});
</script>
</head>
<body>
	请确认提现信息
	<hr>
	提现账户：
	<div style="color: red">${account_id}</div>
	提现金额（元）：
	<div style="color: red">${amount}</div>
	<hr>
	<form id="form" action="withdrawresult" method="post">
		<input type="hidden" name="account_id" id="account_id" value="${account_id}">
		<input type="hidden" name="amount" value="${amount}"> 
		支付密码：<input type="password" name="paycode" id="paycode" maxlength=16> 
		<input type="button" id="b1" name="b1" value="提现"><br>
	</form>
	<table>
		<tr>
			<td><input type="button" value="上一步"
				onclick="window.history.go(-1)"></td>
			<td><div style="color:red" id="d1"></div></td>
		</tr>
	</table>
</body>
</html>