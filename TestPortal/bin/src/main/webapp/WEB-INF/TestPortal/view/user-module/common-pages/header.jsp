
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="page-header navbar navbar-fixed-top">
		<div class="page-header-inner ">
			<!-- logo start -->
			<div class="page-logo logo-white" align="center">
				<a href="#"> <img style="width: 40px" height="40px"
					align="middle"
					src="${pageContext.request.contextPath }/static/img/Iceico_round.png">
				</a>
			</div>
			<!-- logo end -->
			<ul class="nav navbar-nav navbar-left in">
				<li><a href="#" class="menu-toggler sidebar-toggler"><i
						class="icon-menu"></i> </a></li>
			</ul>
			<%-- <img
				src="${pageContext.request.contextPath }/static/img/vidybhavan/VBCMR LOGO.png"
				alt="Logo" style="height: 60px; width: 200px;"> --%>

			<!-- <form class="search-form-opened" action="#" method="GET">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search..."
						name="query"> <span class="input-group-btn"> <a
						href="javascript:;" class="btn submit"> <i
							class="icon-magnifier"></i>
					</a>
					</span>
				</div>
			</form> -->

			<!-- start mobile menu -->
			<a class="menu-toggler responsive-toggler" data-toggle="collapse"
				data-target=".navbar-collapse"> <span><i
					class="fa fa-bars"></i></span>
			</a>
			<!-- end mobile menu -->
			<!-- start header menu -->
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">

					<%-- <li><a href="#"> <img
							src="${pageContext.request.contextPath }/static/img/addgro1.png"
							class="brand_logo" alt="Logo"
							style="height: 44px; width: 40px; margin-bottom: 10px;">
					</a></li> --%>


					<!-- start language menu -->
					<!-- <li class="dropdown language-switch"><a
						class="dropdown-toggle" data-toggle="dropdown"
						aria-expanded="false"> <img src="assets/img/flags/gb.png"
							class="position-left" alt=""> English <span
							class="fa fa-angle-down"></span>
					</a>
						<ul class="dropdown-menu animated tada">
							<li><a class="deutsch"><img
									src="assets/img/flags/de.png" alt=""> Deutsch</a></li>
							<li><a class="ukrainian"><img
									src="assets/img/flags/ua.png" alt=""> Українська</a></li>
							<li><a class="english"><img
									src="assets/img/flags/gb.png" alt=""> English</a></li>
							<li><a class="espana"><img src="assets/img/flags/es.png"
									alt=""> España</a></li>
							<li><a class="russian"><img
									src="assets/img/flags/ru.png" alt=""> Русский</a></li>
						</ul></li> -->
					<!-- end language menu -->
					<!-- start notification dropdown -->
					<!-- <li class="dropdown dropdown-extended dropdown-notification"
						id="header_notification_bar"><a href="javascript:;"
						class="dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-close-others="true"> <i
							class="fa fa-bell-o"></i> <span class="badge headerBadgeColor1">
								6 </span>
					</a>
						<ul class="dropdown-menu animated swing">
							<li class="external">
								<h3>
									<span class="bold">Notifications</span>
								</h3> <span class="notification-label purple-bgcolor">New 6</span>
							</li>
							<li>
								<ul class="dropdown-menu-list small-slimscroll-style"
									data-handle-color="#637283">
									<li><a href="javascript:;"> <span class="time">just
												now</span> <span class="details"> <span
												class="notification-icon circle deepPink-bgcolor"><i
													class="fa fa-check"></i></span> Congratulations!.
										</span>
									</a></li>
									<li><a href="javascript:;"> <span class="time">3
												mins</span> <span class="details"> <span
												class="notification-icon circle purple-bgcolor"><i
													class="fa fa-user o"></i></span> <b>John Micle </b>is now
												following you.
										</span>
									</a></li>
									<li><a href="javascript:;"> <span class="time">7
												mins</span> <span class="details"> <span
												class="notification-icon circle blue-bgcolor"><i
													class="fa fa-comments-o"></i></span> <b>Sneha Jogi </b>sent you a
												message.
										</span>
									</a></li>
									<li><a href="javascript:;"> <span class="time">12
												mins</span> <span class="details"> <span
												class="notification-icon circle pink"><i
													class="fa fa-heart"></i></span> <b>Ravi Patel </b>like your photo.
										</span>
									</a></li>
									<li><a href="javascript:;"> <span class="time">15
												mins</span> <span class="details"> <span
												class="notification-icon circle yellow"><i
													class="fa fa-warning"></i></span> Warning!
										</span>
									</a></li>
									<li><a href="javascript:;"> <span class="time">10
												hrs</span> <span class="details"> <span
												class="notification-icon circle red"><i
													class="fa fa-times"></i></span> Application error.
										</span>
									</a></li>
								</ul>
								<div class="dropdown-menu-footer">
									<a href="javascript:void(0)"> All notifications </a>
								</div>
							</li>
						</ul></li> -->
					<!-- end notification dropdown -->
					<!-- start message dropdown -->
					<li class="dropdown dropdown-extended dropdown-inbox"
						id="header_inbox_bar"><a href="javascript:;"
						class="dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-close-others="true"> <i
							class="fa fa-cogs"></i> <!-- <span class="badge headerBadgeColor2">
								2 </span> -->
					</a>
						<ul class="dropdown-menu animated slideInDown">
							<li class="external">
								<h3>
									<span class="bold">Settings</span>
								</h3> <!-- <span class="notification-label cyan-bgcolor">New 2</span> -->
							</li>
							<li>
								<ul class="dropdown-menu-list small-slimscroll-style"
									data-handle-color="#637283">

									<li><a
										href="${pageContext.request.contextPath }/admin/college">
											<span class="photo"><i class="fa fa-graduation-cap"></i></span>
											<!-- <span class="subject"> </span> --> <span class="subject">
												<span class="from">COLLEGE</span>
										</span>
									</a></li>

									<li><a
										href="${pageContext.request.contextPath }/admin/financial/year">
											<span class="photo"><i class="fa fa-calendar"></i></span> <!-- <span class="subject"> </span> -->
											<span class="subject"> <span class="from">FINANCIAL
													YEAR</span>
										</span>
									</a></li>

									<li><a
										href="${pageContext.request.contextPath }/admin/internship/session">
											<span class="photo"><i class="fa fa-calendar"></i></span> <!-- <span class="subject"> </span> -->
											<span class="subject"> <span class="from">INTERNSHIP
													SESSION</span>
										</span>
									</a></li>

									<li><a
										href="${pageContext.request.contextPath }/admin/internship/duration">
											<span class="photo"><i class="fa fa-calendar"></i></span> <!-- <span class="subject"> </span> -->
											<span class="subject"> <span class="from">INTERNSHIP
													DURATION</span>
										</span>
									</a></li>

									<li><a
										href="${pageContext.request.contextPath }/admin/internship/type">
											<span class="photo"><i class="fa fa-list-alt"></i></span> <!-- <span class="subject"> </span> -->
											<span class="subject"> <span class="from">INTERNSHIP
													TYPE</span>
										</span>
									</a></li>

									<li><a
										href="${pageContext.request.contextPath }/admin/internship/department">
											<span class="photo"><i class="fa fa-list-alt"></i></span> <!-- <span class="subject"> </span> -->
											<span class="subject"> <span class="from">DEPARTMENT</span>
										</span>
									</a></li>

									<li><a
										href="${pageContext.request.contextPath }/admin/holiday">
											<span class="photo"><i class="fa fa-list-alt"></i></span> <!-- <span class="subject"> </span> -->
											<span class="subject"> <span class="from">HOLIDAY</span>
										</span>
									</a></li>

									<li><a href="${pageContext.request.contextPath }/admin/hr">
											<span class="photo"><i class="fa fa-list-alt"></i></span> <!-- <span class="subject"> </span> -->
											<span class="subject"> <span class="from">HR</span>
										</span>
									</a></li>

									<!-- <li><a href="#"> <span class="photo"> <img
												src="assets/img/user/user3.jpg" class="img-circle" alt="">
										</span> <span class="subject"> <span class="from"> John
													Deo </span> <span class="time">16 mins </span>
										</span> <span class="message"> Fwd: Important Notice Regarding
												Your Domain Name... </span>
									</a></li>
									<li><a href="#"> <span class="photo"> <img
												src="assets/img/user/user1.jpg" class="img-circle" alt="">
										</span> <span class="subject"> <span class="from">
													Rajesh </span> <span class="time">2 hrs </span>
										</span> <span class="message"> pls take a print of
												attachments. </span>
									</a></li>
									<li><a href="#"> <span class="photo"> <img
												src="assets/img/user/user8.jpg" class="img-circle" alt="">
										</span> <span class="subject"> <span class="from"> Lina
													Smith </span> <span class="time">40 mins </span>
										</span> <span class="message"> Apply for Ortho Surgeon </span>
									</a></li>
									<li><a href="#"> <span class="photo"> <img
												src="assets/img/user/user5.jpg" class="img-circle" alt="">
										</span> <span class="subject"> <span class="from">
													Jacob Ryan </span> <span class="time">46 mins </span>
										</span> <span class="message"> Request for leave application.
										</span>
									</a></li> -->
								</ul> <!-- <div class="dropdown-menu-footer">
									<a href="#"> All Messages </a>
								</div> -->
							</li>
						</ul></li>
					<!-- end message dropdown -->
					<!-- start manage user dropdown -->
					<li class="dropdown dropdown-user"><a href="javascript:;"
						class="dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-close-others="true"> <%-- <img alt=""
							class="img-circle "
							src="${pageContext.request.contextPath }/static/img/dp.jpg" /> --%>Welcome,
							<span class="username username-hide-on-mobile"> ${user } </span>
							<i class="fa fa-angle-down"></i>
					</a>
						<ul class="dropdown-menu dropdown-menu-default animated jello">
							<!-- 	<li><a href="user_profile.html"> <i class="icon-user"></i>
									Profile
							</a></li>
							<li><a href="#"> <i class="icon-settings"></i> Settings
							</a></li>
							<li><a href="#"> <i class="icon-directions"></i> Help
							</a></li>
							<li class="divider"></li>
							<li><a href="lock_screen.html"> <i class="icon-lock"></i>
									Lock
							</a></li> -->
							<li><a href="${pageContext.request.contextPath }/logout">
									<i class="icon-logout"></i> Log Out
							</a></li>
						</ul></li>
					<!-- end manage user dropdown -->
					<li class="dropdown dropdown-quick-sidebar-toggler"><a
						id="headerSettingButton"
						class="mdl-button mdl-js-button mdl-button--icon pull-right"
						data-upgraded=",MaterialButton"> <i class="material-icons">more_vert</i>
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- end header -->
	<!-- start color quick setting -->
	<div class="quick-setting-main">
		<button class="control-sidebar-btn btn" data-toggle="control-sidebar">
			<i class="fa fa-cog fa-spin"></i>
		</button>
		<div class="quick-setting display-none">
			<ul id="themecolors">

				<li><p class="selector-title">Sidebar Color</p></li>
				<li class="complete"><div class="theme-color sidebar-theme">
						<a href="#" data-theme="white"><span class="head"></span><span
							class="cont"></span></a> <a href="#" data-theme="dark"><span
							class="head"></span><span class="cont"></span></a> <a href="#"
							data-theme="blue"><span class="head"></span><span
							class="cont"></span></a> <a href="#" data-theme="indigo"><span
							class="head"></span><span class="cont"></span></a> <a href="#"
							data-theme="cyan"><span class="head"></span><span
							class="cont"></span></a> <a href="#" data-theme="green"><span
							class="head"></span><span class="cont"></span></a> <a href="#"
							data-theme="red"><span class="head"></span><span class="cont"></span></a>
					</div></li>
				<li><p class="selector-title">Header Brand color</p></li>
				<li class="theme-option"><div class="theme-color logo-theme">
						<a href="#" data-theme="logo-white"><span class="head"></span><span
							class="cont"></span></a> <a href="#" data-theme="logo-dark"><span
							class="head"></span><span class="cont"></span></a> <a href="#"
							data-theme="logo-blue"><span class="head"></span><span
							class="cont"></span></a> <a href="#" data-theme="logo-indigo"><span
							class="head"></span><span class="cont"></span></a> <a href="#"
							data-theme="logo-cyan"><span class="head"></span><span
							class="cont"></span></a> <a href="#" data-theme="logo-green"><span
							class="head"></span><span class="cont"></span></a> <a href="#"
							data-theme="logo-red"><span class="head"></span><span
							class="cont"></span></a>
					</div></li>
				<li><p class="selector-title">Header color</p></li>
				<li class="theme-option"><div class="theme-color header-theme">
						<a href="#" data-theme="header-white"><span class="head"></span><span
							class="cont"></span></a> <a href="#" data-theme="header-dark"><span
							class="head"></span><span class="cont"></span></a> <a href="#"
							data-theme="header-blue"><span class="head"></span><span
							class="cont"></span></a> <a href="#" data-theme="header-indigo"><span
							class="head"></span><span class="cont"></span></a> <a href="#"
							data-theme="header-cyan"><span class="head"></span><span
							class="cont"></span></a> <a href="#" data-theme="header-green"><span
							class="head"></span><span class="cont"></span></a> <a href="#"
							data-theme="header-red"><span class="head"></span><span
							class="cont"></span></a>
					</div></li>
			</ul>
		</div>
	</div>
</body>
</html>