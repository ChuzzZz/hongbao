<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>节目信息</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/theme.bootstrap_3.min.css">
<link rel="stylesheet" href="css/jquery.tablesorter.pager.css">

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/jquery.tablesorter.js"></script>
<script src="js/jquery.tablesorter.widgets.js"></script>
<script src="js/jquery.tablesorter.pager.js"></script>
<script src="js/bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
		$("#myTable").tablesorter({
			sortList : [ [ 0, 0 ] ],
			headers : {
				0 : {
					sorter : false
				},
				1 : {
					sorter : false
				},
				2 : {
					sorter : false
				},
				3 : {
					sorter : false
				}
			},
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
	<input class="btn" type="button" value="回退"
		onclick="window.history.go(-1)">
	<input class="btn" type="button" value="个人主页"
		onclick="window.location.href='MyPage'">
	<c:if test="${showlist!=null}">
		<div class="container">
			<h2>节目列表</h2>
			<br>
			<form action="searchbyrule">
				节目名：<input name="show_name"> &nbsp;&nbsp;表演者：<input
					name="performer"> &nbsp;&nbsp;部门：<input name="department">&nbsp;&nbsp;
				<input type="submit" value="查询">
			</form>
			<br>

			<div class="table-responsive">
				<table id="myTable"
					class="table table-striped table-bordered table-hover tablesorter">
					<thead>
						<tr>
							<th>序号</th>
							<th>节目名</th>
							<th>表演者</th>
							<th>部门</th>
							<th>开始时间</th>
							<th class="sorter-false filter-false"></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${showlist}" var="showinfo" varStatus="order">
							<tr>
								<td>${order.index + 1}</td>
								<td>${showinfo.show_name}</td>
								<td>${showinfo.performer}</td>
								<td>${showinfo.department}</td>
								<td>${showinfo.start_time}</td>
								<td><form action="tip.do">
										<input type="submit" value="打赏"><input class="span2"
											name="amount"> 元 <input type="hidden" name="show_id"
											value="${showinfo.id}">
									</form></td>
							</tr>
						</c:forEach>
					</tbody>

					<tfoot>
						<tr>
							<th colspan="6" id="pager" class="ts-pager form-inline">
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