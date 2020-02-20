<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TEST PORTAL | OTP VERIFICATION</title>

<!-- Bootstrap CSS-->
<link
	href="${pageContext.request.contextPath}/static/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="all">


<link href="${pageContext.request.contextPath}/static/css/login.css"
	rel="stylesheet" type="text/css" media="all">


<link href="${pageContext.request.contextPath}/static/css/main5739.css"
	rel="stylesheet" type="text/css">

<style>
.logo-w {
	padding: 40px 100px !important
}

.reg-form {
	padding-bottom: 30px !important
}

.auth-box-w {
	margin: auto !important
}
</style>

</head>


<body class="auth-wrapper">
	<div class="all-wrapper menu-side with-pattern">
		<div class="auth-box-w">
			<div class="logo-w">
				<a href="index-2.html"><img width="200px" alt=""
					src="${pageContext.request.contextPath}/static/img/Iceico_logo.png"></a>
			</div>
			<h4 class="auth-header">Verify Email</h4>

			<!-- Verification Form -->
			<form id="otpVerifyForm" method="POST" enctype="multipart/form-data">
				<div class="form-group">
					<label>OTP</label> <input class="form-control" id="verifyEmailOtp"
						name="verifyEmailOtp" placeholder="Enter OTP">
				</div>

				<div id="data">${data }</div>
				<input id="emailOtp" name="emailOtp" value="${emailOtp }"><input
					id="finalJson" name="finalJson">
				<div class="mt-3 text-center">
					<div class="row">
						<div class="col-sm-6">
							<button type="button" onclick="submitOtp()"
								class="btn btn-primary">Verify</button>
						</div>
						<div class="col-sm-6">
							<button type="button" onclick="resendOtp()"
								class="btn btn-primary">Resend</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

<script type="text/javascript">
	function submitOtp() {

		var data = $("#data").html();

		var object = {
			"emailOtp" : $("#emailOtp").val(),
			"data" : data,
		};
		$("#finalJson").val(JSON.stringify(object));
		alert(JSON.stringify(object));

		var form = document.getElementById("otpVerifyForm");
		form.setAttribute("action",
				"${pageContext.request.contextPath}/register/verify/otp");
		form.submit();
	}

	function resendOtp() {
		var data = $("#data").html();

		$("#finalJson").val(data);
		var form = document.getElementById("otpVerifyForm");
		form.setAttribute("action",
				"${pageContext.request.contextPath}/register/resend/otp");
		form.submit();
	}
</script>

</html>