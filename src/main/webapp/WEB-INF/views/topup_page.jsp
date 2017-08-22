<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Top up</title>

<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
	min-height: 2000px;
}

.navbar-static-top {
	margin-bottom: 19px;
}
</style>

<script>
	$(document).ready(function() {
		$("#b1").click(function() {
			var amount = document.form.amount.value;
			if (amount.length == 0 || amount == 0) {
				$("#d1").html("不充钱还想变强？");
			} else {
				var reg = /^\d+(\.\d{1,2})?$/;
				if (!reg.test(amount)) {
					alert("请输入整数或至多保留小数点后两位！");
				} else {
					document.form.submit();
				}
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
	<h1>充值才能变强</h1>
	<hr>
	<form name="form" action="topup.do">
		<table>
			<tr>
				<td>充值金额：</td>
				<td><input name="amount" maxlength=16> 元</td>
			</tr>
			<tr>
				<td><input type="button" id="b1" value="下一步"></td>
				<td><div id="d1" style="color: red"></div></td>
			</tr>
		</table>
	</form>

</body>
</html>
