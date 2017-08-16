<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>节目信息</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="/!/assets/libs.min.js?20170814"></script><script type="text/javascript" src="/!/assets/js.min.js?20170814"></script><link rel="stylesheet" type="text/css" href="/!/assets/css.min.css?20170814">
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
	<!--  <a href="admin">返回主页</a><br><br>-->
	
	
	<input class="btn" type="button" value="回退"
		onclick="window.history.go(-1)">
	<input class="btn" type="button" value="管理界面"
	onclick="window.location.href='admin'">
	<c:if test="${showlist!=null}">
		<div class="container">
			<h2>节目列表</h2>
			<br>
			<form action="searchbyrule">节目名：<input name="name"> &nbsp;&nbsp;表演者：<input name="actor"> &nbsp;&nbsp;部门：<input name="department">&nbsp;&nbsp; <input type="submit" value="查询"></form>
			<br>
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
				<th>&nbsp;&nbsp;&nbsp;&nbsp;</th>
				<th>
					<form action="export">
						<input type="submit" value="导出节目单" >
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
				<input id = "before" name="before">And<input id = "after" name="after"><input type="submit" value="确认" onclick="adjustOrder()">
	</form>
			</div>
		</div>
	</c:if>
</html>