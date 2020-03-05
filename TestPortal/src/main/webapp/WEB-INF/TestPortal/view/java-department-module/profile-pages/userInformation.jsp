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
			<div class="element-wrapper">
				<div class="user-profile">
					<div class="up-head-w"
						style="background-image: url(${pageContext.request.contextPath }/static/img/0.jpeg)">
						<!-- <div class="up-social">
							<a href="#"><i class="os-icon os-icon-twitter"></i></a><a
								href="#"><i class="os-icon os-icon-facebook"></i></a>
						</div> -->
						<div class="up-main-info">
							<div class="user-avatar-w">
								<div class="user-avatar">
									<c:if test="${user.fileName==null }">
										<img
											src="${pageContext.request.contextPath }/static/img/avatar-default-icon.png"
											height="100px" width="100px">
									</c:if>
									<c:if test="${user.fileName!=null }">
										<img
											src="${pageContext.request.contextPath }/getImage/${user.fileName}"
											height="100px" width="100px">
									</c:if>
								</div>
							</div>
							<h1 class="up-header" style="text-transform: capitalize;">${user.firstName }&nbsp;${user.lastName }</h1>
							<h5 class="up-sub-header" style="text-transform: capitalize;">${user.position }</h5>
						</div>
						<svg class="decor" width="842px" height="219px"
							viewBox="0 0 842 219" preserveAspectRatio="xMaxYMax meet"
							version="1.1" xmlns="http://www.w3.org/2000/svg"
							xmlns:xlink="http://www.w3.org/1999/xlink">
										<g transform="translate(-381.000000, -362.000000)"
								fill="#FFFFFF">
										<path class="decor-path"
								d="M1223,362 L1223,581 L381,581 C868.912802,575.666667 1149.57947,502.666667 1223,362 Z"></path></g></svg>
					</div>
					<div class="up-controls">
						<div class="row">
							<div class="col-lg-6">
								<div class="value-pair">
									<!-- <div class="label">Status:</div>
									<div class="value badge badge-pill badge-success">Online
									</div> -->
								</div>
								<div class="value-pair">
									<!-- <div class="label">Member Since:</div>
									<div class="value">2011</div> -->
								</div>
							</div>
							<div class="col-lg-6 text-right">
								<!-- <a class="btn btn-primary btn-sm" href="#"><i
									class="os-icon os-icon-link-3"></i><span>Add to Friends</span></a> -->
								<a class="btn btn-primary btn-sm"
									href="${pageContext.request.contextPath }/java/student/profile/update"><i
									class="os-icon os-icon-edit"></i><span>Edit Profile</span></a> <a
									class="btn btn-primary btn-sm"
									href="${pageContext.request.contextPath }/java/user/send/token"><i
									class="os-icon os-icon-edit"></i><span>Change Password</span></a>
							</div>
						</div>
					</div>
					<div class="up-contents">
						<h5 class="element-header">Profile Statistics</h5>
						<div class="row m-b">
							<div class="col-lg-6">
								<div class="row">
									<div class="col-sm-6 b-r b-b">
										<div class="el-tablo centered padded">
											<div class="value">3814</div>
											<div class="label">Products Sold</div>
										</div>
									</div>
									<div class="col-sm-6 b-b b-r">
										<div class="el-tablo centered padded">
											<div class="value">47.5K</div>
											<div class="label">Followers</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6 b-r">
										<div class="el-tablo centered padded">
											<div class="value">$95</div>
											<div class="label">Daily Earnings</div>
										</div>
									</div>
									<div class="col-sm-6 b-r">
										<div class="el-tablo centered padded">
											<div class="value">12</div>
											<div class="label">Products</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="padded">
									<div class="element-info-with-icon smaller">
										<div class="element-info-icon">
											<div class="os-icon os-icon-bar-chart-stats-up"></div>
										</div>
										<div class="element-info-text">
											<h5 class="element-inner-header">Monthly Revenue</h5>
											<div class="element-inner-desc">Calculated every month</div>
										</div>
									</div>
									<div class="el-chart-w">
										<canvas height="130" id="liteLineChart" width="300"></canvas>
									</div>
								</div>
							</div>
						</div>
						<div class="os-tabs-w">
							<div class="os-tabs-controls">
								<ul class="nav nav-tabs bigger">
									<li class="nav-item"><a class="nav-link active"
										data-toggle="tab" href="#tab_overview">Activity</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#tab_sales">Daily Sales</a></li>
								</ul>
								<ul class="nav nav-pills smaller d-none d-md-flex">
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#">Today</a></li>
									<li class="nav-item"><a class="nav-link active"
										data-toggle="tab" href="#">7 Days</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#">14 Days</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#">Last Month</a></li>
								</ul>
							</div>
							<div class="tab-content">
								<div class="tab-pane active" id="tab_overview">
									<div class="timed-activities padded">
										<div class="timed-activity">
											<div class="ta-date">
												<span>21st Jan, 2017</span>
											</div>
											<div class="ta-record-w">
												<div class="ta-record">
													<div class="ta-timestamp">
														<strong>11:55</strong> am
													</div>
													<div class="ta-activity">
														Created a post called <a href="#">Register new symbol</a>
														in Rogue
													</div>
												</div>
												<div class="ta-record">
													<div class="ta-timestamp">
														<strong>2:34</strong> pm
													</div>
													<div class="ta-activity">
														Commented on story <a href="#">How to be a leader</a> in <a
															href="#">Financial</a> category
													</div>
												</div>
												<div class="ta-record">
													<div class="ta-timestamp">
														<strong>7:12</strong> pm
													</div>
													<div class="ta-activity">
														Added <a href="#">John Silver</a> as a friend
													</div>
												</div>
												<div class="ta-record">
													<div class="ta-timestamp">
														<strong>9:39</strong> pm
													</div>
													<div class="ta-activity">
														Started following user <a href="#">Ben Mosley</a>
													</div>
												</div>
											</div>
										</div>
										<div class="timed-activity">
											<div class="ta-date">
												<span>3rd Feb, 2017</span>
											</div>
											<div class="ta-record-w">
												<div class="ta-record">
													<div class="ta-timestamp">
														<strong>9:32</strong> pm
													</div>
													<div class="ta-activity">
														Added <a href="#">John Silver</a> as a friend
													</div>
												</div>
												<div class="ta-record">
													<div class="ta-timestamp">
														<strong>5:14</strong> pm
													</div>
													<div class="ta-activity">
														Commented on story <a href="#">How to be a leader</a> in <a
															href="#">Financial</a> category
													</div>
												</div>
											</div>
										</div>
										<div class="timed-activity">
											<div class="ta-date">
												<span>21st Jan, 2017</span>
											</div>
											<div class="ta-record-w">
												<div class="ta-record">
													<div class="ta-timestamp">
														<strong>11:55</strong> am
													</div>
													<div class="ta-activity">
														Created a post called <a href="#">Register new symbol</a>
														in Rogue
													</div>
												</div>
												<div class="ta-record">
													<div class="ta-timestamp">
														<strong>2:34</strong> pm
													</div>
													<div class="ta-activity">
														Commented on story <a href="#">How to be a leader</a> in <a
															href="#">Financial</a> category
													</div>
												</div>
												<div class="ta-record">
													<div class="ta-timestamp">
														<strong>9:39</strong> pm
													</div>
													<div class="ta-activity">
														Started following user <a href="#">Ben Mosley</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="tab_sales">
									<div class="el-tablo">
										<div class="label">Unique Visitors</div>
										<div class="value">12,537</div>
									</div>
									<div class="el-chart-w">
										<canvas height="150px" id="lineChart" width="600px"></canvas>
									</div>
								</div>
								<div class="tab-pane" id="tab_conversion"></div>
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<c:if test="${ passwordChange==false }">
	<script>
		alert("Please check your mail to change password");
	</script>
</c:if>

<c:if test="${ passMsg==false }">
	<script>
		alert("Password changed successfully");
	</script>
</c:if>

</html>