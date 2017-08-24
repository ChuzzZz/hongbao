<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>抢红包记录</title>

<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
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
				<a class="navbar-brand" href="#">年会系统</a>
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
	
		<c:if test="${redpackagetransaction!=null}">
		<div class="container">
			<h2>红包领取结果</h2>

			<a href="GetAllRedPackageTransaction">点此刷新红包领取情况</a><br>
			<br>
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>用户ID</th>
							<th>红包轮次</th>
							<th>金额（元）</th>
							<th>获得时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${redpackagetransaction}" var="result" varStatus="order">
							<tr>
								<td>${result.account_id}</td>
								<td>${result.round}</td>
								<td>${result.amount/100}</td>
								<td>${result.time}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
	</c:if>
	
</body>
</html>