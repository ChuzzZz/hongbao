<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红包结果</title>
</head>
<body>
	<a href="admin">返回主页</a><br>
	<c:if test="${result!=null}">
	红包雨已开启.以下为红包结果：
	<table>
	<tr><td>用户名</td><td>金额（元）</td><td>获得时间</td></tr>
	<c:forEach items="${result}" var="result">
		<tr><td>${result.account_id}</td><td>${result.amount}</td><td>${result.time}</td></tr>
	</c:forEach>
	</table>
	</c:if>
	<c:if test="${rainoff!=null}">红包雨已停止</c:if>
	<a href="showluckyrainresult">红包雨可能尚未结束，点此刷新</a>
</body>
</html>