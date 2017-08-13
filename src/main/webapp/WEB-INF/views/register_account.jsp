<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册钱包账户</title>
<script type="text/javascript">
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
<div style="color:red">${message}</div>
<form action="register.do">
	<table>
		<tr><td>ITCode：</td><td><input name="itcode"></td></tr>
		<tr><td>密码：</td><td><input name="password"></td></tr>
		<tr><td>支付密码：</td><td><input name="paycode"></td></tr>
		<tr><td><input type="submit" value="注册"></td></tr>
	</table>
</form>
</body>
</html>