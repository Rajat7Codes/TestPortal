<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<title>Admin Dashboard HTML Template</title>
</head>
<body>
	<div class="content-i">
		<div class="content-box">
			<div class="element-wrapper">
				<div class="user-profile">
					<div class="up-head-w"
						style="background-image: url(${pageContext.request.contextPath }/static/img/cover_photo.jpeg)">
						<!-- <div class="up-social">
							<a href="#"><i class="os-icon os-icon-twitter"></i></a><a
								href="#"><i class="os-icon os-icon-facebook"></i></a>
						</div> -->
						<div class="up-main-info">
							<div class="user-avatar-w">
								<div class="user-avatar">
									<img
										src="${pageContext.request.contextPath }/getImage/${user.fileName}"
										height="100px" width="100px">
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
									href="${pageContext.request.contextPath }/student/profile/update"><i
									class="os-icon os-icon-edit"></i><span>Edit Profile</span></a>
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
		<!-- <div class="content-panel">
			<div class="content-panel-close">
				<i class="os-icon os-icon-close"></i>
			</div>
			<div class="element-wrapper">
				<h6 class="element-header">Support Agents</h6>
				<div class="element-box-tp">
					<div class="profile-tile">
						<a class="profile-tile-box" href="users_profile_small.html"><div
								class="pt-avatar-w">
								<img alt="" src="img/avatar1.jpg">
							</div>
							<div class="pt-user-name">John Mayers</div></a>
						<div class="profile-tile-meta">
							<ul>
								<li>Last Login:<strong>Online Now</strong></li>
								<li>Tickets:<strong><a
										href="apps_support_index.html">12</a></strong></li>
								<li>Response Time:<strong>2 hours</strong></li>
							</ul>
							<div class="pt-btn">
								<a class="btn btn-success btn-sm" href="apps_full_chat.html">Send
									Message</a>
							</div>
						</div>
					</div>
					<div class="profile-tile">
						<a class="profile-tile-box" href="users_profile_small.html"><div
								class="pt-avatar-w">
								<img alt="" src="img/avatar3.jpg">
							</div>
							<div class="pt-user-name">Ben Gossman</div></a>
						<div class="profile-tile-meta">
							<ul>
								<li>Last Login:<strong>Offline</strong></li>
								<li>Tickets:<strong><a
										href="apps_support_index.html">9</a></strong></li>
								<li>Response Time:<strong>3 hours</strong></li>
							</ul>
							<div class="pt-btn">
								<a class="btn btn-secondary btn-sm" href="apps_full_chat.html">Send
									Message</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="element-wrapper">
				<h6 class="element-header">Team Members</h6>
				<div class="element-box-tp">
					<div class="input-search-w">
						<input class="form-control rounded bright"
							placeholder="Search team members..." type="search">
					</div>
					<div class="users-list-w">
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
							<div class="user-action">
								<div class="os-icon os-icon-email-forward"></div>
							</div>
						</div>
						<div class="user-w with-status status-green">
							<div class="user-avatar-w">
								<div class="user-avatar">
									<img alt="" src="img/avatar2.jpg">
								</div>
							</div>
							<div class="user-name">
								<h6 class="user-title">Ben Gossman</h6>
								<div class="user-role">Administrator</div>
							</div>
							<div class="user-action">
								<div class="os-icon os-icon-email-forward"></div>
							</div>
						</div>
						<div class="user-w with-status status-red">
							<div class="user-avatar-w">
								<div class="user-avatar">
									<img alt="" src="img/avatar3.jpg">
								</div>
							</div>
							<div class="user-name">
								<h6 class="user-title">Phil Nokorin</h6>
								<div class="user-role">HR Manger</div>
							</div>
							<div class="user-action">
								<div class="os-icon os-icon-email-forward"></div>
							</div>
						</div>
						<div class="user-w with-status status-green">
							<div class="user-avatar-w">
								<div class="user-avatar">
									<img alt="" src="img/avatar4.jpg">
								</div>
							</div>
							<div class="user-name">
								<h6 class="user-title">Jenny Miksa</h6>
								<div class="user-role">Lead Developer</div>
							</div>
							<div class="user-action">
								<div class="os-icon os-icon-email-forward"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div> -->
	</div>
</body>
</html>
