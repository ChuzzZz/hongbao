<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>我的钱包</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#chongzhi").click(function() {
			$("#myContainer").load("topup");
		});
		$("#tixian").click(function() {
			$("#myContainer").load("withdraw");
		});
		$("#jilu").click(function() {
			$("#myContainer").load("getaccounttransactions");
		});
	});
</script>
</head>
<body>

	<div class="container" id="myContainer">

		<!-- Main component for a primary marketing message or call to action -->
		<p>
			ID：<span>${account_id}</span>
		</p>
		<p>
			余额：<span>${balance}</span> 元
		</p>
		<a href="#" id="chongzhi">充值</a>
		<a href="#" id="tixian">提现</a>
		<a href="#" id="jilu">查看交易记录</a>

	</div>
	<!-- /container -->

</body>
</html>
