<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Withdraw</title>
<script type="text/javascript">
function checkAmount(){
	var amount = document.form.amount.value;
	if(amount.length == 0){
		document.getElementById("d1").innerHTML = "金额不能为0！";
	}else{
		document.form.submit();
	}
}
</script>
</head>
<body>
	<form  name="form" action="withdraw.do">
		<table>
			<tr>
				<td>提现金额：</td>
				<td><input name="amount"> 元</td>
			</tr>
			<tr>
				<td><input type="button" value="下一步" onclick="checkAmount()"></td>
				<td><div id="d1" style="color:red"></div></td>
			</tr>
		</table>
	</form>
</body>
</html>