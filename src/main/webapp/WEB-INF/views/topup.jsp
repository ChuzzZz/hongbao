<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Top up</title>
</head>
<body>
<h1>
	充值才能变强
</h1>
<hr>
<div style="color;red">${message}</div>
<form action="topup_page">
	<table>
		<tr><td>ITCode：</td><td><input name="itcode"></td></tr>
		<tr><td>姓名：</td><td><input name="name"></td></tr>
		<tr><td>充值金额：</td><td><input name="amount"></td></tr>
		<tr><td><input type="submit" value="变强"></td></tr>
	</table>
</form>
</body>
</html>
