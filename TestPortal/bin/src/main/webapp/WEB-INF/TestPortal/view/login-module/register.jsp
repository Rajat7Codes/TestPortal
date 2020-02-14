<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VBCMR | Register</title>

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
<style>
input:focus {
	outline: solid 1px green !important;
}
</style>
</head>
<body>
	<c:url var="loginUrl" value="/login" />
	<div class="form-title"></div>
	<div class="login-form text-center">
		<div>
			<img
				src="${pageContext.request.contextPath }/static/img/vidybhavan/VBCMR LOGO.png"
				class="brand_logo" alt="Logo"
				style="height: 50%; width: 100%; margin-bottom: 0px;">
		</div>
		<div class="form formLogin" style="margin-top: -40px;">

			<form
				action="${pageContext.request.contextPath}/register/generate/otp"
				method="post">
				<input type="text" id="fName" name="fName"
					placeholder="Enter First Name" required="required" /> <input
					type="text" id="lName" name="lName" placeholder="Enter Last Name"
					required="required" /> <input type="text" id="emailId"
					name="emailId" placeholder="Enter Email" required="required" /> <input
					type="text" id="mobile" name="mobile" placeholder="Enter Mobile"
					required="required" /> <input type="password" id="password"
					name="password" placeholder="Enter Password" required="required"
					onkeyup="return passwordChanged();" /> <span id="strength"
					style="float: right; margin-top: -20px;"> </span><input
					type="password" id="cpassword" name="cpassword"
					placeholder="Enter Confirm Password" required="required"
					onkeyup="confirmPassword()" /> <span id="cpass"
					style="float: right; margin-top: -20px;"> </span><input
					type="hidden" name="data" id="data" />
				<button onclick="getRegisterDetails();">Register</button>

			</form>
		</div>

	</div>
	
</body>
<!-- start js include path -->
<script
	src="${pageContext.request.contextPath }/static/plugins/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath }/static/js/pages/extra_pages/pages.js"></script>
<!-- end js include path -->

<script type="text/javascript">
	function getRegisterDetails() {

		var fName = jQuery('#fName').val();
		var lName = jQuery('#lName').val();
		var emailId = jQuery('#emailId').val();
		var password = jQuery('#password').val();
		var mobile = jQuery('#mobile').val();

		var registerDetails = {
			'fName' : fName,
			'lName' : lName,
			'emailId' : emailId,
			'password' : password,
			'mobile' : mobile

		};
		jQuery('#data').val(JSON.stringify(registerDetails));

	}

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

</html>