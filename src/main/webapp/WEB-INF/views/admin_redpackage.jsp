<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>抢红包管理</title>
<link rel="stylesheet" href="css/set_8.css">
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link href="css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/general.css">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
	
}

.navbar-static-top {
	margin-bottom: 19px;
}
</style>


<script language="javascript" type="text/javascript"> 
function active(round){
    window.location.href="ActivateRedPackage?round="+round; 
}
function stop(round){
    window.location.href="StopRedPackage?round="+round; 
}
function redpackagehistory(){
	window.location.href="GetAllRedPackageTransaction"; 
}
</script> 

</head>



<body >
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
				<a class="navbar-brand" href="#">年会系统</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav"> 
					<li><a href="admin">主页</a></li>
					<li><a href="adminLuckyrain">红包雨</a></li>
					<li class="active"><a href="adminredpackage">抢红包</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
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
	
	<h1>红包管理</h1>
	<hr>
	
	<!--Set 8 start-->
	<div class="container_even" style = "text-align:center">
	    <div class="set_container">
		<c:if test="${round1==0}"><th><div class="set_8_button4 outline" onclick="active(1)">开启第一轮抢红包<span class="lines"></span></div></th></c:if>
		<c:if test="${round1==1}"><th><div class="set_8_button4 outline" onclick="stop(1)">停止第一轮抢红包<span class="lines"></span></div></th></c:if>
		<c:if test="${round2==0}"><th><div class="set_8_button2 outline" onclick="active(2)">开启第二轮抢红包<span class="lines"></span></div></th></c:if>
		<c:if test="${round2==1}"><th><div class="set_8_button2 outline" onclick="stop(2)">停止第二轮抢红包<span class="lines"></span></div></th></c:if>
		<div style="clear:both"></div>
	    </div>
	    <br>
		<div class="set_container" style = "text-align:center">
		<c:if test="${round3==0}"><th><div class="set_8_button3 outline" onclick="active(3)">开启第三轮抢红包<span class="lines"></span></div></th></c:if>
		<c:if test="${round3==1}"><th><div class="set_8_button3 outline" onclick="stop(3)">停止第三轮抢红包<span class="lines"></span></div></th></c:if>
		<div class="set_8_button outline" onclick="redpackagehistory()">查看红包发放记录<span class="lines"></span></div>
		<div style="clear:both"></div>
	  </div>

	</div>

	 

	<!--Set 8 end--> 

</body>
</html>