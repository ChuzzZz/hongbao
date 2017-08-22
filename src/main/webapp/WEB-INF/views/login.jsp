<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
<title>Log in</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/bootstrapValidator.min.css" />

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrapValidator.min.js"></script>

<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>
<script>
	$(document).ready(function() {
		$('#myForm').bootstrapValidator({
			live : 'enabled',
			message : 'This value is not valid',
			feedbackIcons : {/*input状态样式图片*/
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				inputItcode : {
					message : 'The IT Code is not valid',
					validators : {
						notEmpty : {
							message : 'IT Code不能为空'
						},
						stringLength : {
							min : 1,
							max : 8,
							message : 'IT Code长度必须在1到8之间'
						}
					}
				},
				inputName : {
					message : 'The username is not valid',

					validators : {
						notEmpty : {
							message : '用户名不能为空'
						},
						stringLength : {
							min : 2,
							max : 6,
							message : '用户名长度必须在2到6之间'
						}
					}
				}
			}
		}).on('success.form.bv', function(e) {//点击提交之后
			// Prevent form submission
			e.preventDefault();

			// Get the form instance
			var $form = $(e.target);

			// Get the BootstrapValidator instance
			var bv = $form.data('bootstrapValidator');

			// Use Ajax to submit form data 提交至form标签中的action，result自定义
			var data = decodeURIComponent($("#myForm").serialize(), true);
			$.post("verify", data, function(result) {
				if (result.msg == "yes") {
					document.myForm.submit();
				} else {
					alert("用户不存在");
				}
			}, "json");
		});

		$("#button").on("click", function() {
			var bootstrapValidator = $("#myForm").data('bootstrapValidator');
			bootstrapValidator.validate();
			if (bootstrapValidator.isValid()) {
				var data = decodeURIComponent($("#myForm").serialize(), true);
				alert(data)
				$.post("verify", data, function(result) {
					if (result.msg == "yes") {
						document.myForm.submit();
					} else {
						alert("用户不存在");
					}
				}, "json");
			}
		});
	});
</script>
</head>

<body style="background-image:url(img/girl.jpg)">
	<div class="container">

		<form class="form-signin" id="myForm" name="myForm" role="form"
			method="get" action="login.do">

			<h2 class="form-signin-heading">用户登录</h2>

			<div class="form-group">
				<label for="inputItcode" class="sr-only">IT Code</label> <input
					type="text" class="form-control" id="inputItcode"
					name="inputItcode" placeholder="IT Code" required autofocus>
			</div>

			<div class="form-group">
				<label for="inputName" class="sr-only">Name</label> <input
					type="text" class="form-control" id="inputName" name="inputName"
					placeholder="Name" required>
			</div>

			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住我
				</label>
			</div>

			<div class="form-group">
				<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			</div>

		</form>

	</div>
</body>

</html>