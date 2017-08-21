<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>编辑节目信息</title>

<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
	min-height: 2000px;
}

.navbar-static-top {
	margin-bottom: 19px;
}
</style>
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
	if(a&&b&&c){
		document.form.submit();
	}
}
</script>
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Project name</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="admin">主页</a></li>
					<li><a href="adminLuckyrain">红包雨</a></li>
					<li><a href="adminredpackage">抢红包</a></li>
					<li class="active dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">后台管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="addshow">添加节目</a></li>
							<li><a href="adminGetShowlist">节目单管理</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="userlist">员工管理</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.do">Log out<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	
<form name="form"  method="get" action="addshowinfo">
		<table cellpadding="2">
			<tr>
				<td>节目名称：</td>
				<td><input name="show_name" id="1" onfocus="checkShowName()" onblur="checkShowName()"></td>
				<td><div style="color:#FF0000" id="w1"></div></td>
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
				<td><input type="button" value="添加" onclick="check()"></td>
				<td><div style="color:red">${result}</div></td>
			</tr>
		</table>
	</form>
</body>
</html>