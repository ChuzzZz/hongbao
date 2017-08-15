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
	<a href="admin">返回主页</a><br><br>
	
	<form action="ima" method="post" enctype="multipart/form-data">
	<input type="file" name="uploadFile" value="选择节目单" accept="application/vnd.ms-excel">
	<input type="submit"  name="upload" value="上传节目单" >
	</form>
	<br>
	<form action="export"><input type="submit" value="导出节目单" ></form>
	<br>
	<c:if test="${showlist!=null}">
	节目列表：
	<table>
	<tr><td>顺序</td><td>节目名</td><td>表演者</td><td>部门</td><td>开始时间</td><td></td></tr>
	<c:forEach items="${showlist}" var="showlist" varStatus="status">
		<tr><td>${status.index+1}</td><td>${showlist.show_name}</td><td>${showlist.performer}</td><td>${showlist.department}</td><td>${showlist.start_time}</td><td id="${status.index+1}" style="display:none;">${showlist.id}</td></tr>
	</c:forEach>
	</table>
	</c:if>
	<form name="form" action="adjustOrder">
	交换节目顺序：
	<input id = "before" name="before">And<input id = "after" name="after"><input type="submit" value="确认" onclick="adjustOrder()">
	</form>
</body>
</html>