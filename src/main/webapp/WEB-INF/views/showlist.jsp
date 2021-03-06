<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp"%>
<!DOCTYPE html>
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
		$("#registerButton").click(function() {
			window.location.href = "myaccount";
		});

		$("#topupButton").click(function() {
			window.location.href = "topup";
		});

		$("form").submit(function(e) {
			var id = $(e.target).attr('id');
			if (id == "searchForm") {
				$(this).submit();
			} else {
				e.preventDefault();
				//打赏
				var amount = this.elements[1].value;
				var show_id = this.elements[2].value;
				if (amount == 0) {
					//不充钱
					alert('！！！');
				} else {
					$.post("tip.do", {
						amount : amount,
						show_id : show_id
					}, function(data) {
						switch (data.result) {
						case "success":
							var tip_amount = data.amount;
							var tip_show = data.show_name;
							$("#tipShow").text("打赏的节目：" + tip_show);
							$("#tipAmount").text("打赏的金额：" + tip_amount);
							$('#successModal').modal();
							break;
						case "failed":
							$('#failedModal').modal();
							break;
						case "erro":
							$('#registerModal').modal();
							break;
						}
					}, "json")
				}
			}
		});

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
					<li class="active"><a href="getshowlist">节目</a></li>
					<li><a href="redpackage">红包</a></li>
					<li><a href="myaccount">钱包</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.do">Log out<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<c:if test="${showlist!=null}">
		<div class="container">
			<h2>节目列表</h2>
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
	<!-- 没有激活账户的模态框 -->
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Tip Result</h4>
				</div>
				<div class="modal-body">
					您还没有激活钱包账户，无法打赏<br> 是否前往激活钱包？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">不，稍后再说</button>
					<button type="button" class="btn btn-primary" id="registerButton">是的，前往激活</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 余额不足的模态框 -->
	<div class="modal fade" id="failedModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Tip Result</h4>
				</div>
				<div class="modal-body">
					您的账户余额不足，无法打赏<br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="topupButton">我要变强</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 打赏成功的模态框 -->
	<div class="modal fade" id="successModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Tip Result</h4>
				</div>
				<div class="modal-body">
					<p id="tipShow"></p>
					<p id="tipAmount"></p>
					<p>打赏成功！</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>