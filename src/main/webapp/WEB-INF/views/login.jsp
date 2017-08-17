<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Log in</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"], .form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$("#itcode").focus(function() {
			$("#w1").html("");
		});
		$("#itcode").blur(function() {
			if ($("#itcode").val().length == 0)
				$("#w1").html("ITCode必填");
		});
		$("#username").focus(function() {
			$("#w2").html("");
		});
		$("#username").blur(function() {
			if ($("#itcode").val().length == 0)
				$("#w2").html("姓名必填");
		});
	});
	function checkInfo() {
		var itcode = document.form.itcode.value;
		var name = document.form.username.value;
		if (name.length != 0 && itcode.length != 0) {
			document.form.submit();
		} else {
			checkItcode();
			checkUsername();
		}
	}
</script>
</head>

<body>
	<div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="input-block-level" placeholder="Email address">
        <input type="password" class="input-block-level" placeholder="Password">
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
      </form>

    </div>
    <h1>登陆才能变强</h1>
	<hr>
	<p>${serverTime}</p >
	<form name="form" action="verify">
		<table>
			<tr>
				<td>ITCode：</td>
				<td><input name="itcode" id="itcode"></td>
				<td><div id="w1" style="color: red"></div></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><input name="username" id="username"></td>
				<td><div id="w2" style="color: red"></div></td>
			</tr>
			<tr>
				<td><input type="button" value="登陆" onclick="checkInfo()"></td>
				<td><div id="w3" style="color: red">${login_result}</div></td>
			</tr>
		</table>
	</form>
</body>

</html>