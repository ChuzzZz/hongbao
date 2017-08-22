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
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
	min-height: 2000px;
	background-image: linear-gradient(to top, #ff0844 0%, #ffb199 100%);
}

.navbar-static-top {
	margin-bottom: 19px;
}
</style>
<script>
	function initAJAX() {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		return xmlhttp;
	}
	function startRain(i) {
		var httprequest = initAJAX();
		var url = encodeURI("startluckyrain?round=" + i);
		if (confirm("确定开启红包雨吗？")) {
			httprequest.open("get", url, true);
			httprequest.send();
			document.getElementById(i).disabled = "disabled";
		}
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
	<div style="text-align:center">
	<br><br>
	<c:if test="${round1!=null}">
		<button class="btn btn-primary" id="1" onclick="startRain(1)">开启第一轮红包雨</button><br>
	</c:if><br>
	<c:if test="${round1==null}">
		<button class="btn btn-primary" id="1" onclick="startRain(1)" disabled>开启第一轮红包雨</button><br>
	</c:if><br>
	<c:if test="${round2!=null}">
		<button class="btn btn-primary" id="2" onclick="startRain(2)">开启第二轮红包雨</button><br>
	</c:if><br>
	<c:if test="${round2==null}">
		<button class="btn btn-primary" id="2" onclick="startRain(2)" disabled>开启第二轮红包雨</button><br>
	</c:if><br>
	<c:if test="${round3!=null}">
		<button class="btn btn-primary" id="3" onclick="startRain(3)">开启第三轮红包雨</button><br>
	</c:if><br>
	<c:if test="${round3==null}">
		<button class="btn btn-primary" id="3" onclick="startRain(3)" disabled>开启第三轮红包雨</button><br>
	</c:if><br>
	<a href="showluckyrainresult">查看红包雨记录</a>
	</div><br>
	
	
	
	
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!-- Font-Awesome 图标-->
	<link href="assets/css/font-awesome.min.css" rel="stylesheet">
	<link href="assets/css/style.css" rel="stylesheet">

<section id="services" class="services padding-120 bg-color">
		<div class="container">
			<div class="section-header text-center">
				<h3>Our Services</h3>
				<p><i>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse in facilisis ex, vitae maximus diam. Maecenas nisl dui.</i></p>
			</div>
			<div class="service-items">
				<div class="row">
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="service-item">
							<span><i class="fa fa-desktop" aria-hidden="true"></i></span>
							<h4>Web Design</h4>
							<p>Interdum et malesuada fames ac ante ipsum primis faucib. Suspendisse volutpat porta felises ut cursus.</p>
						</div><!-- service-item -->
					</div>
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="service-item">
							<span><i class="fa fa-code" aria-hidden="true"></i></span>
							<h4>Development</h4>
							<p>Interdum et malesuada fames ac ante ipsum primis faucib. Suspendisse volutpat porta felises ut cursus.</p>
						</div><!-- service-item -->
					</div>
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="service-item">
							<span><i class="fa fa-camera-retro" aria-hidden="true"></i></span>
							<h4>Photography</h4>
							<p>Interdum et malesuada fames ac ante ipsum primis faucib. Suspendisse volutpat porta felises ut cursus.</p>
						</div><!-- service-item -->
					</div>
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="service-item">
							<span><i class="fa fa-line-chart" aria-hidden="true"></i></span>
							<h4>SEO</h4>
							<p>Interdum et malesuada fames ac ante ipsum primis faucib. Suspendisse volutpat porta felises ut cursus.</p>
						</div><!-- service-item -->
					</div>
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="service-item">
							<span><i class="fa fa-shopping-bag" aria-hidden="true"></i></span>
							<h4>Marketing</h4>
							<p>Interdum et malesuada fames ac ante ipsum primis faucib. Suspendisse volutpat porta felises ut cursus.</p>
						</div><!-- service-item -->
					</div>
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="service-item">
							<span><i class="fa fa-cogs" aria-hidden="true"></i></span>
							<h4>Support</h4>
							<p>Interdum et malesuada fames ac ante ipsum primis faucib. Suspendisse volutpat porta felises ut cursus.</p>
						</div><!-- service-item -->
					</div>
				</div><!-- row -->
			</div><!-- service-items -->
		</div><!-- container -->
	</section>
	
	
	
	
</body>
</html>