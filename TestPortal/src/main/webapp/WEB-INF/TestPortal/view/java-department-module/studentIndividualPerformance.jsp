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

	<div class="content-i">
		<div class="content-box">
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

						<!-- <h6 class="element-header">Sales Dashboard</h6>
						<div class="element-content">
							<div class="row">
								<div class="col-sm-4 col-xxxl-3">
									<a class="element-box el-tablo" href="#"><div class="label">Products
											Sold</div>
										<div class="value">57</div>
										<div class="trending trending-up-basic">
											<span>12%</span><i class="os-icon os-icon-arrow-up2"></i>
										</div></a>
								</div>
								<div class="col-sm-4 col-xxxl-3">
									<a class="element-box el-tablo" href="#"><div class="label">Gross
											Profit</div>
										<div class="value">$457</div>
										<div class="trending trending-down-basic">
											<span>12%</span><i class="os-icon os-icon-arrow-down"></i>
										</div></a>
								</div>
								<div class="col-sm-4 col-xxxl-3">
									<a class="element-box el-tablo" href="#"><div class="label">New
											Customers</div>
										<div class="value">125</div>
										<div class="trending trending-down-basic">
											<span>9%</span><i class="os-icon os-icon-arrow-down"></i>
										</div></a>
								</div>
								<div class="d-none d-xxxl-block col-xxxl-3">
									<a class="element-box el-tablo" href="#"><div class="label">Refunds
											Processed</div>
										<div class="value">$294</div>
										<div class="trending trending-up-basic">
											<span>12%</span><i class="os-icon os-icon-arrow-up2"></i>
										</div></a>
								</div>
							</div>
						</div> -->


					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-10 col-xxxl-9">
					<div class="element-wrapper">
						<h6 class="element-header">Todays Performance</h6>
						<div class="element-box">
							<div class="os-tabs-w">
								<div class="tab-content">
									<div class="tab-pane active" id="tab_overview">
										<div class="el-tablo bigger">
											<div class="label">Result Graph</div>
											<!-- <div class="value">10</div> -->
										</div>
										<div class="el-chart-w">
											<!-- <canvas height="150px" id="lineChart" width="600px"></canvas> -->
											<canvas id="myChart" width=200 " height="100"
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
				<div class="col-sm-10 col-xxxl-9">
					<div class="element-wrapper">
						<h6 class="element-header">Monthly Performance</h6>
						<div class="element-box">
							<div class="os-tabs-w">
								<div class="tab-content">
									<div class="tab-pane active" id="tab_overview">
										<div class="el-tablo bigger">
											<div class="label">Result Graph</div>
											<!-- <div class="value">10</div> -->
										</div>
										<div class="el-chart-w">
											<!-- <canvas height="150px" id="lineChart" width="600px"></canvas> -->
											<canvas id="myChart1" width=200 " height="100"
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
				<div class="col-sm-12 col-xxxl-6">
					<div class="element-wrapper">
						<h6 class="element-header">Performance Graph</h6>
						<div class="element-box">
							<div class="os-tabs-w">
								<div class="tab-content">
									<div class="tab-pane active" id="tab_overview">
										<div class="el-tablo bigger">
											<div class="label">Unique Visitors</div>
											<div class="value">12,537</div>
										</div>
										<div class="el-chart-w">
											<canvas height="150px" id="lineChart" width="600px"></canvas>
											<canvas id="myChart" width="250" height="120"
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
			</div>
			<div class="floated-colors-btn second-floated-btn">
				<div class="os-toggler-w">
					<div class="os-toggler-i">
						<div class="os-toggler-pill"></div>
					</div>
				</div>
				<span>Dark </span><span>Colors</span>
			</div>
			<div class="floated-customizer-btn third-floated-btn">
				<div class="icon-w">
					<i class="os-icon os-icon-ui-46"></i>
				</div>
				<span>Customizer</span>
			</div>
			<div class="floated-customizer-panel">
				<div class="fcp-content">
					<div class="close-customizer-btn">
						<i class="os-icon os-icon-x"></i>
					</div>
					<div class="fcp-group">
						<div class="fcp-group-header">Menu Settings</div>
						<div class="fcp-group-contents">
							<div class="fcp-field">
								<label for="">Menu Position</label><select
									class="menu-position-selector"><option value="left">Left</option>
									<option value="right">Right</option>
									<option value="top">Top</option></select>
							</div>
							<div class="fcp-field">
								<label for="">Menu Style</label><select
									class="menu-layout-selector"><option value="compact">Compact</option>
									<option value="full">Full</option>
									<option value="mini">Mini</option></select>
							</div>
							<div class="fcp-field with-image-selector-w">
								<label for="">With Image</label><select
									class="with-image-selector"><option value="yes">Yes</option>
									<option value="no">No</option></select>
							</div>
							<div class="fcp-field">
								<label for="">Menu Color</label>
								<div class="fcp-colors menu-color-selector">
									<div
										class="color-selector menu-color-selector color-bright selected"></div>
									<div class="color-selector menu-color-selector color-dark"></div>
									<div class="color-selector menu-color-selector color-light"></div>
									<div
										class="color-selector menu-color-selector color-transparent"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="fcp-group">
						<div class="fcp-group-header">Sub Menu</div>
						<div class="fcp-group-contents">
							<div class="fcp-field">
								<label for="">Sub Menu Style</label><select
									class="sub-menu-style-selector"><option value="flyout">Flyout</option>
									<option value="inside">Inside/Click</option>
									<option value="over">Over</option></select>
							</div>
							<div class="fcp-field">
								<label for="">Sub Menu Color</label>
								<div class="fcp-colors">
									<div
										class="color-selector sub-menu-color-selector color-bright selected"></div>
									<div class="color-selector sub-menu-color-selector color-dark"></div>
									<div class="color-selector sub-menu-color-selector color-light"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="fcp-group">
						<div class="fcp-group-header">Other Settings</div>
						<div class="fcp-group-contents">
							<div class="fcp-field">
								<label for="">Full Screen?</label><select
									class="full-screen-selector"><option value="yes">Yes</option>
									<option value="no">No</option></select>
							</div>
							<div class="fcp-field">
								<label for="">Show Top Bar</label><select
									class="top-bar-visibility-selector"><option
										value="yes">Yes</option>
									<option value="no">No</option></select>
							</div>
							<div class="fcp-field">
								<label for="">Above Menu?</label><select
									class="top-bar-above-menu-selector"><option
										value="yes">Yes</option>
									<option value="no">No</option></select>
							</div>
							<div class="fcp-field">
								<label for="">Top Bar Color</label>
								<div class="fcp-colors">
									<div
										class="color-selector top-bar-color-selector color-bright selected"></div>
									<div class="color-selector top-bar-color-selector color-dark"></div>
									<div class="color-selector top-bar-color-selector color-light"></div>
									<div
										class="color-selector top-bar-color-selector color-transparent"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="floated-chat-btn">
				<i class="os-icon os-icon-mail-07"></i><span>Demo Chat</span>
			</div>
			<div class="floated-chat-w">
				<div class="floated-chat-i">
					<div class="chat-close">
						<i class="os-icon os-icon-close"></i>
					</div>
					<div class="chat-head">
						<div class="user-w with-status status-green">
							<div class="user-avatar-w">
								<div class="user-avatar">
									<img alt="" src="img/avatar1.jpg">
								</div>
							</div>
							<div class="user-name">
								<h6 class="user-title">John Mayers</h6>
								<div class="user-role">Account Manager</div>
							</div>
						</div>
					</div>
					<div class="chat-messages">
						<div class="message">
							<div class="message-content">Hi, how can I help you?</div>
						</div>
						<div class="date-break">Mon 10:20am</div>
						<div class="message">
							<div class="message-content">Hi, my name is Mike, I will be
								happy to assist you</div>
						</div>
						<div class="message self">
							<div class="message-content">Hi, I tried ordering this
								product and it keeps showing me error code.</div>
						</div>
					</div>
					<div class="chat-controls">
						<input class="message-input"
							placeholder="Type your message here...">
						<div class="chat-extra">
							<a href="#"><span class="extra-tooltip">Attach
									Document</span><i class="os-icon os-icon-documents-07"></i></a><a href="#"><span
								class="extra-tooltip">Insert Photo</span><i
								class="os-icon os-icon-others-29"></i></a><a href="#"><span
								class="extra-tooltip">Upload Video</span><i
								class="os-icon os-icon-ui-51"></i></a>

							<div id="mon">${percentageToday}</div>
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
			labels : ${testToday},
			datasets : [ {

				label : 'Percentage',
				data : ${percentageToday},
				backgroundColor : [ 'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)',  'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)', 'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)',  'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)', 'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)',  'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)', 'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)',  'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)', 'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)'],
				borderColor : [ 'rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)','rgba(255, 99, 132)',
						'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
						'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
						'rgba(255, 159, 64)' ],
				borderWidth : 1
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});
</script>
<script>
    var arr = document.getElementById('mon');
	var ctx = document.getElementById('myChart1');
	var dynamicColors = function() {
        var r = Math.floor(Math.random() * 255);
        var g = Math.floor(Math.random() * 255);
        var b = Math.floor(Math.random() * 255);
        return "rgb(" + r + "," + g + "," + b + ")";
     };
	var myChart = new Chart(ctx, {
		type : 'bar',
		data : {
			labels : ${testMonthly},
			datasets : [ {

				label : 'Percentage',
				data :${percentageMonthly},
				backgroundColor : [ 'rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)',  'rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)', 'rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)',  'rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)', 'rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)',  'rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)', 'rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)',  'rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)', 'rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)'],
			borderColor : [ 'rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)','rgba(255, 99, 132)',
					'rgba(54, 162, 235)', 'rgba(255, 206, 86)',
					'rgba(75, 192, 192)', 'rgba(153, 102, 255)',
					'rgba(255, 159, 64)'],
				borderWidth : 1
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});
</script>
</html>