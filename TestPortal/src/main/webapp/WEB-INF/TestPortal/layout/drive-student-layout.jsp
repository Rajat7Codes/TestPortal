<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta name="description" content="Responsive Admin Template" />
<meta name="" content="" />
<title><tiles:insertAttribute name="title" /></title>

<link href="favicon.png" rel="shortcut icon">
<link href="apple-touch-icon.png" rel="apple-touch-icon">
<link
	href="../fast.fonts.net/cssapi/487b73f1-c2d1-43db-8526-db577e4c822b.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/static/css/main5739.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/bower_components/select2/dist/css/select2.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/bower_components/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/bower_components/dropzone/dist/dropzone.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/bower_components/fullcalendar/dist/fullcalendar.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/bower_components/perfect-scrollbar/css/perfect-scrollbar.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/static/bower_components/slick-carousel/slick/slick.css"
	rel="stylesheet">
<link href="css/main5739.css?version=4.5.0" rel="stylesheet">

<style>
.form-control {
	border-radius: 0px;
}
</style>

</head>
<body
	class="menu-position-side menu-side-left full-screen with-content-panel">
	<div class="all-wrapper with-side-panel solid-bg-all">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<div class="layout-w">
			<tiles:insertAttribute name="sidebar"></tiles:insertAttribute>
			<div class="content-i">
				<div class="content-box">
					<tiles:insertAttribute name="body"></tiles:insertAttribute>
					<tiles:insertAttribute name="footer"></tiles:insertAttribute>
				</div>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath }/static/bower_components/jquery/dist/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/popper.js/dist/umd/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/moment/moment.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/chart.js/dist/Chart.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/select2/dist/js/select2.full.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/jquery-bar-rating/dist/jquery.barrating.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/ckeditor/ckeditor.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap-validator/dist/validator.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/ion.rangeSlider/js/ion.rangeSlider.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/dropzone/dist/dropzone.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/editable-table/mindmup-editabletable.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/perfect-scrollbar/js/perfect-scrollbar.jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/tether/dist/js/tether.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/slick-carousel/slick/slick.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap/js/dist/util.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap/js/dist/alert.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap/js/dist/button.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap/js/dist/carousel.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap/js/dist/collapse.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap/js/dist/dropdown.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap/js/dist/modal.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap/js/dist/tab.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap/js/dist/tooltip.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/bower_components/bootstrap/js/dist/popover.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/demo_customizer5739.js?version=4.5.0"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/main5739.js?version=4.5.0"></script>

	<!-- data tables -->

	<!-- 
	<script type="text/javascript"
		src="//cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script> -->


	<!-- <script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery('#example1').DataTable();
		});
	</script> -->

</body>
</html>