<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>确认提现信息</title>

<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
}

.navbar-static-top {
	margin-bottom: 19px;
}
</style>
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
	<nav class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">年会系统</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="myPage">Home</a></li>
					<li><a href="getshowlist">节目</a></li>
					<li><a href="redpackage">红包</a></li>
					<li class="active"><a href="myaccount">钱包</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.do">Log out<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
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
		<div id="payPassword_container" class="alieditContainer clearfix" data-busy="0">
	<label for="i_payPassword" class="i-block">支付密码：</label>
	<div class="i-block" data-error="i_error">
		<div class="i-block six-password">
			<input class="i-text sixDigitPassword" id="payPassword_rsainput" type="password" autocomplete="off" required="required" value="" name="payPassword_rsainput" data-role="sixDigitPassword" tabindex="" maxlength="6" minlength="6" aria-required="true">
			<div tabindex="0" class="sixDigitPassword-box" style="width: 180px;">
				<i style="width: 29px; border-color: transparent;" class=""><b style="visibility: hidden;"></b></i>
				<i style="width: 29px;"><b style="visibility: hidden;"></b></i>
				<i style="width: 29px;"><b style="visibility: hidden;"></b></i>
				<i style="width: 29px;"><b style="visibility: hidden;"></b></i>
				<i style="width: 29px;"><b style="visibility: hidden;"></b></i>
				<i style="width: 29px;"><b style="visibility: hidden;"></b></i>
				<span style="width: 29px; left: 0px; visibility: hidden;" id="cardwrap" data-role="cardwrap"></span>
			</div>
		</div>
		<span>请输入6位支付密码( 数字！)</span>
	</div>
</div>
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