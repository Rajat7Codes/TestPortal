<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="top-bar color-scheme-transparent">
		<div class="top-menu-controls">
			<!-- 	<div class="element-search autosuggest-search-activator">
				<input placeholder="Start typing to search...">
			</div> -->
			<!-- 	<div
				class="messages-notifications os-dropdown-trigger os-dropdown-position-left">
				<i class="os-icon os-icon-mail-14"></i>
				<div class="new-messages-count">12</div>
				<div class="os-dropdown light message-list">
					<ul>
						<li><a href="#"><div class="user-avatar-w">
									<img alt="" src="img/avatar1.jpg">
								</div>
								<div class="message-content">
									<h6 class="message-from">John Mayers</h6>
									<h6 class="message-title">Account Update</h6>
								</div></a></li>
						<li><a href="#"><div class="user-avatar-w">
									<img alt="" src="img/avatar2.jpg">
								</div>
								<div class="message-content">
									<h6 class="message-from">Phil Jones</h6>
									<h6 class="message-title">Secutiry Updates</h6>
								</div></a></li>
						<li><a href="#"><div class="user-avatar-w">
									<img alt="" src="img/avatar3.jpg">
								</div>
								<div class="message-content">
									<h6 class="message-from">Bekky Simpson</h6>
									<h6 class="message-title">Vacation Rentals</h6>
								</div></a></li>
						<li><a href="#"><div class="user-avatar-w">
									<img alt="" src="img/avatar4.jpg">
								</div>
								<div class="message-content">
									<h6 class="message-from">Alice Priskon</h6>
									<h6 class="message-title">Payment Confirmation</h6>
								</div></a></li>
					</ul>
				</div>
			</div> -->
			<%-- <div
				class="top-icon top-settings os-dropdown-trigger os-dropdown-position-left">
				<i class="os-icon os-icon-ui-46"></i>
				<div class="os-dropdown">
					<div class="icon-w">
						<i class="os-icon os-icon-ui-46"></i>
					</div>
					<ul>
						<li><a
							href="${pageContext.request.contextPath }/admin/department"><i
								class="os-icon os-icon-ui-49"></i><span>Department</span></a></li>
						<li><a
							href="${pageContext.request.contextPath }/admin/subject"><i
								class="os-icon os-icon-grid-10"></i><span>Subject</span></a></li>
						<li><a
							href="${pageContext.request.contextPath }/admin/questionType"><i
								class="os-icon os-icon-ui-44"></i><span>Question Type</span></a></li>
						<li><a
							href="${pageContext.request.contextPath }/admin/category"> <span
								class="photo"><i class="fa fa-graduation-cap"></i></span> <!-- <span class="subject"> </span> -->
								<span class="subject"> <span class="from">CATEGORY</span>
							</span>
						</a></li>
						<li><a href="users_profile_small.html"><i
								class="os-icon os-icon-ui-49"></i><span>Profile Settings</span></a></li>
						<li><a href="users_profile_small.html"><i
								class="os-icon os-icon-grid-10"></i><span>Billing Info</span></a></li>
						<li><a href="users_profile_small.html"><i
								class="os-icon os-icon-ui-44"></i><span>My Invoices</span></a></li>
						<li><a href="users_profile_small.html"><i
								class="os-icon os-icon-ui-15"></i><span>Cancel Account</span></a></li>
					</ul>
				</div>
			</div> --%>
			<div class="logged-user-w">
				<div class="logged-user-i">
					<div class="avatar-w">
						<img
							src="${pageContext.request.contextPath }/getImage/${user.fileName}"
							height="100px" width="100px">
					</div>
					<div class="logged-user-menu color-style-bright">
						<div class="logged-user-avatar-info">
							<div class="avatar-w">
								<img
									src="${pageContext.request.contextPath }/getImage/${user.fileName}"
									height="100px" width="100px">
							</div>
							<div class="logged-user-info-w">
								<div class="logged-user-name">${user.ssoId }</div>
								<div class="logged-user-role">${user.position }</div>
							</div>
						</div>
						<div class="bg-icon">
							<i class="os-icon os-icon-wallet-loaded"></i>
						</div>
						<ul>
							<li><a
								href="${pageContext.request.contextPath }/web/student/profile"><i
									class="os-icon os-icon-user-male-circle2"></i><span>Profile
										Details</span></a></li>

							<li><a data-target="#myModal" data-toggle="modal"><i
									class="os-icon os-icon-common-07"></i><span>Reset
										Password</span></a></li>

							<li><a href="${pageContext.request.contextPath }/logout"><i
									class="os-icon os-icon-signs-11"></i><span>Logout</span></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- MODAL -->
	<div aria-hidden="true" id="myModal"
		class="onboarding-modal modal fade animated" role="dialog"
		tabindex="-1">
		<div class="modal-dialog modal-centered" role="document">
			<div class="modal-content text-center">
				<button aria-label="Close" class="close" data-dismiss="modal"
					type="button">
					<!-- <span class="close-label">Skip Intro</span> -->
					<span class="os-icon os-icon-close"></span>
				</button>
				<!-- <div class="onboarding-slider-w">
				 <div class="onboarding-slide">
						<div class="onboarding-media">
							<img alt="" src="img/bigicon2.png" width="200px">
						</div>
						<div class="onboarding-content with-gradient">
							<h4 class="onboarding-title">Example of onboarding screen!</h4>
							<div class="onboarding-text">This is an example of a
								multistep onboarding screen, you can use it to introduce your
								customers to your app, or collect additional information from
								them before they start using your app.</div>
						</div>
					</div> -->
				<div class="onboarding-slide">
					<div class="onboarding-media">
						<img alt="" src="img/bigicon5.png" width="200px">
					</div>
					<div class="onboarding-content with-gradient">
						<h4 class="onboarding-title">Password Change</h4>
						<!-- <div class="onboarding-text">In this example you can see a
							form where you can request some additional information from the
							customer when they land on the app page.</div> -->
						<form action="${pageContext.request.contextPath }/">
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="">Your User Name</label><input
											class="form-control" placeholder="Enter your User name..."
											value="">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label for="">Your Email Address</label><input
											class="form-control"
											placeholder="Enter your Email address..." value="">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="">Your Mobile Number</label><input
											class="form-control"
											placeholder="Enter your Mobile number..." value="">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label for="">Your New Password</label><input
											class="form-control" placeholder="Enter your New password..."
											value="">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12" align="center">
									<button class="btn btn-primary" type="submit"
										onclick="getData();">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- <div class="onboarding-slide">
						<div class="onboarding-media">
							<img alt="" src="img/bigicon6.png" width="200px">
						</div>
						<div class="onboarding-content with-gradient">
							<h4 class="onboarding-title">Showcase App Features</h4>
							<div class="onboarding-text">In this example you can
								showcase some of the features of your application, it is very
								handy to make new users aware of your hidden features. You can
								use boostrap columns to split them up.</div>
							<div class="row">
								<div class="col-sm-6">
									<ul class="features-list">
										<li>Fully Responsive design</li>
										<li>Pre-built app layouts</li>
										<li>Incredible Flexibility</li>
									</ul>
								</div>
								<div class="col-sm-6">
									<ul class="features-list">
										<li>Boxed & Full Layouts</li>
										<li>Based on Bootstrap 4</li>
										<li>Developer Friendly</li>
									</ul>
								</div>
							</div>
						</div>
					</div> 
				</div>-->
			</div>
		</div>
	</div>
</body>
</html>