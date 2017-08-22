<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>节目信息</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
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
function adjustOrder(){
	var beforeget = document.getElementById("before").value;
	var before = document.getElementById(beforeget).innerHTML;
	document.getElementById("before").value = before;
	var afterget = document.getElementById("after").value;
	var after = document.getElementById(afterget).innerHTML;
	document.getElementById("after").value = after;
	return true;
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
				<a class="navbar-brand" href="#">年会系统</a>
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
	
	<c:if test="${showlist!=null}">
		<div class="container">
			<h2>节目列表</h2>
			<table>
			<tr>
				<th>
					<form action="ima" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<th><input type="file" name="uploadFile" value="选择节目单" accept="application/vnd.ms-excel"></th>
							<th><input type="submit"  name="upload" value="上传节目单" ></th>
						</tr>
					</table>
					</form>
				</th>
				<th>&nbsp;</th>
				<th>
					<form action="export">
						<input type="submit" value="导出节目单" >
					</form>
				</th>
			</tr>
			</table>
			<br>
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>节目名</th>
							<th>表演者</th>
							<th>部门</th>
							<th>开始时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${showlist}" var="showinfo" varStatus="order">
							<tr>
								<td>${order.index + 1}</td>
								<td>${showinfo.show_name}</td>
								<td>${showinfo.performer}</td>
								<td>${showinfo.department}</td>
								<td>${showinfo.start_time}</td>
								<td id="${order.index+1}" style="display:none;">${showinfo.id}</td>
							</tr>
							
						</c:forEach>
					</tbody>
				</table>
				<form name="form" action="adjustOrder">
				交换节目顺序：
				<input id="before" name="before">And<input id="after" name="after">
				<input type="submit" value="确认" onclick="adjustOrder()">
				</form>
			</div>
		</div>
	</c:if>
</html>