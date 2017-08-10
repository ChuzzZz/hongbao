<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员首页</title>
</head>
<body>
	<form name="form" action="">
		<input type="button" id="1" value="开启第一轮红包雨" onclick="startRain()"><br>
		<input type="button" id="2" value="开启第二轮红包雨" onclick="startRain()"><br>
		<input type="button" id="3" value="开启第三轮红包雨" onclick="startRain()"><br>
	</form>
	<a href="showresult">查看红包记录</a>
	<hr>
	<a href="addShow">开始你的表演</a>
</body>
</html>