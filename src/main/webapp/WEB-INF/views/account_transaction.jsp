<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>交易信息</title>

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
<script>
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
			      var d = new Date(s[0], s[1]-1 , s[2], s[3], s[4], s[5], s[6]);
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
				<a class="navbar-brand" href="#">Project name</a>
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
	<c:if test="${AccountTransaction!=null}">
		<div class="container">
			<h2>交易信息</h2>
			<div class="table-responsive">
				<table id="myTable" class="table table-striped table-bordered table-hover tablesorter">
					<thead>
						<tr>
							<th><label>序号</label></th>
							<th><label>金额</label></th>
							<th class="sorter-time"><label>时间</label></th>
							<th class="filter-select filter-exact"
								data-placeholder="All Types"><label>交易类型</label></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${AccountTransaction}" var="AccountTransaction"
							varStatus="order">
							<tr>
								<td>${order.index + 1}</td>
								<td>${AccountTransaction.amount/100}</td>
								<td>${AccountTransaction.time}</td>
								<td>${AccountTransaction.type}</td>
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
	</c:if>
</body>
</html>