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
<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body{font-size:12px;background:#efeeeb;color:#666;}
a,a:hover{text-decoration:none; color:#666;}

.demo{width:300px;margin:40px auto 0 auto;}

/* select */
.select{position:relative;float:left;margin:0 10px;}
.select dt{height:28px;display:inline-block;border:1px solid #d2ccc4;background:#fcfcfb url(images/ico.gif) no-repeat 97px center;line-height:28px;font-weight:bold;padding-left:10px;cursor:pointer;width:90px;padding-right:12px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;position:relative;z-index:99;}
.select dt:hover,.select dt.cur{border:1px solid #409DFE;box-shadow:0 0 3px #409DFE;}
.select dd{position:absolute;left:0;top:29px;border:1px solid #d2ccc4;background:#fff;display:none;}
.select dd ul{padding:4px;width:104px;max-height:250px;overflow:auto;}
.select dd ul li a{line-height:28px;display:block;padding:0 8px;}
.select dd ul li a:hover{background:#f5f5f5;}
</style>
<script type="text/javascript">
$(function(){
	$(".select").each(function(){
		var s=$(this);
		var z=parseInt(s.css("z-index"));
		var dt=$(this).children("dt");
		var dd=$(this).children("dd");
		var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
		var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
		dt.click(function(){dd.is(":hidden")?_show():_hide();});
		dd.find("a").click(function(){dt.html($(this).html());_hide();});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});
	})
})
</script>
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