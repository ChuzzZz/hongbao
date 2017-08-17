<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红包管理</title>
</head>

<body>
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