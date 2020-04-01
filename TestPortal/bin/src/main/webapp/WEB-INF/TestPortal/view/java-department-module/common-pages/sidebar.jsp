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
	<div class="menu-mobile menu-activated-on-click color-scheme-dark">
		<div class="mm-logo-buttons-w">
			<a class="mm-logo"
				href="${pageContext.request.contextPath }/java/student/dashboard"><img
				src="${pageContext.request.contextPath }/static/img/Iceico_round.png"><span>Test
					Portal</span></a>
			<div class="mm-buttons">
				<div class="content-panel-open">
					<div class="os-icon os-icon-grid-circles"></div>
				</div>
				<div class="mobile-menu-trigger">
					<div class="os-icon os-icon-hamburger-menu-1"></div>
				</div>
			</div>
		</div>
		<div class="menu-and-user">
			<div class="logged-user-w">
				<div class="avatar-w">
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
				<div class="logged-user-info-w">
					<div class="logged-user-name">${user.ssoId }</div>
					<div class="logged-user-role">${user.position }</div>
				</div>
			</div>
			<ul class="main-menu">
				<li class="has-sub-menu"><a href="#"><div class="icon-w">
							<div class="os-icon os-icon-edit-32"></div>
						</div> <span>Forms</span></a>
					<ul class="sub-menu">
						<li><a href="forms_regular.html">Regular Forms</a></li>
						<li><a href="forms_validation.html">Form Validation</a></li>
						<li><a href="forms_wizard.html">Form Wizard</a></li>
						<li><a href="forms_uploads.html">File Uploads</a></li>
						<li><a href="forms_wisiwig.html">Wisiwig Editor</a></li>
					</ul></li>
				<li class="has-sub-menu"><a href="#"><div class="icon-w">
							<div class="os-icon os-icon-grid"></div>
						</div> <span>Tables</span></a>
					<ul class="sub-menu">
						<li><a href="tables_regular.html">Regular Tables</a></li>
						<li><a href="tables_datatables.html">Data Tables</a></li>
						<li><a href="tables_editable.html">Editable Tables</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<div
		class="menu-w color-scheme-light color-style-transparent menu-position-side menu-side-left menu-layout-compact sub-menu-style-over sub-menu-color-bright selected-menu-color-light menu-activated-on-hover menu-has-selected-link"
		style="margin-top: -63px;">
		<div class="logo-w">
			<a class="logo"
				href="${pageContext.request.contextPath }/java/student/dashboard">
				<img
				src="${pageContext.request.contextPath }/static/img/Iceico_round.png">
				<div class="logo-label">Test Portal</div>
			</a>
		</div>
		<div class="logged-user-w avatar-inline">
			<div class="logged-user-i">
				<div class="avatar-w">
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
				<div class="logged-user-info-w">
					<div class="logged-user-name">${user.ssoId }</div>
					<div class="logged-user-role">${user.position }</div>
				</div>
				<div class="logged-user-toggler-arrow">
					<div class="os-icon os-icon-chevron-down"></div>
				</div>
				<div class="logged-user-menu color-style-bright">
					<div class="logged-user-avatar-info">
						<div class="avatar-w">
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
							href="${pageContext.request.contextPath }/java/student/profile"><i
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


		<h1 class="menu-page-header">Page Header</h1>
		<ul class="main-menu">
			<!-- <li class="sub-header"><span>Layouts</span></li> -->
			<li class=""><a
				href="${pageContext.request.contextPath }/java/student/test/list">
					<div class="icon-w">
						<div class="os-icon os-icon-list"></div>
					</div> <span> Test List</span>
			</a></li>
			<li class=""><a
				href="${pageContext.request.contextPath }/java/student/test/result">
					<div class="icon-w">
						<div class="os-icon os-icon-window-content"></div>
					</div> <span> Test Result</span>
			</a></li>
			<li class=""><a
				href="${pageContext.request.contextPath }/java/student/test/history">
					<div class="icon-w">
						<div class="os-icon os-icon-newspaper"></div>
					</div> <span> Test History</span>
			</a></li>

			<li><a
				href="${pageContext.request.contextPath }/java/student/student/individual/performance">

					<li class=""><a
						href="${pageContext.request.contextPath }/java/student/individual/performance">
							<div class="icon-w">
								<div class="os-icon os-icon-bar-chart-stats-up"></div>
							</div> <span> My Performance</span>
					</a></li>
		</ul>
	</div>

	<!-- MODAL -->
	<div aria-hidden="true" id="myModal"
		class="onboarding-modal modal fade animated" role="dialog"
		tabindex="-1">
		<div class="modal-dialog modal-centered" role="document">
			<div class="modal-content text-center">
				<button aria-label="Close" class="close" data-dismiss="modal"
					type="button">
					<span class="os-icon os-icon-close"></span>
				</button>
				<div class="onboarding-slide">
					<div class="onboarding-media pt-0 w-100">
						<img alt=""
							src="${pageContext.request.contextPath }/static/img/security3.jpg"
							width="100%"
							style="background-size: cover; background-repeat: no-repeat;">
					</div>
					<div class="onboarding-content with-gradient pt-4 pb-4">
						<h4 class="onboarding-title">Password Change</h4>
						<form
							action="${pageContext.request.contextPath }/java/student/profile/send/token"
							method="get">
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="">Your User Name</label><input name="username"
											class="form-control" placeholder="Enter your User name..."
											value="">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label for="">Your Email Address</label><input name="mailId"
											class="form-control"
											placeholder="Enter your Email address..." value="">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<label for="">Your Mobile Number</label><input name="mobile"
											class="form-control"
											placeholder="Enter your Mobile number..." value="">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label for="">Your New Password</label><input name="password"
											type="password" class="form-control"
											placeholder="Enter your New password..." value="">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 py-3" align="center">
									<button class="btn btn-primary" type="submit">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>