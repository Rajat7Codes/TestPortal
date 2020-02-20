<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TEST PORTAL | REGISTER</title>

<link
	href="../../../../../fonts.googleapis.com/css6079.css?family=Poppins:300,400,500,600,700"
	rel="stylesheet" type="text/css" />
<!-- icons -->
<link
	href="${pageContext.request.contextPath }/static/fonts/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath }/static/fonts/material-design-icons/material-icon.css"
	rel="stylesheet" type="text/css" />
<!-- bootstrap -->
<link
	href="${pageContext.request.contextPath }/static/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<!-- style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/pages/extra_pages.css">
<!-- favicon -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath }/static/img/favicon.png" />
<link
	href="${pageContext.request.contextPath}/static/css/main5739.css?version=4.5.0"
	rel="stylesheet">

<style>
input:focus {
	outline: solid 1px green !important;
}

.logo-w {
	padding: 30px 100px !important
}

.reg-form {
	padding-bottom: 30px !important
}
</style>

</head>
<body>
	<c:url var="loginUrl" value="/login" />
	<div class="all-wrapper menu-side with-pattern">
		<div class="auth-box-w wider">
			<div class="logo-w">
				<a href="index-2.html"><img width="200px" alt=""
					src="${pageContext.request.contextPath}/static/img/Iceico_logo.png"></a>
			</div>
			<h4 class="auth-header">Create new account</h4>
			<form class="reg-form"
				action="${pageContext.request.contextPath }/register/generate/otp"
				method="POST">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label> First Name </label> <input id="fname"
								class="form-control" placeholder="Enter first name" name="fname"
								type="text">
							<div class="pre-icon os-icon os-icon-user-male-circle"></div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label> Last Name </label> <input class="form-control"
								placeholder="Enter last name" id="lname" name="lname"
								type="text">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="w-100"> Username </label> <input class="form-control"
						placeholder="Enter username" id="ssoId" name="ssoId" type="text">
					<div class="pre-icon os-icon os-icon-user-male-circle"></div>
				</div>
				<div class="form-group">
					<label class="w-100"> Email address </label> <input
						class="form-control" placeholder="Enter email" id="emailId"
						name="emailId" type="email">
					<div class="pre-icon os-icon os-icon-email-2-at2"></div>
				</div>
				<div class="form-group">
					<label> Phone Number</label> <input class="form-control"
						name="mobile" id="mobile" placeholder="Enter mobile number"
						type="text">
					<div class="pre-icon os-icon os-icon-phone"></div>
				</div>
				<div class="form-group">
					<label> Gender</label> <select class="form-control" id="gender"
						name="gender">
						<option value="Female">Female</option>
						<option value="Male">Male</option>
						<option value="Transgender">Transgender</option>
					</select>
					<div class="pre-icon os-icon os-icon-user"></div>
				</div>
				<div class="form-group">
					<label> Department</label> <select class="form-control"
						id="department" name="department">
						<option value="Java">Java</option>
						<option value="Web">Web</option>
					</select>
					<div class="pre-icon os-icon os-icon-hierarchy-structure-2">
					</div>
				</div>
				<div class="form-group">
					<label>Position</label> <input class="form-control"
						placeholder="Enter position" id="position" name="position"
						type="text">
					<div class="pre-icon os-icon os-icon-users"></div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label> Password</label> <input class="form-control"
								placeholder="Password" id="password" name="password"
								type="password">
							<div class="pre-icon os-icon os-icon-fingerprint"></div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label>Confirm Password</label> <input id="confirmPassword"
								class="form-control" placeholder="Password" type="password">
						</div>
					</div>
				</div>
				<input id="data" name="data" type="hidden">
				<div class="mt-3 text-center">
					<button id="reg" type="submit" class="btn btn-primary form-control"
						onclick="getFormData();">
						<b>Register</b>
					</button>
				</div>
			</form>
		</div>
	</div>
</body>

<script type="text/javascript">
	function getFormData() {
		var data = {
			"fname" : $("#fname").val(),
			"lname" : $("#lname").val(),
			"username" : $("#ssoId").val(),
			"emailId" : $("#emailId").val(),
			"mobile" : $("#mobile").val(),
			"department" : $("#department").val(),
			"position" : $("#position").val(),
			"password" : $("#password").val(),
			"gender" : $("#gender").val(),
		};

		$('#data').val(JSON.stringify(data));
	}
</script>

<script type="text/javascript">
	function passwordChanged() {
		var strength = document.getElementById("strength");
		var strongRegex = new RegExp(
				"^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
		var mediumRegex = new RegExp(
				"^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$",
				"g");
		var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		var pwd = document.getElementById("password");
		if (pwd.value.length == 0) {
			$("#strength").text("Type Password").css('color', '#994d00').css(
					'font-weight', 'bold').css('font-size', '15px');
			;
		} else if (false == enoughRegex.test(pwd.value)) {
			$("#strength").text("More Characters").css('color', '#cccc00').css(
					'font-weight', 'bold').css('font-size', '15px');
			;
		} else if (strongRegex.test(pwd.value)) {
			$("#strength").text('Strong!').css('color', 'green').css(
					'font-weight', 'bold').css('font-size', '15px');
		} else if (mediumRegex.test(pwd.value)) {
			$("#strength").text('Medium!').css('color', 'orange').css(
					'font-weight', 'bold').css('font-size', '15px');
		} else {
			$("#strength").text('Weak!').css('color', 'red').css('font-weight',
					'bold').css('font-size', '15px');
		}
	}

	function confirmPassword() {
		var password = document.getElementById("password").value;
		var cpassword = document.getElementById("cpassword").value;
		if (password != cpassword) {
			$("#cpass").text('Password do not match!').css('color', 'red').css(
					'font-weight', 'bold').css('font-size', '15px');
		} else {
			$("#cpass").text('Password match!').css('color', 'green').css(
					'font-weight', 'bold').css('font-size', '15px');
		}
	}
</script>

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

</html>