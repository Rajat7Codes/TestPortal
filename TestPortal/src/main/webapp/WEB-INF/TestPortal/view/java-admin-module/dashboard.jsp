<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="utf-8">
<meta content="ie=edge" http-equiv="x-ua-compatible">
<meta content="template language" name="keywords">
<meta content="Tamerlan Soziev" name="author">
<meta content="Admin dashboard html template" name="description">
<meta content="width=device-width,initial-scale=1" name="viewport">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/bower_components/chart.js/dist/Chart.min.js"></script>
</head>
<body
	class="menu-position-side menu-side-left full-screen with-content-panel">
	<!-- 	<div class="content-i">
		<div class="content-box"> -->
	<div class="row">
		<div class="col-sm-12">
			<div class="element-wrapper">
				<!-- <div class="element-actions">
							<form class="form-inline justify-content-sm-end">
								<select class="form-control form-control-sm"><option
										value="Pending">Today</option>
									<option value="Active">Last Week</option>
									<option value="Cancelled">Last 30 Days</option></select>
							</form>
						</div> -->

				<h6 class="element-header">Java Admin Dashboard</h6>
				<div class="element-content">
					<div class="row">
						<div class="col-sm-4 col-xxxl-3">
							<a class="element-box el-tablo" href="#"><div class="label">Users</div>
								<div class="value">${totalJavaUsersCount }</div>
								<div class="trending trending-up-basic">
									<!-- <span>12%</span><i class="os-icon os-icon-arrow-up2"></i> -->
								</div></a>
						</div>
						<div class="col-sm-4 col-xxxl-3">
							<a class="element-box el-tablo"
								href="${pageContext.request.contextPath }/java/admin/add/test/view"><div
									class="label">Test</div>
								<div class="value">${OverallTestCount }</div>
								<div class="trending trending-down-basic">
									<!-- <span>12%</span><i class="os-icon os-icon-arrow-down"></i> -->
								</div></a>
						</div>
						<div class="col-sm-4 col-xxxl-3">
							<a class="element-box el-tablo"
								href="${pageContext.request.contextPath }/java/admin/question/bank"><div
									class="label">Questions</div>
								<div class="value">${totalQuestionsCount }</div>
								<div class="trending trending-down-basic">
									<!-- <span>9%</span><i class="os-icon os-icon-arrow-down"></i> -->
								</div></a>
						</div>
						<!-- <div class="d-none d-xxxl-block col-xxxl-3">
							<a class="element-box el-tablo" href="#"><div class="label">Refunds
									Processed</div>
								<div class="value">$294</div>
								<div class="trending trending-up-basic">
									<span>12%</span><i class="os-icon os-icon-arrow-up2"></i>
								</div></a>
						</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12 col-xxxl-9">
			<div class="element-wrapper">
				<h6 class="element-header">Today Top 10 Students</h6>
				<div class="element-box">
					<div class="os-tabs-w">
						<!-- <div class="os-tabs-controls">
										<ul class="nav nav-tabs smaller">
											<li class="nav-item"><a class="nav-link active"
												data-toggle="tab" href="#tab_overview">Overview</a></li>
											<li class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#tab_sales">Sales</a></li>
										</ul>
										<ul class="nav nav-pills smaller d-none d-md-flex">
											<li class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#">Today</a></li>
											<li class="nav-item"><a class="nav-link active"
												data-toggle="tab" href="#">7 Days</a></li>
											<li class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#">14 Days</a></li>
											<li class="nav-item"><a class="nav-link"
												data-toggle="tab" href="#">Last Month</a></li>
										</ul>
									</div> -->
						<div class="tab-content">
							<div class="tab-pane active" id="tab_overview">
								<div class="el-tablo bigger">
									<div class="label">Today Top Ten Students</div>
									<div class="value">10</div>
								</div>
								<div class="el-chart-w">
									<!-- <canvas height="150px" id="lineChart" width="600px"></canvas> -->
									<canvas id="myChart" width="200" height="100"
										class="chartjs-render-monitor" style="display: block;"></canvas>
								</div>
							</div>
							<div class="tab-pane" id="tab_sales"></div>
							<div class="tab-pane" id="tab_conversion"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<div class="element-wrapper">
				<h6 class="element-header">Today Top 10 Students</h6>
				<div class="element-box-tp">
					<div class="table-responsive">
						<table
							class="table table-bordered table-lg table-v2 table-striped">
							<thead>
								<tr>
									<th class="text-center">Rank</th>
									<th>User Id</th>
									<th>Students</th>
									<th>Date</th>
									<th>Test Name</th>
									<th>Total Marks</th>
									<th>Obtained Marks</th>
									<th>Percentage</th>
									<th>Status</th>
									<!-- <th>Actions</th> -->
								</tr>
							</thead>
							<tbody>
								<c:forEach var="test" items="${testResultStudentToday}"
									varStatus="ind">
									<tr>
										<td class="text-center">${ind.index+1}</td>
										<td>${test.userId }</td>
										<td>${userService.findById(test.userId).getFirstName() }</td>
										<td>${test.date }</td>
										<td>${test.testName }</td>
										<td>${test.totalMarks }</td>
										<td>${test.obtainedMarks }</td>
										<td>${test.percentage }</td>
										<td class="text-center">${test.resultStatus }</div></td>
										<!-- <td class="row-actions"><a href="#"><i
													class="os-icon os-icon-ui-49"></i></a><a href="#"><i
													class="os-icon os-icon-grid-10"></i></a><a class="danger"
												href="#"><i class="os-icon os-icon-ui-15"></i></a></td> -->
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="element-wrapper">
				<h6 class="element-header">Monthly Top 10 Students</h6>
				<div class="element-box-tp">
					<div class="table-responsive">
						<table
							class="table table-bordered table-lg table-v2 table-striped">
							<thead>
								<tr>
									<th class="text-center">Rank</th>
									<th>User Id</th>
									<th>Students</th>
									<th>Date</th>
									<th>Test Name</th>
									<th>Total Marks</th>
									<th>Obtained Marks</th>
									<th>Percentage</th>
									<th>Status</th>
									<!-- <th>Actions</th> -->
								</tr>
							</thead>
							<tbody>
								<c:forEach var="test" items="${testResultStudentMonthly}"
									varStatus="ind">
									<tr>
										<td class="text-center">${ind.index+1}</td>
										<td>${test.userId }</td>
										<td>${userService.findById(test.userId).getFirstName() }</td>
										<td>${test.date }</td>
										<td>${test.testName }</td>
										<td>${test.totalMarks }</td>
										<td>${test.obtainedMarks }</td>
										<td>${test.percentage }</td>
										<td class="text-center">${test.resultStatus }</div></td>
										<!-- <td class="row-actions"><a href="#"><i
													class="os-icon os-icon-ui-49"></i></a><a href="#"><i
													class="os-icon os-icon-grid-10"></i></a><a class="danger"
												href="#"><i class="os-icon os-icon-ui-15"></i></a></td> -->
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="content-panel">
		<div class="element-wrapper">
			<h6 class="element-header">Today Student Result Status</h6>
			<div class="element-box">
				<div class="el-chart-w">
					<canvas id="myPieChart1" width="400" height="400"></canvas>
					<div class="inside-donut-chart-label">
						<strong>${todayTotalTestCount }</strong><span>Total
							Students</span>
					</div>
				</div>
			</div>

			<h6 class="element-header">Monthly Student Result Status</h6>
			<div class="element-box">
				<div class="el-chart-w">
					<canvas id="myPieChart2" width="400" height="400"
						class="chartjs-render-monitor" style="display: block;"></canvas>
					<div class="inside-donut-chart-label">
						<strong>${monthlyTotalTestCount }</strong><span>Total
							Students</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
<script>
	var ctx = document.getElementById('myChart');
	var myChart = new Chart(ctx, {
		type : 'bar',
		data : {
			labels : ${topTenStudentsUserIdJava},
			datasets : [ {
				label : '#UserId & Percentage (%)',
				barPercentage : 0.4,
				data : ${topTenPercentagesjava},
				backgroundColor : [ 'rgba(255, 99, 132)', 'rgba(54, 162, 235)',
						'rgba(255, 206, 86)', 'rgba(75, 192, 192)',
						'rgba(153, 102, 255)', 'rgba(255, 159, 64)','rgba(255, 99, 132)', 'rgba(54, 162, 235)',
						'rgba(255, 206, 86)', 'rgba(75, 192, 192)',
						'rgba(153, 102, 255)', 'rgba(255, 159, 64)','rgba(255, 99, 132)', 'rgba(54, 162, 235)',
						'rgba(255, 206, 86)', 'rgba(75, 192, 192)',
						'rgba(153, 102, 255)', 'rgba(255, 159, 64)','rgba(255, 99, 132)', 'rgba(54, 162, 235)',
						'rgba(255, 206, 86)', 'rgba(75, 192, 192)',
						'rgba(153, 102, 255)', 'rgba(255, 159, 64)' ],
				borderColor : [ 'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)', 'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)' ,'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)' ,'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)' ],
				borderWidth : 1,
				
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true,
						responsive : true,
						maintainAspectRatio : true
					}
				} ]
			}
		}

	});
</script>
<script>
	var ctx = document.getElementById('myPieChart1');
	var myChart = new Chart(ctx, {
		type : 'pie',
		data : {
			labels : [ 'Pass', 'Fail' ],
			datasets : [ {
				label : '#Result',
				data : ${todayPassFailStudentCount},
				backgroundColor : [ 'rgba(54, 162, 235)','rgba(255, 99, 132)',
						'rgba(255, 206, 86)', 'rgba(75, 192, 192)',
						'rgba(153, 102, 255)', 'rgba(255, 159, 64)' ],
				borderColor : [ 'rgba(54, 162, 235, 1)', 'rgba(255, 99, 132, 1)',
						 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)' ],

			} ]
		},
	});
</script>
<script>
	var ctx = document.getElementById('myPieChart2');
	var myChart = new Chart(ctx, {
		type : 'pie',
		data : {
			labels : [ 'Pass', 'Fail' ],
			datasets : [ {
				label : '#Result',
				data : ${monthlyPassFailStudentCount},
				backgroundColor : [  'rgba(54, 162, 235)', 'rgba(255, 99, 132)',
						'rgba(255, 206, 86)', 'rgba(75, 192, 192)',
						'rgba(153, 102, 255)', 'rgba(255, 159, 64)' ],
				borderColor : [ 
						'rgba(54, 162, 235, 1)', 'rgba(255, 99, 132, 1)', 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)' ],
			} ]
		},
	});
</script>

</html>