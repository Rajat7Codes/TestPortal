<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="author" content="Hau Nguyen">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<%-- <title><tiles:insertAttribute name="title" /></title> --%>

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

<c:if test="${ alertMsg!=null }">
	<c:if test="${ alertMsg!=\"Please Check Your Mail For OTP\" }">
		<div class="alert alert-danger">
			<p>${alertMsg}!</p>
		</div>
	</c:if>
	<c:if test="${ alertMsg==\"Please Check Your Mail For OTP\" }">
		<div class="alert alert-success">
			<p>${alertMsg}!</p>
		</div>
	</c:if>
</c:if>



<body class="auth-wrapper">
	<div class="all-wrapper menu-side with-pattern">
		<div class="auth-box-w">
			<div class="logo-w">
				<a href="index-2.html"><img width="200px" alt=""
					src="${pageContext.request.contextPath}/static/img/Iceico_logo.png"></a>
			</div>
			<h4 class="auth-header">Forgot Password</h4>

			<!-- Login Form -->

			<c:if test="${ !isOtp }">
				<form action="${pageContext.request.contextPath}/forgot/password"
					method="post">
					<div class="form-group">
						<label for="">Username</label> <input class="form-control"
							name="userName" placeholder="Enter your username">
						<div class="pre-icon os-icon os-icon-user-male-circle"></div>
					</div>
					<div class="form-group">
						<label for="">Email</label> <input class="form-control"
							name="email" placeholder="Enter your email">
						<div class="pre-icon os-icon os-icon-mail"></div>
					</div>
					<div class="form-group">
						<label for="">Mobile</label> <input class="form-control"
							name="mobileNumber" placeholder="Enter your mobile number">
						<div class="pre-icon os-icon os-icon-phone"></div>
					</div>
					<div class="buttons-w text-center">
						<button type="submit" class="btn btn-primary">Send OTP</button>
					</div>
					<div class="buttons-w mt-3 text-center">
						<label class="form-check-label"> <a
							href="${pageContext.request.contextPath}/login"> Go To Login
						</a>
						</label>
					</div>
				</form>
			</c:if>
			<c:if test="${ isOtp }">
				<form action="${pageContext.request.contextPath}/change/password"
					method="post">
					<div class="form-group">
						<label for="">OTP</label> <input type="text" name="otp" id="otp"
							class="form-control" placeholder="Enter your OTP">
						<div class="pre-icon os-icon os-icon-mail"></div>
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
					<div class="buttons-w text-center">
						<button type="submit" class="btn btn-primary">Reset
							Password</button>
					</div>
					<div class="buttons-w mt-3 text-center">
						<label class="form-check-label"> <a
							href="${pageContext.request.contextPath}/login"> Go To Login
						</a>
						</label>
					</div>

				</form>
			</c:if>





			<!-- <input type="text" id="login" class="form-group fadeIn second"
				name="ssoId" placeholder="Username"> <input type="password"
				id="password" class="form-group fadeIn third" name="password"
				placeholder="Password"> <label style="width: 100%"
				class="text-center pt-4 pb-3 text-secondary"> <input
				type="checkbox" name="remember-me" id="rememberme"> Remember
				Me
			</label> <input type="submit" class="form-group fadeIn fourth" value="Log In">
			</form> -->

			<!-- Remind Passowrd -->
			<%-- <div id="formFooter">
					<a class="underlineHover" href="${pageContext.request.contextPath }/forgot/password">Forgot Password?</a>
				</div> --%>

		</div>
	</div>
</body>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

<script type="text/javascript">
	function sendOtp() {
		var otp = $('#otp').val();

		if (otp != ${otp}) {
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
</script>

</html>
<!-- end document-->