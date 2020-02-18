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
			<form class="reg-form" action="${pageContext.request.contextPath}/admin/user/verify/mail" method="post">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for=""> First Name </label> <input id="fname"
								class="form-control" placeholder="Enter first name" name="fname"
								type="text">
							<div class="pre-icon os-icon os-icon-user-male-circle"></div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for=""> Last Name </label> <input class="form-control"
								placeholder="Enter last name" id="lname" name="lname"
								type="text">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="w-100" for=""> Email
						address </label> <input class="form-control" placeholder="Enter email"
						id="emailId" name="emailId" type="email">
					<div class="pre-icon os-icon os-icon-email-2-at2"></div>
				</div>


				<div class="form-group">
					<label for=""> Phone Number</label> <input class="form-control"
						name="mobile" id="mobile" placeholder="Enter mobile number"
						type="text">
					<div class="pre-icon os-icon os-icon-phone"></div>
				</div>
				<div class="form-group">
					<label for=""> Department</label> <select class="form-control"
						id="department" name="department">
						<option value="Java">Java</option>
						<option value="Web">Web</option>
					</select>
					<div class="pre-icon os-icon os-icon-hierarchy-structure-2">
					</div>
				</div>
				<div class="form-group">
					<label for="">Position</label> <input class="form-control"
						placeholder="Enter position" id="position" name="position"
						type="text">
					<div class="pre-icon os-icon os-icon-phone"></div>
				</div>
				<div class="form-group">
					<label for=""> Image</label> <input type="file"
						class="form-control" id="img" name="image" accept="image/*">
					<div class="pre-icon os-icon os-icon-image"></div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for=""> Password</label> <input class="form-control"
								placeholder="Password" id="password" name="password"
								type="password">
							<div class="pre-icon os-icon os-icon-fingerprint"></div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="">Confirm Password</label> <input
								class="form-control" placeholder="Password" type="password">
						</div>
					</div>
				</div>
				<div class="mt-3 text-center">
					<input type="submit"
						value="Verify Email" id="reg"
						class="btn btn-primary form-control" />
				</div>
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

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

<script type="text/javascript">
	let otpRec = 0;

	function getRegisterDetails() {
		console.log(otpRec);

		/* var fName = $('#fname').val();
		var lName = $('#lname').val();
		var emailId = $('#emailId').val();
		var mobile = $('#mobile').val();
		var department = $("#department").val();
		var position = $('#position').val();
		var image = $('#img').val();
		var password = $('#password').val();

		var registerDetails = {
			'fName' : fName,
			'lName' : lName,
			'emailId' : emailId,
			'password' : password,
			'mobile' : mobile,
			'department' : department,
			'position' : position,
			'image' : image
		};

		document.getElementById("otpBtn").style.display = "none";
		document.getElementById("otp").style.display = "block";
		document.getElementById("reg").style.display = "block";

		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/generate/otp",
			data : registerDetails,
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(response) {

				alert(JSON.stringify(response))


			}
		}); */
	}

	function sendOtp() {
		var emailId = $('#emailId').val();

		if (emailId == null || emailId == "") {
			alert("Enter Email Address to Verify")
		} else {
			var data = {
				'emailId' : emailId
			};

			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/generate/otp",
				data : data,
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(response) {
					document.getElementById("otpBtn").style.display = "none";
					document.getElementById("otp").style.display = "block";
					document.getElementById("reg").style.display = "block";
					alert("Check your mail for OTP " + response)
					otpRec = response;
				}
			});
		}
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