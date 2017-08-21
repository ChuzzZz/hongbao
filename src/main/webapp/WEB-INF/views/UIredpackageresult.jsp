<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>红包结果</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script src="js/mathlib-min.js"></script>
<script src="js/k3d-min.js"></script>
<script src="js/radiation.js"></script>
<link rel="stylesheet" href="css/style.css">
</head>
<body style="background-color: rgba(0,0,0,0);overflow:hidden;text-align:center;">
<canvas id="canvas" style="position: center; background-color: #fed261;text-align:center"></canvas>

<div class="container" id="container" style="text-align:center">
	<div class="RedBox" style="text-align:center">
		<div class="topcontent" style="text-align:center">
			<h2 class="bounceInDown"><b>&nbsp;</b></h2>
			<span class="text flash">${redpackageresult}</span>
			<div class="avatar">
				<div id="open"><img src="img/user8.jpg" alt="" width="80" height="80" class="zoomIn"></div>
			</div>
			<div class="description1 flipInX" ><a href = "myaccount" class="description1 flipInX">前往我的钱包</a></div>	
		</div>
	</div>
</div>

<script>
	var oOpen = document.getElementById("open");
	var oClose = document.getElementById("open");
	var oContainer = document.getElementById("container");

	oChai.onclick = function (){
		oChai.setAttribute("class", "rotate");
	};
</script>

</body>
</html>
