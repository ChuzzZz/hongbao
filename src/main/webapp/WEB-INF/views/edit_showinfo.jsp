<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加节目</title>
<script>
function checkShowName(){
	var name = document.form.show_name.value;
	if(document.activeElement.id == "1"){
		document.getElementById("w1").innerHTML="";
		return true;
	}else{
		if(name.length == 0){
			document.getElementById("w1").innerHTML="节目名称不能为空！";
			return false;
		}else{
			document.getElementById("w1").innerHTML="";
			return true;
		}
	}
}
function checkPerformer(){
	var performer = document.form.performer.value;
	if(document.activeElement.id == "2"){
		document.getElementById("w2").innerHTML="";
		return true;
	}else{
		if(performer.length == 0){
			document.getElementById("w2").innerHTML="表演者不能为空！";
			return false;
		}else{
			document.getElementById("w2").innerHTML="";
			return true;
		}
	}
}
function checkOrder(){
	var order = document.form.order.value;
	if(document.activeElement.id == "4"){
		document.getElementById("w4").innerHTML="";
		return true;
	}else{
		if(order.length == 0){
			document.getElementById("w4").innerHTML="表演顺序不能为空！";
			return false;
		}else{
			document.getElementById("w4").innerHTML="";
			return true;
		}
	}
}
function checkStartTime(){
	var time = document.form.start_time.value;
	var patern = new RegExp(/^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\s+(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d$/);
	var patern2 = new RegExp(/^2017-08-01\s+(19|20|21|22):[0-5]\d:[0-5]\d$/);
	if(document.activeElement.id == "3"){
		document.getElementById("w3").innerHTML = "";
		return true;
	}else{
		if(time.length== 0){
			document.getElementById("w3").innerHTML = "时间不能为空！"
		}else{
			if(patern.exec(time)){
				if(patern2.exec(time)){
					document.getElementById("w3").innerHTML="";
					return true;
				}else{
					document.getElementById("w3").innerHTML="不在表演时间范围内！";
					return false;
				}
			}else{
				document.getElementById("w3").innerHTML="时间格式不正确！";
				return false;
			}
		}
	}
}
function check(){
	var a = checkShowName();
	var b = checkPerformer();
	var c = checkStartTime();
	var d = checkOrder();
	if(a&&b&&c&&d){
		document.form.submit();
	}
}
</script>
</head>
<body>
<form name="form"  method="get" action="addShowInfo">
		<table cellpadding="2">
			<tr>
				<td>节目名称：</td>
				<td><input name="show_name" id="1" onfocus="checkShowName()" onblur="checkShowName()"></td>
				<td><div style="color:#FF0000" id="w1"></div></td>
			</tr>
			<tr>
				<td>表演顺序:</td>
				<td><input name="order" id="4" onfocus="checkOrder()" onblur="checkOrder()"></td>
				<td><div style="color:#FF0000" id="w4"></div></td>
			</tr>
			<tr>
				<td>表演者:</td>
				<td><input name="performer" id="2" onfocus="checkPerformer()" onblur="checkPerformer()"></td>
				<td><div style="color:#FF0000" id="w2"></div></td>
			</tr>
			<tr>
				<td>开始时间：</td>
				<td><input name="start_time" id="3" onfocus="checkStartTime()" onblur="checkStartTime()"></td>
				<td><div style="color:#FF0000" id="w3"></div></td>
			</tr>
			<tr>
				<td>报送单位：</td>
				<td>
					<select name="department">
						<option value="Soft">软件学院</option>
						<option value="Teda">泰达学院</option>
						<option value="Study">电竞学院</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><input type="button" value="添加" onclick="check()" ></td>
				<td><div style="color:red">${result}</div></td>
			</tr>
		</table>
	</form>
</body>
</html>