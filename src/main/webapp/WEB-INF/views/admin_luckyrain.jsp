<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>	
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>红包雨管理</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!-- Font-Awesome 图标-->
	<link href="assets/css/font-awesome.min.css" rel="stylesheet">
	<link href="assets/css/style.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background-image: linear-gradient(to top, #ff0844 0%, #ffb199 100%);
}

.navbar-static-top {
	margin-bottom: 19px;
}
</style>
<script language="javascript" type="text/javascript"> 
function startRain(round){
    window.location.href="startLuckyrain?round="+round; 
}
function stop(round){
    window.location.href="StopRedPackage?round="+round; 
}
function redpackagehistory(){
	window.location.href="GetAllRedPackageTransaction"; 
}
</script> 
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!-- Font-Awesome 图标-->
	<link href="assets/css/font-awesome.min.css" rel="stylesheet">
	<link href="assets/css/style.css" rel="stylesheet">
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
				<a class="navbar-brand" href="#">年会系统</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="admin">主页</a></li>
					<li class="active"><a href="adminLuckyrain">红包雨</a></li>
					<li><a href="adminredpackage">抢红包</a></li>
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

<section id="services" class="services padding-120 bg-color">
		<div class="container">
			<div class="section-header text-center">
				<h3>红包雨管理</h3>
				<p><i>可以在这里选择开启红包雨，为每个用户发放红包！</i></p>
			</div>
			<div class="service-items">
				<div class="row">
				<c:if test="${round1!=null}">
				
					<div class="col-md-4 col-sm-6 col-xs-12" id="1" onclick="startRain(1)">
						<div class="service-item">
							<span><i class="fa fa-desktop" aria-hidden="false"></i></span>
							<h4>第一轮红包雨</h4>
							<p>点击开启本轮红包雨</p>
						</div><!-- service-item -->
					</div>
				</c:if>
				<c:if test="${round1==null}">
					<div class="col-md-4 col-sm-6 col-xs-12" id="1">
						<div class="service-item">
							<span><i class="fa fa-desktop" aria-hidden="false"></i></span>
							<h4>第一轮红包雨</h4>
							<p>本轮红包雨已开启过</p>
						</div><!-- service-item -->
					</div>
				</c:if>
				
					<c:if test="${round2!=null}">
					<div class="col-md-4 col-sm-6 col-xs-12" id="2" onclick="startRain(2)">
						<div class="service-item">
							<span><i class="fa fa-desktop" aria-hidden="false"></i></span>
							<h4>第二轮红包雨</h4>
							<p>点击开启本轮红包雨</p>
						</div><!-- service-item -->
					</div>
				</c:if>
				<c:if test="${round2==null}">
					<div class="col-md-4 col-sm-6 col-xs-12" id="2">
						<div class="service-item">
							<span><i class="fa fa-desktop" aria-hidden="false"></i></span>
							<h4>第二轮红包雨</h4>
							<p>本轮红包雨已开启过</p>
						</div><!-- service-item -->
					</div>
				</c:if>
					<c:if test="${round3!=null}">
				
					<div class="col-md-4 col-sm-6 col-xs-12" id="3" onclick="startRain(3)">
						<div class="service-item">
							<span><i class="fa fa-desktop" aria-hidden="false"></i></span>
							<h4>第三轮红包雨</h4>
							<p>点击开启本轮红包雨</p>
						</div><!-- service-item -->
					</div>
				</c:if>
				<c:if test="${round3==null}">
					<div class="col-md-4 col-sm-6 col-xs-12" id="3">
						<div class="service-item">
							<span><i class="fa fa-desktop" aria-hidden="false"></i></span>
							<h4>第三轮红包雨</h4>
							<p>本轮红包雨已开启过</p>
						</div><!-- service-item -->
					</div>
				</c:if>
				</div><!-- row -->
			</div><!-- service-items -->
		</div><!-- container -->
		<br><br><br><br><br><br><br><br>
		<br><br><br><br><br><br><br><br>
</section>

</body>
</html>