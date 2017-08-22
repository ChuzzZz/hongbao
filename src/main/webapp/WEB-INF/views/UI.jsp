<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>主界面</title>

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
</head>

<body >
	<nav class="navbar navbar-default navbar-static-top" >
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
					<li class="active"><a href="myPage">Home</a></li>
					<li><a href="getshowlist">节目</a></li>
					<li><a href="redpackage">红包</a></li>
					<li><a href="myaccount">钱包</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.do">Log out<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container" id="myContainer">

		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>XX企业年会</h1>
			<p>本次年会将于2017-08-01 19:00:00开始</p>
			<p>参与节目、抢红包，尽情享受年会</p>
			<p>
				<a class="btn btn-lg btn-primary" href="getshowlist" role="button">查看节目列表 &raquo;</a>
			</p>
		</div>

	</div>
	<!-- /container -->

</body>

</html>