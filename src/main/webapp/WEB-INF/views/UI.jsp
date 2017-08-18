<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
<script type="text/javascript">
	$(document).ready(function(){
		$("#myHome").click(function(){
			$("li").removeClass("active");
			$(this).parent().addClass("active");
			$("#myContainer").load("html/home.html");
		});
		$("#show").click(function(){
			$("li").removeClass("active");
			$(this).parent().addClass("active");
			$("#myContainer").load("getshowlist");
		});
		$("#hongbao").click(function(){
			$("li").removeClass("active");
			$(this).parent().addClass("active");
			$("#myContainer").load("redpackage");
		});
		$("#qianbao").click(function(){
			$("li").removeClass("active");
			$(this).parent().addClass("active");
			$("#myContainer").load("myaccount");
		});
	});
</script>
</head>

<body>

<!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#" id="myHome">Home</a></li>
            <li><a href="#" id="show">节目</a></li>
            <li><a href="#" id="hongbao">红包</a></li>
            <li><a href="#" id="qianbao">钱包</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">钱包<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">充值</a></li>
                <li><a href="#">提现</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">交易记录</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Default</a></li>
            <li class="active"><a href="./">Static top <span class="sr-only">(current)</span></a></li>
            <li><a href="#">Fixed top</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container" id="myContainer">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Navbar example</h1>
        <p>This example is a quick exercise to illustrate how the default, static and fixed to top navbar work. It includes the responsive CSS and HTML, so it also adapts to your viewport and device.</p>
        <p>To see the difference between static and fixed top navbars, just scroll.</p>
        <p>
          <a class="btn btn-lg btn-primary" href="#" role="button">View navbar docs &raquo;</a>
        </p>
      </div>

    </div> <!-- /container -->
    
</body>

</html>