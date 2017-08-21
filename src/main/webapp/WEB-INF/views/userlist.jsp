<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>员工列表</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="/!/assets/libs.min.js?20170814"></script>
<script type="text/javascript" src="/!/assets/js.min.js?20170814"></script>
<link rel="stylesheet" type="text/css" href="/!/assets/css.min.css?20170814">
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
					<li><a href="adminredpackage">抢红包</a></li>
					<li class="active dropdown"><a class="dropdown-toggle"
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
	
	<c:if test="${userlist!=null}">
		<div class="container">
			<h2>员工列表</h2>
			<br>
			<table>
			<tr>
				<th>
					<form action="imu" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<th><input type="file" name="uploadFile" value="选择Excel文档" accept="application/vnd.ms-excel"></th>
							<th><input type="submit"  name="upload" value="上传员工列表" ></th>
						</tr>
					</table>
					</form>
				</th>
				<th>&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th>
					<form action="exportuserlist">
						<input type="submit" value="导出员工列表" >
					</form>
				</th>
			</tr>
			</table>
	<br>
	
	<br>
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>itcode</th>
							<th>姓名</th>
							<th>类别</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userlist}" var="userinfo" varStatus="order">
							<tr>
								<td>${order.index + 1}</td>
								<td>${userinfo.itcode}</td>
								<td>${userinfo.name}</td>
								<td>${userinfo.onsite}</td>
							</tr>
							
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
</html>