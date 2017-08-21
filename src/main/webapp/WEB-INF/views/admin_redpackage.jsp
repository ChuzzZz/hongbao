<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>抢红包管理</title>

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
					<li><a href="admin">主页</a></li>
					<li><a href="adminLuckyrain">红包雨</a></li>
					<li class="active"><a href="adminredpackage">抢红包</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">后台管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="addshow">添加节目</a></li>
							<li><a href="adminGetShowlist">节目单管理</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="userlist">员工管理</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.do">Log out<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	
	<h1>红包管理</h1>
	<hr>
	<table>
	<tr>
	<c:if test="${round1==0}"><th><input type="button" value="开启第一轮抢红包" onclick="window.location.href='ActivateRedPackage?round=1'"></th></c:if>
	<c:if test="${round1==1}"><th><input type="button" value="停止第一轮抢红包" onclick="window.location.href='StopRedPackage?round=1'"></th></c:if>
	<c:if test="${round2==0}"><th><input type="button" value="开启第二轮抢红包" onclick="window.location.href='ActivateRedPackage?round=2'"></th></c:if>
	<c:if test="${round2==1}"><th><input type="button" value="停止第二轮抢红包" onclick="window.location.href='StopRedPackage?round=2'"></th></c:if>
	<c:if test="${round3==0}"><th><input type="button" value="开启第三轮抢红包" onclick="window.location.href='ActivateRedPackage?round=3'"></th></c:if>
	<c:if test="${round3==1}"><th><input type="button" value="停止第三轮抢红包" onclick="window.location.href='StopRedPackage?round=3'"></th></c:if>
	</tr>

	</table>
	<input type="button" value="查看抢红包记录" onclick="window.location.href='GetAllRedPackageTransaction'">
</body>
</html>