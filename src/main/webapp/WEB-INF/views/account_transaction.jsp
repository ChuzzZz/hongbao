<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>交易信息</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<input class="btn" type="button" value="回退"
		onclick="window.history.go(-1)">
	<input class="btn" type="button" value="个人主页"
		onclick="window.location.href='MyPage'">
	<c:if test="${AccountTransaction!=null}">
		<div class="container">
			<h2>交易信息</h2>
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th><label>序号</label></th>
							<th><a href="getaccounttransactions?ordertype=amount">金额</a></th>
							<th><a href="getaccounttransactions?ordertype=time">时间</a></th>
							<th><a href="getaccounttransactions?ordertype=type">交易类型</a></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${AccountTransaction}" var="AccountTransaction" varStatus="order">
							<tr>
								<td>${order.index + 1}</td>
								<td>${AccountTransaction.amount}</td>
								<td>${AccountTransaction.time}</td>
								<td>${AccountTransaction.type}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:if>
</body>
</html>