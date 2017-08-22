<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>红包雨结果</title>

<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="css/theme.bootstrap_3.min.css">
<link rel="stylesheet" href="css/jquery.tablesorter.pager.css">

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/jquery.tablesorter.js"></script>
<script src="js/jquery.tablesorter.widgets.js"></script>
<script src="js/jquery.tablesorter.pager.js"></script>
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
$(document).ready(function() {
	$.tablesorter.addParser({
		  // set a unique id
		  id: 'time',
		  is: function(s, table, cell, $cell) {
		    // return false so this parser is not auto detected
			  return false;
		  },
		  format: function(s, table, cell, cellIndex) {
		    // format your data for normalization
			  s = s.replace(/\-/g," ");
		      s = s.replace(/:/g," ");
		      s = s.replace(/\./g," ");
		      s = s.split(" ");
		      var d = new Date(s[0], s[1], s[2], s[3], s[4], s[5], s[6]);
		      return $.tablesorter.formatFloat(d.getTime());
		  },
		  // set type, either numeric or text
		  type: 'numeric'
		});
	$("#myTable").tablesorter({
		widthFixed : true,
		widgets : [ 'zebra', 'filter', 'columns' ],
		widgetOptions : {
			// using the default zebra striping class name, so it actually isn't included in the theme variable above
			// this is ONLY needed for bootstrap theming if you are using the filter widget, because rows are hidden
			zebra : [ "even", "odd" ],

			// class names added to columns when sorted
			columns : [ "primary", "secondary", "tertiary" ],

			// reset filters button
			filter_reset : '.reset',

			// extra css class name (string or array) added to the filter element (input or select)
			filter_cssFilter : "form-control"
		}
	});
	$("#myTable").tablesorterPager({
		container : $("#pager"),
		positionFixed : false,

		// target the pager page select dropdown - choose a page
		cssGoto : ".pagenum",

		// remove rows from the table to speed up the sort of large tables.
		// setting this to false, only hides the non-visible rows; needed if you plan to add/remove rows with the pager enabled.
		removeRows : false,

		// output string - default is '{page}/{totalPages}';
		// possible variables: {page}, {totalPages}, {filteredPages}, {startRow}, {endRow}, {filteredRows} and {totalRows}
		output : '{startRow} - {endRow} / {filteredRows} ({totalRows})'
	});
});
</script>
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

		<c:if test="${result!=null}">
		<div class="container">
			<h2>红包雨结果</h2>
			<a href="showluckyrainresult">红包雨可能尚未结束，点此刷新</a><br>
			<div class="table-responsive">
				<table id="myTable" class="table table-striped table-bordered table-hover tablesorter">
					<thead>
						<tr>
							<th>账户ID</th>
							<th>金额（元）</th>
							<th class="sorter-time">获得时间</th>
							<th class="filter-select filter-exact"
								data-placeholder="所有轮次">红包轮次</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${result}" var="result" varStatus="order">
							<tr>
								<td>${result.account_id}</td>
								<td>${result.amount/100}</td>
								<td>${result.time}</td>
								<td>${result.round}</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<th colspan="4" id="pager" class="ts-pager form-inline">
								<div class="btn-group btn-group-sm" role="group">
									<button type="button" class="btn btn-default first">
										<span class="glyphicon glyphicon-step-backward"></span>
									</button>
									<button type="button" class="btn btn-default prev">
										<span class="glyphicon glyphicon-backward"></span>
									</button>
								</div> <span class="pagedisplay"></span>
								<div class="btn-group btn-group-sm" role="group">
									<button type="button" class="btn btn-default next">
										<span class="glyphicon glyphicon-forward"></span>
									</button>
									<button type="button" class="btn btn-default last">
										<span class="glyphicon glyphicon-step-forward"></span>
									</button>
								</div> <select class="form-control input-sm pagesize"
								title="Select page size">
									<option selected="selected" value="10">10</option>
									<option value="20">20</option>
									<option value="30">30</option>
									<option value="50">50</option>
									<option value="all">All Rows</option>
							</select> <select class="form-control input-sm pagenum"
								title="Select page number"></select>
							</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<c:if test="${rainoff!=null}">红包雨已停止</c:if>
		
	</c:if>
	
</body>
</html>