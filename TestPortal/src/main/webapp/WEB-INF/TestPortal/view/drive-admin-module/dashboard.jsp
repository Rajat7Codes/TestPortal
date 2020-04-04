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
	<div class="row vh-100">
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

				<h6 class="element-header">Admin Dashboard</h6>
				<div class="element-content">
					<div class="row">
						<div class="col-sm-4 col-xxxl-3">
							<a class="element-box el-tablo" href="#"><div class="label">Users</div>
								<div class="value">${totalDriveUsersCount }</div>
								<div class="trending trending-up-basic">
									<!-- <span>12%</span><i class="os-icon os-icon-arrow-up2"></i> -->
								</div></a>
						</div>
						<div class="col-sm-4 col-xxxl-3">
							<a class="element-box el-tablo"
								href="${pageContext.request.contextPath }/drive/admin/add/test/view"><div
									class="label">Test</div>
								<div class="value">${OverallTestCount }</div>
								<div class="trending trending-down-basic">
									<!-- <span>12%</span><i class="os-icon os-icon-arrow-down"></i> -->
								</div></a>
						</div>
						<div class="col-sm-4 col-xxxl-3">
							<a class="element-box el-tablo"
								href="${pageContext.request.contextPath }/drive/admin/question/bank"><div
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
</body>
</html>