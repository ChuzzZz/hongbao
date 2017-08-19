<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Withdraw</title>

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
<script type="application/javascript">
$(document).ready(function(){
	$("#b1").click(function() {
		amount = $("#amount").val();
		if (amount.length == 0 || amount == 0) {
			$("#d1").html("提现金额不能为空");
		} else {
			$.get("checkbalance?amount=" + amount, function(data) {
				if (data.msg == "yes") {
					$("#form").submit();
				} else {
					$("#d1").html("余额不足");
				}
			},"json");
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
				<a class="navbar-brand" href="#">Project name</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="myPage">Home</a></li>
					<li><a href="getshowlist">节目</a></li>
					<li><a href="redpackage">红包</a></li>
					<li class="active"><a href="myaccount">钱包</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">钱包<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a>充值</a></li>
							<li><a>提现</a></li>
							<li role="separator" class="divider"></li>
							<li><a>交易记录</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.do">Log out<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<form id="form" action="withdraw.do">
		<table>
			<tr>
				<td>提现金额：</td>
				<td><input name="amount" id="amount" maxlength=16> 元</td>
			</tr>
			<tr>
				<td><input type="button" id="b1" value="下一步"></td>
				<td><div id="d1" style="color: red"></div></td>
			</tr>
		</table>
	</form> 
</body>
</html>