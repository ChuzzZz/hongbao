<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log in</title>
<script>
function checkItcode(){
	var itcode = document.form.itcode.value;
	if(document.activeElement.id == "1"){
		document.getElementById("w1").innerHTML="";
	}else{
		if(itcode.length == 0){
			document.getElementById("w1").innerHTML="ITCode不能为空！";
		}else{
			document.getElementById("w1").innerHTML="";
		}		
	}
}
function checkUsername(){
	var name = document.form.username.value;
	if(document.activeElement.id == "2"){
		document.getElementById("w2").innerHTML="";
	}else{
		if(name.length == 0){
			document.getElementById("w2").innerHTML="姓名不能为空！";
		}else{
			document.getElementById("w2").innerHTML="";
		}		
	}
}
function checkInfo(){
	var itcode = document.form.itcode.value;
	var name = document.form.username.value;
	if(name.length!=0 && itcode.length!=0){
		document.form.submit();
	}else{
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
<form name="form" action="login_verify">
	<table>
		<tr>
			<td>ITCode：</td><td><input name="itcode" id="1" onblur="checkItcode()" onfocus="checkItcode()"></td>
			<td><div id="w1" style="color:red"></div></td>
		</tr>
		<tr>
			<td>姓名：</td><td><input name="username" id="2" onblur="checkUsername()" onfocus="checkUsername()"></td>
			<td><div id="w2" style="color:red"></div></td>
		</tr>
		<tr>
			<td><input type="button" value="登陆" onclick="checkInfo()"></td>
			<td><div id="w3" style="color:red">${login_result}</div></td>
		</tr>
	</table>
</form>
</body>

</html>