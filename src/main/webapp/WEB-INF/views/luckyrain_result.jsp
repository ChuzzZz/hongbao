<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红包结果</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<input class="btn" type="button" value="回退"
		onclick="window.history.go(-1)">
	<input class="btn" type="button" value="管理界面"
		onclick="window.location.href='admin'">
		<c:if test="${result!=null}">
		<div class="container">
			<h2>红包结果</h2>

			<a href="showluckyrainresult">红包雨可能尚未结束，点此刷新</a><br>
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
						<c:forEach items="${result}" var="result" varStatus="order">
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
		<c:if test="${rainoff!=null}">红包雨已停止</c:if>
		
	</c:if>
	
</body>
</html>