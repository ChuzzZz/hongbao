<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0, shrink-to-fit=no">

<title>节目报送</title>

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

.demo{width:300px;margin:10px auto 0 auto;padding-right:12px;}

/* select */
.select{position:relative;float:right;margin:0 10px;}
.select dt{height:28px;display:inline-block;border:1px solid #d2ccc4;background:#fcfcfb url(images/ico.gif) no-repeat 97px center;line-height:28px;font-weight:bold;padding-left:10px;cursor:pointer;width:90px;padding-right:12px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;position:right;z-index:99;}
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
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
.body {
	min-height: 2000px;
}
.navbar-static-top {
	margin-bottom: 19px;
}
</style>
<script src="libs/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>

<link rel="stylesheet" type="text/css" href="css/index.css">

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
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">


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
		
		
<script>
	function choose(a){
		document.getElementById("department").value=a;
	}

</script>

<script>
	$(document).ready(function() {
		$("#ok").click(function(){
			alert(result.msg);
			data = $("#registForm").serialize();
			$.post("addshowinfo", data, function(result){
				if(result.msg == "success"){
					$("#successModal").modal();
				}else{
					$('#failedModal').modal();
				}
			},"json");
		});
		$("#showButton").click(function(){
			window.location.href = "admingetshowlist";
		});
	});

</script>
</head>

<body>


	<div id="regist-main">
			<form id="registForm"  >
				<ol>
					<li>
						<label for="UserName">节目名称：
						<span class="kitjs-validator" for="@UserName" rules="[{notNull:true, message:'工号不能为空'}]"></span>
						</label>
						<span class="field-validation-valid" data-valmsg-for="UserName" data-valmsg-replace="true"></span>
						<input id="show_name" name="show_name" type="text" value="">
					</li>
					
					<li>
						<label for="Password">表演者：
						<span class="kitjs-validator" for="@Password" rules="[{notNull:true, message:'密码不能为空'},{minLength:'6',message:'密码长度最少为6位'}]"></span>
						</label>
						<span class="field-validation-valid" data-valmsg-for="Password" data-valmsg-replace="true"></span>
						<input id="performer" name="performer" type="text">
					</li>
					
					<li>
					<table><tr>
						<td>报送部门：<input type="text" id="department" name="department" readonly="readonly"></td>
						</tr><tr>
						<td><div class="demo">
						<dl class="select">
						<dt>选择部门</dt>
							<dd>
								<ul>
									<li><a onclick="choose('软件学院')">软件学院</a></li>
									<li><a onclick="choose('泰达学院')">泰达学院</a></li>
									<li><a onclick="choose('信息学院')">信息学院</a></li>
								</ul>
							</dd>
						</dl>
						
						
						</div>
						</td>
						</tr>
						</table>
					</li>
				</ol>
				<div class="registError"></div>
				<input type="hidden" value = "2017-08-01 19:00:00" name = "start_time">
				<input type="submit" value="确定" id = "ok" class="btn-submit">
			</form>
		</div>
<!-- 添加节目失败的模态框 -->
	<div class="modal fade" id="failedModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">结果</h4>
				</div>
				<div class="modal-body">
					添加节目信息失败
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加节目成功的模态框 -->
	<div class="modal fade" id="successModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">结果</h4>
				</div>
				<div class="modal-body">
					添加节目信息成功！
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="showButton" data-dismiss="modal">查看节目单</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>