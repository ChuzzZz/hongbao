<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员首页</title>
<script>
	function initAJAX() {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlhttp;
	}
	function startRain() {
		var httprequest = initAJAX();
		var i = document.activeElement.id;
		var url = encodeURI("startluckyrain?round=" + i);
		if (confirm("确定开启红包雨吗？")) {
			httprequest.open("get", url, true);
			httprequest.send();
			document.getElementById(i).disabled = "disabled";
		}
	}
</script>
</head>
<body>
	<form name="form" action="">
		<input type="button" id="1" value="开启第一轮红包雨" onclick="startRain()"><br>
		<input type="button" id="2" value="开启第二轮红包雨" onclick="startRain()"><br>
		<input type="button" id="3" value="开启第三轮红包雨" onclick="startRain()"><br>
	</form>
	<a href="showluckyrainresult">查看红包雨记录</a>
	<hr>
	<input type="button" value="添加节目" onclick="window.location.href='addshow'"><br>
	<a href="admingetshowlist">查看节目单</a>
	<hr>
	<input type="button" value="抢红包开启">
</body>
</html>