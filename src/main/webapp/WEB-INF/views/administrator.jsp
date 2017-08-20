<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>管理员首页</title>

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
	function initAJAX() {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlhttp;
	}
	function startRain(i) {
		var httprequest = initAJAX();
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
					<li><a href="myPage">主页</a></li>
					<li><a href="getshowlist">红包雨</a></li>
					<li><a href="redpackage">抢红包</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">节目管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="addshow">添加节目</a></li>
							<li><a href="">导入员工信息</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="adminGetShowlist">查看节目单</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.do">Log out<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<form name="form" action="">
	<c:if test="${round1!=null}">
		<input type="button" id="1" value="开启第一轮红包雨" onclick="startRain(1)"><br>
	</c:if>
	<c:if test="${round1==null}">
		<input type="button" id="1" value="开启第一轮红包雨" onclick="startRain(1)" disabled="disabled"><br>
	</c:if>
	<c:if test="${round2!=null}">
		<input type="button" id="2" value="开启第二轮红包雨" onclick="startRain(2)"><br>
	</c:if>
	<c:if test="${round2==null}">
		<input type="button" id="2" value="开启第二轮红包雨" onclick="startRain(2)" disabled="disabled"><br>
	</c:if>
	<c:if test="${round3!=null}">
		<input type="button" id="3" value="开启第三轮红包雨" onclick="startRain(3)"><br>
	</c:if>
	<c:if test="${round3==null}">
		<input type="button" id="3" value="开启第三轮红包雨" onclick="startRain(3)" disabled="disabled"><br>
	</c:if>
	</form>
	<a href="showluckyrainresult">查看红包雨记录</a>
	<hr>
	<input type="button" value="添加节目" onclick="window.location.href='addshow'"><br>
	<a href="adminGetShowlist">查看节目单</a>
	
	
	<hr>
	<input type="button" value="抢红包管理" onclick="window.location.href='adminredpackage'">
</body>
</html>