<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>节目信息</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<input class="btn" type="button" value="回退"
		onclick="window.history.go(-1)">
	<input class="btn" type="button" value="个人主页"
		onclick="window.location.href='MyPage'">
	<c:if test="${showlist!=null}">
		<div class="container">
			<h2>节目列表</h2>
			<br>
			<form action="searchbyrule">节目名：<input name="actor"> &nbsp;&nbsp;表演者：<input name="department"> &nbsp;&nbsp;部门：<input name="name">&nbsp;&nbsp; <input type="submit" value="查询"></form>
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
							<th></th>
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
								<td><form action="tip.do">
										<input type="submit" value="打赏"><input class="span2" name="amount"> 元 
										<input type="hidden" name="show_id" value="${showinfo.id}">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
</body>
</html>