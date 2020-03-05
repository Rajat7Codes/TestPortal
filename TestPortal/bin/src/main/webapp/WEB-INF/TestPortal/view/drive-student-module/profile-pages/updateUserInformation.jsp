<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>JAVA DASHBOARD</title>
</head>
<body
	class="menu-position-side menu-side-left full-screen with-content-panel ">
	<div class="content-i">
		<div class="content-box">
			<div class="row">
				<div class="col-sm-5">
					<div class="user-profile compact">
						<div class="up-head-w"
							style="background-image: url(${pageContext.request.contextPath }/static/img/cover_photo.jpeg)">
							<!-- <div class="up-social">
								<a href="#"><i class="os-icon os-icon-twitter"></i></a><a
									href="#"><i class="os-icon os-icon-facebook"></i></a>
							</div> -->
							<div class="up-main-info">
								<h2 class="up-header" style="">${user.firstName }&nbsp;${user.lastName }</h2>
								<h6 class="up-sub-header" style="">${user.position }</h6>
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
								<div class="col-sm-6">
									<!-- <div class="value-pair">
										<div class="label">Status:</div>
										<div class="value badge badge-pill badge-success">Online</div>
									</div> -->
								</div>
								<div class="col-sm-6 text-right">
									<a class="btn btn-primary btn-sm"
										href="${pageContext.request.contextPath }/drive/student/profile"><i
										class="os-icon os-icon-user"></i><span>View Profile</span></a>
								</div>
							</div>
						</div>
						<div class="up-contents">
							<div class="m-b">
								<div class="row m-b">
									<div class="col-sm-6 b-r b-b">
										<div class="el-tablo centered padded-v">
											<div class="value">25</div>
											<div class="label">Products Sold</div>
										</div>
									</div>
									<div class="col-sm-6 b-b">
										<div class="el-tablo centered padded-v">
											<div class="value">315</div>
											<div class="label">Friends</div>
										</div>
									</div>
								</div>
								<div class="padded">
									<div class="os-progress-bar primary">
										<div class="bar-labels">
											<div class="bar-label-left">
												<span>Profile Completion</span><span class="positive">+10</span>
											</div>
											<div class="bar-label-right">
												<span class="info">72/100</span>
											</div>
										</div>
										<div class="bar-level-1" style="width: 100%">
											<div class="bar-level-2" style="width: 80%">
												<div class="bar-level-3" style="width: 30%"></div>
											</div>
										</div>
									</div>
									<div class="os-progress-bar primary">
										<div class="bar-labels">
											<div class="bar-label-left">
												<span>Status Unlocked</span><span class="positive">+5</span>
											</div>
											<div class="bar-label-right">
												<span class="info">45/100</span>
											</div>
										</div>
										<div class="bar-level-1" style="width: 100%">
											<div class="bar-level-2" style="width: 30%">
												<div class="bar-level-3" style="width: 10%"></div>
											</div>
										</div>
									</div>
									<div class="os-progress-bar primary">
										<div class="bar-labels">
											<div class="bar-label-left">
												<span>Followers</span><span class="negative">-12</span>
											</div>
											<div class="bar-label-right">
												<span class="info">74/100</span>
											</div>
										</div>
										<div class="bar-level-1" style="width: 100%">
											<div class="bar-level-2" style="width: 80%">
												<div class="bar-level-3" style="width: 60%"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="element-wrapper">
						<div class="element-box">
							<h6 class="element-header">User Activity</h6>
							<div class="timed-activities compact">
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
												Created a post called <a href="#">Register new symbol</a> in
												Rogue
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
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-7">
					<div class="element-wrapper">
						<div class="element-box">

							<form action="${pageContext.request.contextPath }/java/user/save"
								name="formValidateForm" id="formValidateForm" method="post"
								enctype="multipart/form-data">

								<div class="element-info">
									<div class="element-info-with-icon">
										<div class="element-info-icon">
											<div class="os-icon os-icon-wallet-loaded"></div>
										</div>
										<div class="element-info-text">
											<h5 class="element-inner-header">Profile Settings</h5>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label> Email address</label> <input class="form-control"
										data-error="Your email address is invalid"
										placeholder="Enter email" required="required" type="email"
										readonly="readonly" id="email" name="email"
										value="${user.email }" />
									<div
										class="help-block form-text with-errors form-control-feedback"></div>
								</div>
								<div class="form-group">
									<label>Mobile Number</label> <input class="form-control"
										id="mobileNumber" name="mobileNumber" readonly="readonly"
										value="${user.mobileNumber }" />
								</div>
								<div class="form-group">
									<label>Department</label> <input class="form-control"
										id="department" name="department"
										value="${user.department.departmentName }" readonly="readonly" />
								</div>
								<fieldset class="form-group">
									<legend>
										<span>Personal Info</span>
									</legend>
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label> First Name</label> <input class="form-control"
													data-error="Please input your First Name"
													placeholder="First Name" required="required" id="firstName"
													name="firstName" value="${user.firstName }" />
												<div
													class="help-block form-text with-errors form-control-feedback"></div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label>Last Name</label> <input class="form-control"
													data-error="Please input your Last Name"
													placeholder="Last Name" required="required" name="lastName"
													id="lastName" value="${user.lastName }" />
												<div
													class="help-block form-text with-errors form-control-feedback"></div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label> Date of Birth</label> <input class=" form-control"
													name="dob" type="date" id="dob" placeholder="Date of birth"
													required="required" value="${user.dob }" />
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label> Gender </label> <select class="form-control"
													name="gender" id="gender">
													<option value="Male"
														${user.gender == 'Male' ? 'selected' : ''}>Male</option>
													<option value="Female"
														${user.gender == 'Female' ? 'selected' : ''}>Female</option>
													<option value="Transgender"
														${user.gender == 'Transgender' ? 'selected' : ''}>Transgender</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label>Position</label> <input class="form-control"
											id="position" name="position" required="required"
											value="${user.position }" />
									</div>
									<div class="form-group">
										<label>Profile Photo</label> <input class="form-control"
											id="fileName" name="fileName" type="file" />
									</div>
									<div class="form-group">
										<label> About Yourself</label>
										<textarea class="form-control" rows="3" name="description"
											id="description"><c:out
												value="${user.description }" /></textarea>
									</div>
								</fieldset>
								<div class="form-check">
									<label class="form-check-label"><input
										class="form-check-input" type="checkbox" required="required">I
										agree to terms and conditions</label>
								</div>

								<input id="id" name="id" value="${user.id }" type="hidden" /> <input
									id="ssoId" name="ssoId" value="${user.ssoId }" type="hidden" />
								<input id="password" name="password" value="${user.password }"
									type="hidden" /> <input id="jsonData" name="jsonData"
									type="hidden" />

								<div class="form-buttons-w">
									<button class="btn btn-primary" type="submit"
										onclick="getData();">Submit</button>
								</div>
							</form>
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

<!-- <script type="text/javascript">
	$(function() {
		$("#gender").val('${user.gender}');
	});
</script> -->

<script type="text/javascript">
	function getData() {
		var data = {
			"email" : $("#email").val(),
			"position" : $("#position").val(),
			"department" : $("#department").val(),
			"mobileNumber" : $("#mobileNumber").val(),
			"firstName" : $("#firstName").val(),
			"lastName" : $("#lastName").val(),
			"dob" : $("#dob").val(),
			"gender" : $("#gender").val(),
			"description" : $("#description").val(),
			"id" : parseInt($("#id").val()),
			"ssoId" : $("#ssoId").val(),
			"password" : $("#password").val()
		}

		$('#jsonData').val(JSON.stringify(data));
	}
</script>
</html>
