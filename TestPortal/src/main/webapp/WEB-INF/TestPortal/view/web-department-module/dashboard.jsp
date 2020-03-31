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
</head>


<body
	class="menu-position-side menu-side-left full-screen with-content-panel ">

	<div class="content-i">
		<div class="content-box">
			<div class="row">
				<div class="col-sm-12">
					<div class="element-wrapper">

						<h6 class="element-header">Student Dashboard</h6>
						<div class="element-content">
							<div class="row">
								<div class="col-sm-4 col-xxxl-4">
									<a class="element-box el-tablo" href="#"><div class="label">Total
											Students</div>
										<div class="value">${totalJavaUsersCount }</div>
										<div class="trending trending-up-basic">
											<!-- <span>12%</span><i class="os-icon os-icon-arrow-up2"></i> -->
										</div></a>
								</div>
								<div class="col-sm-4 col-xxxl-4">
									<a class="element-box el-tablo" href="#"><div class="label">Total
											Test</div>
										<div class="value">${totalTestList }</div>
										<div class="trending trending-down-basic">
											<!-- <span>12%</span><i class="os-icon os-icon-arrow-down"></i> -->
										</div></a>
								</div>
								<div class="col-sm-4 col-xxxl-4">
									<a class="element-box el-tablo" href="#"><div class="label">Total
											Questions</div>
										<div class="value">${testQuestions }</div>
										<div class="trending trending-down-basic">
											<!-- <span>9%</span><i class="os-icon os-icon-arrow-down"></i> -->
										</div></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12  col-xxxl-6"">
					<div class="element-wrapper">

						<h6 class="element-header">Today Students Result</h6>
						<div class="element-box">
							<div class="table-responsive">
								<table
									class="table table-bordered table-lg table-v2 table-striped">
									<thead>
										<tr>
											<th class="text-center">Rank</th>
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
				<div class="col-sm-12  col-xxxl-6"">
					<div class="element-wrapper">

						<h6 class="element-header">Monthly Top 10 Students</h6>
						<div class="element-box">
							<div class="table-responsive">
								<table
									class="table table-bordered table-lg table-v2 table-striped">
									<thead>
										<tr>
											<th class="text-center">Rank</th>
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

			<%-- 	<div class="row">
				<div class="col-sm-12  col-xxxl-6"">
					<div class="element-wrapper">

						<h6 class="element-header">Monthly Top 10 Students</h6>
						<div class="element-box">
							<div class="table-responsive">
								<table
									class="table table-bordered table-lg table-v2 table-striped">
									<thead>
										<tr>
											<th class="text-center">Rank</th>
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
			</div> --%>



			<%-- <div class="row">
				<div class="col-sm-12 col-xxxl-6">



					<div class="element-wrapper">
						<h6 class="element-header">Unique Visitors Graph</h6>
						<div class="element-box">
							<div class="os-tabs-w">
								<div class="os-tabs-controls">
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
								</div>
								<div class="tab-content">
									<div class="tab-pane active" id="tab_overview">
										<div class="el-tablo bigger">
											<div class="label">Unique Visitors</div>
											<div class="value">12,537</div>
										</div>
										<div class="el-chart-w">
											<canvas height="150px" id="lineChart" width="600px"></canvas>
										</div>
									</div>
									<div class="tab-pane" id="tab_sales"></div>
									<div class="tab-pane" id="tab_conversion"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-4 d-xxxl-none"></div>
				<div class="d-none d-xxxl-block col-xxxl-6">
					<div class="element-wrapper">
						<div class="element-actions">
							<form class="form-inline justify-content-sm-end">
								<select class="form-control form-control-sm rounded"><option
										value="Pending">Today</option>
									<option value="Active">Last Week</option>
									<option value="Cancelled">Last 30 Days</option></select>
							</form>
						</div>
						<h6 class="element-header">Inventory Stats</h6>
						<div class="element-box">
							<div class="os-progress-bar primary">
								<div class="bar-labels">
									<div class="bar-label-left">
										<span class="bigger">Eyeglasses</span>
									</div>
									<div class="bar-label-right">
										<span class="info">25 items / 10 remaining</span>
									</div>
								</div>
								<div class="bar-level-1" style="width: 100%">
									<div class="bar-level-2" style="width: 70%">
										<div class="bar-level-3" style="width: 40%"></div>
									</div>
								</div>
							</div>
							<div class="os-progress-bar primary">
								<div class="bar-labels">
									<div class="bar-label-left">
										<span class="bigger">Outwear</span>
									</div>
									<div class="bar-label-right">
										<span class="info">18 items / 7 remaining</span>
									</div>
								</div>
								<div class="bar-level-1" style="width: 100%">
									<div class="bar-level-2" style="width: 40%">
										<div class="bar-level-3" style="width: 20%"></div>
									</div>
								</div>
							</div>
							<div class="os-progress-bar primary">
								<div class="bar-labels">
									<div class="bar-label-left">
										<span class="bigger">Shoes</span>
									</div>
									<div class="bar-label-right">
										<span class="info">15 items / 12 remaining</span>
									</div>
								</div>
								<div class="bar-level-1" style="width: 100%">
									<div class="bar-level-2" style="width: 60%">
										<div class="bar-level-3" style="width: 30%"></div>
									</div>
								</div>
							</div>
							<div class="os-progress-bar primary">
								<div class="bar-labels">
									<div class="bar-label-left">
										<span class="bigger">Jeans</span>
									</div>
									<div class="bar-label-right">
										<span class="info">12 items / 4 remaining</span>
									</div>
								</div>
								<div class="bar-level-1" style="width: 100%">
									<div class="bar-level-2" style="width: 30%">
										<div class="bar-level-3" style="width: 10%"></div>
									</div>
								</div>
							</div>
							<div class="mt-4 border-top pt-3">
								<div class="element-actions d-none d-sm-block">
									<form class="form-inline justify-content-sm-end">
										<select
											class="form-control form-control-sm form-control-faded"><option
												selected="true">Last 30 days</option>
											<option>This Week</option>
											<option>This Month</option>
											<option>Today</option></select>
									</form>
								</div>
								<h6 class="element-box-header">Inventory History</h6>
								<div class="el-chart-w">
									<canvas
										data-chart-data="13,28,19,24,43,49,40,35,42,46,38,32,45"
										height="50" id="liteLineChartV3" width="300"></canvas>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div> --%>
		</div>

		<div class="content-panel">
			<div class="content-panel-close">
				<i class="os-icon os-icon-close"></i>
			</div>
			<div class="element-wrapper">
				<h6 class="element-header">Quick Links</h6>
				<div class="element-box-tp">
					<div class="el-buttons-list full-width">
						<a class="btn btn-white btn-sm" href="#"><i
							class="os-icon os-icon-delivery-box-2"></i><span>Create
								New Product</span></a><a class="btn btn-white btn-sm" href="#"><i
							class="os-icon os-icon-window-content"></i><span>Customer
								Comments</span></a><a class="btn btn-white btn-sm" href="#"><i
							class="os-icon os-icon-wallet-loaded"></i><span>Store
								Settings</span></a>
					</div>
				</div>
			</div>
			<div class="element-wrapper">
				<h6 class="element-header">Top Selling Today</h6>
				<div class="element-box">
					<div class="el-chart-w">
						<canvas height="120" id="donutChart" width="120"></canvas>
						<div class="inside-donut-chart-label">
							<strong>142</strong><span>Total Orders</span>
						</div>
					</div>
					<div class="el-legend condensed">
						<div class="row">
							<div class="col-auto col-xxxxl-6 ml-sm-auto mr-sm-auto col-6">
								<div class="legend-value-w">
									<div class="legend-pin legend-pin-squared"
										style="background-color: #6896f9;"></div>
									<div class="legend-value">
										<span>Prada</span>
										<div class="legend-sub-value">14 Pairs</div>
									</div>
								</div>
								<div class="legend-value-w">
									<div class="legend-pin legend-pin-squared"
										style="background-color: #85c751;"></div>
									<div class="legend-value">
										<span>Maui Jim</span>
										<div class="legend-sub-value">26 Pairs</div>
									</div>
								</div>
							</div>
							<div class="col-6 d-lg-none d-xxl-block">
								<div class="legend-value-w">
									<div class="legend-pin legend-pin-squared"
										style="background-color: #806ef9;"></div>
									<div class="legend-value">
										<span>Gucci</span>
										<div class="legend-sub-value">17 Pairs</div>
									</div>
								</div>
								<div class="legend-value-w">
									<div class="legend-pin legend-pin-squared"
										style="background-color: #d97b70;"></div>
									<div class="legend-value">
										<span>Armani</span>
										<div class="legend-sub-value">12 Pairs</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
