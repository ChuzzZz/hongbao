<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0, shrink-to-fit=no">

<title>注册钱包账户</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
<script src="libs/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>

<link rel="stylesheet" type="text/css" href="css/index.css">
.body {
	min-height: 2000px;
}

.navbar-static-top {
	margin-bottom: 19px;
}
</style>

<script src="js/kit.js"></script>
		<!--[if IE]>
		<script src="js/ieFix.js"></script>
		<![endif]-->
		<script type="text/javascript">
			var _gaq = _gaq || [];
			_gaq.push(['_setAccount', 'UA-30210234-1']);
			_gaq.push(['_trackPageview']);
			(function() {
				var ga = document.createElement('script');
				ga.type = 'text/javascript';
				ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0];
				s.parentNode.insertBefore(ga, s);
			})();

		</script>
		<script src="js/dom.js"></script>
		<script src="js/form.js"></script>
		<link rel="stylesheet" href="css/css.css" />
		<link rel="stylesheet" href="css/login.css" />
		<link rel="stylesheet" href="css/validator.css" />
		<!--validator-->
		<script src="js/validator.js"></script>
		<script src="js/autowired.validator.js"></script>
		<style>
			table td {
				font-size: 14px;
			}
			label {
				cursor: pointer;
				margin-right: 1em;
			}
		</style>
</head>

<body>


	<div id="regist-main">
			<form id="registForm" action="register.do" method="post" >
				<ol>
					<li>
						<label for="UserName">工号：
						<span class="kitjs-validator" for="@UserName" rules="[{notNull:true, message:'工号不能为空'}]"></span>
						</label>
						<span class="field-validation-valid" data-valmsg-for="UserName" data-valmsg-replace="true"></span>
						<input id="itcode" name="itcode" type="text" value="">
					</li>
					
					<li>
						<label for="Password">设置支付密码：
						<span class="kitjs-validator" for="@Password" rules="[{notNull:true, message:'密码不能为空'},{minLength:'6',message:'密码长度最少为6位'}]"></span>
						</label>
						<span class="field-validation-valid" data-valmsg-for="Password" data-valmsg-replace="true"></span>
						<input id="paycode" name="paycode" type="password">
					</li>
					<li>
						<label for="ConfirmPassword">重新输入密码：
						<span class="kitjs-validator" for="@ConfirmPassword" rules="[{notNull:true, message:'重新输入密码不能为空'},{equalWith:'@Password',message:'两次输入密码必须一致'},{minLength:'6',message:'密码长度最少为6位'}]"></span>
						</label>
						<span class="field-validation-valid" data-valmsg-for="ConfirmPassword" data-valmsg-replace="true"></span>
						<input id="confirm_paycode" name="confirm_paycode" type="password">
					</li>
				</ol>
				<div class="registError"></div>
				<input type="submit" value="注册" class="btn-submit">
			</form>
		</div>
	
	
	
	
</body>
</html>