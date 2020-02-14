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
<!-- google font -->
<link
	href="../../../../../fonts.googleapis.com/css6079.css?family=Poppins:300,400,500,600,700"
	rel="stylesheet" type="text/css" />
<!-- icons -->
<link
	href="${pageContext.request.contextPath }/static/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath }/static/fonts/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath }/static/fonts/material-design-icons/material-icon.css"
	rel="stylesheet" type="text/css" />
<!--bootstrap -->

<link
	href="${pageContext.request.contextPath }/static/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<!-- data tables -->
<!-- data tables -->
<link
	href="${pageContext.request.contextPath }/static/plugins/datatables/plugins/bootstrap/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<!-- 
<link
	href="http://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css"
	rel="stylesheet" /> -->
<link
	href="${pageContext.request.contextPath }/static/plugins/summernote/summernote.css"
	rel="stylesheet">
<!-- Material Design Lite CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/plugins/material/material.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/material_style.css">
<!-- animation -->
<link
	href="${pageContext.request.contextPath }/static/css/pages/animate_page.css"
	rel="stylesheet">
<!-- inbox style -->
<link
	href="${pageContext.request.contextPath }/static/css/pages/inbox.min.css"
	rel="stylesheet" type="text/css" />
<!-- Theme Styles -->

<link
	href="${pageContext.request.contextPath }/static/css/plugins.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/static/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath }/static/css/responsive.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath }/static/css/theme-color.css"
	rel="stylesheet" type="text/css" />
<!-- favicon -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath }/static/img/logo/vbcmrLogo50X50.gif" />

<style>
.form-control {
	border-radius: 0px;
}
</style>
</head>

<body
	class="page-header-fixed sidemenu-closed-hidelogo page-content-white page-md header-white dark-sidebar-color logo-dark">

	<div class="page-wrapper">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<div class="page-container">
			<tiles:insertAttribute name="sidebar"></tiles:insertAttribute>
			<div class="page-content-wrapper">
				<div class="page-content">
					<tiles:insertAttribute name="body"></tiles:insertAttribute>
				</div>
				<tiles:insertAttribute name="chatSidebarContainer"></tiles:insertAttribute>
			</div>
		</div>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>


	<script
		src="${pageContext.request.contextPath }/static/plugins/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/plugins/popper/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/plugins/jquery-blockui/jquery.blockui.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- bootstrap -->
	<script
		src="${pageContext.request.contextPath }/static/plugins/bootstrap/js/bootstrap.min.js"></script>

	<!-- data tables -->
	<script
		src="${pageContext.request.contextPath }/static/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/plugins/datatables/plugins/bootstrap/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/pages/table/table_data.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/pages/sparkline/sparkline-data.js"></script>
	<!-- Common js-->
	<script src="${pageContext.request.contextPath }/static/js/app.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/layout.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/theme-color.js"></script>
	<!-- material -->
	<script
		src="${pageContext.request.contextPath }/static/plugins/material/material.min.js"></script>
	<!-- animation -->
	<script
		src="${pageContext.request.contextPath }/static/js/pages/ui/animations.js"></script>
	<!-- chart js -->
	<script
		src="${pageContext.request.contextPath }/static/plugins/chart-js/Chart.bundle.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/plugins/chart-js/utils.js"></script>
	<%-- <script
		src="${pageContext.request.contextPath }/static/js/pages/chart/chartjs/home-data.js"></script> --%>
	<!-- summernote -->
	<script
		src="${pageContext.request.contextPath }/static/plugins/summernote/summernote.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/pages/summernote/summernote-data.js"></script>

	<!-- data tables -->


	<!-- <script type="text/javascript"
		src="//cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script> -->

	<!-- <script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery('#example1').DataTable();
		});
	</script> -->

</body>
</html>