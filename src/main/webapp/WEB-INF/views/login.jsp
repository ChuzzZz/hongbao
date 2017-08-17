<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log in</title>
<script src="js/jquery-3.2.1.min.js"></script>
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
	<h1>登陆才能变强</h1>
	<hr>
	<p>${serverTime}</p>
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