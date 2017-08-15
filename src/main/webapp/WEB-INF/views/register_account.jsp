<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>注册钱包账户</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#b1").click(function() {
			var paycode = document.getElementById("paycode");
			var b1 = document.getElementById("b1");
			if (paycode.type == "password") {
				paycode.type = "text";
				b1.value = "隐藏";
			} else {
				paycode.type = "password";
				b1.value = "显示";
			}
		});

		$("#myform").validate({
			debug : true, //调试模式取消submit的默认提交功能   
			focusInvalid : false, //当为false时，验证无效时，没有焦点响应  
			//onkeyup : false,
			//onfocusout: false,
			submitHandler : function(form) {
				form.submit();
			},

			rules : {
				itcode : {
					required : true
				},
				paycode : {
					required : true,
					rangelength : [ 6, 16 ]
				},
				confirm_paycode : {
					required : true,
					equalTo : "#paycode"
				}
			},
			messages : {
				itcode : {
					required : "ITcode必填"
				},
				paycode : {
					required : "支付密码不能为空",
					rangelength : "密码长度应该为6-16位"
				},
				confirm_paycode : {
					required : "确认密码不能为空",
					equalTo : "两次密码输入不一致"
				}
			}

		});
	});
</script>
</head>
<body>
	<div style="color: red">${message}</div>
	<form id="myform" action="register.do">
		<table>
			<tr>
				<td>ITCode：</td>
				<td><input name="itcode"></td>
			</tr>
			<tr>
				<td>设置支付密码：</td>
				<td><input type="button" id="b1" value="显示"><input
					type="password" id="paycode" name="paycode"></td>
			</tr>
			<tr>
				<td>确认支付密码：</td>
				<td><input type="password" id="confirm_paycode"
					name="confirm_paycode"></td>
			</tr>
			<tr>
				<td><input type="submit" value="注册"></td>
			</tr>
		</table>
	</form>
</body>
</html>