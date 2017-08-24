<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>我的钱包</title>
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" href="css/theme.css" media="all">
<script src="js/jquery.min.js"></script>
<script>
jQuery(document).ready(function($) {
	$('.topup').click(function(){
		
		$('.theme-popover-mask').fadeIn(100);
		$("#topup").slideDown(200);
	})
	
	$('.withdraw').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	})
	
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})

})
</script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
}

.navbar-static-top {
	margin-bottom: 19px;
}
</style>
		<link rel="stylesheet" type="text/css" href="css/default.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<script src="js/modernizr.custom.js"></script>
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
					<li><a href="myPage">Home</a></li>
					<li><a href="getshowlist">节目</a></li>
					<li><a href="redpackage">红包</a></li>
					<li class="active"><a href="myaccount">钱包</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.do">Log out<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
		<div class="container">

			<div class="main">
				<ul class="cbp-ig-grid">
					<li>
						<a>
							<span class="cbp-ig-icon cbp-ig-icon-shoe"></span>
							<h3 class="cbp-ig-title">余额：${balance}</h3>
							<span class="cbp-ig-category">您的工号：${account_id}</span>
						</a>
					</li>
					<li>
						<a href="getaccounttransactions">
							<span class="cbp-ig-icon cbp-ig-icon-ribbon"></span>
							<h3 class="cbp-ig-title">消费记录</h3>
							<span class="cbp-ig-category">金钱是大事</span>
						</a>
					</li>
					<li>
						<a class = "topup" href="javascript:;">
							<span class="cbp-ig-icon cbp-ig-icon-milk"></span>
							<h3 class="cbp-ig-title">充值</h3>
							<span class="cbp-ig-category">赞赏别人的成果</span>
						</a>
					</li>
					<li>
						<a class = "withdraw" href="javascript:;">
							<span class="cbp-ig-icon cbp-ig-icon-whippy"></span>
							<h3 class="cbp-ig-title">提现</h3>
							<span class="cbp-ig-category">我们下次再见</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
		
		
		
<div class="theme-popover" id = "topup">
     <div class="theme-poptit">
          <a href="javascript:;" title="关闭" class="close">×</a>
          <h3>账户充值</h3>
     </div>
     <div class="theme-popbod dform">
           <form class="theme-signin" name="loginform" action="topup.do" method="post">
                <ol>
                     <li><h4>请完成充值验证</h4></li>
                     <li><strong>充值金额：</strong><input class="ipt" type="text" name="amount" value="50" size="20" /></li>
                     <li><strong>消费密码：</strong><input class="ipt" type="password" name="paycode" value="" size="20" /></li>
                     <li><input class="btn btn-primary" type="submit" name="submit" value=" 确 认 充 值 " /></li>
                </ol>
           </form>
     </div>
</div>

<div class="theme-popover" id = "withdraw">
     <div class="theme-poptit">
          <a href="javascript:;" title="关闭" class="close">×</a>
          <h3 id="title">账户提现</h3>
     </div>
     <div class="theme-popbod dform">
           <form class="theme-signin" id= "fo" name="loginform" action="withdraw.do" method="post">
                <ol>
                     <li><h4>请完成提现验证</h4></li>
                     <li><strong>提现金额：</strong><input class="ipt" type="text" name="amount" value="50" size="20" /></li>
                     <li><strong>消费密码：</strong><input class="ipt" type="password" name="paycode" value="" size="20" /></li>
                     <li><input class="btn btn-primary" type="submit" name="submit" value=" 确 认 提 现 " /></li>
                </ol>
           </form>
     </div>
</div>

<div class="theme-popover-mask" ></div>
	</body>
</html>
