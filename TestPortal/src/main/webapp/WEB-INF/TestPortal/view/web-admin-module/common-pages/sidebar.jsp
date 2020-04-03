<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.menu-w .logo-w img {
	width: 26px;
}
</style>

</head>
<body>
	<div class="menu-mobile menu-activated-on-click color-scheme-dark">
		<div class="mm-logo-buttons-w">
			<a class="mm-logo"
				href="${pageContext.request.contextPath }/web/admin/dashboard"><img
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
							<div class="os-icon os-icon-grid"></div>
						</div> <span>Question Bank</span></a>
					<ul class="sub-menu">
						<li><a
							href="${pageContext.request.contextPath }/web/admin/question/bank/new">New</a></li>
						<li><a
							href="${pageContext.request.contextPath }/web/admin/question/bank">List</a></li>
						<li><a
							href="${pageContext.request.contextPath }/web/admin/question/bank/search">Search</a></li>
					</ul></li>

				<li class="has-sub-menu"><a href="#"><div class="icon-w">
							<div class="os-icon os-icon-edit-32"></div>
						</div> <span>Test</span></a>
					<ul class="sub-menu">
						<li><a
							href="${pageContext.request.contextPath }/web/admin/add/test">New</a></li>
						<li><a
							href="${pageContext.request.contextPath }/web/admin/add/test/view">List</a></li>
					</ul></li>

				<li><a
					href="${pageContext.request.contextPath }/web/admin/test/result/list">
						<div class="icon-w">
							<div class="os-icon os-icon-window-content"></div>
						</div> <span>Results</span>
				</a></li>
			</ul>

		</div>
	</div>
	<div
		class="menu-w color-scheme-light color-style-transparent menu-position-side menu-side-left menu-layout-compact sub-menu-style-over sub-menu-color-bright selected-menu-color-light menu-activated-on-hover menu-has-selected-link"
		style="margin-top: -54px;">
		<div class="logo-w">
			<a class="logo"
				href="${pageContext.request.contextPath }/web/admin/dashboard">
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
							href="${pageContext.request.contextPath }/web/admin/profile"><i
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

			<li class="has-sub-menu"><a href="#"><div class="icon-w">
						<div class="os-icon os-icon-grid"></div>
					</div> <span>Question Bank</span></a>
				<div class="sub-menu-w">
					<div class="sub-menu-header">Question Bank</div>
					<div class="sub-menu-icon">
						<i class="os-icon os-icon-grid"></i>
					</div>
					<div class="sub-menu-i">
						<ul class="sub-menu">
							<li><a
								href="${pageContext.request.contextPath }/web/admin/question/bank/new">New</a></li>
							<li><a
								href="${pageContext.request.contextPath }/web/admin/question/bank">List</a></li>
							<li><a
								href="${pageContext.request.contextPath }/web/admin/question/bank/search">Search</a></li>
						</ul>
					</div>
				</div></li>

			<li class="has-sub-menu"><a href="#"><div class="icon-w">
						<div class="os-icon os-icon-edit-32"></div>
					</div> <span>Test</span></a>
				<div class="sub-menu-w">
					<div class="sub-menu-header">Test</div>
					<div class="sub-menu-icon">
						<i class="os-icon os-icon-grid"></i>
					</div>
					<div class="sub-menu-i">
						<ul class="sub-menu">
							<li><a
								href="${pageContext.request.contextPath }/web/admin/add/test">New</a></li>
							<li><a
								href="${pageContext.request.contextPath }/web/admin/add/test/view">List</a></li>
						</ul>
					</div>
				</div></li>

			<li><a
				href="${pageContext.request.contextPath }/web/admin/test/result/list">
					<div class="icon-w">
						<div class="os-icon os-icon-window-content"></div>
					</div> <span>Results</span>
			</a></li>
		</ul>
	</div>
</body>
</html>