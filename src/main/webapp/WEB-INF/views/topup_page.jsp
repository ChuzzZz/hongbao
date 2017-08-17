<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top up</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="application/javascript">
$(document).ready(function(){
	$("#b1").click(function(){
		var amount = document.form.amount.value;
	 	if(amount.length == 0){
	 		document.getElementById("d1").innerHTML = "金额不能为空！";
	 	}else{
	 		if(amount == 0){
	 			$("#d1").html("不充钱还想变强？");
	 		}else{
	 			document.form.submit();
	 		}
	 	}
	});
});


</script>
</head>
<body>
	<h1>充值才能变强</h1>
	<hr>
	<form name="form" action="topup.do">
		<table>
			<tr>
				<td>充值金额：</td>
				<td><input name="amount" maxlength=16> 元</td>
			</tr>
			<tr>
				<td><input type="button" id="b1" value="下一步"></td>
				<td><div id="d1" style="color: red"></div></td>
			</tr>
		</table>
	</form>
</body>
</html>
