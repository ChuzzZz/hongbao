<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>节目信息</title>
<script>
function adjustOrder(){
	
}
</script>
</head>
<body>
	<a href="admin">返回主页</a><br>
	<c:if test="${showlist!=null}">
	节目列表：
	<table>
	<tr><td>编号</td><td>节目名</td><td>表演者</td><td>部门</td><td>开始时间</td><td></td></tr>
	<c:forEach items="${showlist}" var="showlist">
		<tr><td>${showlist.s_order}</td><td>${showlist.show_name}</td><td>${showlist.performer}</td><td>${showlist.department}</td><td>${showlist.start_time}</td></tr>
	</c:forEach>
	</table>
	</c:if>
	<form name="form">
	调整节目顺序：
	<input name="before">To<input name="after"><input type="button" value="确认" onclick="adjustOrder()">
	</form>
</body>
</html>