<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>注册钱包账户</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
	min-height: 2000px;
}

.navbar-static-top {
	margin-bottom: 19px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#b1").click(function() {
			var paycode = document.getElementById("paycode");
			var b1 = document.getElementById("b1");
			if (paycode.type == "password") {
				paycode.type = "text";
				b1.value = "隐藏";
			} else {
				paycode.type = "password";
				b1.value = "显示";
			}
		});

		$("#myform").validate({
			debug : true, //调试模式取消submit的默认提交功能   
			focusInvalid : false, //当为false时，验证无效时，没有焦点响应  
			//onkeyup : false,
			//onfocusout: false,
			submitHandler : function(form) {
				form.submit();
			},

			rules : {
				itcode : {
					required : true
				},
				paycode : {
					required : true,
					rangelength : [ 6, 16 ]
				},
				confirm_paycode : {
					required : true,
					equalTo : "#paycode"
				}
			},
			messages : {
				itcode : {
					required : "ITcode必填"
				},
				paycode : {
					required : "支付密码不能为空",
					rangelength : "密码长度应该为6-16位"
				},
				confirm_paycode : {
					required : "确认密码不能为空",
					equalTo : "两次密码输入不一致"
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
				<a class="navbar-brand" href="#">Project name</a>
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
	<div style="color: red">${message}</div>
	<form id="myform" action="register.do">
		<table>
			<tr>
				<td>ITCode：</td>
				<td><input name="itcode"></td>
			</tr>
			<tr>
				<td>设置支付密码：</td>
				<td><input type="button" id="b1" value="显示"><input
					type="password" id="paycode" name="paycode"></td>
			</tr>
			<tr>
				<td>确认支付密码：</td>
				<td><input type="password" id="confirm_paycode"
					name="confirm_paycode"></td>
			</tr>
			<tr>
				<td><input type="submit" value="注册"></td>
			</tr>
		</table>
	</form>
</body>
</html>