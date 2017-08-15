<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Withdraw</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="application/javascript">
$(document).ready(function(){
	$("#b1").click(function() {
		amount = $("#amount").val();
		if (amount.length == 0 || amount == 0) {
			$("#d1").html("提现金额不能为空");
		} else {
			$.get("checkbalance?amount=" + amount, function(data) {
				if (data.msg == "yes") {
					$("#form").submit();
				} else {
					$("#d1").html("余额不足");
				}
			},"json");
		}
	});
});
</script>
</head>
<body>
	<form id="form" action="withdraw.do">
		<table>
			<tr>
				<td>提现金额：</td>
				<td><input name="amount" id="amount"> 元</td>
			</tr>
			<tr>
				<td><input type="button" id="b1" value="下一步"></td>
				<td><div id="d1" style="color: red"></div></td>
			</tr>
		</table>
	</form> 
</body>
</html>